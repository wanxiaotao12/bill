package com.guo.common;


public class PageQueryWrapper<T> extends PageQuery<T> {

	private static final long serialVersionUID = 1L;
	
	public PageQueryWrapper(int pageNo, int pageSize, int itemCount,T t){
		super(pageNo, pageSize);
		this.itemCount = itemCount;
		this.setQuery(t);
		startRow = getStartIndex()< 1 ? 0 : getStartIndex() - 1;
//		endRow   = getEndIndex();
	}
	
	private int itemCount;
	private int startRow;
//	private int endRow;
	
    public int getItemCount() {
		return itemCount;
	}

	public int getStartRow() {
		return startRow;
	}

//	public int getEndRow() {
//		return endRow;
//	}

	private int getStartIndex() {
        return (this.getPageNo() - 1) * this.getPageSize() + 1;
    }
    
//	private int getEndIndex() {
//        int end = this.getPageNo() * this.getPageSize();
//        if (end > itemCount) {
//            end = itemCount;
//        }
//        return end;
//    }
}
