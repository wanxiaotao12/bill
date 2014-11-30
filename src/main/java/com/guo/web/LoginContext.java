/*
 * Copyright 2014 Aliyun.com All right reserved. This software is the
 * confidential and proprietary information of Aliyun.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Aliyun.com .
 */

package com.guo.web;

import com.guo.bill.pojo.User;

/**
 * Created by xiaotao.wxt on 2014/11/30.
 */
public class LoginContext {
    public static final ThreadLocal curUser = new ThreadLocal();

    public static void setCurUser(User user) {
        curUser.set(user);
    }

    public static User getCurUser() {
        return (User)curUser.get();
    }
}
