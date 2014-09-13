package com.guo.bill.enumtype;


/**
 *
 */
public enum VarietyEnum {

    onnage_01("1", "块"),
    onnage_02("2", "大块"),
    onnage_03("3", "中块"),
    onnage_04("4", "粉"),
    onnage_05("5", "原煤");

    private String code;
    private String desc;

    private VarietyEnum(String code, String desc) {
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
        for (VarietyEnum pm : VarietyEnum.values()) {
            if (pm.getCode().equals(key)) {
                return pm.getDesc();
            }
        }
        return "未知";
    }

}
