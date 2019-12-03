package com.puyue.www.qiaoge.model.mine.order;

/**
 * Created by ${daff}
 * on 2018/10/26
 * 备注
 */
public class CartGetReductModel {

    /**
     * code : 1
     * message : 成功
     * data : 已优惠1.0元再购10元,立享满20.0减2.0元活动
     * success : true
     * error : false
     */

    private int code;
    private String message;
    private String data;
    private boolean success;
    private boolean error;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
