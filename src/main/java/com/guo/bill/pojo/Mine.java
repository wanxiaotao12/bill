package com.guo.bill.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：</b>Mine:<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五 
 * @version:1.0
 */
public class Mine implements Serializable {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private Integer id;	/**	 *矿名	 */	private String mineName;	/**	 *创建时间	 */	private Date createTime;	/**	 *修改时间	 */	private String editTime;	/**	 *创建人	 */	private String creator;	/**	 *修改人	 */	private String editor;	/**	 *删除状态	 */	private String state;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getMineName() {	    return this.mineName;	}	public void setMineName(String mineName) {	    this.mineName=mineName;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getEditTime() {	    return this.editTime;	}	public void setEditTime(String editTime) {	    this.editTime=editTime;	}	public String getCreator() {	    return this.creator;	}	public void setCreator(String creator) {	    this.creator=creator;	}	public String getEditor() {	    return this.editor;	}	public void setEditor(String editor) {	    this.editor=editor;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}

