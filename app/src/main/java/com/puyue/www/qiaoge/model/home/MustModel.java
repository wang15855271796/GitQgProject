package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王涛} on 2020/3/5
 */
public class MustModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"type":"批发","activeId":null,"productMainId":4662,"productId":4583,"productName":"【津山口福】剁椒料（1kg/包）-G","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"businessType":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//2df7b84f572148c299aa45d1c30c82d8.png","spec":"1kg*10包/箱、2kg*10包/箱","salesVolume":"销量：122","minMaxPrice":"￥10.1-20.2","specialOffer":"","prodSpecs":[{"productId":4583,"spec":"1kg*10包/箱","prodDeduct":0},{"productId":4588,"spec":"2kg*10包/箱","prodDeduct":0}],"inventory":"库存：126箱","prodPrices":[{"price":"￥10.1/箱","oldPrice":"","priceId":1937,"productUnit":958,"unitDesc":"","cartNum":0}],"deductAmount":""},{"type":"批发","activeId":null,"productMainId":4667,"productId":4591,"productName":"丽水一级分类2","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"businessType":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//e9b86a8626db4402b6ea50e3f036cf11.png","spec":"500g","salesVolume":"销量：22","minMaxPrice":"￥0.02","specialOffer":"","prodSpecs":[{"productId":4591,"spec":"500g","prodDeduct":1}],"inventory":"库存：81箱","prodPrices":[{"price":"￥0.02/箱","oldPrice":"￥0.20","priceId":2270,"productUnit":967,"unitDesc":"","cartNum":0}],"deductAmount":""},{"type":"批发","activeId":null,"productMainId":4677,"productId":4602,"productName":"仓库","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"businessType":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//2df7b84f572148c299aa45d1c30c82d8.png","spec":"500","salesVolume":"销量：12","minMaxPrice":"￥10.2","specialOffer":"","prodSpecs":[{"productId":4602,"spec":"500","prodDeduct":0}],"inventory":"库存：100箱","prodPrices":[{"price":"￥10.2/箱","oldPrice":"","priceId":1975,"productUnit":977,"unitDesc":"","cartNum":0}],"deductAmount":""},{"type":"批发","activeId":null,"productMainId":4681,"productId":4605,"productName":"调拨测试","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"businessType":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//2df7b84f572148c299aa45d1c30c82d8.png","spec":"500g、600g","salesVolume":"销量：1000","minMaxPrice":"￥15-51","specialOffer":"","prodSpecs":[{"productId":4605,"spec":"500g","prodDeduct":0},{"productId":4606,"spec":"600g","prodDeduct":0}],"inventory":"库存：1包","prodPrices":[{"price":"￥51/箱","oldPrice":"","priceId":2437,"productUnit":981,"unitDesc":"10包/箱","cartNum":0}],"deductAmount":""}]
     * success : true
     * error : false
     */

    private int code;
    private String message;
    private boolean success;
    private boolean error;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : 批发
         * activeId : null
         * productMainId : 4662
         * productId : 4583
         * productName : 【津山口福】剁椒料（1kg/包）-G
         * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
         * flag : 1
         * businessType : 1
         * typeUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//2df7b84f572148c299aa45d1c30c82d8.png
         * spec : 1kg*10包/箱、2kg*10包/箱
         * salesVolume : 销量：122
         * minMaxPrice : ￥10.1-20.2
         * specialOffer :
         * prodSpecs : [{"productId":4583,"spec":"1kg*10包/箱","prodDeduct":0},{"productId":4588,"spec":"2kg*10包/箱","prodDeduct":0}]
         * inventory : 库存：126箱
         * prodPrices : [{"price":"￥10.1/箱","oldPrice":"","priceId":1937,"productUnit":958,"unitDesc":"","cartNum":0}]
         * deductAmount :
         */

        private String type;
        private Object activeId;
        private int productMainId;
        private int productId;
        private String productName;
        private String defaultPic;
        private int flag;
        private int businessType;
        private String typeUrl;
        private String spec;
        private String salesVolume;
        private String minMaxPrice;
        private String specialOffer;
        private String inventory;
        private String deductAmount;
        private List<ProdSpecsBean> prodSpecs;
        private List<ProdPricesBean> prodPrices;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getActiveId() {
            return activeId;
        }

        public void setActiveId(Object activeId) {
            this.activeId = activeId;
        }

        public int getProductMainId() {
            return productMainId;
        }

        public void setProductMainId(int productMainId) {
            this.productMainId = productMainId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getDefaultPic() {
            return defaultPic;
        }

        public void setDefaultPic(String defaultPic) {
            this.defaultPic = defaultPic;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public String getTypeUrl() {
            return typeUrl;
        }

        public void setTypeUrl(String typeUrl) {
            this.typeUrl = typeUrl;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(String salesVolume) {
            this.salesVolume = salesVolume;
        }

        public String getMinMaxPrice() {
            return minMaxPrice;
        }

        public void setMinMaxPrice(String minMaxPrice) {
            this.minMaxPrice = minMaxPrice;
        }

        public String getSpecialOffer() {
            return specialOffer;
        }

        public void setSpecialOffer(String specialOffer) {
            this.specialOffer = specialOffer;
        }

        public String getInventory() {
            return inventory;
        }

        public void setInventory(String inventory) {
            this.inventory = inventory;
        }

        public String getDeductAmount() {
            return deductAmount;
        }

        public void setDeductAmount(String deductAmount) {
            this.deductAmount = deductAmount;
        }

        public List<ProdSpecsBean> getProdSpecs() {
            return prodSpecs;
        }

        public void setProdSpecs(List<ProdSpecsBean> prodSpecs) {
            this.prodSpecs = prodSpecs;
        }

        public List<ProdPricesBean> getProdPrices() {
            return prodPrices;
        }

        public void setProdPrices(List<ProdPricesBean> prodPrices) {
            this.prodPrices = prodPrices;
        }

        public static class ProdSpecsBean {
            /**
             * productId : 4583
             * spec : 1kg*10包/箱
             * prodDeduct : 0
             */

            private int productId;
            private String spec;
            private int prodDeduct;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public int getProdDeduct() {
                return prodDeduct;
            }

            public void setProdDeduct(int prodDeduct) {
                this.prodDeduct = prodDeduct;
            }
        }

        public static class ProdPricesBean {
            /**
             * price : ￥10.1/箱
             * oldPrice :
             * priceId : 1937
             * productUnit : 958
             * unitDesc :
             * cartNum : 0
             */

            private String price;
            private String oldPrice;
            private int priceId;
            private int productUnit;
            private String unitDesc;
            private int cartNum;

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

            public int getPriceId() {
                return priceId;
            }

            public void setPriceId(int priceId) {
                this.priceId = priceId;
            }

            public int getProductUnit() {
                return productUnit;
            }

            public void setProductUnit(int productUnit) {
                this.productUnit = productUnit;
            }

            public String getUnitDesc() {
                return unitDesc;
            }

            public void setUnitDesc(String unitDesc) {
                this.unitDesc = unitDesc;
            }

            public int getCartNum() {
                return cartNum;
            }

            public void setCartNum(int cartNum) {
                this.cartNum = cartNum;
            }
        }
    }
}
