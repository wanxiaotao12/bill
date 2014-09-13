package com.guo.bill.manager.impl;

import com.guo.bill.dao.DetailbillDao;
import com.guo.bill.manager.DetailbillManager;
import com.guo.bill.pojo.Detailbill;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 描述：</b>DetailbillManagerImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
@Component("detailbillManager")
public class DetailbillManagerImpl implements DetailbillManager {
	private final static Logger log= LoggerFactory.getLogger(DetailbillManagerImpl.class);
	@Autowired
	private DetailbillDao detailbillDao;

	@Override
	public void insertDetailbill(Detailbill detailbill) {
		detailbillDao.insert(detailbill);
	}
	
	@Override
	public void updateDetailbill(Detailbill detailbill) {
		detailbillDao.update(detailbill);
	}
	
	@Override
	public void deleteDetailbillByPriKey(Integer id) {
		detailbillDao.deleteByPriKey(id);
	}
	
	@Override
	public Detailbill findDetailbillByPriKey(Integer id) {
		return detailbillDao.findByPriKey(id);
	}
	
	@Override
	public List<Detailbill> searchDetailbillList(Query<DetailbillQuery> query) {
		return detailbillDao.getAllList(query);
	}
	
	@Override
	public Integer getItemCount(PageQuery<DetailbillQuery> pageQuery) {
		return detailbillDao.getItemCount(pageQuery);
	}
	
	@Override
	public List<Detailbill> searchPageDetailbillList(PageQuery<DetailbillQuery> pageQuery) {
		Integer itemCount = getItemCount(pageQuery);
		return detailbillDao.getPageList(pageQuery, itemCount);
	}
}
