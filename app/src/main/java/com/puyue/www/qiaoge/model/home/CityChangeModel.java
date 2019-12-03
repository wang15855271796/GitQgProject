package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王文博} on 2019/8/8
 */
public class CityChangeModel {

    /**
     * code : 1
     * message : 成功
     * data : ["杭州市","天津市","大同市","北京市","深圳市","宁波市","南充市","唐山市"]
     * success : true
     * error : false
     */

    private int code;
    private String message;
    private boolean success;
    private boolean error;
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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
