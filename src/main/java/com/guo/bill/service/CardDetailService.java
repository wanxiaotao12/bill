package com.guo.bill.service;

import com.guo.bill.CardDetailQuery;
import com.guo.bill.po.CardStatisticsPo;
import com.guo.bill.pojo.CardDetail;
import com.guo.bill.pojo.Detailbill;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.common.*;

import java.util.Date;

/**
 * Created by xiaotao.wxt on 2014/9/7.
 */
public interface CardDetailService {
    public GenericResult<String> addDetailbill(CardDetail cardDetail);

    public BasicResult modifyDetailbill(CardDetail cardDetail);

    public GenericResult<CardDetail> findByPriKey(Integer id);

    public BasicResult deleteByPriKey(Integer id);

    public ListResult<CardDetail> searchDetailbill(Query<CardDetailQuery> query);

    public BasicResult<CardStatisticsPo> getStatistics(PageQuery<CardDetailQuery> pageQuery);

    public PageListResult<CardDetail> searchPageCardDetail(PageQuery<CardDetailQuery> pageQuery);


    public BasicResult createDailyStatistics(String datetime);

}
