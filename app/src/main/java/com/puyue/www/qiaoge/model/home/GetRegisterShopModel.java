package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/6
 */
public class GetRegisterShopModel {


    /**
     * code : 1
     * message : 成功
     * data : [{"id":1,"name":"烧烤","userNum":0,"orderNum":0,"saleAmount":"0"},{"id":2,"name":"麻辣烫","userNum":0,"orderNum":0,"saleAmount":"0"}]
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
         * id : 1
         * name : 烧烤
         * userNum : 0
         * orderNum : 0
         * saleAmount : 0
         */

        private int id;
        private String name;
        private int userNum;
        private int orderNum;
        private String saleAmount;
        private boolean flag;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUserNum() {
            return userNum;
        }

        public void setUserNum(int userNum) {
            this.userNum = userNum;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getSaleAmount() {
            return saleAmount;
        }

        public void setSaleAmount(String saleAmount) {
            this.saleAmount = saleAmount;
        }
    }
}
