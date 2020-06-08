package com.puyue.www.qiaoge.model;

import java.util.List;

/**
 * Created by ${王涛} on 2020/6/5
 */
public class CancleReasonModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"code":"门店倒闭了，不干了","type":"APP_USER_CANCEL"},{"code":"已有其他账号，这是多余账号","type":"APP_USER_CANCEL"}]
     */

    private int code;
    private String message;
    private boolean success;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "CancleReasonModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "code='" + code + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }

        /**
         * code : 门店倒闭了，不干了
         * type : APP_USER_CANCEL
         */

        private String code;
        private String type;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
