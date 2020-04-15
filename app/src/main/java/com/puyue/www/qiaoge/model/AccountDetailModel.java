package com.puyue.www.qiaoge.model;

/**
 * Created by ${王涛} on 2019/12/18
 */
public class AccountDetailModel {

    /**
     * code : 1
     * message : 成功
     * data : {"subId":23,"name":"wjh","phone":"18123564251","inPoint":1,"inBalance":1,"inGift":1}
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
         * subId : 23
         * name : wjh
         * phone : 18123564251
         * inPoint : 1
         * inBalance : 1
         * inGift : 1
         */

        private int subId;
        private String name;
        private String phone;
        private int inPoint;
        private int inBalance;
        private int inGift;
        private String amountLimit;
        private String amount;
        private String notification;
        private String warnAmount;

        public String getAmountLimit() {
            return amountLimit;
        }

        public void setAmountLimit(String amountLimit) {
            this.amountLimit = amountLimit;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getNotification() {
            return notification;
        }

        public void setNotification(String notification) {
            this.notification = notification;
        }

        public String getWarnAmount() {
            return warnAmount;
        }

        public void setWarnAmount(String warnAmount) {
            this.warnAmount = warnAmount;
        }

        public int getSubId() {
            return subId;
        }

        public void setSubId(int subId) {
            this.subId = subId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getInPoint() {
            return inPoint;
        }

        public void setInPoint(int inPoint) {
            this.inPoint = inPoint;
        }

        public int getInBalance() {
            return inBalance;
        }

        public void setInBalance(int inBalance) {
            this.inBalance = inBalance;
        }

        public int getInGift() {
            return inGift;
        }

        public void setInGift(int inGift) {
            this.inGift = inGift;
        }
    }
}
