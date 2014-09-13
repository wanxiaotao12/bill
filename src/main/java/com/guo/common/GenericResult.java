package com.guo.common;

import java.io.Serializable;

/**
 * 单对象结果返回类
 * 描述：
 * @author：<a href="mailto:libao@jd.com">libao</a>
 * @since：2013年11月23日 上午9:35:57
 * @version:1.0
 */
public class GenericResult<T> extends AbstractResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
}
