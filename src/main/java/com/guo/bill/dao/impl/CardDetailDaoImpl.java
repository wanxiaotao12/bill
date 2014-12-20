package com.guo.bill.dao.impl;

import com.guo.bill.CardDetailQuery;
import com.guo.bill.dao.AccountDao;
import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.CardDetailDao;
import com.guo.bill.dao.DictionaryDao;
import com.guo.bill.enumtype.AccountTypeEnum;
import com.guo.bill.enumtype.CardOperationEnum;
import com.guo.bill.enumtype.DictionaryEnum;
import com.guo.bill.enumtype.PayTypeEnum;
import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.pojo.*;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import com.guo.common.Query;
import com.guo.web.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/7.
 */
@Repository("cardDetailDao")
public class CardDetailDaoImpl extends BaseDao implements CardDetailDao {

    @Autowired
    private AccountDao    accountDao;
    @Autowired
    private DictionaryDao dictionaryDao;

    /**
     * 客户付款
     *
     * @param cardDetail
     * @param cusNo
     */
    @Override
    @Transactional
    public void cuspay(CardDetail cardDetail, String cusNo) {

        Dictionary cus = dictionaryDao.findByTypeAndCode(DictionaryEnum.CUS_NO.getCode(), cusNo);
        if (cus == null) {
            throw new RuntimeException("字典表：客户不存在");
        }
        cardDetail.setOthername(cus.getName());
        cardDetail.setOtherno(cusNo);



        Account oldCusAccount = accountDao.getByNoAndType(cusNo, AccountTypeEnum.CUSTOMER.getCode());
        if (oldCusAccount == null) {
            throw new RuntimeException("账户表：客户账户不存在");
        }

        /**
         * 银行卡付款
         */
        if(PayTypeEnum.CARD.getCode().equals(cardDetail.getPayType())) {
            Account oldCardAccount = accountDao.getByNoAndType(cardDetail.getCardno(), AccountTypeEnum.CARD.getCode());
            if (oldCardAccount == null) {
                throw new RuntimeException("账户表：银行卡账户不存在");
            }

            BigDecimal nowCardPrice = oldCardAccount.getPrice().add(cardDetail.getPrice());
            cardDetail.setCardname(oldCardAccount.getAccountname());
            cardDetail.setOperation(CardOperationEnum.CUS_PAID.getCode());

            cardDetail.setOldCardPrice(oldCardAccount.getPrice());
            cardDetail.setNowCardPrice(nowCardPrice);

            Account cardAccount = new Account();
            cardAccount.setAccounttype(AccountTypeEnum.CARD.getCode());
            cardAccount.setAccountno(oldCardAccount.getAccountno());
            cardAccount.setPrice(nowCardPrice);

            accountDao.update(cardAccount);

        }


        //
        BigDecimal nowCusPrice = oldCusAccount.getPrice().add(cardDetail.getPrice());
        cardDetail.setOldOthernoPrice(oldCusAccount.getPrice());
        cardDetail.setNowOthernoPrice(nowCusPrice);
        cardDetail.setState(StateEnum.NORMAL.getCode());
        this.insert(cardDetail);

        //修改客户账户金额
        Account cusAccount = new Account();
        cusAccount.setAccounttype(AccountTypeEnum.CUSTOMER.getCode());
        cusAccount.setAccountno(cusNo);
        cusAccount.setPrice(nowCusPrice);

        accountDao.update(cusAccount);

    }

    @Override
    @Transactional
    public void del(Integer cardDetailId) {
        CardDetail cardDetail = this.findById(cardDetailId);
        if (cardDetail == null) {
            throw new RuntimeException("CardDetail表：该记录存在");
        } else if (!StateEnum.NORMAL.getCode().equals(cardDetail.getState())) {
            throw new RuntimeException("CardDetail表：该记录不能删除， 该状态是："+ StateEnum.getValue(cardDetail.getState()));
        }

        /**
         * 现金付款
         */
        if(PayTypeEnum.CASH.getCode().equals(cardDetail.getPayType())) {
            Account oldCusAccount = accountDao.getByNoAndType(cardDetail.getOtherno(), AccountTypeEnum.CUSTOMER.getCode());
            if (oldCusAccount == null) {
                throw new RuntimeException("账户表：" + cardDetail.getOtherno() + "," + cardDetail.getOthername() + ",账户不存在");
            }
            Account cusAccount = new Account();

            cusAccount.setAccounttype(AccountTypeEnum.CUSTOMER.getCode());
            cusAccount.setAccountno(cardDetail.getOtherno());
            cusAccount.setPrice(oldCusAccount.getPrice().subtract(cardDetail.getPrice()));

            accountDao.update(cusAccount);

            cardDetail.setOldOthernoPrice(oldCusAccount.getPrice());
            cardDetail.setNowOthernoPrice(cusAccount.getPrice());
            cardDetail.setState(StateEnum.REVERSAL.getCode());
            cardDetail.setDelId(cardDetail.getId());
            this.insert(cardDetail);

        } else {

            CardDetail newCardDetail = new CardDetail();
            newCardDetail.setId(cardDetailId);
            newCardDetail.setState(StateEnum.DELETE.getCode());
            this.update(newCardDetail);

            Account oldCardAccount = accountDao.getByNoAndType(cardDetail.getCardno(), AccountTypeEnum.CARD.getCode());
            if (oldCardAccount == null) {
                throw new RuntimeException("账户表：银行卡账户不存在");
            }
            String accounType = "";
            BigDecimal cardAccountPrice = new BigDecimal(0);
            if (CardOperationEnum.MINE_PREPAID.getCode().equals(cardDetail.getOperation())) {
                accounType = AccountTypeEnum.MINE.getCode();
                cardAccountPrice = oldCardAccount.getPrice().add(cardDetail.getPrice());
            } else if (CardOperationEnum.CUS_PAID.getCode().equals(cardDetail.getOperation())) {
                accounType = AccountTypeEnum.CUSTOMER.getCode();
                cardAccountPrice = oldCardAccount.getPrice().subtract(cardDetail.getPrice());
            }

            Account cardAccount = new Account();
            cardAccount.setAccounttype(AccountTypeEnum.CARD.getCode());
            cardAccount.setAccountno(oldCardAccount.getAccountno());
            cardAccount.setPrice(cardAccountPrice);

            accountDao.update(cardAccount);

            Account oldCusAccount = accountDao.getByNoAndType(cardDetail.getOtherno(), accounType);
            if (oldCusAccount == null) {
                throw new RuntimeException("账户表：" + cardDetail.getOtherno() + "," + cardDetail.getOthername() + ",账户不存在");
            }
            Account cusAccount = new Account();

            cusAccount.setAccounttype(accounType);
            cusAccount.setAccountno(cardDetail.getOtherno());
            cusAccount.setPrice(oldCusAccount.getPrice().subtract(cardDetail.getPrice()));

            accountDao.update(cusAccount);

            cardDetail.setOldCardPrice(oldCardAccount.getPrice());
            cardDetail.setNowCardPrice(cardAccount.getPrice());
            cardDetail.setOldOthernoPrice(oldCusAccount.getPrice());
            cardDetail.setNowOthernoPrice(cusAccount.getPrice());
            cardDetail.setState(StateEnum.REVERSAL.getCode());
            cardDetail.setDelId(cardDetail.getId());
            this.insert(cardDetail);
        }

    }

