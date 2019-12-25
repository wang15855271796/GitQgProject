package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王涛} on 2019/12/24
 */
public class TabModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"tabId":0,"title":"进行中","activeType":11}]
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
        /**
         * tabId : 0
         * title : 进行中
         * activeType : 11
         */

        private int tabId;
        private String title;
        private int activeType;

        public int getTabId() {
            return tabId;
        }

        public void setTabId(int tabId) {
            this.tabId = tabId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getActiveType() {
            return activeType;
        }

        public void setActiveType(int activeType) {
            this.activeType = activeType;
        }
    }
}
