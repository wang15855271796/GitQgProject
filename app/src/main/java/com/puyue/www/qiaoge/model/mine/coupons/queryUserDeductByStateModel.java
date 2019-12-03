package com.puyue.www.qiaoge.model.mine.coupons;

import java.util.List;

/**
 * Created by ${daff} on 2018/9/20
 */
public class queryUserDeductByStateModel{


    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":2,"total":14,"list":[{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000015"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000014"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000013"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000012"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000010"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000011"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000008"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000009"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000006"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000007"}],"hasPrePage":false,"hasNextPage":true}
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
         * pageNum : 1
         * pageSize : 10
         * startRow : 0
         * pages : 2
         * total : 14
         * list : [{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000015"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000014"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000013"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000012"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000010"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000011"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000008"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000009"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000006"},{"giftName":"满100减2元","giftType":"满减券","amount":2,"amountStr":"2.00元","limitAmtStr":"满100.00元可用","limitAmt":100,"applyFrom":"人工积分兑换","dateTime":"2018-09-20-2018-12-20","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092000000007"}]
         * hasPrePage : false
         * hasNextPage : true
         */

        private int pageNum;
        private int pageSize;
        private int startRow;
        private int pages;
        private int total;
        private boolean hasPrePage;
        private boolean hasNextPage;
        private List<ListBean> list;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public boolean isHasPrePage() {
            return hasPrePage;
        }

        public void setHasPrePage(boolean hasPrePage) {
            this.hasPrePage = hasPrePage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * giftName : 满100减2元
             * giftType : 满减券
             * amount : 2.0
             * amountStr : 2.00元
             * limitAmtStr : 满100.00元可用
             * limitAmt : 100.0
             * applyFrom : 人工积分兑换
             * dateTime : 2018-09-20-2018-12-20
             * role : []
             * overTimePic :
             * usedPic :
             * unAblePic :
             * state : ENABLED
             * giftDetailNo : 112018092000000015
             */

            private String giftName;
            private String giftType;
            private String amount;
            private String amountStr;
            private String limitAmtStr;
            private double limitAmt;
            private String applyFrom;
            private String dateTime;
            private String overTimePic;
            private String usedPic;
            private String unAblePic;
            private String state;
            private String giftDetailNo;
            private List<String> role;

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }

            public String getGiftType() {
                return giftType;
            }

            public void setGiftType(String giftType) {
                this.giftType = giftType;
            }

            public  String  getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAmountStr() {
                return amountStr;
            }

            public void setAmountStr(String amountStr) {
                this.amountStr = amountStr;
            }

            public String getLimitAmtStr() {
                return limitAmtStr;
            }

            public void setLimitAmtStr(String limitAmtStr) {
                this.limitAmtStr = limitAmtStr;
            }

            public double getLimitAmt() {
                return limitAmt;
            }

            public void setLimitAmt(double limitAmt) {
                this.limitAmt = limitAmt;
            }

            public String getApplyFrom() {
                return applyFrom;
            }

            public void setApplyFrom(String applyFrom) {
                this.applyFrom = applyFrom;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public String getOverTimePic() {
                return overTimePic;
            }

            public void setOverTimePic(String overTimePic) {
                this.overTimePic = overTimePic;
            }

            public String getUsedPic() {
                return usedPic;
            }

            public void setUsedPic(String usedPic) {
                this.usedPic = usedPic;
            }

            public String getUnAblePic() {
                return unAblePic;
            }

            public void setUnAblePic(String unAblePic) {
                this.unAblePic = unAblePic;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getGiftDetailNo() {
                return giftDetailNo;
            }

            public void setGiftDetailNo(String giftDetailNo) {
                this.giftDetailNo = giftDetailNo;
            }

            public List<String> getRole() {
                return role;
            }

            public void setRole(List<String> role) {
                this.role = role;
            }
        }
    }
}
