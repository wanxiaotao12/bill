package com.guo.bill.enumtype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public enum AccountTypeEnum {
    MINE("1", "煤矿"),
    CARD("2", "银行卡"),
    CUSTOMER("3", "客户");

    private String code;
    private String desc;

    private AccountTypeEnum(String code, String desc) {
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
        for (AccountTypeEnum pm : AccountTypeEnum.values()) {
            if (pm.getCode().equals(key)) {
                return pm.getDesc();
            }
        }
        return null;
    }

    public static Map toMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
            map.put(accountTypeEnum.getCode(), accountTypeEnum.getDesc());
        }

        return map;
    }

    public static List toList() {
        List list = new ArrayList();
        for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("code", accountTypeEnum.getCode());
            map.put("desc", accountTypeEnum.getDesc());

            list.add(map);
        }

        return list;
    }

}
