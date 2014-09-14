package com.guo.bill.dao.impl;

import com.guo.bill.CardDetailQuery;
import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.CardDailyStatisticsDao;
import com.guo.bill.pojo.CardDailyStatistics;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/11.
 */
@Repository("cardDailyStatisticsDao")
public class CardDailyStatisticsDaoImpl extends BaseDao implements CardDailyStatisticsDao {
    @Override public void insert(CardDailyStatistics cardDailyStatistics) {
        insert("CardDailyStatistics.insert",cardDailyStatistics);
    }

    @Override public void del(String datetime) {
        update("CardDailyStatistics.update",datetime);
    }

    @Override public List<CardDailyStatistics> getPageList(PageQuery<CardDailyStatistics> pageQuery,
                                                           Integer itemCount) {
        PageQueryWrapper<CardDailyStatistics> wrapper = new PageQueryWrapper<CardDailyStatistics>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());

        return this.queryForList("CardDailyStatistics.getPagenationList",wrapper);
    }

    @Override public int getItemCount(PageQuery<CardDailyStatistics> pageQuery) {
        return (Integer)this.queryForObject("CardDailyStatistics.getItemCount",pageQuery);
    }

}
