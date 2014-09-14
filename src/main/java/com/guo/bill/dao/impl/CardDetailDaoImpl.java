package com.guo.bill.dao.impl;

import com.guo.bill.CardDetailQuery;
import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.CardDetailDao;
import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.pojo.CardDetail;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.bill.pojo.Mine;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import com.guo.common.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/7.
 */
@Repository("cardDetailDao")
public class CardDetailDaoImpl extends BaseDao implements CardDetailDao {
    @Override
    public void insert(CardDetail cardDetail) {
        cardDetail.setState(StateEnum.NORMAL.getCode());
        insert("CardDetail.insert", cardDetail);
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
