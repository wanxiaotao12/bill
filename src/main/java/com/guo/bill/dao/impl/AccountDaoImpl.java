package com.guo.bill.dao.impl;

import com.guo.bill.dao.AccountDao;
import com.guo.bill.dao.BaseDao;
import com.guo.bill.pojo.Account;
import com.guo.bill.pojo.CardDailyStatistics;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
@Repository("accountDao")
public class AccountDaoImpl extends BaseDao implements AccountDao {
    @Override
    public List<Account> findByType(String accountType) {
        return queryForList("Account.findByType", accountType);
    }

    @Override public Account getByNoAndType(String accountno, String accountType) {
        Map map = new HashMap<String,String>();
        map.put("accountno",accountno);
        map.put("accounttype",accountType);
        return (Account)queryForObject("Account.getByNoAndType", map);
    }

    @Override
    public boolean update(Account account) {
        int num =update("Account.update", account);
        if(num == 1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Account> getPageList(PageQuery<Account> pageQuery,
                                                           Integer itemCount) {
        PageQueryWrapper<Account> wrapper = new PageQueryWrapper<Account>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());

        return this.queryForList("Account.getPagenationList",wrapper);    }

    @Override
    public int getItemCount(PageQuery<Account> pageQuery) {
        return (Integer)this.queryForObject("Account.getItemCount",pageQuery);
    }
}
