package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/11
 */
public class SpecialMoreGoodModel {


    /**
     * code : 1
     * message : 成功
     * data : [{"price":"￥3/箱","oldPrice":"","salesVolume":0,"title":"抹茶大福","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/303113e3ccb04299a48a7c90bfe56873.jpg","spec":"00","activeId":7,"businessType":11,"soldOut":0},{"price":"￥5/箱","oldPrice":"￥1.76/箱","salesVolume":0,"title":"蓉黄桃大福","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/635adcc8f9984ab486fcc3f0893fc249.jpg","spec":"123456","activeId":6,"businessType":11,"soldOut":0}]
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * price : ￥3/箱
         * oldPrice :
         * salesVolume : 0
         * title : 抹茶大福
         * pic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/303113e3ccb04299a48a7c90bfe56873.jpg
         * spec : 00
         * activeId : 7
         * businessType : 11
         * soldOut : 0
         */

        private String price;
        private String oldPrice;
        private String salesVolume;
        private String title;
        private String pic;
        private String spec;
        private int activeId;
        private int businessType;
        private int soldOut;

        public String getFlagUrl() {
            return flagUrl;
        }

        public void setFlagUrl(String flagUrl) {
            this.flagUrl = flagUrl;
        }

        private String flagUrl;
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

        public String getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(String salesVolume) {
            this.salesVolume = salesVolume;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public int getActiveId() {
            return activeId;
        }

        public void setActiveId(int activeId) {
            this.activeId = activeId;
        }

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public int getSoldOut() {
            return soldOut;
        }

        public void setSoldOut(int soldOut) {
            this.soldOut = soldOut;
        }
    }
}
