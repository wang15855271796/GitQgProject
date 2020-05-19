package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王涛} on 2019/10/10
 */
public class ExchangeProductModel {


    /**
     * code : 1
     * message : 成功
     * data : {"type":"批发","productMainId":329,"productName":"供应商4","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"123456/箱","salesVolume":"129","minMaxPrice":null,"specialOffer":"","prodSpecs":null,"inventory":"2包","prodPrices":[{"price":"￥0.6/包","oldPrice":"","priceId":1376,"productUnit":540},{"price":"￥11/箱","oldPrice":"","priceId":1377,"productUnit":539},{"price":"￥12/2包","oldPrice":"","priceId":1378,"productUnit":540},{"price":"￥13/3包","oldPrice":"","priceId":1379,"productUnit":540},{"price":"￥14/4包","oldPrice":"","priceId":1380,"productUnit":540}]}
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
         * type : 批发
         * productMainId : 329
         * productName : 供应商4
         * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
         * flag : 1
         * typeUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png
         * spec : 123456/箱
         * salesVolume : 129
         * minMaxPrice : null
         * specialOffer :
         * prodSpecs : null
         * inventory : 2包
         * prodPrices : [{"price":"￥0.6/包","oldPrice":"","priceId":1376,"productUnit":540},{"price":"￥11/箱","oldPrice":"","priceId":1377,"productUnit":539},{"price":"￥12/2包","oldPrice":"","priceId":1378,"productUnit":540},{"price":"￥13/3包","oldPrice":"","priceId":1379,"productUnit":540},{"price":"￥14/4包","oldPrice":"","priceId":1380,"productUnit":540}]
         */
        int activeId;
        int productId;
        private String type;
        private int productMainId;
        private String productName;
        private String defaultPic;
        private int flag;
        private String typeUrl;
        private String spec;
        private String salesVolume;
        private String minMaxPrice;
        private String specialOffer;
        private List<ProdSpecsBean> prodSpecs;
        private String inventory;
        private List<ProdPricesBean> prodPrices;

        @Override
        public String toString() {
            return "DataBean{" +
                    "activeId=" + activeId +
                    ", productId=" + productId +
                    ", type='" + type + '\'' +
                    ", productMainId=" + productMainId +
                    ", productName='" + productName + '\'' +
                    ", defaultPic='" + defaultPic + '\'' +
                    ", flag=" + flag +
                    ", typeUrl='" + typeUrl + '\'' +
                    ", spec='" + spec + '\'' +
                    ", salesVolume='" + salesVolume + '\'' +
                    ", minMaxPrice='" + minMaxPrice + '\'' +
                    ", specialOffer='" + specialOffer + '\'' +
                    ", prodSpecs=" + prodSpecs +
                    ", inventory='" + inventory + '\'' +
                    ", prodPrices=" + prodPrices +
                    '}';
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getActiveId() {
            return activeId;
        }

        public void setActiveId(int activeId) {
            this.activeId = activeId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getProductMainId() {
            return productMainId;
        }

        public void setProductMainId(int productMainId) {
            this.productMainId = productMainId;
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

        public List<ProdSpecsBean> getProdSpecs() {
            return prodSpecs;
        }

        public void setProdSpecs(List<ProdSpecsBean> prodSpecs) {
            this.prodSpecs = prodSpecs;
        }

        public String getInventory() {
            return inventory;
        }

        public void setInventory(String inventory) {
            this.inventory = inventory;
        }

        public List<ProdPricesBean> getProdPrices() {
            return prodPrices;
        }

        public void setProdPrices(List<ProdPricesBean> prodPrices) {
            this.prodPrices = prodPrices;
        }


        public static class ProdSpecsBean {
            /**
             * productId : 405
             * spec : 60
             */
            private int productId;
            private String spec;

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

        }


        public static class ProdPricesBean {
            @Override
            public String toString() {
                return "ProdPricesBean{" +
                        "price='" + price + '\'' +
                        ", oldPrice='" + oldPrice + '\'' +
                        ", priceId=" + priceId +
                        ", productUnit=" + productUnit +
                        '}';
            }

            /**
             * price : ￥0.6/包
             * oldPrice :
             * priceId : 1376
             * productUnit : 540
             */
            private int cartNum;
            private String price;
            private String oldPrice;
            private int priceId;
            private int productUnit;
            private String unitDesc;
            private String specialOffer;
            public String getSpecialOffer() {
                return specialOffer;
            }

            public void setSpecialOffer(String specialOffer) {
                this.specialOffer = specialOffer;
            }

            public int getCartNum() {
                return cartNum;
            }

            public void setCartNum(int cartNum) {
                this.cartNum = cartNum;
            }

            public String getUnitDesc() {
                return unitDesc;
            }

            public void setUnitDesc(String unitDesc) {
                this.unitDesc = unitDesc;
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
        }
    }

    @Override
    public String toString() {
        return "ExchangeProductModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", error=" + error +
                ", success=" + success +
                '}';
    }
}
