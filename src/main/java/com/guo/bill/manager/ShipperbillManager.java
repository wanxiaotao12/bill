package com.guo.bill.manager;

import com.guo.bill.pojo.Shipperbill;
import com.guo.bill.pojo.ShipperbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;

import java.util.List;

/**
 * 描述：</b>ShipperbillManager<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
public interface ShipperbillManager {

	public void insertShipperbill(Shipperbill shipperbill);
    
    public void updateShipperbill(Shipperbill shipperbill);
    
    public void deleteShipperbillByPriKey(Integer id);
	
    public Shipperbill findShipperbillByPriKey(Integer id);
    
    public List<Shipperbill> searchShipperbillList(Query<ShipperbillQuery> query);
	
	public Integer getItemCount(PageQuery<ShipperbillQuery> pageQuery);
    
    public List<Shipperbill> searchPageShipperbillList(PageQuery<ShipperbillQuery> pageQuery);
}
