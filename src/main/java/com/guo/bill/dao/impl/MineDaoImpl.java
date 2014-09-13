package com.guo.bill.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.HashMap;

import com.guo.bill.dao.BaseDao;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import com.guo.common.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.guo.bill.dao.MineDao;
import com.guo.bill.pojo.Mine;
import com.guo.bill.pojo.MineQuery;

 /**
 * 描述：</b>MineDaoImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
@Repository("mineDao")
public class MineDaoImpl extends BaseDao implements MineDao {
	private final static Logger log= LoggerFactory.getLogger(MineDaoImpl.class);
	
	@Override
	public void insert(Mine mine){
		insert("MINE.insert", mine);
	}
	
	@Override
	public void update(Mine mine){
		update("MINE.update", mine);
	}
	
	@Override
	public void deleteByPriKey(Integer id){
		Mine mine = new Mine();
		mine.setId(id);
		this.delete("MINE.deleteByPriKey", mine);
	}
	
	@Override
	public Mine findByPriKey(Integer id){
		Mine mine = new Mine();
		mine.setId(id);
		mine = (Mine)this.queryForObject("MINE.findByPriKey", mine);
		return mine;
	}
	
	@Override
	public List<Mine> getAllList(Query<MineQuery> query) {
		return this.queryForList("MINE.getAllList",query);
	}

	@Override
	public Integer getItemCount(PageQuery<MineQuery> pageQuery) {
		return (Integer) queryForObject("MINE.getItemCount",pageQuery);
	}

	@Override
	public List<Mine> getPageList(PageQuery<MineQuery> pageQuery, Integer itemCount) {
		PageQueryWrapper<MineQuery> wrapper = new PageQueryWrapper<MineQuery>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return this.queryForList("MINE.getPagenationList",wrapper);
	}
}
