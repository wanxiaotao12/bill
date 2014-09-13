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
    private String cardno;//卡号
    private BigDecimal price;//金额
    private String remark;//备注

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
}
