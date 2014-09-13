package com.guo.bill.dao;

import com.guo.bill.pojo.Detailbill;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;

import java.util.List;

/**
 * 描述：</b>DetailbillDao<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
public interface DetailbillDao {
	
	public void insert(Detailbill detailbill);
	
	public void update(Detailbill detailbill);
	
	public void deleteByPriKey(Integer id);
	
	public Detailbill findByPriKey(Integer id);
	
	public List<Detailbill> getAllList(Query<DetailbillQuery> query);

	public Integer getItemCount(PageQuery<DetailbillQuery> pageQuery);

	public List<Detailbill> getPageList(PageQuery<DetailbillQuery> pageQuery, Integer itemCount);
}
