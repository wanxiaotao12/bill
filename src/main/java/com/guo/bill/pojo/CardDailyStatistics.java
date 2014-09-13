package com.guo.bill.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xiaotao.wxt on 2014/9/11.
 */
public class CardDailyStatistics extends BasePojo{

    private Integer    id;
    private Date       datetime;
    private String     cardno;
    private BigDecimal income;
    private BigDecimal     outlay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getOutlay() {
        return outlay;
    }

    public void setOutlay(BigDecimal outlay) {
        this.outlay = outlay;
    }
}
