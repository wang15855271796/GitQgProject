package com.puyue.www.qiaoge.model.mine.order;

import java.util.List;

/**
 * Created by ${王文博} on 2019/8/28
 */
public class NewReturnOrderModel {

    /**
     * code : 1
     * message : 成功
     * data : {"returnProductMainId":34,"returnState":1,"returnStateStr":"退货成功","products":[{"businessId":1,"businessType":1,"typeImg":null,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","productName":"巧克力大福","spec":"0","returnNum":"0箱","returnTotalAmount":"1.00","additionFlag":0},{"businessId":2,"businessType":1,"typeImg":null,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/3cd5997e6be6481b8f6b1da0bac48183.jpg","productName":"批发多单位商品","spec":"1箱子=10盒","returnNum":"1盒","returnTotalAmount":"30.00","additionFlag":0},{"businessId":3,"businessType":1,"typeImg":null,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/54fe0128a3274b19bc46a624b4d5b7ee.jpg","productName":"批发商品3","spec":"100","returnNum":"1困","returnTotalAmount":"400.00","additionFlag":0},{"businessId":4,"businessType":1,"typeImg":null,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/635adcc8f9984ab486fcc3f0893fc249.jpg","productName":"蓉黄桃大福","spec":"123456","returnNum":"1箱","returnTotalAmount":"1.00","additionFlag":0}],"returnAmount":"432.00","actualAmount":"11.00","returnChannel":"余额","returnReason":"hjg","orderId":"20190404155455-f3b2","returnMemo":null,"applier":"19222222222","applyDate":"2019-04-04 15:55:19","checkDate":"2019-04-04 16:51"}
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
         * returnProductMainId : 34
         * returnState : 1
         * returnStateStr : 退货成功
         * products : [{"businessId":1,"businessType":1,"typeImg":null,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","productName":"巧克力大福","spec":"0","returnNum":"0箱","returnTotalAmount":"1.00","additionFlag":0},{"businessId":2,"businessType":1,"typeImg":null,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/3cd5997e6be6481b8f6b1da0bac48183.jpg","productName":"批发多单位商品","spec":"1箱子=10盒","returnNum":"1盒","returnTotalAmount":"30.00","additionFlag":0},{"businessId":3,"businessType":1,"typeImg":null,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/54fe0128a3274b19bc46a624b4d5b7ee.jpg","productName":"批发商品3","spec":"100","returnNum":"1困","returnTotalAmount":"400.00","additionFlag":0},{"businessId":4,"businessType":1,"typeImg":null,"defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/635adcc8f9984ab486fcc3f0893fc249.jpg","productName":"蓉黄桃大福","spec":"123456","returnNum":"1箱","returnTotalAmount":"1.00","additionFlag":0}]
         * returnAmount : 432.00
         * actualAmount : 11.00
         * returnChannel : 余额
         * returnReason : hjg
         * orderId : 20190404155455-f3b2
         * returnMemo : null
         * applier : 19222222222
         * applyDate : 2019-04-04 15:55:19
         * checkDate : 2019-04-04 16:51
         * titleText
         */

        private int returnProductMainId;
        private int returnState;
        private String returnStateStr;
        private String returnAmount;
        private String actualAmount;
        private String returnChannel;
        private String returnReason;
        private String orderId;
        private String returnMemo;
        private String applier;
        private String applyDate;
        private String checkDate;
        private List<ProductsBean> products;
        public String titleText;
        public int getReturnProductMainId() {
            return returnProductMainId;
        }

        public void setReturnProductMainId(int returnProductMainId) {
            this.returnProductMainId = returnProductMainId;
        }

        public int getReturnState() {
            return returnState;
        }

        public void setReturnState(int returnState) {
            this.returnState = returnState;
        }

        public String getReturnStateStr() {
            return returnStateStr;
        }

        public void setReturnStateStr(String returnStateStr) {
            this.returnStateStr = returnStateStr;
        }

        public String getReturnAmount() {
            return returnAmount;
        }

        public void setReturnAmount(String returnAmount) {
            this.returnAmount = returnAmount;
        }

        public String getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(String actualAmount) {
            this.actualAmount = actualAmount;
        }

        public String getReturnChannel() {
            return returnChannel;
        }

        public void setReturnChannel(String returnChannel) {
            this.returnChannel = returnChannel;
        }

        public String getReturnReason() {
            return returnReason;
        }

        public void setReturnReason(String returnReason) {
            this.returnReason = returnReason;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getReturnMemo() {
            return returnMemo;
        }

        public void setReturnMemo(String returnMemo) {
            this.returnMemo = returnMemo;
        }

        public String getApplier() {
            return applier;
        }

        public void setApplier(String applier) {
            this.applier = applier;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            /**
             * businessId : 1
             * businessType : 1
             * typeImg : null
             * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
             * productName : 巧克力大福
             * spec : 0
             * returnNum : 0箱
             * returnTotalAmount : 1.00
             * additionFlag : 0
             * onShelves

             */
            public int productMainId;
            String afterPrice;
            private int businessId;
            private int businessType;
            private String typeImg;
            private String defaultPic;
            private String productName;
            private String spec;
            private String returnNum;
            private String returnTotalAmount;
            private int additionFlag;

            public int onShelves;

            public int getProductMainId() {
                return productMainId;
            }

            public void setProductMainId(int productMainId) {
                this.productMainId = productMainId;
            }

            public String getAfterPrice() {
                return afterPrice;
            }

            public void setAfterPrice(String afterPrice) {
                this.afterPrice = afterPrice;
            }

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public int getBusinessType() {
                return businessType;
            }

            public void setBusinessType(int businessType) {
                this.businessType = businessType;
            }

            public String getTypeImg() {
                return typeImg;
            }

            public void setTypeImg(String typeImg) {
                this.typeImg = typeImg;
            }

            public String getDefaultPic() {
                return defaultPic;
            }

            public void setDefaultPic(String defaultPic) {
                this.defaultPic = defaultPic;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getReturnNum() {
                return returnNum;
            }

            public void setReturnNum(String returnNum) {
                this.returnNum = returnNum;
            }

            public String getReturnTotalAmount() {
                return returnTotalAmount;
            }

            public void setReturnTotalAmount(String returnTotalAmount) {
                this.returnTotalAmount = returnTotalAmount;
            }

            public int getAdditionFlag() {
                return additionFlag;
            }

            public void setAdditionFlag(int additionFlag) {
                this.additionFlag = additionFlag;
            }
        }
    }
}
