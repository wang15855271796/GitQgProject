package com.puyue.www.qiaoge.model.cart;

/**
 * Created by ${王文博} on 2019/4/23
 */
public class CartPointModel {

    /**
     * code : 1
     * message : 成功
     * data : {"orderAmount":"25","rechargeAmount":"1000","role":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/8cf54df1b0bb45dcb57ce374ac6a6109.png"}
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
         * orderAmount : 25
         * rechargeAmount : 1000
         * role : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/8cf54df1b0bb45dcb57ce374ac6a6109.png
         */

        private String orderAmount;
        private String rechargeAmount;
        private String role;

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getRechargeAmount() {
            return rechargeAmount;
        }

        public void setRechargeAmount(String rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
