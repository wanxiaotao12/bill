package com.guo.web.bill;

import com.guo.bill.pojo.Detailbill;
import com.guo.bill.pojo.DetailbillQuery;
import com.guo.bill.service.DetailbillService;
import com.guo.common.BasicResult;
import com.guo.common.GenericResult;
import com.guo.common.PageQuery;
import com.guo.util.SystemTools;
import com.guo.web.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：</b>DetailbillController<br>
 *
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五
 * @version:1.0
 */
@Controller
@RequestMapping("/detailbill")
public class DetailbillController extends BaseController {
    @Autowired
    private DetailbillService detailbillService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "detailbillIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView detailbillIndex(@ModelAttribute DetailbillQuery query,
                                        @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
                                        @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
        PageQuery<DetailbillQuery> pageQuery = new PageQuery<DetailbillQuery>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        ModelAndView mav = new ModelAndView();
        pageQuery.setQuery(query);
        mav.addObject("query", query);
        mav.setViewName("bill/detailbillIndex");
        mav.addObject("pageInfos", SystemTools.convertPaginatedList(detailbillService.searchPageDetailbill(pageQuery)));
        return mav;
    }

    /**
     * 详情
     *
     * @return
     */
    @RequestMapping(value = "detailbillDetail", method = RequestMethod.GET)
    public ModelAndView detailbillDetail(@RequestParam(required = true, value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bill/detailbillDetail");
        GenericResult<Detailbill> result = detailbillService.findByPriKey(id);
        mav.addObject("detailbill", result.getValue());
        return mav;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdd(HttpServletRequest request, ModelMap model) {
        return "bill/detailbillAdd";
    }

    /**
     * 保存信息
     *
     * @return
     */
    @RequestMapping(value = "/detailbillAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView detailbillAdd(@ModelAttribute Detailbill detailbill) {
        GenericResult<String> result = detailbillService.addDetailbill(detailbill);
        ModelAndView mav = new ModelAndView();
        if ("FPXS0000".equals(result.getCode())) {
            mav.setViewName("redirect:/detailbill/detailbillIndex.do");
        } else {
            mav.setViewName("bill/detailbillAdd");
            mav.addObject("detailbill", detailbill);
        }
        return mav;
    }

    /**
     * 跳转到编辑页面
     *
     * @return
     */
    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(@RequestParam(required = true, value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bill/detailbillEdit");
        GenericResult<Detailbill> result = detailbillService.findByPriKey(id);
        mav.addObject("detailbill", result.getValue());
        return mav;
    }

    /**
     * 编辑
     *
     * @return
     */
    @RequestMapping(value = "/detailbillEdit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView detailbillEdit(@ModelAttribute Detailbill detailbill) {
        BasicResult result = detailbillService.modifyDetailbill(detailbill);
        ModelAndView mav = new ModelAndView();
        if ("FPXS0000".equals(result.getCode())) {
            mav.setViewName("redirect:/detailbill/detailbillIndex.do");
        } else {
            mav.setViewName("bill/detailbillEdit");
            mav.addObject("detailbill", detailbill);
        }
        return mav;
    }


    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "detailbillDelete", method = RequestMethod.GET)
    public ModelAndView detailbillDelete(@RequestParam(required = true, value = "id") String id) {
        ModelAndView mav = new ModelAndView();
//		mav.setViewName("redirect:/detailbill/detailbillIndex.do");
//		BasicResult result = detailbillService.deleteByPriKey(id);
//		if(!"FPXS0000".equals(result.getCode())){
//			//TODO 删除失败
//		}
        return mav;
    }


}
