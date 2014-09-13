package com.guo.bill.dao;

import com.guo.bill.pojo.Shipperbill;
import com.guo.bill.pojo.ShipperbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;

import java.util.List;

/**
 * 描述：</b>ShipperbillDao<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
public interface ShipperbillDao {
	
	public void insert(Shipperbill shipperbill);
	
	public void update(Shipperbill shipperbill);
	
	public void deleteByPriKey(Integer id);
	
	public Shipperbill findByPriKey(Integer id);
	
	public List<Shipperbill> getAllList(Query<ShipperbillQuery> query);

	public Integer getItemCount(PageQuery<ShipperbillQuery> pageQuery);

	public List<Shipperbill> getPageList(PageQuery<ShipperbillQuery> pageQuery, Integer itemCount);
}
