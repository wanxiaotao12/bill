package com.guo.bill.dao.impl;

import com.guo.bill.CardDetailQuery;
import com.guo.bill.dao.AccountDao;
import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.CardDetailDao;
import com.guo.bill.enumtype.AccountTypeEnum;
import com.guo.bill.enumtype.OperationEnum;
import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.pojo.Account;
import com.guo.bill.pojo.CardDetail;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.bill.pojo.Mine;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import com.guo.common.Query;
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
    private AccountDao accountDao;

    @Override
    public void insert(CardDetail cardDetail) {
        cardDetail.setState(StateEnum.NORMAL.getCode());
        insert("CardDetail.insert", cardDetail);
    }

    public static void main(String[] args) {
        BigDecimal a  = new BigDecimal(10);
        BigDecimal b  = new BigDecimal(1);
        System.out.println(a.subtract(b));

    }
    @Override
    @Transactional
    public boolean addMinePrepaid(CardDetail cardDetail) {


        Account oldCardAccount = accountDao.getByNoAndType(cardDetail.getCardno(),AccountTypeEnum.CARD.getCode());
        if(oldCardAccount == null) {
            throw new RuntimeException("银行卡账户不存在");
        }

        Account cardAccount = new Account();
        cardAccount.setAccounttype(AccountTypeEnum.CARD.getCode());
        cardAccount.setAccountno(cardDetail.getCardno());
        cardAccount.setPrice(oldCardAccount.getPrice().subtract(cardDetail.getPrice()));
        boolean updateFlag = accountDao.update(cardAccount);

        if(!updateFlag) {
            throw new RuntimeException("更新银行卡金额错误");
        }

        Account oldMineAccount = accountDao.getByNoAndType(cardDetail.getOtherno(),AccountTypeEnum.MINE.getCode());
        if(oldMineAccount == null) {
            throw new RuntimeException("煤矿账户不存在");
        }

        Account mineAccount = new Account();
        mineAccount.setAccounttype(AccountTypeEnum.MINE.getCode());
        mineAccount.setAccountno(cardDetail.getOtherno());
        mineAccount.setPrice(oldMineAccount.getPrice().add(cardDetail.getPrice()));
        updateFlag =  accountDao.update(mineAccount);

        cardDetail.setState(StateEnum.NORMAL.getCode());
        cardDetail.setCardname(oldCardAccount.getAccountname());
        cardDetail.setOthername(oldMineAccount.getAccountname());
        cardDetail.setOperation(OperationEnum.MINE_PREPAID.getCode());
        this.insert(cardDetail);

        if(updateFlag) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(CardDetail mine) {

    }

    @Override
    public void deleteByPriKey(Integer id) {
        update("CardDetail.del",id);
    }

    @Override
    public Mine findByPriKey(Integer id) {
        return null;
    }

    @Override
    public List<CardDetail> getAllList(Query<CardDetailQuery> query) {
        return null;
    }

    @Override public List<CardDetail> getListByDateTime(String datetime) {
        return this.queryForList("CardDetail.getListByDateTime",datetime);
    }

    @Override public Integer getItemCount(PageQuery<CardDetailQuery> pageQuery) {
        return (Integer)this.queryForObject("CardDetail.getItemCount",pageQuery);
    }

    @Override public List<CardDetail> getPageList(PageQuery<CardDetailQuery> pageQuery, Integer itemCount) {
        PageQueryWrapper<CardDetailQuery> wrapper = new PageQueryWrapper<CardDetailQuery>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
        return this.queryForList("CardDetail.getPagenationList",wrapper);
    }
}
