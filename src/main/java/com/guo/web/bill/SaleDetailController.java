package com.guo.web.bill;

import com.guo.bill.dao.DictionaryDao;
import com.guo.bill.dao.SaleDetailDao;
import com.guo.bill.enumtype.DictionaryEnum;
import com.guo.bill.enumtype.StateEnum;
import com.guo.bill.pojo.Dictionary;
import com.guo.bill.pojo.SaleDetail;
import com.guo.bill.pojo.SaleDetailQuery;
import com.guo.bill.service.SaleDetailService;
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
import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：</b>ShipperbillController<br>
 *
 * @author：<a href="mailto:*@jd.com">系统生成</a>
 * @since：2014年04月18日 11时51分47秒 星期五
 * @version:1.0
 */
@Controller
@RequestMapping("/saledetail")
public class SaleDetailController extends BaseController {

    @Autowired
    private SaleDetailDao saleDetailDao;

    @Autowired
    private DictionaryDao     dictionaryDao;
    @Autowired
    private SaleDetailService saleDetailService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView index(@ModelAttribute SaleDetailQuery query,
                              @RequestParam(required = false, value = "buyDatetimeStr") String buyDatetimeStr,
                              @RequestParam(required = false, value = "pageNo",
                                      defaultValue = "1") int pageNo,
                              @RequestParam(required = false, value = "pageSize",
                                      defaultValue = "10") int pageSize) {

        PageQuery<SaleDetailQuery> pageQuery = new PageQuery<SaleDetailQuery>();
        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        if (query != null) {

            if (query.getState() == null) {
                query.setState(StateEnum.NORMAL.getCode());
            }

        }
        ModelAndView mav = new ModelAndView();
        pageQuery.setQuery(query);
        mav.addObject("query", query);
        mav.setViewName("bill/sale/index");
        mav.addObject("pageInfos",
                SystemTools.convertPaginatedList(saleDetailService.searchPage(pageQuery)));
        return mav;
    }

    /**
     * 详情
     *
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam(required = true, value = "id") Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bill/sale/detail");
        SaleDetail saleDetail = saleDetailDao.findById(id);
        mav.addObject("saleDetail", saleDetail);
        return mav;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value = "/toAdd", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView toAdd(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        List<Dictionary> mineList = dictionaryDao.findByType(DictionaryEnum.MINE);
        if (CollectionUtils.isNotEmpty(mineList)) {
            mav.addObject("mineList", mineList);
        }

        List<Dictionary> cusList = dictionaryDao.findByType(DictionaryEnum.CUS_NO);
        if (CollectionUtils.isNotEmpty(cusList)) {
            mav.addObject("cusList", cusList);
        }

        mav.setViewName("bill/sale/add");
        return mav;
    }

    /**
     * 保存信息
     *
     * @return
     */
    @RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView add(@ModelAttribute SaleDetail saleDetail) {
        Dictionary cus = dictionaryDao.findByTypeAndCode(DictionaryEnum.CUS_NO, saleDetail.getCustomerNo());
        Dictionary mine = dictionaryDao.findByTypeAndCode(DictionaryEnum.MINE, saleDetail.getMineNo());
        saleDetail.setCustomerName(cus.getName());
        saleDetail.setMineName(mine.getName());
        saleDetail.setTotalAmount(saleDetail.getUnitPrice().multiply(saleDetail.getOnnage()));

        saleDetail.setTaxCostTotalAmount(saleDetail.getTaxCostUnitPrice().multiply(saleDetail.getOnnage()));

        saleDetail.setTaxPoint(saleDetail.getTaxPoint().divide(new BigDecimal(100)));

        BigDecimal notaxCostUnitPrice = (new BigDecimal(1)).subtract(saleDetail.getTaxPoint())
                .multiply(saleDetail.getTaxCostUnitPrice());
        saleDetail.setNotaxCostUnitPrice(notaxCostUnitPrice);
        saleDetail.setNotaxCostTotalAmount(notaxCostUnitPrice.multiply(saleDetail.getOnnage()));

        saleDetail.setTaxAmount(saleDetail.getTaxCostTotalAmount().multiply(saleDetail.getTaxPoint()));

        saleDetailDao.add(saleDetail);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/saledetail/index.do");
        return mav;
    }

    /**
     * 跳转到编辑页面
     *
     * @return
     */
    //    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    //    public ModelAndView toEdit(@RequestParam(required = true, value = "id") Integer id) {
    //        ModelAndView mav = new ModelAndView();
    //        mav.setViewName("bill/shipperbillEdit");
    //        GenericResult<SaleDetail> result = shipperbillService.findByPriKey(id);
    //        mav.addObject("shipperbill", result.getValue());
    //        return mav;
    //    }

    /**
     * 编辑
     *
     * @return
     */
    //    @RequestMapping(value = "/shipperbillEdit", method = { RequestMethod.GET, RequestMethod.POST })
    //    public ModelAndView shipperbillEdit(@ModelAttribute SaleDetail shipperbill) {
    //        BasicResult result = shipperbillService.modifyShipperbill(shipperbill);
    //        ModelAndView mav = new ModelAndView();
    //        if ("FPXS0000".equals(result.getCode())) {
    //            mav.setViewName("redirect:/shipperbill/shipperbillIndex.do");
    //        } else {
    //            mav.setViewName("bill/shipperbillEdit");
    //            mav.addObject("shipperbill", shipperbill);
    //        }
    //        return mav;
    //    }

    /**
     * 删除
     *
     * @return
     */
        @RequestMapping(value = "del", method = RequestMethod.GET)
        public ModelAndView del(@RequestParam(required = true, value = "id") Integer id) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/saledetial/index.do");
//            BasicResult result = shipperbillService.deleteByPriKey(id);
//            if (!"FPXS0000".equals(result.getCode())) {
//                //TODO 删除失败
//            }
            return mav;
        }

}
