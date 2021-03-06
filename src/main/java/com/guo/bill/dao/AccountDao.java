package com.guo.bill.dao;

import com.guo.bill.pojo.Account;
import com.guo.common.PageQuery;

import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
public interface AccountDao {

    public void addAccount(Account account);

    public void insert(Account account);

    public List<Account>   findByType(String accountType);

    public Account   getByNoAndType(String accountno,String accountType);

    public void update(Account account);

    public List<Account> getPageList(PageQuery<Account> pageQuery, Integer itemCount);

    public int getItemCount(PageQuery<Account> pageQuery);
}
