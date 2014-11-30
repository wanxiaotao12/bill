package com.guo.bill.dao.impl;

import com.guo.bill.dao.AccountDao;
import com.guo.bill.dao.BaseDao;
import com.guo.bill.dao.DictionaryDao;
import com.guo.bill.enumtype.DictionaryEnum;
import com.guo.bill.pojo.Account;
import com.guo.bill.pojo.Dictionary;
import com.guo.common.PageQuery;
import com.guo.common.PageQueryWrapper;
import com.guo.web.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
@Repository("accountDao")
public class AccountDaoImpl extends BaseDao implements AccountDao {
    @Autowired
    private DictionaryDao dictionaryDao;

    @Override
    @Transactional
    public void addAccount(Account account) {
        Dictionary oldDictionary = dictionaryDao.findByTypeAndName(account.getAccounttype(), account.getAccountname());
        if(oldDictionary != null) {
            throw  new RuntimeException("表：dictionary已存在，"+ account.getAccountname());
        }

        Dictionary accountNumDict = dictionaryDao.findByTypeAndCode("4",account.getAccounttype());
        if(accountNumDict ==null || accountNumDict.getOrderNo() == null) {
            throw  new RuntimeException("获取账户编号失败");
        }

        String accountNo = DictionaryEnum.getEnum(account.getAccounttype()).getPrefix() + (accountNumDict.getOrderNo()+1);
        Dictionary dictionary = new Dictionary();
        dictionary.setType(account.getAccounttype());
        dictionary.setCode(accountNo);
        dictionary.setName(account.getAccountname());
        dictionary.setPinyin(account.getPinyin());

        dictionaryDao.insert(dictionary);

        dictionaryDao.updateOrderNo(accountNumDict.getId(),accountNumDict.getOrderNo());

        account.setAccountno(accountNo);
        this.insert(account);

    }

    @Override
    public void insert(Account account) {
        account.setCreator(LoginContext.getCurUser().getRealName());
        this.insert("Account.insert",account);
    }

    @Override
    public List<Account> findByType(String accountType) {
        return queryForList("Account.findByType", accountType);
    }

    @Override
    public Account getByNoAndType(String accountno, String accountType) {
        Map map = new HashMap<String,String>();
        map.put("accountno",accountno);
        map.put("accounttype",accountType);
        return (Account)queryForObject("Account.getByNoAndType", map);
    }

    @Override
    public void update(Account account) {
        int num =update("Account.update", account);
        if(num != 1){
            throw new RuntimeException("账户表："+account.getAccountno() +","+ account.getAccountname()+",更新失败");
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
