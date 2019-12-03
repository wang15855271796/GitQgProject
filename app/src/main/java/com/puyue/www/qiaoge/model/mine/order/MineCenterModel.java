package com.puyue.www.qiaoge.model.mine.order;

/**
 * Created by ${王文博} on 2019/6/12
 */
public class MineCenterModel {

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
