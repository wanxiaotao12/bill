package com.guo.bill.dao;

import com.guo.bill.pojo.SaleDetail;
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
	
	public void insert(SaleDetail shipperbill);
	
	public void update(SaleDetail shipperbill);
	
	public void deleteByPriKey(Integer id);
	
	public SaleDetail findByPriKey(Integer id);
	
	public List<SaleDetail> getAllList(Query<ShipperbillQuery> query);

	public Integer getItemCount(PageQuery<ShipperbillQuery> pageQuery);

	public List<SaleDetail> getPageList(PageQuery<ShipperbillQuery> pageQuery, Integer itemCount);
}
