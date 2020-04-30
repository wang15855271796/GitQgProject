package com.puyue.www.qiaoge.event;

import java.util.List;

/**
 * Created by ${王涛} on 2020/4/20
 */
public class TurnModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"num":1,"poolNo":"满0元减11元"},{"num":2,"poolNo":"满0元减2.02元"},{"num":3,"poolNo":"满0元减1.1元"},{"num":4,"poolNo":"6元无门槛消费券"},{"num":5,"poolNo":"满0元减0.01元"}]
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
        return "TurnModel{" +
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
         * num : 1
         * poolNo : 满0元减11元
         */

        private int num;
        private String poolNo;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPoolNo() {
            return poolNo;
        }

        public void setPoolNo(String poolNo) {
            this.poolNo = poolNo;
        }
    }
}
