package com.guo.util;

import com.guo.bill.enumtype.VarietyEnum;
import com.guo.bill.manager.MineManager;
import com.guo.bill.pojo.Mine;
//import com.jd.jrpcore.common.enumtype.invoice.InvoiceStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：页面下拉列表数据源获取类
 */
public class DataSourceTools {

    @Autowired
    private static MineManager mineManager;

    public String getInvoiceStatus(String invoceStatus) {
        String status = "未知";
//        InvoiceStatusEnum[] list = InvoiceStatusEnum.values();
//        for (InvoiceStatusEnum invoiceStatusEnum : list) {
//            if (invoiceStatusEnum.getCode().equals(invoceStatus)) {
//                status = invoiceStatusEnum.getDesc();
//                break;
//            }
//        }
        return status;
    }

    /**
     * 取得品种列表
     *
     * @return
     */
    public List<DataItem> getVarietyList() {
        List<DataItem> dateItemList = new ArrayList<DataItem>();
        VarietyEnum[] list = VarietyEnum.values();
        for (VarietyEnum invoiceStatusEnum : list) {
            DataItem item = new DataItem();
            item.setCode(invoiceStatusEnum.getCode());
            item.setMessage(invoiceStatusEnum.getDesc());

            dateItemList.add(item);
        }

        return dateItemList;
    }

    /**
     * 取得品种列表
     *
     * @return
     */
    public String getVarietyName(String onnageCode) {
        return  VarietyEnum.getValue(onnageCode);
    }

    public List<Mine> getMineList() {
        List<Mine> mineList = mineManager.searchMineList(null);

        return mineList;
    }

    public static void setMineManager(MineManager mineManager) {
        mineManager = mineManager;
    }
}
