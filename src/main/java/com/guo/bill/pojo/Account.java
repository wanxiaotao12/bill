package com.guo.bill.pojo;

import java.math.BigDecimal;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
public class Account extends BasePojo {
    private Integer id;

    private String     accounttype;
    private String     accountname;
    private BigDecimal price;

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
}
