package com.guo.bill.dao;

import com.guo.bill.pojo.Account;

import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
public interface AccountDao {
    public List<Account>   findByType(String accountType);
}
