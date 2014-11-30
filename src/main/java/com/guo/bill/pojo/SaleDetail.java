package com.guo.bill.pojo;import java.io.Serializable;import java.math.BigDecimal;import java.util.Date;/** * 描述：</b>Shipperbill:<br> * @author：<a href="mailto:*@jd.com">系统生成</a> * @since：2014年04月18日 11时51分47秒 星期五  * @version:1.0 */public class SaleDetail extends BasePojo implements Serializable {    private static final long serialVersionUID = 1L;    /**     *     */    private Integer id;    /**     *购买日期     */    private Date    buyDatetime;    /**     * 客户编号     */    private String customerNo;    /**     *客户姓名     */    private String customerName;    /**     *车牌号     */    private String licenseplateNumber;    /**     * 品种     */    private String varietiy;    /**     *单价     */    private BigDecimal unitPrice;    /**     *吨位     */    private BigDecimal onnage;    /**     *总金额     */    private BigDecimal totalAmount;    /**     *煤矿编号     */    private String mineNo;    /**     *煤矿名称     */    private String mineName;    /**     * 税点     */    private BigDecimal taxPoint;    /**     *含税成本单价     */    private BigDecimal taxCostUnitPrice;    /**     *含税成本总金额     */    private BigDecimal taxCostTotalAmount;    /**     *不含税成本单价     */    private BigDecimal notaxCostUnitPrice;    /**     *不含税成本总金额     */    private BigDecimal notaxCostTotalAmount;    /**     * 税金额     */    private BigDecimal taxAmount;    /**     * 原煤矿金额     */    private BigDecimal oldMinePrice;    /**     * 现煤矿金额     */    private BigDecimal nowMinePrice;    /**     * 原煤矿税金额     */    private BigDecimal oldTaxAmount;    /**     * 现煤矿税金额     */    private BigDecimal nowTaxAmount;    /**     * 原客户金额     */    private BigDecimal oldCusPrice;    /**     * 现客户金额     */    private BigDecimal nowCusPrice;    /**     * 冲正id    */    private Integer delId;    public Integer getId() {        return this.id;    }    public void setId(Integer id) {        this.id = id;    }    public Date getBuyDatetime() {        return buyDatetime;    }    public void setBuyDatetime(Date buyDatetime) {        this.buyDatetime = buyDatetime;    }    public String getCustomerNo() {        return customerNo;    }    public void setCustomerNo(String customerNo) {        this.customerNo = customerNo;    }    public String getCustomerName() {        return customerName;    }    public void setCustomerName(String customerName) {        this.customerName = customerName;    }    public String getLicenseplateNumber() {        return licenseplateNumber;    }    public void setLicenseplateNumber(String licenseplateNumber) {        this.licenseplateNumber = licenseplateNumber;    }    public String getVarietiy() {        return varietiy;    }    public void setVarietiy(String varietiy) {        this.varietiy = varietiy;    }    public BigDecimal getUnitPrice() {        return unitPrice;    }    public void setUnitPrice(BigDecimal unitPrice) {        this.unitPrice = unitPrice;    }    public BigDecimal getOnnage() {        return onnage;    }    public void setOnnage(BigDecimal onnage) {        this.onnage = onnage;    }    public BigDecimal getTotalAmount() {        return totalAmount;    }    public void setTotalAmount(BigDecimal totalAmount) {        this.totalAmount = totalAmount;    }    public String getMineNo() {        return mineNo;    }    public void setMineNo(String mineNo) {        this.mineNo = mineNo;    }    public String getMineName() {        return mineName;    }    public void setMineName(String mineName) {        this.mineName = mineName;    }    public BigDecimal getTaxPoint() {        return taxPoint;    }    public void setTaxPoint(BigDecimal taxPoint) {        this.taxPoint = taxPoint;    }    public BigDecimal getTaxCostUnitPrice() {        return taxCostUnitPrice;    }    public void setTaxCostUnitPrice(BigDecimal taxCostUnitPrice) {        this.taxCostUnitPrice = taxCostUnitPrice;    }    public BigDecimal getTaxCostTotalAmount() {        return taxCostTotalAmount;    }    public void setTaxCostTotalAmount(BigDecimal taxCostTotalAmount) {        this.taxCostTotalAmount = taxCostTotalAmount;    }    public BigDecimal getNotaxCostUnitPrice() {        return notaxCostUnitPrice;    }    public void setNotaxCostUnitPrice(BigDecimal notaxCostUnitPrice) {        this.notaxCostUnitPrice = notaxCostUnitPrice;    }    public BigDecimal getNotaxCostTotalAmount() {        return notaxCostTotalAmount;    }    public void setNotaxCostTotalAmount(BigDecimal notaxCostTotalAmount) {        this.notaxCostTotalAmount = notaxCostTotalAmount;    }    public BigDecimal getTaxAmount() {        return taxAmount;    }    public void setTaxAmount(BigDecimal taxAmount) {        this.taxAmount = taxAmount;    }    public BigDecimal getOldMinePrice() {        return oldMinePrice;    }    public void setOldMinePrice(BigDecimal oldMinePrice) {        this.oldMinePrice = oldMinePrice;    }    public BigDecimal getNowMinePrice() {        return nowMinePrice;    }    public void setNowMinePrice(BigDecimal nowMinePrice) {        this.nowMinePrice = nowMinePrice;    }    public BigDecimal getOldTaxAmount() {        return oldTaxAmount;    }    public void setOldTaxAmount(BigDecimal oldTaxAmount) {        this.oldTaxAmount = oldTaxAmount;    }    public BigDecimal getNowTaxAmount() {        return nowTaxAmount;    }    public void setNowTaxAmount(BigDecimal nowTaxAmount) {        this.nowTaxAmount = nowTaxAmount;    }    public BigDecimal getOldCusPrice() {        return oldCusPrice;    }    public void setOldCusPrice(BigDecimal oldCusPrice) {        this.oldCusPrice = oldCusPrice;    }    public BigDecimal getNowCusPrice() {        return nowCusPrice;    }    public void setNowCusPrice(BigDecimal nowCusPrice) {        this.nowCusPrice = nowCusPrice;    }    public Integer getDelId() {        return delId;    }    public void setDelId(Integer delId) {        this.delId = delId;    }}