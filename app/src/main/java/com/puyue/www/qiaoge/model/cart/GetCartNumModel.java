package com.puyue.www.qiaoge.model.cart;

/**
 * Created by win7 on 2018/7/9.
 */

public class GetCartNumModel {

    /**
     * code : 1
     * message : 成功
     * data : {"num":3,"totalPrice":150,"deliveryFee":null}
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
         * num : 3
         * totalPrice : 150.0
         * deliveryFee : null
         */

        private String num;
        private String totalPrice;
        private String deliveryFee;
        private String sendAmount;

        public String getSendAmount() {
            return sendAmount;
        }

        public void setSendAmount(String sendAmount) {
            this.sendAmount = sendAmount;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getDeliveryFee() {
            return deliveryFee;
        }

        public void setDeliveryFee(String deliveryFee) {
            this.deliveryFee = deliveryFee;
        }
    }
}
