package com.guo.bill.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public enum CardOperationEnum {
    MINE_PREPAID("1","煤矿预付"),
    CUS_PAID("2","客户付款"),
    INCOME("3", "收入"),
    EXPEND("4", "支出");

    private String code;
    private String desc;

    private CardOperationEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }

    public static String getValue(String key) {
        for (CardOperationEnum pm : CardOperationEnum.values()) {
            if (pm.getCode().equals(key)) {
                return pm.getDesc();
            }
        }
        return null;
    }

    public static Map toMap(){
        Map<String,String> map = new HashMap<String, String>();
        for(CardOperationEnum cardOperationEnum : CardOperationEnum.values())  {
            map.put(cardOperationEnum.getCode(), cardOperationEnum.getDesc());
        }

        return map;
    }

}
