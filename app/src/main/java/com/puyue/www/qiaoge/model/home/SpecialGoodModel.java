package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/11
 */
public class SpecialGoodModel {

    /**
     * code : 1
     * message : 成功
     * data : {"activeId":6,"productId":0,"activeTitle":"蓉黄桃大福","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/635adcc8f9984ab486fcc3f0893fc249.jpg","picCarousel":["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/635adcc8f9984ab486fcc3f0893fc249.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/86f5d02beeeb4cb2b7757e7596d64f52.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/a826a15bd6d245588d34d0a5266410f9.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/20e9f169a0c94d03b90cfecff5ce85a8.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/b0726e5696a84e5aa11098eabaea0ac0.jpg"],"picDetail":["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/93cfaa2f95724b2d87ff1c360ef213bf.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/2ef2229944ea420e8a585fdb74e09acc.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/beb6ceae73194094a8bd8d6494cffd48.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/68b505bf2d434efc9a318c3116fdde1f.jpg"],"oldPrice":"￥1.76/箱","price":"5","inventory":"45","monthSalesVolume":"0","intrduction":null,"originPlace":"0","currentTime":0,"startTime":0,"endTime":0,"type":"","progress":"","specification":"123456","origin":"0","instructions":null,"combinationPrice":"","prodList":[],"saleDoneUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","saleFinshUrl":"","flag":false,"purchaseLimit":"","unitName":"箱","available":true}
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
         * activeId : 6
         * productId : 0
         * activeTitle : 蓉黄桃大福
         * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/635adcc8f9984ab486fcc3f0893fc249.jpg
         * picCarousel : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/635adcc8f9984ab486fcc3f0893fc249.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/86f5d02beeeb4cb2b7757e7596d64f52.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/a826a15bd6d245588d34d0a5266410f9.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/20e9f169a0c94d03b90cfecff5ce85a8.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/b0726e5696a84e5aa11098eabaea0ac0.jpg"]
         * picDetail : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/93cfaa2f95724b2d87ff1c360ef213bf.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/2ef2229944ea420e8a585fdb74e09acc.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/beb6ceae73194094a8bd8d6494cffd48.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/68b505bf2d434efc9a318c3116fdde1f.jpg"]
         * oldPrice : ￥1.76/箱
         * price : 5
         * inventory : 45
         * monthSalesVolume : 0
         * intrduction : null
         * originPlace : 0
         * currentTime : 0
         * startTime : 0
         * endTime : 0
         * type :
         * progress :
         * specification : 123456
         * origin : 0
         * instructions : null
         * combinationPrice :
         * prodList : []
         * saleDoneUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png
         * saleFinshUrl :
         * flag : false
         * purchaseLimit :
         * unitName : 箱
         * available : true
         */

        private int activeId;
        private int productId;
        private String activeTitle;
        private String defaultPic;
        private String oldPrice;
        private String price;
        private String inventory;
        private String monthSalesVolume;
        private Object intrduction;
        private String originPlace;
        private int currentTime;
        private int startTime;
        private int endTime;
        private String type;
        private String progress;
        private String specification;
        private String origin;
        private Object instructions;
        private String combinationPrice;
        private String saleDoneUrl;
        private String saleFinshUrl;
        private boolean flag;
        private String purchaseLimit;
        private String unitName;

        private boolean available;
        private List<String> picCarousel;
        private List<String> picDetail;
        private List<?> prodList;


        public int getActiveId() {
            return activeId;
        }

        public void setActiveId(int activeId) {
            this.activeId = activeId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getActiveTitle() {
            return activeTitle;
        }

        public void setActiveTitle(String activeTitle) {
            this.activeTitle = activeTitle;
        }

        public String getDefaultPic() {
            return defaultPic;
        }

        public void setDefaultPic(String defaultPic) {
            this.defaultPic = defaultPic;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getInventory() {
            return inventory;
        }

        public void setInventory(String inventory) {
            this.inventory = inventory;
        }

        public String getMonthSalesVolume() {
            return monthSalesVolume;
        }

        public void setMonthSalesVolume(String monthSalesVolume) {
            this.monthSalesVolume = monthSalesVolume;
        }

        public Object getIntrduction() {
            return intrduction;
        }

        public void setIntrduction(Object intrduction) {
            this.intrduction = intrduction;
        }

        public String getOriginPlace() {
            return originPlace;
        }

        public void setOriginPlace(String originPlace) {
            this.originPlace = originPlace;
        }

        public int getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(int currentTime) {
            this.currentTime = currentTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public Object getInstructions() {
            return instructions;
        }

        public void setInstructions(Object instructions) {
            this.instructions = instructions;
        }

        public String getCombinationPrice() {
            return combinationPrice;
        }

        public void setCombinationPrice(String combinationPrice) {
            this.combinationPrice = combinationPrice;
        }

        public String getSaleDoneUrl() {
            return saleDoneUrl;
        }

        public void setSaleDoneUrl(String saleDoneUrl) {
            this.saleDoneUrl = saleDoneUrl;
        }

        public String getSaleFinshUrl() {
            return saleFinshUrl;
        }

        public void setSaleFinshUrl(String saleFinshUrl) {
            this.saleFinshUrl = saleFinshUrl;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getPurchaseLimit() {
            return purchaseLimit;
        }

        public void setPurchaseLimit(String purchaseLimit) {
            this.purchaseLimit = purchaseLimit;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public List<String> getPicCarousel() {
            return picCarousel;
        }

        public void setPicCarousel(List<String> picCarousel) {
            this.picCarousel = picCarousel;
        }

        public List<String> getPicDetail() {
            return picDetail;
        }

        public void setPicDetail(List<String> picDetail) {
            this.picDetail = picDetail;
        }

        public List<?> getProdList() {
            return prodList;
        }

        public void setProdList(List<?> prodList) {
            this.prodList = prodList;
        }
    }
}
