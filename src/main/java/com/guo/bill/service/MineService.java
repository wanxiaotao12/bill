package com.guo.bill.service;

import com.guo.bill.pojo.Mine;
import com.guo.bill.pojo.MineQuery;
import com.guo.common.*;

/**
 * 描述：</b>MineService<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
public interface MineService {

	public GenericResult<String> addMine(Mine mine);
	
    public BasicResult modifyMine(Mine mine);
    
    public GenericResult<Mine> findByPriKey(Integer id);
    
    public BasicResult deleteByPriKey(Integer id);
    
    public ListResult<Mine> searchMine(Query<MineQuery> query);
    
    public PageListResult<Mine> searchPageMine(PageQuery<MineQuery> pageQuery);
	
}
