package com.puyue.www.qiaoge.model.mine.coupons;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * @author daff
 * @date 2018/9/23.
 * 备注 选择优惠券
 */
public class UserChooseDeductModel extends BaseModel {


    /**
     * data : {"enable":[{"giftName":"满30减2","giftType":"满减券","amount":"2","amountStr":"2.00元","limitAmtStr":"满30.00元可用","limitAmt":30,"applyFrom":"人工积分兑换","dateTime":"2018-09-27-2018-11-27","role":["不予团购使用"],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092700000095"},{"giftName":"无门槛使用","giftType":"满减券","amount":"6","amountStr":"6.00元","limitAmtStr":"满0.00元可用","limitAmt":0,"applyFrom":"人工积分兑换","dateTime":"2018-09-27-2018-12-27","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092700000094"},{"giftName":"满20减0.05元","giftType":"满减券","amount":"0.05","amountStr":"0.05元","limitAmtStr":"满20.00元可用","limitAmt":20,"applyFrom":"人工积分兑换","dateTime":"2018-09-27-2019-03-27","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092700000093"}],"unable":[],"all":[{"giftName":"满30减2","giftType":"满减券","amount":"2","amountStr":"2.00元","limitAmtStr":"满30.00元可用","limitAmt":30,"applyFrom":"人工积分兑换","dateTime":"2018-09-27-2018-11-27","role":["不予团购使用"],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092700000095"},{"giftName":"无门槛使用","giftType":"满减券","amount":"6","amountStr":"6.00元","limitAmtStr":"满0.00元可用","limitAmt":0,"applyFrom":"人工积分兑换","dateTime":"2018-09-27-2018-12-27","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092700000094"},{"giftName":"满20减0.05元","giftType":"满减券","amount":"0.05","amountStr":"0.05元","limitAmtStr":"满20.00元可用","limitAmt":20,"applyFrom":"人工积分兑换","dateTime":"2018-09-27-2019-03-27","role":[],"overTimePic":"","usedPic":"","unAblePic":"","state":"ENABLED","giftDetailNo":"112018092700000093"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<EnableBean> enable;
        private List<?> unable;
        private List<AllBean> all;

        public List<EnableBean> getEnable() {
            return enable;
        }

        public void setEnable(List<EnableBean> enable) {
            this.enable = enable;
        }

        public List<?> getUnable() {
            return unable;
        }

        public void setUnable(List<?> unable) {
            this.unable = unable;
        }

        public List<AllBean> getAll() {
            return all;
        }

        public void setAll(List<AllBean> all) {
            this.all = all;
        }

        public static class EnableBean {
            /**
             * giftName : 满30减2
             * giftType : 满减券
             * amount : 2
             * amountStr : 2.00元
             * limitAmtStr : 满30.00元可用
             * limitAmt : 30.0
             * applyFrom : 人工积分兑换
             * dateTime : 2018-09-27-2018-11-27
             * role : ["不予团购使用"]
             * overTimePic :
             * usedPic :
             * unAblePic :
             * state : ENABLED
             * giftDetailNo : 112018092700000095
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

            public String getAmount() {
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

        public static class AllBean {
            /**
             * giftName : 满30减2
             * giftType : 满减券
             * amount : 2
             * amountStr : 2.00元
             * limitAmtStr : 满30.00元可用
             * limitAmt : 30.0
             * applyFrom : 人工积分兑换
             * dateTime : 2018-09-27-2018-11-27
             * role : ["不予团购使用"]
             * overTimePic :
             * usedPic :
             * unAblePic :
             * state : ENABLED
             * giftDetailNo : 112018092700000095
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

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

            private boolean flag;

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

            public String getAmount() {
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
