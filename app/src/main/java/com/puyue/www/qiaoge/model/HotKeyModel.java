package com.puyue.www.qiaoge.model;

import java.util.List;

/**
 * Created by ${王涛} on 2020/6/30
 */
public class HotKeyModel {


    /**
     * code : 1
     * message : 成功
     * data : []
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String keyBegin;
        private String keyEnd;
        private String key;

        public String getKeyBegin() {
            return keyBegin;
        }

        public void setKeyBegin(String keyBegin) {
            this.keyBegin = keyBegin;
        }

        public String getKeyEnd() {
            return keyEnd;
        }

        public void setKeyEnd(String keyEnd) {
            this.keyEnd = keyEnd;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
