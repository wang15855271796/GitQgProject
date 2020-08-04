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
     * data : {"nextMonth":3,"nextYear":2020,"nowMonth":1,"nowYear":2020,"lastMonth":null,"lastYear":null,"inAmt":"0","outAmt":"1.98","records":[{"flowRecordTypeName":"购物消费","createDate":"2020-01-14 14:46:06","amount":"-1.98","walletRecordChannelType":"支付宝","recordType":1,"userId":562,"subUserId":562,"type":1,"id":249579,"iconUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/6115d2affece4b23bd1b311c3e8e2436.png"}]}
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
         * nextMonth : 3
         * nextYear : 2020
         * nowMonth : 1
         * nowYear : 2020
         * lastMonth : null
         * lastYear : null
         * inAmt : 0
         * outAmt : 1.98
         * records : [{"flowRecordTypeName":"购物消费","createDate":"2020-01-14 14:46:06","amount":"-1.98","walletRecordChannelType":"支付宝","recordType":1,"userId":562,"subUserId":562,"type":1,"id":249579,"iconUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/6115d2affece4b23bd1b311c3e8e2436.png"}]
         */

        private int nextMonth;
        private int nextYear;
        private int nowMonth;
        private int nowYear;
        private String lastMonth;
        private String lastYear;
        private String inAmt;
        private String outAmt;
        private List<RecordsBean> records;

        public int getNextMonth() {
            return nextMonth;
        }

        public void setNextMonth(int nextMonth) {
            this.nextMonth = nextMonth;
        }

        public int getNextYear() {
            return nextYear;
        }

        public void setNextYear(int nextYear) {
            this.nextYear = nextYear;
        }

        public int getNowMonth() {
            return nowMonth;
        }

        public void setNowMonth(int nowMonth) {
            this.nowMonth = nowMonth;
        }

        public int getNowYear() {
            return nowYear;
        }

        public void setNowYear(int nowYear) {
            this.nowYear = nowYear;
        }

        public String getLastMonth() {
            return lastMonth;
        }

        public void setLastMonth(String lastMonth) {
            this.lastMonth = lastMonth;
        }

        public String getLastYear() {
            return lastYear;
        }

        public void setLastYear(String lastYear) {
            this.lastYear = lastYear;
        }

        public String getInAmt() {
            return inAmt;
        }

        public void setInAmt(String inAmt) {
            this.inAmt = inAmt;
        }

        public String getOutAmt() {
            return outAmt;
        }

        public void setOutAmt(String outAmt) {
            this.outAmt = outAmt;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * flowRecordTypeName : 购物消费
             * createDate : 2020-01-14 14:46:06
             * amount : -1.98
             * walletRecordChannelType : 支付宝
             * recordType : 1
             * userId : 562
             * subUserId : 562
             * type : 1
             * id : 249579
             * iconUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/6115d2affece4b23bd1b311c3e8e2436.png
             */
            private String dateTime;
            private String flowRecordTypeName;
            private String createDate;
            private String amount;
            private String walletRecordChannelType;
            private int recordType;
            private int userId;
            private int subUserId;
            private int type;
            private int id;
            private String iconUrl;

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public String getFlowRecordTypeName() {
                return flowRecordTypeName;
            }

            public void setFlowRecordTypeName(String flowRecordTypeName) {
                this.flowRecordTypeName = flowRecordTypeName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getWalletRecordChannelType() {
                return walletRecordChannelType;
            }

            public void setWalletRecordChannelType(String walletRecordChannelType) {
                this.walletRecordChannelType = walletRecordChannelType;
            }

            public int getRecordType() {
                return recordType;
            }

            public void setRecordType(int recordType) {
                this.recordType = recordType;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getSubUserId() {
                return subUserId;
            }

            public void setSubUserId(int subUserId) {
                this.subUserId = subUserId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }
        }
    }
}
