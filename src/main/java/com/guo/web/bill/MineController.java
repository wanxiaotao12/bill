package com.guo.web.bill;

import com.guo.bill.pojo.Mine;
import com.guo.bill.pojo.MineQuery;
import com.guo.bill.service.MineService;
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
 * 描述：</b>MineController<br>
 *
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五
 * @version:1.0
 */
@Controller
@RequestMapping("/mine")
public class MineController extends BaseController {
    @Autowired
    private MineService mineService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "mineIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mineIndex(@ModelAttribute MineQuery query,
                                  @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
                                  @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) {
        PageQuery<MineQuery> pageQuery = new PageQuery<MineQuery>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        ModelAndView mav = new ModelAndView();
        pageQuery.setQuery(query);
        mav.addObject("query", query);
        mav.setViewName("bill/mineIndex");
        mav.addObject("pageInfos", SystemTools.convertPaginatedList(mineService.searchPageMine(pageQuery)));
        return mav;
    }

    /**
     * 详情
     *
     * @return
     */
    @RequestMapping(value = "mineDetail", method = RequestMethod.GET)
    public ModelAndView mineDetail(@RequestParam(required = true, value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bill/mineDetail");
        GenericResult<Mine> result = mineService.findByPriKey(id);
        mav.addObject("mine", result.getValue());
        return mav;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdd(HttpServletRequest request, ModelMap model) {
        return "bill/mineAdd";
    }

    /**
     * 保存信息
     *
     * @return
     */
    @RequestMapping(value = "/mineAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mineAdd(@ModelAttribute Mine mine) {
        GenericResult<String> result = mineService.addMine(mine);
        ModelAndView mav = new ModelAndView();
        if ("FPXS0000".equals(result.getCode())) {
            mav.setViewName("redirect:/mine/mineIndex.do");
        } else {
            mav.setViewName("bill/mineAdd");
            mav.addObject("mine", mine);
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
        mav.setViewName("bill/mineEdit");
        GenericResult<Mine> result = mineService.findByPriKey(id);
        mav.addObject("mine", result.getValue());
        return mav;
    }

    /**
     * 编辑
     *
     * @return
     */
    @RequestMapping(value = "/mineEdit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mineEdit(@ModelAttribute Mine mine) {
        BasicResult result = mineService.modifyMine(mine);
        ModelAndView mav = new ModelAndView();
        if ("FPXS0000".equals(result.getCode())) {
            mav.setViewName("redirect:/mine/mineIndex.do");
        } else {
            mav.setViewName("bill/mineEdit");
            mav.addObject("mine", mine);
        }
        return mav;
    }


    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "mineDelete", method = RequestMethod.GET)
    public ModelAndView mineDelete(@RequestParam(required = true, value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/mine/mineIndex.do");
        BasicResult result = mineService.deleteByPriKey(id);
        if (!"FPXS0000".equals(result.getCode())) {
            //TODO 删除失败
        }
        return mav;
    }


}
