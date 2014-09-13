package com.guo.bill.service;

import com.guo.bill.pojo.Detailbill;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.common.*;

/**
 * 描述：</b>DetailbillService<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
public interface DetailbillService {

	public GenericResult<String> addDetailbill(Detailbill detailbill);
	
    public BasicResult modifyDetailbill(Detailbill detailbill);
    
    public GenericResult<Detailbill> findByPriKey(Integer id);
    
    public BasicResult deleteByPriKey(Integer id);
    
    public ListResult<Detailbill> searchDetailbill(Query<DetailbillQuery> query);
    
    public PageListResult<Detailbill> searchPageDetailbill(PageQuery<DetailbillQuery> pageQuery);
	
}
