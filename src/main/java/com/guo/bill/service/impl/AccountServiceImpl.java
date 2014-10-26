package com.guo.bill.service.impl;

import com.guo.bill.dao.AccountDao;
import com.guo.bill.pojo.Account;
import com.guo.bill.pojo.CardDailyStatistics;
import com.guo.bill.service.AccountService;
import com.guo.common.CodeEnum;
import com.guo.common.PageListResult;
import com.guo.common.PageQuery;
import com.guo.common.Pagenation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private final static Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Override public PageListResult<Account> searchPage(
            PageQuery<Account> pageQuery) {
        PageListResult<Account> result = new PageListResult<Account>();
        try {
            Integer itemCount = accountDao.getItemCount(pageQuery);

            List<Account> list = accountDao.getPageList(pageQuery, itemCount);
            Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
            result.setPagenation(pagenation);
            result.setValues(list);

            result.setMessage("查询成功");
            log.debug("------DetailbillServiceImpl.searchPage 查询成功------");
        } catch (Exception ex) {
            log.error("------DetailbillServiceImpl.searchPage 异常", ex);
            result.setCode(CodeEnum.SuccessOrFaildEnum.UNKNOWN.getCode());
            result.setMessage("未知异常");
        }
        return result;
    }
}
