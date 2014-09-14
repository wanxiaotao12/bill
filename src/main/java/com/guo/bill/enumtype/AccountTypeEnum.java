package com.guo.bill.enumtype;


/**
 *
 */
public enum AccountTypeEnum {

    CARD("1", "卡"),
    CUSTOMER("2", "客户");

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

}
