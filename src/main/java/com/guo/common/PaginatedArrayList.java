package com.guo.common;

import java.util.ArrayList;

/**
 * Created by xiaotao.wxt on 2014/9/6.
 */
public class PaginatedArrayList extends ArrayList {
    private int index;
    private int pageSize;
    private int totalItem;
    private int totalPage;

    private int previousPage;
    private int nextPage;

    private boolean previousPageAvailable;
    private boolean nextPageAvailable;
    public PaginatedArrayList(int index,int pageSize, int totalPage){
        this.index = index;
        this.pageSize = pageSize;
        this.totalPage = totalPage;


        if(index >1) {
            this.previousPageAvailable = true;
            this.previousPage = index -1;
        }
        if(index < totalPage) {
            this.nextPageAvailable = true;
            this.nextPage = index +1;
        }
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isNextPageAvailable() {
        return nextPageAvailable;
    }

    public void setNextPageAvailable(boolean nextPageAvailable) {
        this.nextPageAvailable = nextPageAvailable;
    }

    public boolean isPreviousPageAvailable() {
        return previousPageAvailable;
    }

    public void setPreviousPageAvailable(boolean previousPageAvailable) {
        this.previousPageAvailable = previousPageAvailable;
    }
}
