package com.guo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created with IntelliJ IDEA.
 * User:22
 * Date: 13-12-06
 * Time: 下午7:49
 */

public abstract class BaseController{
    public final static Logger log = LoggerFactory.getLogger(BaseController.class);
    
    @InitBinder  
    protected void initBinder(WebDataBinder binder) {  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    protected void responseJson(HttpServletResponse response, String json) throws IOException
    {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
