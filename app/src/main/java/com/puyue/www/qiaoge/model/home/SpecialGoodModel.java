package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/11
 */
public class SpecialGoodModel {
    /**
     * code : 1
     * message : 成功
     * data : {"topPics":["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png"],"detailPics":[],"activityType":3,"actTypeName":"组合特卖","activeId":612,"currentTime":1576833531762,"startTime":1576467247000,"endTime":0,"limitNum":"限购500组","totalNum":"500组","showPrice":"120.23","showOldPrice":"￥132.25","progress":"0","price":"￥120.23/组","oldPrice":"原价￥132.25/组","introduction":"","activeName":"团购","remainNum":"余量:210组","saleVolume":"销量:290","spec":"","cartNum":"","saleDone":1}
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
         * topPics : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png"]
         * detailPics : []
         * activityType : 3
         * actTypeName : 组合特卖
         * activeId : 612
         * currentTime : 1576833531762
         * startTime : 1576467247000
         * endTime : 0
         * limitNum : 限购500组
         * totalNum : 500组
         * showPrice : 120.23
         * showOldPrice : ￥132.25
         * progress : 0
         * price : ￥120.23/组
         * oldPrice : 原价￥132.25/组
         * introduction :
         * activeName : 团购
         * remainNum : 余量:210组
         * saleVolume : 销量:290
         * spec :
         * cartNum :
         * saleDone : 1
         */
        int warnMe;
        private int activityType;
        private String actTypeName;
        private int activeId;
        private long currentTime;
        private long startTime;
        private long endTime;
        private String limitNum;
        private String totalNum;
        private String showPrice;
        private String showOldPrice;
        private String progress;
        private String price;
        private String oldPrice;
        private String introduction;
        private String activeName;
        private String remainNum;
        private String saleVolume;
        private String spec;
        private String cartNum;
        private int saleDone;
        private List<String> topPics;
        private List<String> detailPics;

        public int getWarnMe() {
            return warnMe;
        }

        public void setWarnMe(int warnMe) {
            this.warnMe = warnMe;
        }

        public int getActivityType() {
            return activityType;
        }

        public void setActivityType(int activityType) {
            this.activityType = activityType;
        }

        public String getActTypeName() {
            return actTypeName;
        }

        public void setActTypeName(String actTypeName) {
            this.actTypeName = actTypeName;
        }

        public int getActiveId() {
            return activeId;
        }

        public void setActiveId(int activeId) {
            this.activeId = activeId;
        }

        public long getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(long currentTime) {
            this.currentTime = currentTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getLimitNum() {
            return limitNum;
        }

        public void setLimitNum(String limitNum) {
            this.limitNum = limitNum;
        }

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getShowPrice() {
            return showPrice;
        }

        public void setShowPrice(String showPrice) {
            this.showPrice = showPrice;
        }

        public String getShowOldPrice() {
            return showOldPrice;
        }

        public void setShowOldPrice(String showOldPrice) {
            this.showOldPrice = showOldPrice;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getActiveName() {
            return activeName;
        }

        public void setActiveName(String activeName) {
            this.activeName = activeName;
        }

        public String getRemainNum() {
            return remainNum;
        }

        public void setRemainNum(String remainNum) {
            this.remainNum = remainNum;
        }

        public String getSaleVolume() {
            return saleVolume;
        }

        public void setSaleVolume(String saleVolume) {
            this.saleVolume = saleVolume;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getCartNum() {
            return cartNum;
        }

        public void setCartNum(String cartNum) {
            this.cartNum = cartNum;
        }

        public int getSaleDone() {
            return saleDone;
        }

        public void setSaleDone(int saleDone) {
            this.saleDone = saleDone;
        }

        public List<String> getTopPics() {
            return topPics;
        }

        public void setTopPics(List<String> topPics) {
            this.topPics = topPics;
        }

        public List<String> getDetailPics() {
            return detailPics;
        }

        public void setDetailPics(List<String> detailPics) {
            this.detailPics = detailPics;
        }
    }
}
