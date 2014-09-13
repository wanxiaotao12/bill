package com.guo.bill.manager.impl;

import com.guo.bill.dao.ProfitbillDao;
import com.guo.bill.manager.ProfitbillManager;
import com.guo.bill.pojo.Profitbill;
import com.guo.bill.pojo.ProfitbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 描述：</b>ProfitbillManagerImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
@Component("profitbillManager")
public class ProfitbillManagerImpl implements ProfitbillManager {
	private final static Logger log= LoggerFactory.getLogger(ProfitbillManagerImpl.class);
	@Autowired
	private ProfitbillDao profitbillDao;

	@Override
	public void insertProfitbill(Profitbill profitbill) {
		profitbillDao.insert(profitbill);
	}
	
	@Override
	public void updateProfitbill(Profitbill profitbill) {
		profitbillDao.update(profitbill);
	}
	
	@Override
	public void deleteProfitbillByPriKey(Integer id) {
		profitbillDao.deleteByPriKey(id);
	}
	
	@Override
	public Profitbill findProfitbillByPriKey(Integer id) {
		return profitbillDao.findByPriKey(id);
	}
	
	@Override
	public List<Profitbill> searchProfitbillList(Query<ProfitbillQuery> query) {
		return profitbillDao.getAllList(query);
	}
	
	@Override
	public Integer getItemCount(PageQuery<ProfitbillQuery> pageQuery) {
		return profitbillDao.getItemCount(pageQuery);
	}
	
	@Override
	public List<Profitbill> searchPageProfitbillList(PageQuery<ProfitbillQuery> pageQuery) {
		Integer itemCount = getItemCount(pageQuery);
		return profitbillDao.getPageList(pageQuery, itemCount);
	}
}
