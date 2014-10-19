package com.guo.bill.service.impl;

import java.util.List;

import com.guo.bill.pojo.SaleDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guo.bill.service.ShipperbillService;
import com.guo.bill.manager.ShipperbillManager;
import com.guo.bill.pojo.ShipperbillQuery;
import com.guo.common.*;


/**
 * 描述：</b>ShipperbillServiceImpl<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
@Service("shipperbillService")
public class ShipperbillServiceImpl implements ShipperbillService {
	private final static Logger log= LoggerFactory.getLogger(ShipperbillServiceImpl.class);
	@Autowired
	private ShipperbillManager shipperbillManager;
	
	@Override
	public GenericResult<String> addShipperbill(SaleDetail shipperbill) {
		GenericResult<String> result = new GenericResult<String>();
		 try {
			if (shipperbill != null) {
				//TODO:generate sequence id by CacheUtils.getSeq method Or remove this block.
				String id="";//CacheUtils.getSeq(RedisKeyConstants.XXX, length);
				//shipperbill.setId(id);
				shipperbillManager.insertShipperbill(shipperbill);
				
				result.setValue(id);
				result.setMessage("保存成功");
				log.debug("------ShipperbillServiceImpl.addShipperbill 保存成功------");
			} else {
				log.error("------ShipperbillServiceImpl.addShipperbill - shipperbill is null!");
				result.setCode("");
				result.setMessage("shipperbill is null");
			}
		 } catch (final Exception e) {
			 log.error("------ShipperbillServiceImpl.addShipperbill 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public BasicResult modifyShipperbill(SaleDetail shipperbill) {
		BasicResult result = new BasicResult();
		try {
			if (shipperbill != null) {
				shipperbillManager.updateShipperbill(shipperbill);
				result.setMessage("编辑成功");
				log.debug("------ShipperbillServiceImpl.modifyShipperbill 编辑成功------");
			} else {
				log.error("------ShipperbillServiceImpl.modifyShipperbill - shipperbill is null!");
				result.setCode("");
				result.setMessage("shipperbill is null");
			}
		} catch (Exception e) {
			log.error("------ShipperbillServiceImpl.modifyShipperbill 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public GenericResult<SaleDetail> findByPriKey(Integer id) {
		GenericResult<SaleDetail> result = new GenericResult<SaleDetail>();
		try{
			result.setValue(shipperbillManager.findShipperbillByPriKey(id));
			result.setMessage("查询成功");
			log.debug("------ShipperbillServiceImpl.findByPriKey 查询成功------");
		}catch (Exception e){
			log.error("------ShipperbillServiceImpl.findByPriKey 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public BasicResult deleteByPriKey(Integer id) {
		BasicResult result = new BasicResult();
		try{
			shipperbillManager.deleteShipperbillByPriKey(id);
			result.setMessage("删除成功");
			log.debug("------ShipperbillServiceImpl.deleteByPriKey 删除成功------");
		}catch (Exception e){
			log.error("------ShipperbillServiceImpl.deleteByPriKey 异常",e);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public ListResult<SaleDetail> searchShipperbill(Query<ShipperbillQuery> query) {
		ListResult<SaleDetail> result = new ListResult<SaleDetail>();
		try {
			result.setValues(shipperbillManager.searchShipperbillList(query));
			result.setMessage("查询成功");
			log.debug("------ShipperbillServiceImpl.searchShipperbill 查询成功------");
		} catch (Exception ex) {
			log.error("------ShipperbillServiceImpl.searchShipperbill 异常", ex);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
	
	@Override
	public PageListResult<SaleDetail> searchPageShipperbill(
			PageQuery<ShipperbillQuery> pageQuery) {
		PageListResult<SaleDetail> result = new PageListResult<SaleDetail>();
		try {
			List<SaleDetail> list = shipperbillManager.searchPageShipperbillList(pageQuery);
			Integer itemCount = shipperbillManager.getItemCount(pageQuery);
			Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
			result.setPagenation(pagenation);
			result.setValues(list);
			
			result.setMessage("查询成功");
			log.debug("------ShipperbillServiceImpl.searchPageShipperbill 查询成功------");
		} catch (Exception ex) {
			log.error("------ShipperbillServiceImpl.searchPageShipperbill 异常", ex);
			result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
			result.setMessage("未知异常");
		}
		return result;
	}
}
