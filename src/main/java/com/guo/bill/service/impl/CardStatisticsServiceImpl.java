package com.guo.bill.service.impl;

import com.guo.bill.dao.AccountDao;
import com.guo.bill.dao.CardDailyStatisticsDao;
import com.guo.bill.dao.CardDetailDao;
import com.guo.bill.enumtype.AccountTypeEnum;
import com.guo.bill.enumtype.OperationEnum;
import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.pojo.Account;
import com.guo.bill.pojo.CardDailyStatistics;
import com.guo.bill.pojo.CardDetail;
import com.guo.bill.service.CardStatisticsService;
import com.guo.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
@Service("cardStatisticsService")
public class CardStatisticsServiceImpl implements CardStatisticsService {
    private final static Logger log = LoggerFactory.getLogger(CardStatisticsServiceImpl.class);

    @Autowired
    private CardDailyStatisticsDao cardDailyStatisticsDao;

    @Autowired
    private CardDetailDao cardDetailDao;

    @Autowired
    private AccountDao accountDao;

    @Override public PageListResult<CardDailyStatistics> searchPageCardStatistics(
            PageQuery<CardDailyStatistics> pageQuery) {
        PageListResult<CardDailyStatistics> result = new PageListResult<CardDailyStatistics>();
        try {
            List<CardDailyStatistics> list = cardDailyStatisticsDao.getPageList(pageQuery, 0);
            Integer itemCount = cardDailyStatisticsDao.getItemCount(pageQuery);
            Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
            result.setPagenation(pagenation);
            result.setValues(list);

            result.setMessage("查询成功");
            log.debug("------DetailbillServiceImpl.searchPageCardDetail 查询成功------");
        } catch (Exception ex) {
            log.error("------DetailbillServiceImpl.searchPageCardDetail 异常", ex);
            result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
            result.setMessage("未知异常");
        }
        return result;
    }

    @Override
    public BasicResult createDailyStatistics(String datetime) {

        BasicResult result = new BasicResult();
        try {
            List<CardDetail> list = cardDetailDao.getListByDateTime(datetime);
            Map<String, CardDailyStatistics> map = new HashMap<String, CardDailyStatistics>();
            for (CardDetail cardDetail : list) {
                if (map.get(cardDetail.getCardno()) == null) {
                    CardDailyStatistics cardDailyStatistics = new CardDailyStatistics();
                    SimpleDateFormat sf =new java.text.SimpleDateFormat("yyyy-MM-dd");

                    cardDailyStatistics.setDatetime(sf.parse(datetime));
                    cardDailyStatistics.setCardno(cardDetail.getCardno());
                    cardDailyStatistics.setIncome(new BigDecimal(0));
                    cardDailyStatistics.setOutlay(new BigDecimal(0));
                    cardDailyStatistics.setSurplus(new BigDecimal(0));
                    cardDailyStatistics.setState(StateEnum.NORMAL.getCode());
                    map.put(cardDetail.getCardno(), cardDailyStatistics);
                }
                CardDailyStatistics cardDailyStatistics = map.get(cardDetail.getCardno());
                if (OperationEnum.INCOME.getCode().equals(cardDetail.getOperation())) {
                    cardDailyStatistics.setIncome(cardDailyStatistics.getIncome().add(cardDetail.getPrice()));
                } else if (OperationEnum.EXPEND.getCode().equals(cardDetail.getOperation())) {
                    cardDailyStatistics.setOutlay(cardDailyStatistics.getOutlay().add(cardDetail.getPrice()));
                }

            }

            cardDailyStatisticsDao.del(datetime);

            List<Account> accountsList = accountDao.findByType(AccountTypeEnum.CARD.getCode());
            Map<String, Account> accountMap = new HashMap<String, Account>();
            for(Account account :accountsList) {
                accountMap.put(account.getAccountname(),account);
            }
            for (String key : map.keySet()) {
                CardDailyStatistics cardDailyStatistics = map.get(key);
                cardDailyStatistics.setSurplus(cardDailyStatistics.getIncome().subtract(cardDailyStatistics.getOutlay()));

                Account account = accountMap.get(cardDailyStatistics.getCardno());
                if(account!=null) {
                    BigDecimal price = account.getPrice().add(cardDailyStatistics.getSurplus());
                    cardDailyStatistics.setCurSurplus(price);
                    account.setPrice(price);
                    accountDao.update(account);
                }
                cardDailyStatisticsDao.insert(map.get(key));
            }

            result.setMessage("查询成功");
            log.debug("------DetailbillServiceImpl.searchPageCardDetail 查询成功------");
        } catch (Exception ex) {
            log.error("------DetailbillServiceImpl.searchPageCardDetail 异常", ex);
            result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
            result.setMessage("未知异常");
        }
        return result;
    }
}
