package com.puyue.www.qiaoge.model.mine.coupons;

import java.util.List;

/**
 * Created by ${daff} on 2018/9/20
 */
public class queryUserList {

    /**
     * giftName : 满200减10元
     * giftType : 满减券
     * amount : 10
     * amountStr : 10.00元
     * limitAmtStr : 满200.00元可用
     * limitAmt : 200
     * applyFrom : 人工积分兑换
     * dateTime : 2018-09-14-2018-12-14
     * role : ["不予秒杀，团购活动一起使用"]
     * overTimePic :
     * usedPic :
     * unAblePic :
     * state : ENABLED
     * giftDetailNo : 2018091410053218368493783
     */

    private String giftName;
    private String giftType;
    private int amount;
    private String amountStr;
    private String limitAmtStr;
    private int limitAmt;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

    public int getLimitAmt() {
        return limitAmt;
    }

    public void setLimitAmt(int limitAmt) {
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
