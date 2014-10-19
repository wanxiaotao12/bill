package com.guo.bill.dao.impl;

import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.ShipperbillDao;
import com.guo.bill.pojo.SaleDetail;
import com.guo.bill.pojo.ShipperbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import com.guo.common.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述：</b>ShipperbillDaoImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
@Repository("shipperbillDao")
public class ShipperbillDaoImpl extends BaseDao implements ShipperbillDao {
	private final static Logger log= LoggerFactory.getLogger(ShipperbillDaoImpl.class);
	
	@Override
	public void insert(SaleDetail shipperbill){
		insert("SaleDetail.insert", shipperbill);
	}
	
	@Override
	public void update(SaleDetail shipperbill){
		update("SaleDetail.update", shipperbill);
	}
	
	@Override
	public void deleteByPriKey(Integer id){
		SaleDetail shipperbill = new SaleDetail();
		shipperbill.setId(id);
		this.delete("SaleDetail.deleteByPriKey", shipperbill);
	}
	
	@Override
	public SaleDetail findByPriKey(Integer id){
		SaleDetail shipperbill = new SaleDetail();
		shipperbill.setId(id);
		shipperbill = (SaleDetail)this.queryForObject("SaleDetail.findByPriKey", shipperbill);
		return shipperbill;
	}
	
	@Override
	public List<SaleDetail> getAllList(Query<ShipperbillQuery> query) {
		return this.queryForList("SaleDetail.getAllList",query);
	}

	@Override
	public Integer getItemCount(PageQuery<ShipperbillQuery> pageQuery) {
		return (Integer) queryForObject("SaleDetail.getItemCount",pageQuery);
	}

	@Override
	public List<SaleDetail> getPageList(PageQuery<ShipperbillQuery> pageQuery, Integer itemCount) {
		PageQueryWrapper<ShipperbillQuery> wrapper = new PageQueryWrapper<ShipperbillQuery>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return this.queryForList("SaleDetail.getPagenationList",wrapper);
	}
}
