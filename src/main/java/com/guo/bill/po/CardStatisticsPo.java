package com.guo.bill.po;

import com.guo.bill.pojo.CardDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/8.
 */
public class CardStatisticsPo {
    private BigDecimal       income;
    private BigDecimal       expend;
    private BigDecimal       total;

    private List<CardDetail> cardDetailList;

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getExpend() {
        return expend;
    }

    public void setExpend(BigDecimal expend) {
        this.expend = expend;
    }

    public List<CardDetail> getCardDetailList() {
        return cardDetailList;
    }

    public void setCardDetailList(List<CardDetail> cardDetailList) {
        this.cardDetailList = cardDetailList;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
