package com.guo.bill.service.impl;

import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.manager.ProfitbillManager;
import com.guo.bill.pojo.Profitbill;
import com.guo.bill.pojo.ProfitbillQuery;
import com.guo.bill.service.ProfitbillService;
import com.guo.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 描述：</b>ProfitbillServiceImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
@Service("profitbillService")
public class ProfitbillServiceImpl implements ProfitbillService {
	private final static Logger log= LoggerFactory.getLogger(ProfitbillServiceImpl.class);
	@Autowired
	private ProfitbillManager profitbillManager;
	
	@Override
	public GenericResult<String> addProfitbill(Profitbill profitbill) {
		GenericResult<String> result = new GenericResult<String>();
		 try {
			if (profitbill != null) {
				//TODO:generate sequence id by CacheUtils.getSeq method Or remove this block.
				String id="";//CacheUtils.getSeq(RedisKeyConstants.XXX, length);
				//profitbill.setId(id);
                profitbill.setCreateTime(new Date());
                profitbill.setState(StateEnum.NORMAL.getCode());
				profitbillManager.insertProfitbill(profitbill);
				
				result.setValue(id);
				result.setMessage("保存成功");
				log.debug("------ProfitbillServiceImpl.addProfitbill 保存成功------");
			} else {
				log.error("------ProfitbillServiceImpl.addProfitbill - profitbill is null!");
				result.setCode("");
				result.setMessage("profitbill is null");
			}
		 } catch (final Exception e) {
			 log.error("------ProfitbillServiceImpl.addProfitbill 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public BasicResult modifyProfitbill(Profitbill profitbill) {
		BasicResult result = new BasicResult();
		try {
			if (profitbill != null) {
				profitbillManager.updateProfitbill(profitbill);
				result.setMessage("编辑成功");
				log.debug("------ProfitbillServiceImpl.modifyProfitbill 编辑成功------");
			} else {
				log.error("------ProfitbillServiceImpl.modifyProfitbill - profitbill is null!");
				result.setCode("");
				result.setMessage("profitbill is null");
			}
		} catch (Exception e) {
			log.error("------ProfitbillServiceImpl.modifyProfitbill 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public GenericResult<Profitbill> findByPriKey(Integer id) {
		GenericResult<Profitbill> result = new GenericResult<Profitbill>();
		try{
			result.setValue(profitbillManager.findProfitbillByPriKey(id));
			result.setMessage("查询成功");
			log.debug("------ProfitbillServiceImpl.findByPriKey 查询成功------");
		}catch (Exception e){
			log.error("------ProfitbillServiceImpl.findByPriKey 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public BasicResult deleteByPriKey(Integer id) {
		BasicResult result = new BasicResult();
		try{
            Profitbill profitbill = profitbillManager.findProfitbillByPriKey(id);
            if(profitbill !=null){
                Profitbill newProfitbill = new Profitbill();
                newProfitbill.setId(id);
                newProfitbill.setState(StateEnum.DELETE.getCode());
                profitbillManager.updateProfitbill(newProfitbill);
            }
			result.setMessage("删除成功");
			log.debug("------ProfitbillServiceImpl.deleteByPriKey 删除成功------");
		}catch (Exception e){
			log.error("------ProfitbillServiceImpl.deleteByPriKey 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public ListResult<Profitbill> searchProfitbill(Query<ProfitbillQuery> query) {
		ListResult<Profitbill> result = new ListResult<Profitbill>();
		try {
			result.setValues(profitbillManager.searchProfitbillList(query));
			result.setMessage("查询成功");
			log.debug("------ProfitbillServiceImpl.searchProfitbill 查询成功------");
		} catch (Exception ex) {
			log.error("------ProfitbillServiceImpl.searchProfitbill 异常", ex);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public PageListResult<Profitbill> searchPageProfitbill(
			PageQuery<ProfitbillQuery> pageQuery) {
		PageListResult<Profitbill> result = new PageListResult<Profitbill>();
		try {
			List<Profitbill> list = profitbillManager.searchPageProfitbillList(pageQuery);
			Integer itemCount = profitbillManager.getItemCount(pageQuery);
			Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
			result.setPagenation(pagenation);
			result.setValues(list);
			
			result.setMessage("查询成功");
			log.debug("------ProfitbillServiceImpl.searchPageProfitbill 查询成功------");
		} catch (Exception ex) {
			log.error("------ProfitbillServiceImpl.searchPageProfitbill 异常", ex);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
}
