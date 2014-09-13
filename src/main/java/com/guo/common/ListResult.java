package com.guo.common;

import java.io.Serializable;
import java.util.List;

/**
 * 集合结果返回类
 * 描述：
 * @author：<a href="mailto:libao@jd.com">libao</a>
 * @since：2013年11月23日 上午9:36:24
 * @version:1.0
 */
public class ListResult<T> extends AbstractResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<T> values;

	public List<T> getValues() {
		return values;
	}

	public void setValues(List<T> values) {
		this.values = values;
	}
}
