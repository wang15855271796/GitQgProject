package com.puyue.www.qiaoge.model.home;

import com.puyue.www.qiaoge.helper.BigDecimalUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ${王文博} on 2019/8/17
 */
public class WalletDetailModel {


    /**
     * code : 1
     * message : 成功
     * data : {"flowRecordType":"购物","amount":-2,"channelType":"余额","time":"2019-08-08 15:55:37","orderId":"20190808155529-4d6a","businessNo":"20190808155529-4d6a","subUser":null,"proDesc":"2","returnMainIdList":null,"expireTime":null}
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
         * flowRecordType : 购物
         * amount : -2
         * channelType : 余额 支付方式
         * time : 2019-08-08 15:55:37
         * orderId : 20190808155529-4d6a 订单号
         * businessNo : 20190808155529-4d6a 业务id
         *iconUrl
         * subUser : null子账号
         * proDesc : 2商品说明
         * returnMainIdList : null 退货订单
         * expireTime : null会员到期时间
         */

        private String flowRecordType;
       public BigDecimal amount;
        private String channelType;
        private String time;
        private String orderId;
        private String businessNo;
        private Object subUser;
        private String proDesc;
     public List<Integer> returnMainIdList;
        private Object expireTime;

public String returnMainId;
        public String iconUrl;

        public String flowRecordTypeStr;

        public String goUrl;

        public int orderDeliveryType;
        public String orderStatus;

        public String orderStatusStr;



        public String getFlowRecordType() {
            return flowRecordType;
        }

        public void setFlowRecordType(String flowRecordType) {
            this.flowRecordType = flowRecordType;
        }



        public String getChannelType() {
            return channelType;
        }

        public void setChannelType(String channelType) {
            this.channelType = channelType;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getBusinessNo() {
            return businessNo;
        }

        public void setBusinessNo(String businessNo) {
            this.businessNo = businessNo;
        }

        public Object getSubUser() {
            return subUser;
        }

        public void setSubUser(Object subUser) {
            this.subUser = subUser;
        }

        public String getProDesc() {
            return proDesc;
        }

        public void setProDesc(String proDesc) {
            this.proDesc = proDesc;
        }



        public Object getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(Object expireTime) {
            this.expireTime = expireTime;
        }
    }
}
