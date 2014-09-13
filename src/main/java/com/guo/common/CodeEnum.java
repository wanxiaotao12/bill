package com.guo.common;

/**
 * Created by IntelliJ IDEA.
 * User: wan
 * Date: 14-9-3
 * Time: 上午1:41
 * To change this template use File | Settings | File Templates.
 */
public class CodeEnum {
    /**
     * 成功失败
     */
    public enum SuccessOrFaildEnum {
        SUCCESS("FPXS0000", "成功"),
        FAILED("FPXE9990", "失败"),
        UNKNOWN("FPXE9999", "未知异常"),
        DUPLICATE("FPXE9998", "主键重复");
        private String code;
        private String message;

        private SuccessOrFaildEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}
