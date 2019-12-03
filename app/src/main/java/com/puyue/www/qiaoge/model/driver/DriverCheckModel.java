package com.puyue.www.qiaoge.model.driver;

/**
 * Created by ${王文博} on 2019/6/17
 */
public class DriverCheckModel {


    /**
     * code : 1
     * message : 成功
     * data : {"driverIsRight":true,"pickNumber":"A1","address":"浙江省杭州市拱墅区和睦xin","driverName":null,"orderId":"20190516113101-ecae"}
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
         * driverIsRight : true
         * pickNumber : A1
         * address : 浙江省杭州市拱墅区和睦xin
         * driverName : null
         * orderId : 20190516113101-ecae
         */

        private boolean driverIsRight;
        private String pickNumber;
        private String address;
        private String driverName;
        private String orderId;

        public boolean isDriverIsRight() {
            return driverIsRight;
        }

        public void setDriverIsRight(boolean driverIsRight) {
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
    }
}
