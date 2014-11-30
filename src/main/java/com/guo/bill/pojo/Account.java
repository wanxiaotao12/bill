package com.guo.bill.pojo;

import java.math.BigDecimal;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
public class Account extends BasePojo {
    private Integer id;

    private String     accounttype;
    private String     accountno;
    private String     accountname;
    private String     pinyin;
    private BigDecimal price;

    /**
     * 税金额
     */
    private BigDecimal taxAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getPinyin() {
        return pinyin;
    }


    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
