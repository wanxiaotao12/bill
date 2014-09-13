package com.guo.bill.dao.impl;

import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.ProfitbillDao;
import com.guo.bill.pojo.Profitbill;
import com.guo.bill.pojo.ProfitbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import com.guo.common.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

 /**
 * 描述：</b>ProfitbillDaoImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
@Repository("profitbillDao")
public class ProfitbillDaoImpl extends BaseDao implements ProfitbillDao {
	private final static Logger log= LoggerFactory.getLogger(ProfitbillDaoImpl.class);
	
	@Override
	public void insert(Profitbill profitbill){
		insert("PROFITBILL.insert", profitbill);
	}
	
	@Override
	public void update(Profitbill profitbill){
		update("PROFITBILL.update", profitbill);
	}
	
	@Override
	public void deleteByPriKey(Integer id){
		Profitbill profitbill = new Profitbill();
		profitbill.setId(id);
		this.delete("PROFITBILL.deleteByPriKey", profitbill);
	}
	
	@Override
	public Profitbill findByPriKey(Integer id){
		Profitbill profitbill = new Profitbill();
		profitbill.setId(id);
		profitbill = (Profitbill)this.queryForObject("PROFITBILL.findByPriKey", profitbill);
		return profitbill;
	}
	
	@Override
	public List<Profitbill> getAllList(Query<ProfitbillQuery> query) {
		return this.queryForList("PROFITBILL.getAllList",query);
	}
	
	@Override
	public Integer getItemCount(PageQuery<ProfitbillQuery> pageQuery) {
		return (Integer) queryForObject("PROFITBILL.getItemCount",pageQuery);
	}

	@Override
	public List<Profitbill> getPageList(PageQuery<ProfitbillQuery> pageQuery, Integer itemCount) {
		PageQueryWrapper<ProfitbillQuery> wrapper = new PageQueryWrapper<ProfitbillQuery>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return this.queryForList("PROFITBILL.getPagenationList",wrapper);
	}
}
