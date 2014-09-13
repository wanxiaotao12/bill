package com.guo.common;

/**
 * Created by IntelliJ IDEA.
 * User: wan
 * Date: 14-9-3
 * Time: 上午1:38
 * To change this template use File | Settings | File Templates.
 */
public class BasicResult<T> {
    private String code;
    private String message;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
