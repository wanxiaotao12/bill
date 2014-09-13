package com.guo.bill.manager;

import com.guo.bill.pojo.Mine;
import com.guo.bill.pojo.MineQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;

import java.util.List;

/**
 * 描述：</b>MineManager<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
public interface MineManager {

	public void insertMine(Mine mine);
    
    public void updateMine(Mine mine);
    
    public void deleteMineByPriKey(Integer id);
	
    public Mine findMineByPriKey(Integer id);
    
    public List<Mine> searchMineList(Query<MineQuery> query);
	
	public Integer getItemCount(PageQuery<MineQuery> pageQuery);
    
    public List<Mine> searchPageMineList(PageQuery<MineQuery> pageQuery);
}
