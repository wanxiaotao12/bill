package com.guo.bill.service.impl;

import com.guo.bill.manager.MineManager;
import com.guo.bill.pojo.Mine;
import com.guo.bill.pojo.MineQuery;
import com.guo.bill.service.MineService;
import com.guo.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 描述：</b>MineServiceImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
@Service("mineService")
public class MineServiceImpl implements MineService {
	private final static Logger log= LoggerFactory.getLogger(MineServiceImpl.class);
	@Autowired
	private MineManager mineManager;
	
	@Override
	public GenericResult<String> addMine(Mine mine) {
		GenericResult<String> result = new GenericResult<String>();
		 try {
			if (mine != null) {
				//TODO:generate sequence id by CacheUtils.getSeq method Or remove this block.
				String id="";//CacheUtils.getSeq(RedisKeyConstants.XXX, length);
				//mine.setId(id);
                mine.setCreateTime(new Date());
				mineManager.insertMine(mine);
				
				result.setValue(id);
				result.setMessage("保存成功");
				log.debug("------MineServiceImpl.addMine 保存成功------");
			} else {
				log.error("------MineServiceImpl.addMine - mine is null!");
				result.setCode("");
				result.setMessage("mine is null");
			}
		 } catch (final Exception e) {
			 log.error("------MineServiceImpl.addMine 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public BasicResult modifyMine(Mine mine) {
		BasicResult result = new BasicResult();
		try {
			if (mine != null) {
				mineManager.updateMine(mine);
				result.setMessage("编辑成功");
				log.debug("------MineServiceImpl.modifyMine 编辑成功------");
			} else {
				log.error("------MineServiceImpl.modifyMine - mine is null!");
				result.setCode("");
				result.setMessage("mine is null");
			}
		} catch (Exception e) {
			log.error("------MineServiceImpl.modifyMine 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public GenericResult<Mine> findByPriKey(Integer id) {
		GenericResult<Mine> result = new GenericResult<Mine>();
		try{
			result.setValue(mineManager.findMineByPriKey(id));
			result.setMessage("查询成功");
			log.debug("------MineServiceImpl.findByPriKey 查询成功------");
		}catch (Exception e){
			log.error("------MineServiceImpl.findByPriKey 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public BasicResult deleteByPriKey(Integer id) {
		BasicResult result = new BasicResult();
		try{
			mineManager.deleteMineByPriKey(id);
			result.setMessage("删除成功");
			log.debug("------MineServiceImpl.deleteByPriKey 删除成功------");
		}catch (Exception e){
			log.error("------MineServiceImpl.deleteByPriKey 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public ListResult<Mine> searchMine(Query<MineQuery> query) {
		ListResult<Mine> result = new ListResult<Mine>();
		try {
			result.setValues(mineManager.searchMineList(query));
			result.setMessage("查询成功");
			log.debug("------MineServiceImpl.searchMine 查询成功------");
		} catch (Exception ex) {
			log.error("------MineServiceImpl.searchMine 异常", ex);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public PageListResult<Mine> searchPageMine(
			PageQuery<MineQuery> pageQuery) {
		PageListResult<Mine> result = new PageListResult<Mine>();
		try {
			List<Mine> list = mineManager.searchPageMineList(pageQuery);
			Integer itemCount = mineManager.getItemCount(pageQuery);
			Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
			result.setPagenation(pagenation);
			result.setValues(list);
			
			result.setMessage("查询成功");
			log.debug("------MineServiceImpl.searchPageMine 查询成功------");
		} catch (Exception ex) {
			log.error("------MineServiceImpl.searchPageMine 异常", ex);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
}
