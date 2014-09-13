package com.guo.bill.enumtype;


/**
 *
 */
public enum StateEnum {

    NORMAL("1", "正常"),
    DELETE("0", "删除");

    private String code;
    private String desc;

    private StateEnum(String code, String desc) {
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
        for (StateEnum pm : StateEnum.values()) {
            if (pm.getCode().equals(key)) {
                return pm.getDesc();
            }
        }
        return null;
    }

}
