package com.guo.common;

/**
 * 结果返回基类
 * 描述：
 * @author：<a href="mailto:libao@jd.com">libao</a>
 * @since：2013年11月23日 上午9:35:42
 * @version:1.0
 */
public abstract class AbstractResult {
	private String code = "FPXS0000";
	private String desc;
	private String message;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
