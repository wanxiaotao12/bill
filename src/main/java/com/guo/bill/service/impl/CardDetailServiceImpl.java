package com.guo.bill.service.impl;

import com.guo.bill.CardDetailQuery;
import com.guo.bill.dao.CardDailyStatisticsDao;
import com.guo.bill.dao.CardDetailDao;
import com.guo.bill.enumtype.CardOperationEnum;
import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.po.CardStatisticsPo;
import com.guo.bill.pojo.CardDetail;
import com.guo.bill.service.CardDetailService;
import com.guo.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/7.
 */
@Service("cardDetailService")
public class CardDetailServiceImpl implements CardDetailService {
    private final static Logger log = LoggerFactory.getLogger(CardDetailServiceImpl.class);

    @Autowired
    private CardDetailDao          cardDetailDao;
    @Autowired
    private CardDailyStatisticsDao cardDailyStatisticsDao;

    @Override
    public GenericResult<String> addDetailbill(CardDetail cardDetail) {
        GenericResult<String> result = new GenericResult<String>();
        try {
            if (cardDetail != null) {
                //TODO:generate sequence id by CacheUtils.getSeq method Or remove this block.
                String id = "";//CacheUtils.getSeq(RedisKeyConstants.XXX, length);
                //mine.setId(id);
                cardDetail.setCreateTime(new Date());
                cardDetail.setState(StateEnum.NORMAL.getCode());
                cardDetailDao.insert(cardDetail);

                result.setValue(id);
                result.setMessage("保存成功");
                log.debug("------MineServiceImpl.addMine 保存成功------");
            } else {
                log.error("------MineServiceImpl.addMine - mine is null!");
                result.setCode("");
                result.setMessage("mine is null");
            }
        } catch (final Exception e) {
            log.error("------MineServiceImpl.addMine 异常", e);
            result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
            result.setMessage("未知异常");
        }
        return result;
    }

    @Override
    public GenericResult<String> addMinePrePaid(CardDetail cardDetail) {
        GenericResult<String> result = new GenericResult<String>();
        try {
            if (cardDetail != null) {
                //TODO:generate sequence id by CacheUtils.getSeq method Or remove this block.
                String id = "";//CacheUtils.getSeq(RedisKeyConstants.XXX, length);
                //mine.setId(id);
                cardDetail.setCreateTime(new Date());
                cardDetail.setState(StateEnum.NORMAL.getCode());
                cardDetailDao.insert(cardDetail);

                result.setValue(id);
                result.setMessage("保存成功");
                log.debug("------MineServiceImpl.addMine 保存成功------");
            } else {
                log.error("------MineServiceImpl.addMine - mine is null!");
                result.setCode("");
                result.setMessage("mine is null");
            }
        } catch (final Exception e) {
            log.error("------MineServiceImpl.addMine 异常", e);
            result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
            result.setMessage("未知异常");
        }
        return result;
    }

    @Override
    public BasicResult modifyDetailbill(CardDetail cardDetail) {
        return null;
    }

    @Override
    public GenericResult<CardDetail> findByPriKey(Integer id) {
        return null;
    }

    @Override
    public BasicResult deleteByPriKey(Integer id) {
        CardDetail cardDetail = cardDetailDao.findById(id);
        if(cardDetail == null) {
            throw new RuntimeException("CardDetail表：该记录存在");
        }
        if(CardOperationEnum.MINE_PREPAID.getCode().equals(cardDetail.getOperation())) {

        } else if(CardOperationEnum.CUS_PAID.getCode().equals(cardDetail.getOperation())){
            cardDetailDao.del(id);
        }
        cardDetailDao.deleteByPriKey(id);
        return null;
    }

    @Override
    public ListResult<CardDetail> searchDetailbill(Query<CardDetailQuery> query) {
        return null;
    }

    @Override public BasicResult<CardStatisticsPo> getStatistics(PageQuery<CardDetailQuery> pageQuery) {
        BasicResult<CardStatisticsPo> result = new BasicResult<CardStatisticsPo>();
        try {
            pageQuery.setPageNo(1);
            pageQuery.setPageSize(Integer.MAX_VALUE);
            List<CardDetail> list = cardDetailDao.getPageList(pageQuery, 0);
            BigDecimal income = new BigDecimal(0);
            BigDecimal expend = new BigDecimal(0);
            for (CardDetail cardDetail : list) {
                if (CardOperationEnum.INCOME.getCode().equals(cardDetail.getOperation())) {
                    income = income.add(cardDetail.getPrice());
                } else if (CardOperationEnum.EXPEND.getCode().equals(cardDetail.getOperation())) {
                    expend = expend.add(cardDetail.getPrice());
                }
            }

            BigDecimal total = income.subtract(expend);
            CardStatisticsPo cardStatisticsPo = new CardStatisticsPo();
            cardStatisticsPo.setCardDetailList(list);
            cardStatisticsPo.setIncome(income);
            cardStatisticsPo.setExpend(expend);
            cardStatisticsPo.setTotal(total);

            result.setData(cardStatisticsPo);

            result.setMessage("查询成功");
            log.debug("------DetailbillServiceImpl.searchPage 查询成功------");
        } catch (Exception ex) {
            log.error("------DetailbillServiceImpl.searchPage 异常", ex);
            result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
            result.setMessage("未知异常");
        }
        return result;
    }

    @Override public PageListResult<CardDetail> searchPageCardDetail(PageQuery<CardDetailQuery> pageQuery) {
        PageListResult<CardDetail> result = new PageListResult<CardDetail>();
        try {
            List<CardDetail> list = cardDetailDao.getPageList(pageQuery, 0);
//            BigDecimal income = new BigDecimal(0);
//            BigDecimal expend = new BigDecimal(0);
//            for (CardDetail cardDetail : list) {
//                if (OperationEnum.INCOME.getCode().equals(cardDetail.getOperation())) {
//                    income.add(cardDetail.getPrice());
//                } else if (OperationEnum.EXPEND.getCode().equals(cardDetail.getOperation())) {
//                    expend.add(cardDetail.getPrice());
//                }
//            }
            Integer itemCount = cardDetailDao.getItemCount(pageQuery);
            Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
            result.setPagenation(pagenation);
            result.setValues(list);

            result.setMessage("查询成功");
        } catch (Exception ex) {
            log.error("------DetailbillServiceImpl.searchPage 异常", ex);
            result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
            result.setMessage("未知异常");
        }
        return result;
    }


}
