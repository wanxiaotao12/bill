package com.guo.web.bill;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class IndexController {
//	public final static Logger log = LoggerFactory.getLogger(IndexController.class);
//    @Resource
//    private RcOperateAuthService rcOoperateAuthService;
//    @Resource
//    private CookieUtils cookieUtils ;
//
//    @RequestMapping(value = "/index.do")
//    public ModelAndView index(@RequestParam(value="authId", required=false, defaultValue="") String authId) {
//    	log.debug("-------------------------------------------------------------------");
//    	log.debug("-----------------authId:"+authId+"--------------------------------");
//
//    	ModelAndView mav = new ModelAndView("/index");
//
//    	/*保存当前登陆用户*/
//    	String userId = LoginContext.getLoginContext().getPin();
//    	mav.addObject(Constants.OPERATE_WEB_LOGIN_USER, userId);
//
//    	/*根据userId查询导航*/
//    	ListResult<Menu> resultList = rcOoperateAuthService.getNaviMenuByUserId(userId);
//    	List<Menu> naviList = resultList.getValues();
//    	mav.addObject(Constants.OPERATE_WEB_NAVI_LIST, naviList);
//
//    	/*根据userId,authId查询子菜单*/
//    	String curAuthId = null;
//    	if(StringUtil.isNotBlank(authId)){
//    		/*当前导航菜单权限编码*/
//            curAuthId = authId;
//            for (Menu navi : naviList) {
//            	if(authId.equals(navi.getAuthId())){
//            		navi.setSelected(true);
//            	}
//			}
//    	}else{
//    		/*第一个默认导航菜单权限编码*/
//    		Menu firstNavi = naviList.get(0);
//    		curAuthId = firstNavi.getAuthId();
//    		firstNavi.setSelected(true);
//    	}
//        log.debug("curAuthId: " + curAuthId);
//        LinkedHashMap<Menu, ArrayList<Menu>> menuTree = rcOoperateAuthService.getSubMenuTree(userId,curAuthId);
//        mav.addObject(Constants.OPERATE_WEB_MENU_TREE, menuTree);
//        log.debug("-------------------------------------------------------------------");
//        return mav;
//    }
//
//    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
//    public String logout(@RequestParam(value = "locale", required = false) Locale locale,
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Model model) throws Exception {
//    	log.debug("---------------------------------------");
//        log.debug("登出");
//
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Cache-Control", "no-cache");
//        response.setHeader("Pragma", "no-cache");
//
//        Cookie[] cookies = request.getCookies();
//        for(Cookie cookie :cookies){
//            cookieUtils.deleteCookie(response,cookie.getName());
//        }
//
//        return "redirect:/index.do";
//    }
}
