package com.guo.bill.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xiaotao.wxt on 2014/9/7.
 */
public class CardDetail  extends BasePojo{
    private int  id;
    private Date datetime;
    private String operation;//操作类型
    private String cardno;//卡编号
    private String cardname;//卡名
    private BigDecimal oldCardPrice;
    private BigDecimal nowCardPrice;
    private String otherno;//对方账号编号
    private String othername;//对象账号名称
    private BigDecimal oldOthernoPrice;
    private BigDecimal nowOthernoPrice;
    private BigDecimal price;//金额
    private String remark;//备注
    private String payType;//付款方式
    /**
     * 冲正id
     */
    private Integer delId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOtherno() {
        return otherno;
    }

    public void setOtherno(String otherno) {
        this.otherno = otherno;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public BigDecimal getOldCardPrice() {
        return oldCardPrice;
    }

    public void setOldCardPrice(BigDecimal oldCardPrice) {
        this.oldCardPrice = oldCardPrice;
    }

    public BigDecimal getOldOthernoPrice() {
        return oldOthernoPrice;
    }

    public void setOldOthernoPrice(BigDecimal oldOthernoPrice) {
        this.oldOthernoPrice = oldOthernoPrice;
    }

    public BigDecimal getNowCardPrice() {
        return nowCardPrice;
    }

    public void setNowCardPrice(BigDecimal nowCardPrice) {
        this.nowCardPrice = nowCardPrice;
    }

    public BigDecimal getNowOthernoPrice() {
        return nowOthernoPrice;
    }

    public void setNowOthernoPrice(BigDecimal nowOthernoPrice) {
        this.nowOthernoPrice = nowOthernoPrice;
    }

    public Integer getDelId() {
        return delId;
    }

    public void setDelId(Integer delId) {
        this.delId = delId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
