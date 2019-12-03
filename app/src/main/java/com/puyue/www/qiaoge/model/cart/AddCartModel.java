package com.puyue.www.qiaoge.model.cart;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class AddCartModel {

    /**
     * code : 1
     * message : 成功
     * data : null
     * error : false
     * success : true
     */

    public int code;
    public String message;
    public Object data;
    public boolean error;
    public boolean success;

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
