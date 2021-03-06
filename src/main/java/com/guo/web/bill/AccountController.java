package com.guo.web.bill;

import com.guo.bill.dao.AccountDao;
import com.guo.bill.enumtype.AccountTypeEnum;
import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.pojo.Account;
import com.guo.bill.service.AccountService;
import com.guo.common.PageQuery;
import com.guo.util.Pinyin;
import com.guo.util.SystemTools;
import com.guo.web.LoginContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiaotao.wxt on 2014/9/14.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDao accountDao;

    @RequestMapping(value = "index", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView detailbillIndex(@ModelAttribute Account query,
                                        @RequestParam(required = false, value = "pageNo",
                                                defaultValue = "1") int pageNo,
                                        @RequestParam(required = false, value = "pageSize",
                                                defaultValue = "10") int pageSize) {
        PageQuery<Account> pageQuery = new PageQuery<Account>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);

        if (StringUtils.isBlank(query.getAccounttype())) {
            query.setAccounttype(null);
        }
        query.setState(StateEnum.NORMAL.getCode());
        pageQuery.setQuery(query);

        ModelAndView mav = new ModelAndView();
        mav.addObject("query", query);
        mav.setViewName("bill/accountIndex");
        mav.addObject("pageInfos", SystemTools.convertPaginatedList(accountService.searchPage(pageQuery)));
        mav.addObject("accountTypeMap", AccountTypeEnum.toMap());

        mav.addObject("curUser", LoginContext.getCurUser());

        return mav;
    }

    @RequestMapping(value = "toAdd", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView toAdd() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/bill/accountAdd");
        mav.addObject("AccountTypeList", AccountTypeEnum.toList());
        mav.addObject("curUser", LoginContext.getCurUser());
        return mav;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView add(@ModelAttribute Account account) {
        ModelAndView mav = new ModelAndView();
        account.setPinyin(Pinyin.getPinyin(account.getAccountname()));
        accountDao.addAccount(account);
        mav.setViewName("redirect:/account/index.do");

        return mav;
    }
}
