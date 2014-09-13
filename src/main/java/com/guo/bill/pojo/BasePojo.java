package com.guo.bill.pojo;

import java.util.Date;

/**
 * Created by xiaotao.wxt on 2014/9/7.
 */
public abstract class BasePojo {
    private Date createTime;
    private Date editTime;
    private String creator;
    private String editor;
    private String state;//删除标识， 1：有效， 0：删除

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
