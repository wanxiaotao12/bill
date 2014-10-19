package com.guo.bill.dao;

import com.guo.bill.pojo.Account;
import com.guo.common.PageQuery;

import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
public interface AccountDao {
    public List<Account>   findByType(String accountType);

    public Account   getByNoAndType(String accountno,String accountType);

    public boolean update(Account account);

    public List<Account> getPageList(PageQuery<Account> pageQuery, Integer itemCount);

    public int getItemCount(PageQuery<Account> pageQuery);
}
