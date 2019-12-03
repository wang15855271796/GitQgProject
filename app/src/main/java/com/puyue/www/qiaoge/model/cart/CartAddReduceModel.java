package com.puyue.www.qiaoge.model.cart;

public class CartAddReduceModel {

    /**
     * code : -1
     * error : true
     * message : 商品库存不足
     * success : false
     */

    private int code;
    private boolean error;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
