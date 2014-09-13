package com.guo.util;

public class DataItem {
	private String code;
	private String parent;
	private String message;
	
	public DataItem(){}

	public DataItem(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public DataItem(String code, String parent, String message) {
		this.code = code;
		this.parent = parent;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
}
