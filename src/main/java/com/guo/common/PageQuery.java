package com.guo.common;

/**
 * Created by IntelliJ IDEA.
 * User: wan
 * Date: 14-9-3
 * Time: 上午1:29
 * To change this template use File | Settings | File Templates.
 */
public class PageQuery<T>  extends Query<T>{
   private int pageNo;
	private int pageSize;

	public PageQuery(){
	}

	public PageQuery(int pageNo, int pageSize){
		this.pageNo=pageNo;
		this.pageSize=pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
