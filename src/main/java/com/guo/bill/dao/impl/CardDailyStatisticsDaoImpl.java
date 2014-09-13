package com.guo.bill.dao.impl;

import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.CardDailyStatisticsDao;
import com.guo.bill.pojo.CardDailyStatistics;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaotao.wxt on 2014/9/11.
 */
@Repository("cardDailyStatisticsDao")
public class CardDailyStatisticsDaoImpl extends BaseDao implements CardDailyStatisticsDao {
    @Override public void insert(CardDailyStatistics cardDailyStatistics) {
        insert("CardDailyStatistics.insert",cardDailyStatistics);
    }
}
