package com.puyue.www.qiaoge.model.driver;

/**
 * Created by ${王文博} on 2019/6/17
 */
public class DriverScanOrderModel {


    /**
     * code : 1
     * message : 成功
     * data : {"driverIsRight":null,"pickNumber":"E3","address":"浙江省杭州市余杭区ｖｈ","driverName":null,"orderId":null}
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * driverIsRight : null
         * pickNumber : E3
         * address : 浙江省杭州市余杭区ｖｈ
         * driverName : null
         * orderId : null
         */

        private Object driverIsRight;
        private String pickNumber;
        private String address;
        private Object driverName;
        private Object orderId;

        public Object getDriverIsRight() {
            return driverIsRight;
        }

        public void setDriverIsRight(Object driverIsRight) {
            this.driverIsRight = driverIsRight;
        }

        public String getPickNumber() {
            return pickNumber;
        }

        public void setPickNumber(String pickNumber) {
            this.pickNumber = pickNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getDriverName() {
            return driverName;
        }

        public void setDriverName(Object driverName) {
            this.driverName = driverName;
        }

        public Object getOrderId() {
            return orderId;
        }

        public void setOrderId(Object orderId) {
            this.orderId = orderId;
        }
    }
}
