package com.guo.bill.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
/**
 * 描述：</b>ShipperbillQuery:<br>
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五 
 * @version:1.0
 */
public class ShipperbillQuery implements Serializable {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private Integer id;	/**	 *货主姓名	 */	private String shipperName;	/**	 *交易类型	 */	private String tradeType;	/**	 *购买日期	 */	private Date buyDatetime;	/**	 *车牌号	 */	private String licenseplatenumber;	/**	 *单价	 */	private BigDecimal unitprice;	/**	 *吨位	 */	private BigDecimal onnage;	/**	 *总金额	 */	private BigDecimal totalamount;	/**	 *矿名	 */	private String mineName;	/**	 *已付金额	 */	private BigDecimal paidAmount;	/**	 *未付金额	 */	private BigDecimal unpaidAmount;	/**	 *创建时间	 */	private Date createTime;	/**	 *修改时间	 */	private String editTime;	/**	 *创建人	 */	private String creator;	/**	 *修改人	 */	private String editor;	/**	 *删除状态	 */	private String state;	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getShipperName() {	    return this.shipperName;	}	public void setShipperName(String shipperName) {	    this.shipperName=shipperName;	}	public String getTradeType() {	    return this.tradeType;	}	public void setTradeType(String tradeType) {	    this.tradeType=tradeType;	}	public Date getBuyDatetime() {	    return this.buyDatetime;	}	public void setBuyDatetime(Date buyDatetime) {	    this.buyDatetime=buyDatetime;	}	public String getLicenseplatenumber() {	    return this.licenseplatenumber;	}	public void setLicenseplatenumber(String licenseplatenumber) {	    this.licenseplatenumber=licenseplatenumber;	}	public BigDecimal getUnitprice() {	    return this.unitprice;	}	public void setUnitprice(BigDecimal unitprice) {	    this.unitprice=unitprice;	}	public BigDecimal getOnnage() {	    return this.onnage;	}	public void setOnnage(BigDecimal onnage) {	    this.onnage=onnage;	}	public BigDecimal getTotalamount() {	    return this.totalamount;	}	public void setTotalamount(BigDecimal totalamount) {	    this.totalamount=totalamount;	}	public String getMineName() {	    return this.mineName;	}	public void setMineName(String mineName) {	    this.mineName=mineName;	}	public BigDecimal getPaidAmount() {	    return this.paidAmount;	}	public void setPaidAmount(BigDecimal paidAmount) {	    this.paidAmount=paidAmount;	}	public BigDecimal getUnpaidAmount() {	    return this.unpaidAmount;	}	public void setUnpaidAmount(BigDecimal unpaidAmount) {	    this.unpaidAmount=unpaidAmount;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getEditTime() {	    return this.editTime;	}	public void setEditTime(String editTime) {	    this.editTime=editTime;	}	public String getCreator() {	    return this.creator;	}	public void setCreator(String creator) {	    this.creator=creator;	}	public String getEditor() {	    return this.editor;	}	public void setEditor(String editor) {	    this.editor=editor;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}
}

