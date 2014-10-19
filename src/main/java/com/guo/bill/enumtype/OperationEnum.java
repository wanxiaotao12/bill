package com.guo.bill.enumtype;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public enum OperationEnum {
    MINE_PREPAID("1","煤矿预付"),
    INCOME("2", "收入"),
    EXPEND("3", "支出");

    private String code;
    private String desc;

    private OperationEnum(String code, String desc) {
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
        for (OperationEnum pm : OperationEnum.values()) {
            if (pm.getCode().equals(key)) {
                return pm.getDesc();
            }
        }
        return null;
    }

    public static Map toMap(){
        Map<String,String> map = new HashMap<String, String>();
        for(OperationEnum operationEnum : OperationEnum.values())  {
            map.put(operationEnum.getCode(),operationEnum.getDesc());
        }

        return map;
    }

}
