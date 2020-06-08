package com.puyue.www.qiaoge.model;

import java.io.Serializable;

/**
 * Created by ${王涛} on 2020/6/4
 */
public class CancleModel implements Serializable {

    /**
     * code : 1
     * message : 成功
     * data : {"userId":1188,"weekOrderNum":1,"noOrderNum":7,"subUserNum":1,"balance":9.9999606246E8,"deductNum":6,"point":3933}
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

    public static class DataBean implements Serializable{
        /**
         * userId : 1188
         * weekOrderNum : 1
         * noOrderNum : 7
         * subUserNum : 1
         * balance : 9.9999606246E8
         * deductNum : 6
         * point : 3933
         */

        private int userId;
        private int weekOrderNum;
        private int noOrderNum;
        private int subUserNum;
        private double balance;
        private int deductNum;
        private int point;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWeekOrderNum() {
            return weekOrderNum;
        }

        public void setWeekOrderNum(int weekOrderNum) {
            this.weekOrderNum = weekOrderNum;
        }

        public int getNoOrderNum() {
            return noOrderNum;
        }

        public void setNoOrderNum(int noOrderNum) {
            this.noOrderNum = noOrderNum;
        }

        public int getSubUserNum() {
            return subUserNum;
        }

        public void setSubUserNum(int subUserNum) {
            this.subUserNum = subUserNum;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public int getDeductNum() {
            return deductNum;
        }

        public void setDeductNum(int deductNum) {
            this.deductNum = deductNum;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }
    }
}
