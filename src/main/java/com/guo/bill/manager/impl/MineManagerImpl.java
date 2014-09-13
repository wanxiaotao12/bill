package com.guo.bill.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.HashMap;

import com.guo.common.PageQuery;
import com.guo.common.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.guo.bill.manager.MineManager;
import com.guo.bill.dao.MineDao;
import com.guo.bill.pojo.Mine;
import com.guo.bill.pojo.MineQuery;



/**
 * 描述：</b>MineManagerImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
@Component("mineManager")
public class MineManagerImpl implements MineManager {
	private final static Logger log= LoggerFactory.getLogger(MineManagerImpl.class);
	@Autowired
	private MineDao mineDao;

	@Override
	public void insertMine(Mine mine) {
		mineDao.insert(mine);
	}
	
	@Override
	public void updateMine(Mine mine) {
		mineDao.update(mine);
	}
	
	@Override
	public void deleteMineByPriKey(Integer id) {
		mineDao.deleteByPriKey(id);
	}
	
	@Override
	public Mine findMineByPriKey(Integer id) {
		return mineDao.findByPriKey(id);
	}
	
	@Override
	public List<Mine> searchMineList(Query<MineQuery> query) {
		return mineDao.getAllList(query);
	}
	
	@Override
	public Integer getItemCount(PageQuery<MineQuery> pageQuery) {
		return mineDao.getItemCount(pageQuery);
	}
	
	@Override
	public List<Mine> searchPageMineList(PageQuery<MineQuery> pageQuery) {
		Integer itemCount = getItemCount(pageQuery);
		return mineDao.getPageList(pageQuery, itemCount);
	}
}
