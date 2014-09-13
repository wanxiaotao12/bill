package com.guo.bill.manager;

import com.guo.bill.pojo.Profitbill;
import com.guo.bill.pojo.ProfitbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;

import java.util.List;

/**
 * 描述：</b>ProfitbillManager<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
public interface ProfitbillManager {

	public void insertProfitbill(Profitbill profitbill);
    
    public void updateProfitbill(Profitbill profitbill);
    
    public void deleteProfitbillByPriKey(Integer id);
	
    public Profitbill findProfitbillByPriKey(Integer id);
    
    public List<Profitbill> searchProfitbillList(Query<ProfitbillQuery> query);
	
	public Integer getItemCount(PageQuery<ProfitbillQuery> pageQuery);
    
    public List<Profitbill> searchPageProfitbillList(PageQuery<ProfitbillQuery> pageQuery);
}
