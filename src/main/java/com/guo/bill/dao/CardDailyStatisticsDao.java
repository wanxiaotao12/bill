package com.guo.bill.dao;

import com.guo.bill.CardDetailQuery;
import com.guo.bill.pojo.CardDailyStatistics;
import com.guo.common.PageQuery;

import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/11.
 */
public interface CardDailyStatisticsDao {
    public void insert(CardDailyStatistics cardDailyStatistics);

    public void del(String datetime);

    public List<CardDailyStatistics> getPageList(PageQuery<CardDailyStatistics> pageQuery, Integer itemCount);

    public int getItemCount(PageQuery<CardDailyStatistics> pageQuery);
}
