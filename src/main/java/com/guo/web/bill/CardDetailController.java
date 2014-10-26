package com.guo.web.bill;

import com.guo.bill.CardDetailQuery;
import com.guo.bill.dao.CardDetailDao;
import com.guo.bill.dao.DictionaryDao;
import com.guo.bill.enumtype.DictionaryEnum;
import com.guo.bill.enumtype.OperationEnum;
import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.pojo.CardDetail;
import com.guo.bill.pojo.Dictionary;
import com.guo.bill.pojo.Mine;
import com.guo.bill.service.CardDetailService;
import com.guo.bill.service.MineService;
import com.guo.common.CodeEnum;
import com.guo.common.GenericResult;
import com.guo.common.ListResult;
import com.guo.common.PageQuery;
import com.guo.util.SystemTools;
import com.guo.web.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 描述：</b>DetailbillController<br>
 *
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分46秒 星期五
 * @version:1.0
 */
@Controller
@RequestMapping("/cardDetail")
public class CardDetailController extends BaseController {
    @Autowired
    private CardDetailService cardDetailService;

    @Autowired
    private DictionaryDao dictionaryDao;

    @Autowired
    private MineService mineService;

    @Autowired
    private CardDetailDao cardDetailDao;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "cardDetailIndex", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView detailbillIndex(@ModelAttribute CardDetailQuery query,
                                        @RequestParam(required = false, value = "pageNo",
                                                defaultValue = "1") int pageNo,
                                        @RequestParam(required = false, value = "pageSize",
                                                defaultValue = "10") int pageSize) {
        PageQuery<CardDetailQuery> pageQuery = new PageQuery<CardDetailQuery>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setPageSize(Integer.MAX_VALUE);
        ModelAndView mav = new ModelAndView();
        query.setState(StateEnum.NORMAL.getCode());
        pageQuery.setQuery(query);
        mav.addObject("query", query);
        mav.setViewName("bill/cardDetailIndex");
        mav.addObject("pageInfos", SystemTools.convertPaginatedList(cardDetailService.searchPageCardDetail(pageQuery)));

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sf.format(new Date());

        mav.addObject("today", today);
        mav.addObject("operationMap", OperationEnum.toMap());
        return mav;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.GET)
    public ModelAndView detailbillDetail(@RequestParam(required = true, value = "id") Integer id) {
        cardDetailService.deleteByPriKey(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/cardDetail/cardDetailIndex.do");
        return mav;
    }

    /**
     * 客户付款
     *
     * @return
     */
    @RequestMapping(value = "/toCuspay", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView toAdd(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();

        //        ListResult<Mine> mineListResult = mineService.searchMine(null);
        List<Dictionary> cusList = dictionaryDao.findByType(DictionaryEnum.CUS_NO);
        if (CollectionUtils.isNotEmpty(cusList)) {
            mav.addObject("cusList", cusList);
        }
        List<Dictionary> cardNOList = dictionaryDao.findByType(DictionaryEnum.CARD_NO);

        if (CollectionUtils.isNotEmpty(cardNOList)) {
            mav.addObject("cardList", cardNOList);
        }
        mav.setViewName("bill/card/cuspay");
        return mav;
    }

    /**
     * 添加客户付款
     *
     * @return
     */
    @RequestMapping(value = "/cuspay", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView detailbillAdd(@ModelAttribute CardDetail cardDetail,
                                      @RequestParam(required = true, value = "customerNo") String customerNo) {

        cardDetailDao.cuspay(cardDetail,customerNo);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/cardDetail/cardDetailIndex.do");

        return mav;
    }

}
