package com.puyue.www.qiaoge.model.home;

/**
 * Created by ${王文博} on 2019/8/17
 */
public class GetSumPriceModel {


    /**
     * code : 1
     * message : 成功
     * data : {"out":9125.79,"in":597}
     * success : true
     * error : false
     */

    private int code;
    private String message;
    private DataBean data;
    private boolean success;
    private boolean error;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public static class DataBean {
        /**
         * out : 9125.79
         * in : 597
         */

        private double out;
        private double in;

        public double getOut() {
            return out;
        }

        public void setOut(double out) {
            this.out = out;
        }

        public double getIn() {
            return in;
        }

        public void setIn(double in) {
            this.in = in;
        }
    }
}
