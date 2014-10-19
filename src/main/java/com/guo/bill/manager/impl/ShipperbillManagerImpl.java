package com.guo.bill.manager.impl;

import java.util.List;

import com.guo.bill.pojo.SaleDetail;
import com.guo.common.PageQuery;
import com.guo.common.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.guo.bill.manager.ShipperbillManager;
import com.guo.bill.dao.ShipperbillDao;
import com.guo.bill.pojo.ShipperbillQuery;



/**
 * 描述：</b>ShipperbillManagerImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
@Component("shipperbillManager")
public class ShipperbillManagerImpl implements ShipperbillManager {
	private final static Logger log= LoggerFactory.getLogger(ShipperbillManagerImpl.class);
	@Autowired
	private ShipperbillDao shipperbillDao;

	@Override
	public void insertShipperbill(SaleDetail shipperbill) {
		shipperbillDao.insert(shipperbill);
	}
	
	@Override
	public void updateShipperbill(SaleDetail shipperbill) {
		shipperbillDao.update(shipperbill);
	}
	
	@Override
	public void deleteShipperbillByPriKey(Integer id) {
		shipperbillDao.deleteByPriKey(id);
	}
	
	@Override
	public SaleDetail findShipperbillByPriKey(Integer id) {
		return shipperbillDao.findByPriKey(id);
	}
	
	@Override
	public List<SaleDetail> searchShipperbillList(Query<ShipperbillQuery> query) {
		return shipperbillDao.getAllList(query);
	}
	
	@Override
	public Integer getItemCount(PageQuery<ShipperbillQuery> pageQuery) {
		return shipperbillDao.getItemCount(pageQuery);
	}
	
	@Override
	public List<SaleDetail> searchPageShipperbillList(PageQuery<ShipperbillQuery> pageQuery) {
		Integer itemCount = getItemCount(pageQuery);
		return shipperbillDao.getPageList(pageQuery, itemCount);
	}
}
