package com.guo.bill.dao;

import com.guo.bill.pojo.Mine;
import com.guo.bill.pojo.MineQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;

import java.util.List;

/**
 * 描述：</b>MineDao<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
public interface MineDao {
	
	public void insert(Mine mine);
	
	public void update(Mine mine);
	
	public void deleteByPriKey(Integer id);
	
	public Mine findByPriKey(Integer id);
	
	public List<Mine> getAllList(Query<MineQuery> query);

	public Integer getItemCount(PageQuery<MineQuery> pageQuery);

	public List<Mine> getPageList(PageQuery<MineQuery> pageQuery, Integer itemCount);
}
