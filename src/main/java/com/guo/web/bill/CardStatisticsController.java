package com.guo.web.bill;

import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.pojo.CardDailyStatistics;
import com.guo.bill.service.CardStatisticsService;
import com.guo.common.BasicResult;
import com.guo.common.PageQuery;
import com.guo.util.SystemTools;
import com.guo.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述：</b>DetailbillController<br>
 *
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五
 * @version:1.0
 */
@Controller
@RequestMapping("/cardStatistics")
public class CardStatisticsController extends BaseController {
    @Autowired
    private CardStatisticsService cardStatisticsService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "index", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView detailbillIndex(@ModelAttribute CardDailyStatistics query,
                                        @RequestParam(required = false, value = "pageNo",
                                                defaultValue = "1") int pageNo,
                                        @RequestParam(required = false, value = "pageSize",
                                                defaultValue = "10") int pageSize) {
        PageQuery<CardDailyStatistics> pageQuery = new PageQuery<CardDailyStatistics>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setPageSize(Integer.MAX_VALUE);
        ModelAndView mav = new ModelAndView();

        query.setState(StateEnum.NORMAL.getCode());
        pageQuery.setQuery(query);
        mav.addObject("query", query);
        mav.setViewName("bill/cardStatisticsIndex");
        mav.addObject("pageInfos", SystemTools.convertPaginatedList(cardStatisticsService.searchPageCardStatistics(
                pageQuery)));

        return mav;
    }

    /**
     * 生成日常汇总
     *
     * @return
     */
        @RequestMapping(value = "toCreateDaliyStatistics", method = RequestMethod.GET)
        public ModelAndView toCreateDaliyStatistics(@RequestParam(required = true, value = "datetime") String datetime) {

            BasicResult result = cardStatisticsService.createDailyStatistics(datetime);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/cardStatistics/index.do");
//            mav.addObject("detailbill", result.getValue());
            return mav;
        }



}
