package com.guo.bill.dao.impl;

import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.DetailbillDao;
import com.guo.bill.pojo.Detailbill;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import com.guo.common.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


 /**
 * 描述：</b>DetailbillDaoImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
@Repository("detailbillDao")
public class DetailbillDaoImpl extends BaseDao implements DetailbillDao {
	private final static Logger log= LoggerFactory.getLogger(DetailbillDaoImpl.class);
	
	@Override
	public void insert(Detailbill detailbill){
		insert("DETAILBILL.insert", detailbill);
	}
	
	@Override
	public void update(Detailbill detailbill){
		update("DETAILBILL.update", detailbill);
	}
	
	@Override
	public void deleteByPriKey(Integer id){
		Detailbill detailbill = new Detailbill();
		detailbill.setId(id);
		this.delete("DETAILBILL.deleteByPriKey", detailbill);
	}
	
	@Override
	public Detailbill findByPriKey(Integer id){
		Detailbill detailbill = new Detailbill();
		detailbill.setId(id);
		detailbill = (Detailbill)this.queryForObject("DETAILBILL.findByPriKey", detailbill);
		return detailbill;
	}
	
	@Override
	public List<Detailbill> getAllList(Query<DetailbillQuery> query) {
		return this.queryForList("DETAILBILL.getAllList",query);
	}

	@Override
	public Integer getItemCount(PageQuery<DetailbillQuery> pageQuery) {
		return (Integer) queryForObject("DETAILBILL.getItemCount",pageQuery);
	}

	@Override
	public List<Detailbill> getPageList(PageQuery<DetailbillQuery> pageQuery, Integer itemCount) {
		PageQueryWrapper<DetailbillQuery> wrapper = new PageQueryWrapper<DetailbillQuery>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return this.queryForList("DETAILBILL.getPagenationList",wrapper);
	}
}