    @Override
    public void insert(CardDetail cardDetail) {
        cardDetail.setCreator(LoginContext.getCurUser().getRealName());
        insert("CardDetail.insert", cardDetail);
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(1);
        System.out.println(a.subtract(b));

    }

    @Override
    @Transactional
    public boolean addMinePrepaid(CardDetail cardDetail) {
        Account oldCardAccount = accountDao.getByNoAndType(cardDetail.getCardno(), AccountTypeEnum.CARD.getCode());
        if (oldCardAccount == null) {
            throw new RuntimeException("银行卡账户不存在");
        }

        Account cardAccount = new Account();
        cardAccount.setAccounttype(AccountTypeEnum.CARD.getCode());
        cardAccount.setAccountno(cardDetail.getCardno());
        cardAccount.setPrice(oldCardAccount.getPrice().subtract(cardDetail.getPrice()));
        accountDao.update(cardAccount);

        Account oldMineAccount = accountDao.getByNoAndType(cardDetail.getOtherno(), AccountTypeEnum.MINE.getCode());
        if (oldMineAccount == null) {
            throw new RuntimeException("煤矿账户不存在");
        }

        Account mineAccount = new Account();
        mineAccount.setAccounttype(AccountTypeEnum.MINE.getCode());
        mineAccount.setAccountno(cardDetail.getOtherno());
        mineAccount.setPrice(oldMineAccount.getPrice().add(cardDetail.getPrice()));
        accountDao.update(mineAccount);

        cardDetail.setState(StateEnum.NORMAL.getCode());
        cardDetail.setCardname(oldCardAccount.getAccountname());
        cardDetail.setOldCardPrice(oldCardAccount.getPrice());
        cardDetail.setNowCardPrice(cardAccount.getPrice());
        cardDetail.setOthername(oldMineAccount.getAccountname());
        cardDetail.setOldOthernoPrice(oldMineAccount.getPrice());
        cardDetail.setNowOthernoPrice(mineAccount.getPrice());
        cardDetail.setOperation(CardOperationEnum.MINE_PREPAID.getCode());
        cardDetail.setState(StateEnum.NORMAL.getCode());
        cardDetail.setPayType(PayTypeEnum.CARD.getCode());
        this.insert(cardDetail);

        return true;

    }

    @Override
    public void update(CardDetail cardDetail) {
        int num = update("CardDetail.update", cardDetail);
        if (num != 1) {
            throw new RuntimeException("更新表：CardDetail失败，id=" + cardDetail.getId());
        }
    }

    @Override
    public void deleteByPriKey(Integer id) {
        update("CardDetail.del", id);
    }

    @Override
    public CardDetail findById(Integer id) {
        return (CardDetail) queryForObject("CardDetail.findById", id);
    }

    @Override
    public List<CardDetail> getAllList(Query<CardDetailQuery> query) {
        return null;
    }

    @Override public List<CardDetail> getListByDateTime(String datetime) {
        return this.queryForList("CardDetail.getListByDateTime", datetime);
    }

    @Override public Integer getItemCount(PageQuery<CardDetailQuery> pageQuery) {
        return (Integer) this.queryForObject("CardDetail.getItemCount", pageQuery);
    }

    @Override public List<CardDetail> getPageList(PageQuery<CardDetailQuery> pageQuery, Integer itemCount) {
        PageQueryWrapper<CardDetailQuery> wrapper = new PageQueryWrapper<CardDetailQuery>(pageQuery.getPageNo(),
                pageQuery.getPageSize(), itemCount, pageQuery.getQuery());
        return this.queryForList("CardDetail.getPagenationList", wrapper);
    }
}
