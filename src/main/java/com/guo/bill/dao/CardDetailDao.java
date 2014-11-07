package com.guo.bill.dao;

import com.guo.bill.CardDetailQuery;
import com.guo.bill.pojo.CardDetail;
import com.guo.bill.pojo.Mine;
import com.guo.bill.pojo.MineQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;

import java.util.Date;
import java.util.List;

/**
 * 描述：</b>CardDetailDao<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
public interface CardDetailDao {

    /**
     * 客户付款
     * @param cardDetail
     * @param cusNo
     */
    public void cuspay(CardDetail cardDetail,String cusNo);

    /**
     * 删除
     */
    public void del(Integer cardDetailId);

	public void insert(CardDetail cardDetail);

    /**
     * 煤矿预付款
     * @param cardDetail
     * @return
     */
	public boolean addMinePrepaid(CardDetail cardDetail);

	public void update(CardDetail cardDetail);
	
	public void deleteByPriKey(Integer id);
	
	public CardDetail findById(Integer id);
	
	public List<CardDetail> getAllList(Query<CardDetailQuery> query);

	public List<CardDetail> getListByDateTime(String datetime);

	public Integer getItemCount(PageQuery<CardDetailQuery> pageQuery);

	public List<CardDetail> getPageList(PageQuery<CardDetailQuery> pageQuery, Integer itemCount);
}
