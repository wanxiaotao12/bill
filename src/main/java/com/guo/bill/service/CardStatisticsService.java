package com.guo.bill.service;

import com.guo.bill.pojo.CardDailyStatistics;
import com.guo.common.BasicResult;
import com.guo.common.PageListResult;
import com.guo.common.PageQuery;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
public interface CardStatisticsService {
    public PageListResult<CardDailyStatistics> searchPageCardStatistics(PageQuery<CardDailyStatistics> pageQuery);

    public BasicResult createDailyStatistics(String datetime);

}
