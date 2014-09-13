package com.guo.bill.manager;

import com.guo.bill.pojo.Detailbill;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.common.PageQuery;
import com.guo.common.Query;

import java.util.List;

/**
 * 描述：</b>DetailbillManager<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
public interface DetailbillManager {

	public void insertDetailbill(Detailbill detailbill);
    
    public void updateDetailbill(Detailbill detailbill);
    
    public void deleteDetailbillByPriKey(Integer id);
	
    public Detailbill findDetailbillByPriKey(Integer id);
    
    public List<Detailbill> searchDetailbillList(Query<DetailbillQuery> query);

	public Integer getItemCount(PageQuery<DetailbillQuery> pageQuery);

    public List<Detailbill> searchPageDetailbillList(PageQuery<DetailbillQuery> pageQuery);
}
