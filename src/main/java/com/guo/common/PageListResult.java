package com.guo.common;

import java.io.Serializable;

/**
 * 分页集合结果返回类
 * 描述：
 * @author：<a href="mailto:libao@jd.com">libao</a>
 * @since：2013年11月23日 上午9:38:13
 * @version:1.0
 */
public class PageListResult<T> extends ListResult<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Pagenation pagenation;

	public Pagenation getPagenation() {
		return pagenation;
	}

	public void setPagenation(Pagenation pagenation) {
		this.pagenation = pagenation;
	}
}
