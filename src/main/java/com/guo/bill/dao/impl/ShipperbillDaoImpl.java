package com.guo.bill.dao.impl;

import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.ShipperbillDao;
import com.guo.bill.pojo.Shipperbill;
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
	public void insert(Shipperbill shipperbill){
		insert("SHIPPERBILL.insert", shipperbill);
	}
	
	@Override
	public void update(Shipperbill shipperbill){
		update("SHIPPERBILL.update", shipperbill);
	}
	
	@Override
	public void deleteByPriKey(Integer id){
		Shipperbill shipperbill = new Shipperbill();
		shipperbill.setId(id);
		this.delete("SHIPPERBILL.deleteByPriKey", shipperbill);
	}
	
	@Override
	public Shipperbill findByPriKey(Integer id){
		Shipperbill shipperbill = new Shipperbill();
		shipperbill.setId(id);
		shipperbill = (Shipperbill)this.queryForObject("SHIPPERBILL.findByPriKey", shipperbill);
		return shipperbill;
	}
	
	@Override
	public List<Shipperbill> getAllList(Query<ShipperbillQuery> query) {
		return this.queryForList("SHIPPERBILL.getAllList",query);
	}

	@Override
	public Integer getItemCount(PageQuery<ShipperbillQuery> pageQuery) {
		return (Integer) queryForObject("SHIPPERBILL.getItemCount",pageQuery);
	}

	@Override
	public List<Shipperbill> getPageList(PageQuery<ShipperbillQuery> pageQuery, Integer itemCount) {
		PageQueryWrapper<ShipperbillQuery> wrapper = new PageQueryWrapper<ShipperbillQuery>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return this.queryForList("SHIPPERBILL.getPagenationList",wrapper);
	}
}
