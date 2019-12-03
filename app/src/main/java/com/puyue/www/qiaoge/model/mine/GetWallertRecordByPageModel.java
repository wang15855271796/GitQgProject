package com.puyue.www.qiaoge.model.mine;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/28.
 */

public class GetWallertRecordByPageModel {

    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":1,"total":6,"list":[{"flowRecordTypeName":"充值","createDate":"2018-05-02 14:38:36","amount":"100.00","walletRecordChannelType":"支付宝"},{"flowRecordTypeName":"提现(提现中)","createDate":"2018-04-28 14:41:01","amount":"100.00","walletRecordChannelType":"支付宝"},{"flowRecordTypeName":"提现(提现中)","createDate":"2018-04-28 14:31:37","amount":"100.00","walletRecordChannelType":"银行卡"},{"flowRecordTypeName":"提现(提现中)","createDate":"2018-04-28 14:31:14","amount":"100.00","walletRecordChannelType":"支付宝"},{"flowRecordTypeName":"充值","createDate":"2018-04-28 11:07:00","amount":"100.00","walletRecordChannelType":"支付宝"},{"flowRecordTypeName":"充值","createDate":"2018-04-28 11:05:46","amount":"100.00","walletRecordChannelType":"支付宝"}],"hasPrePage":false,"hasNextPage":false}
     * success : true
     * error : false
     */

    public int code;
    public String message;
    public DataBean data;
    public boolean success;
    public boolean error;

    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * startRow : 0
         * pages : 1
         * total : 6
         * list : [{"flowRecordTypeName":"充值","createDate":"2018-05-02 14:38:36","amount":"100.00","walletRecordChannelType":"支付宝"},{"flowRecordTypeName":"提现(提现中)","createDate":"2018-04-28 14:41:01","amount":"100.00","walletRecordChannelType":"支付宝"},{"flowRecordTypeName":"提现(提现中)","createDate":"2018-04-28 14:31:37","amount":"100.00","walletRecordChannelType":"银行卡"},{"flowRecordTypeName":"提现(提现中)","createDate":"2018-04-28 14:31:14","amount":"100.00","walletRecordChannelType":"支付宝"},{"flowRecordTypeName":"充值","createDate":"2018-04-28 11:07:00","amount":"100.00","walletRecordChannelType":"支付宝"},{"flowRecordTypeName":"充值","createDate":"2018-04-28 11:05:46","amount":"100.00","walletRecordChannelType":"支付宝"}]
         * hasPrePage : false
         * hasNextPage : false
         */

        public int pageNum;
        public int pageSize;
        public int startRow;
        public int pages;
        public int total;
        public boolean hasPrePage;
        public boolean hasNextPage;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * flowRecordTypeName : 充值
             * createDate : 2018-05-02 14:38:36
             * amount : 100.00
             * walletRecordChannelType : 支付宝
             *  "type": 2,
             *         "id": 1806
             */

            public String flowRecordTypeName;
            public String createDate;
            public String amount;
            public int recordType;
            public String walletRecordChannelType;
            public int type;
            public int id;

            public String iconUrl;
        }
    }
}
