package com.guo.web.bill;

import com.guo.bill.pojo.Shipperbill;
import com.guo.bill.pojo.ShipperbillQuery;
import com.guo.bill.service.ShipperbillService;
import com.guo.util.SystemTools;
import com.guo.web.BaseController;
import com.guo.common.BasicResult;
import com.guo.common.GenericResult;
import com.guo.common.PageQuery;
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
 * 描述：</b>ShipperbillController<br>
 *
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五
 * @version:1.0
 */
@Controller
@RequestMapping("/shipperbill")
public class ShipperbillController extends BaseController {
    @Autowired
    private ShipperbillService shipperbillService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "shipperbillIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView shipperbillIndex(@ModelAttribute ShipperbillQuery query,
                                         @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
                                         @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
        PageQuery<ShipperbillQuery> pageQuery = new PageQuery<ShipperbillQuery>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        ModelAndView mav = new ModelAndView();
        pageQuery.setQuery(query);
        mav.addObject("query", query);
        mav.setViewName("bill/shipperbillIndex");
        mav.addObject("pageInfos", SystemTools.convertPaginatedList(shipperbillService.searchPageShipperbill(pageQuery)));
        return mav;
    }

    /**
     * 详情
     *
     * @return
     */
    @RequestMapping(value = "shipperbillDetail", method = RequestMethod.GET)
    public ModelAndView shipperbillDetail(@RequestParam(required = true, value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bill/shipperbillDetail");
        GenericResult<Shipperbill> result = shipperbillService.findByPriKey(id);
        mav.addObject("shipperbill", result.getValue());
        return mav;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdd(HttpServletRequest request, ModelMap model) {
        return "bill/shipperbillAdd";
    }

    /**
     * 保存信息
     *
     * @return
     */
    @RequestMapping(value = "/shipperbillAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView shipperbillAdd(@ModelAttribute Shipperbill shipperbill) {
        GenericResult<String> result = shipperbillService.addShipperbill(shipperbill);
        ModelAndView mav = new ModelAndView();
        if ("FPXS0000".equals(result.getCode())) {
            mav.setViewName("redirect:/shipperbill/shipperbillIndex.do");
        } else {
            mav.setViewName("bill/shipperbillAdd");
            mav.addObject("shipperbill", shipperbill);
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
        mav.setViewName("bill/shipperbillEdit");
        GenericResult<Shipperbill> result = shipperbillService.findByPriKey(id);
        mav.addObject("shipperbill", result.getValue());
        return mav;
    }

    /**
     * 编辑
     *
     * @return
     */
    @RequestMapping(value = "/shipperbillEdit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView shipperbillEdit(@ModelAttribute Shipperbill shipperbill) {
        BasicResult result = shipperbillService.modifyShipperbill(shipperbill);
        ModelAndView mav = new ModelAndView();
        if ("FPXS0000".equals(result.getCode())) {
            mav.setViewName("redirect:/shipperbill/shipperbillIndex.do");
        } else {
            mav.setViewName("bill/shipperbillEdit");
            mav.addObject("shipperbill", shipperbill);
        }
        return mav;
    }


    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "shipperbillDelete", method = RequestMethod.GET)
    public ModelAndView shipperbillDelete(@RequestParam(required = true, value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/shipperbill/shipperbillIndex.do");
        BasicResult result = shipperbillService.deleteByPriKey(id);
        if (!"FPXS0000".equals(result.getCode())) {
            //TODO 删除失败
        }
        return mav;
    }


}
