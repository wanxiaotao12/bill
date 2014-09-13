package com.guo.web.bill;

import com.guo.bill.manager.MineManager;
import com.guo.bill.pojo.Mine;
import com.guo.bill.pojo.Profitbill;
import com.guo.bill.pojo.ProfitbillQuery;
import com.guo.bill.service.ProfitbillService;
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
import java.util.List;

/**
 * 描述：</b>ProfitbillController<br>
 *
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五
 * @version:1.0
 */
@Controller
@RequestMapping("/profitbill")
public class ProfitbillController extends BaseController {
    @Autowired
    private ProfitbillService profitbillService;

    @Autowired
    private  MineManager mineManager;


    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "profitbillIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView profitbillIndex(@ModelAttribute ProfitbillQuery query,
                                        @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
                                        @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
        PageQuery<ProfitbillQuery> pageQuery = new PageQuery<ProfitbillQuery>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        ModelAndView mav = new ModelAndView();
        pageQuery.setQuery(query);
        mav.addObject("query", query);
        mav.setViewName("bill/profitbillIndex");
        mav.addObject("pageInfos", SystemTools.convertPaginatedList(profitbillService.searchPageProfitbill(pageQuery)));

        log.debug("******************************************************************");
        return mav;
    }

    /**
     * 详情
     *
     * @return
     */
    @RequestMapping(value = "profitbillDetail", method = RequestMethod.GET)
    public ModelAndView profitbillDetail(@RequestParam(required = true, value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bill/profitbillDetail");
        GenericResult<Profitbill> result = profitbillService.findByPriKey(id);
        mav.addObject("profitbill", result.getValue());
        return mav;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView toAdd(HttpServletRequest request, ModelMap model) {
        List<Mine> mineList = mineManager.searchMineList(null);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("bill/profitbillAdd");

        mav.addObject("mineList", mineList);


        return mav;
    }

    /**
     * 保存信息
     *
     * @return
     */
    @RequestMapping(value = "/profitbillAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView profitbillAdd(@ModelAttribute Profitbill profitbill) {
        GenericResult<String> result = profitbillService.addProfitbill(profitbill);
        ModelAndView mav = new ModelAndView();
        if ("FPXS0000".equals(result.getCode())) {
            mav.setViewName("redirect:/profitbill/profitbillIndex.do");
        } else {
            mav.setViewName("bill/profitbillAdd");
            mav.addObject("profitbill", profitbill);
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
        mav.setViewName("bill/profitbillEdit");
        GenericResult<Profitbill> result = profitbillService.findByPriKey(id);
        mav.addObject("profitbill", result.getValue());
        return mav;
    }

    /**
     * 编辑
     *
     * @return
     */
    @RequestMapping(value = "/profitbillEdit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView profitbillEdit(@ModelAttribute Profitbill profitbill) {
        BasicResult result = profitbillService.modifyProfitbill(profitbill);
        ModelAndView mav = new ModelAndView();
        if ("FPXS0000".equals(result.getCode())) {
            mav.setViewName("redirect:/profitbill/profitbillIndex.do");
        } else {
            mav.setViewName("bill/profitbillEdit");
            mav.addObject("profitbill", profitbill);
        }
        return mav;
    }


    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "profitbillDelete", method = RequestMethod.GET)
    public ModelAndView profitbillDelete(@RequestParam(required = true, value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/profitbill/profitbillIndex.do");
        BasicResult result = profitbillService.deleteByPriKey(id);
        if (!"FPXS0000".equals(result.getCode())) {
            //TODO 删除失败
        }
        return mav;
    }


}
