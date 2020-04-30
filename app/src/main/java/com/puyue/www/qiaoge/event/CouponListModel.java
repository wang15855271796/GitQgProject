package com.puyue.www.qiaoge.event;

import java.util.List;

/**
 * Created by ${王涛} on 2020/4/17
 */
public class CouponListModel {

    /**
     * code : 1
     * message : 成功
     * data : {"id":1,"title":"恭喜您获得翘歌优惠券","content":"优惠券到账通知","gifts":[{"giftName":"6元无门槛消费券","giftType":null,"amount":null,"amountStr":"6","limitAmtStr":"满0元可用","limitAmt":null,"applyFrom":null,"dateTime":"有效期至2020-10-15","role":["全场通用"],"overTimePic":null,"usedPic":null,"unAblePic":null,"state":null,"giftDetailNo":null,"orderId":null}],"jumpType":0,"jumpUrl":null}
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
         * id : 1
         * title : 恭喜您获得翘歌优惠券
         * content : 优惠券到账通知
         * gifts : [{"giftName":"6元无门槛消费券","giftType":null,"amount":null,"amountStr":"6","limitAmtStr":"满0元可用","limitAmt":null,"applyFrom":null,"dateTime":"有效期至2020-10-15","role":["全场通用"],"overTimePic":null,"usedPic":null,"unAblePic":null,"state":null,"giftDetailNo":null,"orderId":null}]
         * jumpType : 0
         * jumpUrl : null
         */

        private int id;
        private String title;
        private String content;
        private int jumpType;
        private Object jumpUrl;
        private List<GiftsBean> gifts;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getJumpType() {
            return jumpType;
        }

        public void setJumpType(int jumpType) {
            this.jumpType = jumpType;
        }

        public Object getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(Object jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public List<GiftsBean> getGifts() {
            return gifts;
        }

        public void setGifts(List<GiftsBean> gifts) {
            this.gifts = gifts;
        }

        public static class GiftsBean {
            /**
             * giftName : 6元无门槛消费券
             * giftType : null
             * amount : null
             * amountStr : 6
             * limitAmtStr : 满0元可用
             * limitAmt : null
             * applyFrom : null
             * dateTime : 有效期至2020-10-15
             * role : ["全场通用"]
             * overTimePic : null
             * usedPic : null
             * unAblePic : null
             * state : null
             * giftDetailNo : null
             * orderId : null
             */

            private String giftName;
            private Object giftType;
            private Object amount;
            private String amountStr;
            private String limitAmtStr;
            private Object limitAmt;
            private Object applyFrom;
            private String dateTime;
            private Object overTimePic;
            private Object usedPic;
            private Object unAblePic;
            private Object state;
            private Object giftDetailNo;
            private String orderId;
            private List<String> role;

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }

            public Object getGiftType() {
                return giftType;
            }

            public void setGiftType(Object giftType) {
                this.giftType = giftType;
            }

            public Object getAmount() {
                return amount;
            }

            public void setAmount(Object amount) {
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

            public Object getLimitAmt() {
                return limitAmt;
            }

            public void setLimitAmt(Object limitAmt) {
                this.limitAmt = limitAmt;
            }

            public Object getApplyFrom() {
                return applyFrom;
            }

            public void setApplyFrom(Object applyFrom) {
                this.applyFrom = applyFrom;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public Object getOverTimePic() {
                return overTimePic;
            }

            public void setOverTimePic(Object overTimePic) {
                this.overTimePic = overTimePic;
            }

            public Object getUsedPic() {
                return usedPic;
            }

            public void setUsedPic(Object usedPic) {
                this.usedPic = usedPic;
            }

            public Object getUnAblePic() {
                return unAblePic;
            }

            public void setUnAblePic(Object unAblePic) {
                this.unAblePic = unAblePic;
            }

            public Object getState() {
                return state;
            }

            public void setState(Object state) {
                this.state = state;
            }

            public Object getGiftDetailNo() {
                return giftDetailNo;
            }

            public void setGiftDetailNo(Object giftDetailNo) {
                this.giftDetailNo = giftDetailNo;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
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
