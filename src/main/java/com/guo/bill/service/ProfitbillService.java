package com.guo.bill.service;

import com.guo.bill.pojo.Profitbill;
import com.guo.bill.pojo.ProfitbillQuery;
import com.guo.common.*;

/**
 * 描述：</b>ProfitbillService<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
public interface ProfitbillService {

	public GenericResult<String> addProfitbill(Profitbill profitbill);
	
    public BasicResult modifyProfitbill(Profitbill profitbill);
    
    public GenericResult<Profitbill> findByPriKey(Integer id);
    
    public BasicResult deleteByPriKey(Integer id);
    
    public ListResult<Profitbill> searchProfitbill(Query<ProfitbillQuery> query);
    
    public PageListResult<Profitbill> searchPageProfitbill(PageQuery<ProfitbillQuery> pageQuery);
	
}
