package com.guo.bill.service;

import com.guo.bill.pojo.Shipperbill;
import com.guo.bill.pojo.ShipperbillQuery;
import com.guo.common.*;

/**
 * 描述：</b>ShipperbillService<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
public interface ShipperbillService {

	public GenericResult<String> addShipperbill(Shipperbill shipperbill);
	
    public BasicResult modifyShipperbill(Shipperbill shipperbill);
    
    public GenericResult<Shipperbill> findByPriKey(Integer id);
    
    public BasicResult deleteByPriKey(Integer id);
    
    public ListResult<Shipperbill> searchShipperbill(Query<ShipperbillQuery> query);
    
    public PageListResult<Shipperbill> searchPageShipperbill(PageQuery<ShipperbillQuery> pageQuery);
	
}
