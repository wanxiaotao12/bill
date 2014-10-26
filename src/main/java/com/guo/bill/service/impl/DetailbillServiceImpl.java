package com.guo.bill.service.impl;

import java.util.List;

import com.guo.common.BasicResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guo.bill.service.DetailbillService;
import com.guo.bill.manager.DetailbillManager;
import com.guo.bill.pojo.Detailbill;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.common.*;


/**
 * 描述：</b>DetailbillServiceImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
@Service("detailbillService")
public class DetailbillServiceImpl implements DetailbillService {
	private final static Logger log= LoggerFactory.getLogger(DetailbillServiceImpl.class);
	@Autowired
	private DetailbillManager detailbillManager;
	
	@Override
	public GenericResult<String> addDetailbill(Detailbill detailbill) {
		GenericResult<String> result = new GenericResult<String>();
		 try {
			if (detailbill != null) {
				//TODO:generate sequence id by CacheUtils.getSeq method Or remove this block.
				String id="";//CacheUtils.getSeq(RedisKeyConstants.XXX, length);
				//detailbill.setId(id);
				detailbillManager.insertDetailbill(detailbill);
				
				result.setValue(id);
				result.setMessage("保存成功");
				log.debug("------DetailbillServiceImpl.addDetailbill 保存成功------");
			} else {
				log.error("------DetailbillServiceImpl.addDetailbill - detailbill is null!");
				result.setCode("");
				result.setMessage("detailbill is null");
			}
		 } catch (final Exception e) {
			 log.error("------DetailbillServiceImpl.addDetailbill 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public BasicResult modifyDetailbill(Detailbill detailbill) {
		BasicResult result = new BasicResult();
		try {
			if (detailbill != null) {
				detailbillManager.updateDetailbill(detailbill);
				result.setMessage("编辑成功");
				log.debug("------DetailbillServiceImpl.modifyDetailbill 编辑成功------");
			} else {
				log.error("------DetailbillServiceImpl.modifyDetailbill - detailbill is null!");
				result.setCode("");
				result.setMessage("detailbill is null");
			}
		} catch (Exception e) {
			log.error("------DetailbillServiceImpl.modifyDetailbill 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public GenericResult<Detailbill> findByPriKey(Integer id) {
		GenericResult<Detailbill> result = new GenericResult<Detailbill>();
		try{
			result.setValue(detailbillManager.findDetailbillByPriKey(id));
			result.setMessage("查询成功");
			log.debug("------DetailbillServiceImpl.findByPriKey 查询成功------");
		}catch (Exception e){
			log.error("------DetailbillServiceImpl.findByPriKey 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public BasicResult deleteByPriKey(Integer id) {
		BasicResult result = new BasicResult();
		try{
			detailbillManager.deleteDetailbillByPriKey(id);
			result.setMessage("删除成功");
			log.debug("------DetailbillServiceImpl.deleteByPriKey 删除成功------");
		}catch (Exception e){
			log.error("------DetailbillServiceImpl.deleteByPriKey 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public ListResult<Detailbill> searchDetailbill(Query<DetailbillQuery> query) {
		ListResult<Detailbill> result = new ListResult<Detailbill>();
		try {
			result.setValues(detailbillManager.searchDetailbillList(query));
			result.setMessage("查询成功");
			log.debug("------DetailbillServiceImpl.searchDetailbill 查询成功------");
		} catch (Exception ex) {
			log.error("------DetailbillServiceImpl.searchDetailbill 异常", ex);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public PageListResult<Detailbill> searchPageDetailbill(
			PageQuery<DetailbillQuery> pageQuery) {
		PageListResult<Detailbill> result = new PageListResult<Detailbill>();
		try {
			List<Detailbill> list = detailbillManager.searchPageDetailbillList(pageQuery);
			Integer itemCount = detailbillManager.getItemCount(pageQuery);
			Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
			result.setPagenation(pagenation);
			result.setValues(list);
			
			result.setMessage("查询成功");
			log.debug("------DetailbillServiceImpl.searchPage 查询成功------");
		} catch (Exception ex) {
			log.error("------DetailbillServiceImpl.searchPage 异常", ex);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
}
