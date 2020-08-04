package com.puyue.www.qiaoge.model;

/**
 * Created by ${王涛} on 2020/7/13
 */
public class ExCouponModel {


    /**
     * code : 1
     * message : 成功
     * data : null
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private Object data;
    private boolean error;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
