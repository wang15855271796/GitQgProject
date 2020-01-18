package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class GetProductDetailModel {


    /**
     * code : 1
     * message : 成功
     * data : {"type":"批发","productMainId":4606,"productId":405,"productName":"多规格20","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","flag":1,"typeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png","spec":"60","salesVolume":"71","minMaxPrice":"￥120-100","specialOffer":"","prodSpecs":[{"productId":405,"spec":"60"},{"productId":404,"spec":"50"}],"inventory":"10箱2包","prodPrices":[{"price":"￥120/箱","oldPrice":"","priceId":1579,"productUnit":843,"unitDesc":""}],"introduction":"","detailPic":[],"topPic":[]}
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
         * productMainId : 4606
         * productId : 405
         * productName : 多规格20
         * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
         * flag : 1
         * typeUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1e240f4b1b2c425d9369ec15ce5e585d.png
         * spec : 60
         * salesVolume : 71
         * minMaxPrice : ￥120-100
         * specialOffer :
         * prodSpecs : [{"productId":405,"spec":"60"},{"productId":404,"spec":"50"}]
         * inventory : 10箱2包
         * prodPrices : [{"price":"￥120/箱","oldPrice":"","priceId":1579,"productUnit":843,"unitDesc":""}]
         * introduction :
         * detailPic : []
         * topPic : []
         */
        private int saleDone;
        private String type;
        private int productMainId;
        private int productId;
        private String productName;
        private String defaultPic;
        private int flag;
        private String typeUrl;
        private String spec;
        private String salesVolume;
        private String minMaxPrice;
        private String specialOffer;
        private String inventory;
        private String introduction;
        private List<ProdSpecsBean> prodSpecs;
        private List<ProdPricesBean> prodPrices;
        private List<String> detailPic;
        private List<String> topPic;

        @Override
        public String toString() {
            return "DataBean{" +
                    "type='" + type + '\'' +
                    ", productMainId=" + productMainId +
                    ", productId=" + productId +
                    ", productName='" + productName + '\'' +
                    ", defaultPic='" + defaultPic + '\'' +
                    ", flag=" + flag +
                    ", typeUrl='" + typeUrl + '\'' +
                    ", spec='" + spec + '\'' +
                    ", salesVolume='" + salesVolume + '\'' +
                    ", minMaxPrice='" + minMaxPrice + '\'' +
                    ", specialOffer='" + specialOffer + '\'' +
                    ", inventory='" + inventory + '\'' +
                    ", introduction='" + introduction + '\'' +
                    ", prodSpecs=" + prodSpecs +
                    ", prodPrices=" + prodPrices +
                    ", detailPic=" + detailPic +
                    ", topPic=" + topPic +
                    '}';
        }

        public int getSaleDone() {
            return saleDone;
        }

        public void setSaleDone(int saleDone) {
            this.saleDone = saleDone;
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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
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

        public List<String> getDetailPic() {
            return detailPic;
        }

        public void setDetailPic(List<String> detailPic) {
            this.detailPic = detailPic;
        }

        public List<String> getTopPic() {
            return topPic;
        }

        public void setTopPic(List<String> topPic) {
            this.topPic = topPic;
        }

        public static class ProdSpecsBean {
            /**
             * productId : 405
             * spec : 60
             */

            private int productId;
            private String spec;
            int prodDeduct;

            public int getProdDeduct() {
                return prodDeduct;
            }

            public void setProdDeduct(int prodDeduct) {
                this.prodDeduct = prodDeduct;
            }

            @Override
            public String toString() {
                return "ProdSpecsBean{" +
                        "productId=" + productId +
                        ", spec='" + spec + '\'' +
                        '}';
            }

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
            /**
             * price : ￥120/箱
             * oldPrice :
             * priceId : 1579
             * productUnit : 843
             * unitDesc :
             */

            private String price;
            private String oldPrice;
            private int priceId;
            private int productUnit;
            private String unitDesc;
            int num;

            @Override
            public String toString() {
                return "ProdPricesBean{" +
                        "price='" + price + '\'' +
                        ", oldPrice='" + oldPrice + '\'' +
                        ", priceId=" + priceId +
                        ", productUnit=" + productUnit +
                        ", unitDesc='" + unitDesc + '\'' +
                        ", num=" + num +
                        '}';
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
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

            public String getUnitDesc() {
                return unitDesc;
            }

            public void setUnitDesc(String unitDesc) {
                this.unitDesc = unitDesc;
            }
        }
    }
}
