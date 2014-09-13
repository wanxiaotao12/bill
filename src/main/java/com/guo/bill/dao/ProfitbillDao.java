package com.guo.bill.dao;

import com.guo.bill.pojo.Profitbill;
import com.guo.bill.pojo.ProfitbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;

import java.util.List;

/**
 * 描述：</b>ProfitbillDao<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
public interface ProfitbillDao {
	
	public void insert(Profitbill profitbill);
	
	public void update(Profitbill profitbill);
	
	public void deleteByPriKey(Integer id);
	
	public Profitbill findByPriKey(Integer id);
	
	public List<Profitbill> getAllList(Query<ProfitbillQuery> query);

	public Integer getItemCount(PageQuery<ProfitbillQuery> pageQuery);
	public List<Profitbill> getPageList(PageQuery<ProfitbillQuery> pageQuery, Integer itemCount);
}
