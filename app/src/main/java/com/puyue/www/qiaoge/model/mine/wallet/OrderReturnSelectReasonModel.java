package com.puyue.www.qiaoge.model.mine.wallet;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/15
 */
public class OrderReturnSelectReasonModel {

    /**
     * code : 1
     * message : 成功
     * data : ["错发","质量问题","日期/年份不符","其他","错买","漏发","其他"]
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
