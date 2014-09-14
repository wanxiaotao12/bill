package com.guo.bill.service;

import com.guo.bill.pojo.Account;
import com.guo.common.PageListResult;
import com.guo.common.PageQuery;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
public interface AccountService {

    public PageListResult<Account> searchPage(PageQuery<Account> pageQuery);


}
