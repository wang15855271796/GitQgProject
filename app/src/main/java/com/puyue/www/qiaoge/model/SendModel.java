package com.puyue.www.qiaoge.model;

/**
 * Created by ${王涛} on 2020/5/25
 */
public class SendModel {

    /**
     * code : 1
     * message : 成功
     * data : 565642
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean data;
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

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
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
