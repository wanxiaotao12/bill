package com.guo.bill.enumtype;


/**
 *
 */
public enum OperationEnum {

    INCOME("1", "收入"),
    EXPEND("2", "支出");

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

}
