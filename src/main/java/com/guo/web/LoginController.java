/*
 * Copyright 2014 Aliyun.com All right reserved. This software is the
 * confidential and proprietary information of Aliyun.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Aliyun.com .
 */

package com.guo.web;

import com.guo.bill.dao.UserDao;
import com.guo.bill.pojo.User;
import com.guo.common.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xiaotao.wxt on 2014/11/10.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView login(@RequestParam(required = true, value = "userName") String userName,
                      @RequestParam(required = false, value = "password") String password,
                      HttpServletRequest request,
                      HttpServletResponse response){
        ModelAndView mav = new ModelAndView();

        String loginVm = "/bill/login";
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            mav.setViewName(loginVm);
            mav.addObject("error","用户名或密码错误");
            return mav;
        }

        User userResult = userDao.findByUserNameAndPassword(userName,password);
        if(userResult != null) {
            HttpSession session = request.getSession();
            User user = new User();
            user.setUserName(userName);
            user.setRealName(userResult.getRealName());
            session.setAttribute(Constant.LOGIN_SESSION_KEY,user);
            mav.setViewName("redirect:/saledetail/index.do");
        } else {
            mav.setViewName(loginVm);
            mav.addObject("error","用户名或密码错误");
            return mav;
        }

        return mav;
    }
}
