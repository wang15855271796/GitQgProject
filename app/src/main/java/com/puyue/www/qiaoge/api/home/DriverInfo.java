package com.puyue.www.qiaoge.api.home;

import java.util.List;

/**
 * Created by ${王涛} on 2020/1/5
 */
public class DriverInfo {

    /**
     * code : 1
     * message : 成功
     * data : [{"driverName":"刘亮","orderId":"0200104094430","icon":" https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/other%402x.png","sendTime":"2019.6.3","driverPhone":18657104842}]
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "DriverInfo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", error=" + error +
                ", success=" + success +
                ", data=" + data +
                '}';
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
         * driverName : 刘亮
         * orderId : 0200104094430
         * icon :  https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/apps/icon/other%402x.png
         * sendTime : 2019.6.3
         * driverPhone : 18657104842
         */

        private String driverName;
        private String orderId;
        private String icon;
        private String sendTime;
        private long driverPhone;

        @Override
        public String toString() {
            return "DataBean{" +
                    "driverName='" + driverName + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", icon='" + icon + '\'' +
                    ", sendTime='" + sendTime + '\'' +
                    ", driverPhone=" + driverPhone +
                    '}';
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public long getDriverPhone() {
            return driverPhone;
        }

        public void setDriverPhone(long driverPhone) {
            this.driverPhone = driverPhone;
        }
    }
}
