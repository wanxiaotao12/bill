/*
 * Copyright 2014 Aliyun.com All right reserved. This software is the
 * confidential and proprietary information of Aliyun.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Aliyun.com .
 */

package com.guo.web;

import com.guo.bill.pojo.User;
import com.guo.common.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by xiaotao.wxt on 2014/11/10.
 */
public class LoginFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                                   FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest )servletRequest;
        HttpServletResponse response = (HttpServletResponse )servletResponse;
        String str = request.getRequestURI();
        StringBuffer str2 = request.getRequestURL();
        if("/".equals(str)
                || "/index.html".equals(str)
                || "/login.do".equals(str)) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constant.LOGIN_SESSION_KEY);
        if(user == null) {
            response.sendRedirect("/index.html");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override public void destroy() {

    }
}
