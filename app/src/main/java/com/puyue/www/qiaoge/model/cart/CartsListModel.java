package com.puyue.www.qiaoge.model.cart;

import com.puyue.www.qiaoge.api.market.TestModel;

import java.util.List;

/**
 * Created by ${王涛} on 2019/10/7
 */
public class CartsListModel {


    /**
     * code : 1
     * message : 成功
     * data : {"validList":[{"productName":"萨达嘎斯","flagUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1481b068195a43cbb8b7ddc5413fa7c0.png","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/19ce296af8954172afefd136550f8aad.jpg","businessType":3,"specProductList":[{"businessId":472,"inventory":"0组","cartId":15609,"picUrl":null,"spec":"sd","productDescVOList":[{"price":"20.00","priceStr":"￥20.00/组","unit":null,"highNum":1,"productNum":1,"unitDesc":"","productCombinationPriceId":null,"oldPrice":null}]}],"additionProductVOList":null,"valid":true}],"inValidList":[{"productName":"旺达-15","flagUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/afa2f351e7d74757bd59b740901440d1.png","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","businessType":11,"specProductList":[{"businessId":398,"inventory":"10箱","cartId":12941,"picUrl":null,"spec":"150g*4个*18包/箱","productDescVOList":[{"price":"220.00","priceStr":"￥220.00/箱","unit":15,"highNum":1,"productNum":5,"unitDesc":"10盒/箱","productCombinationPriceId":null,"oldPrice":"280.00/箱"}]}],"additionProductVOList":null,"valid":false}],"sendAmount":"200.00"}
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
         * validList : [{"productName":"萨达嘎斯","flagUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1481b068195a43cbb8b7ddc5413fa7c0.png","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/19ce296af8954172afefd136550f8aad.jpg","businessType":3,"specProductList":[{"businessId":472,"inventory":"0组","cartId":15609,"picUrl":null,"spec":"sd","productDescVOList":[{"price":"20.00","priceStr":"￥20.00/组","unit":null,"highNum":1,"productNum":1,"unitDesc":"","productCombinationPriceId":null,"oldPrice":null}]}],"additionProductVOList":null,"valid":true}]
         * inValidList : [{"productName":"旺达-15","flagUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/afa2f351e7d74757bd59b740901440d1.png","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","businessType":11,"specProductList":[{"businessId":398,"inventory":"10箱","cartId":12941,"picUrl":null,"spec":"150g*4个*18包/箱","productDescVOList":[{"price":"220.00","priceStr":"￥220.00/箱","unit":15,"highNum":1,"productNum":5,"unitDesc":"10盒/箱","productCombinationPriceId":null,"oldPrice":"280.00/箱"}]}],"additionProductVOList":null,"valid":false}]
         * sendAmount : 200.00
         */

        private String sendAmount;
        private List<ValidListBean> validList;
        private List<InValidListBean> inValidList;

        public String getSendAmount() {
            return sendAmount;
        }

        public void setSendAmount(String sendAmount) {
            this.sendAmount = sendAmount;
        }

        public List<ValidListBean> getValidList() {
            return validList;
        }

        public void setValidList(List<ValidListBean> validList) {
            this.validList = validList;
        }

        public List<InValidListBean> getInValidList() {
            return inValidList;
        }

        public void setInValidList(List<InValidListBean> inValidList) {
            this.inValidList = inValidList;
        }

        public static class ValidListBean {
            /**
             * productName : 萨达嘎斯
             * flagUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/1481b068195a43cbb8b7ddc5413fa7c0.png
             * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/19ce296af8954172afefd136550f8aad.jpg
             * businessType : 3
             * specProductList : [{"businessId":472,"inventory":"0组","cartId":15609,"picUrl":null,"spec":"sd","productDescVOList":[{"price":"20.00","priceStr":"￥20.00/组","unit":null,"highNum":1,"productNum":1,"unitDesc":"","productCombinationPriceId":null,"oldPrice":null}]}]
             * additionProductVOList : null
             * valid : true
             */
            //判断是否选中
            private boolean isSelected = true;
            private String productName;
            private String flagUrl;
            private String defaultPic;
            private int businessType;
            public int businessId;
            int productMainId;
            private List<AdditionProductVOList> additionProductVOList;
            private boolean valid;
            private List<SpecProductListBean> specProductList;

            public int getProductMainId() {
                return productMainId;
            }

            public void setProductMainId(int productMainId) {
                this.productMainId = productMainId;
            }

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getFlagUrl() {
                return flagUrl;
            }

            public void setFlagUrl(String flagUrl) {
                this.flagUrl = flagUrl;
            }

            public String getDefaultPic() {
                return defaultPic;
            }

            public void setDefaultPic(String defaultPic) {
                this.defaultPic = defaultPic;
            }

            public int getBusinessType() {
                return businessType;
            }

            public void setBusinessType(int businessType) {
                this.businessType = businessType;
            }

            public List<AdditionProductVOList> getAdditionProductVOList() {
                return additionProductVOList;
            }

            public void setAdditionProductVOList(List<AdditionProductVOList> additionProductVOList) {
                this.additionProductVOList = additionProductVOList;
            }

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public List<SpecProductListBean> getSpecProductList() {
                return specProductList;
            }

            public void setSpecProductList(List<SpecProductListBean> specProductList) {
                this.specProductList = specProductList;
            }

            @Override
            public String toString() {
                return "ValidListBean{" +
                        "isSelected=" + isSelected +
                        ", productName='" + productName + '\'' +
                        ", flagUrl='" + flagUrl + '\'' +
                        ", defaultPic='" + defaultPic + '\'' +
                        ", businessType=" + businessType +
                        ", businessId=" + businessId +
                        ", additionProductVOList=" + additionProductVOList +
                        ", valid=" + valid +
                        ", specProductList=" + specProductList +
                        '}';
            }

            public static class SpecProductListBean {
                /**
                 * businessId : 472
                 * inventory : 0组
                 * cartId : 15609
                 * picUrl : null
                 * spec : sd
                 * productDescVOList : [{"price":"20.00","priceStr":"￥20.00/组","unit":null,"highNum":1,"productNum":1,"unitDesc":"","productCombinationPriceId":null,"oldPrice":null}]
                 */
                private List<AdditionProductVOList> additionProductVOList;
                private int businessId;
                private String inventory;
                private int cartId;
                private Object picUrl;
                private String spec;
                private List<ProductDescVOListBean> productDescVOList;
                boolean isSelected = true;

                @Override
                public String toString() {
                    return "SpecProductListBean{" +
                            "businessId=" + businessId +
                            ", inventory='" + inventory + '\'' +
                            ", cartId=" + cartId +
                            ", picUrl=" + picUrl +
                            ", spec='" + spec + '\'' +
                            ", productDescVOList=" + productDescVOList +
                            ", isSelected=" + isSelected +
                            '}';
                }

                public List<AdditionProductVOList> getAdditionProductVOList() {
                    return additionProductVOList;
                }

                public void setAdditionProductVOList(List<AdditionProductVOList> additionProductVOList) {
                    this.additionProductVOList = additionProductVOList;
                }

                public boolean isSelected() {
                    return isSelected;
                }

                public void setSelected(boolean selected) {
                    isSelected = selected;
                }

                public int getBusinessId() {
                    return businessId;
                }

                public void setBusinessId(int businessId) {
                    this.businessId = businessId;
                }

                public String getInventory() {
                    return inventory;
                }

                public void setInventory(String inventory) {
                    this.inventory = inventory;
                }

                public int getCartId() {
                    return cartId;
                }

                public void setCartId(int cartId) {
                    this.cartId = cartId;
                }

                public Object getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(Object picUrl) {
                    this.picUrl = picUrl;
                }

                public String getSpec() {
                    return spec;
                }

                public void setSpec(String spec) {
                    this.spec = spec;
                }

                public List<ProductDescVOListBean> getProductDescVOList() {
                    return productDescVOList;
                }

                public void setProductDescVOList(List<ProductDescVOListBean> productDescVOList) {
                    this.productDescVOList = productDescVOList;
                }

                public static class ProductDescVOListBean {
                    /**
                     * price : 20.00
                     * priceStr : ￥20.00/组
                     * unit : null
                     * highNum : 1
                     * productNum : 1
                     * unitDesc :
                     * productCombinationPriceId : null
                     * oldPrice : null
                     */
                    private int cartNum;
                    public boolean isSelected;
                    private String price;
                    private String priceStr;
                    private Object unit;
                    private int highNum;
                    private int productNum;
                    private String unitDesc;
                    private int productCombinationPriceId;
                    private Object oldPrice;

                    public int getCartNum() {
                        return cartNum;
                    }

                    public void setCartNum(int cartNum) {
                        this.cartNum = cartNum;
                    }

                    @Override
                    public String toString() {
                        return "ProductDescVOListBean{" +
                                "isSelected=" + isSelected +
                                ", price='" + price + '\'' +
                                ", priceStr='" + priceStr + '\'' +
                                ", unit=" + unit +
                                ", highNum=" + highNum +
                                ", productNum=" + productNum +
                                ", unitDesc='" + unitDesc + '\'' +
                                ", productCombinationPriceId=" + productCombinationPriceId +
                                ", oldPrice=" + oldPrice +
                                '}';
                    }

                    public boolean isSelected() {
                        return isSelected;
                    }

                    public void setSelected(boolean selected) {
                        isSelected = selected;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }

                    public String getPriceStr() {
                        return priceStr;
                    }

                    public void setPriceStr(String priceStr) {
                        this.priceStr = priceStr;
                    }

                    public Object getUnit() {
                        return unit;
                    }

                    public void setUnit(Object unit) {
                        this.unit = unit;
                    }

                    public int getHighNum() {
                        return highNum;
                    }

                    public void setHighNum(int highNum) {
                        this.highNum = highNum;
                    }

                    public int getProductNum() {
                        return productNum;
                    }

                    public void setProductNum(int productNum) {
                        this.productNum = productNum;
                    }

                    public String getUnitDesc() {
                        return unitDesc;
                    }

                    public void setUnitDesc(String unitDesc) {
                        this.unitDesc = unitDesc;
                    }

                    public int getProductCombinationPriceId() {
                        return productCombinationPriceId;
                    }

                    public void setProductCombinationPriceId(int productCombinationPriceId) {
                        this.productCombinationPriceId = productCombinationPriceId;
                    }

                    public Object getOldPrice() {
                        return oldPrice;
                    }

                    public void setOldPrice(Object oldPrice) {
                        this.oldPrice = oldPrice;
                    }
                }

                public static class AdditionProductVOList {

                    private String productMainId;
                    private String productId;
                    private String picUrl;
                    private String name;
                    private String spec;
                    private String inventory;
                    private String additionNum;
                    private int additionFlag;
                    private String finishUrl;
                    private String flagUrl;

                    public String getProductMainId() {
                        return productMainId;
                    }

                    public void setProductMainId(String productMainId) {
                        this.productMainId = productMainId;
                    }

                    public String getProductId() {
                        return productId;
                    }

                    public void setProductId(String productId) {
                        this.productId = productId;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getSpec() {
                        return spec;
                    }

                    public void setSpec(String spec) {
                        this.spec = spec;
                    }

                    public String getInventory() {
                        return inventory;
                    }

                    public void setInventory(String inventory) {
                        this.inventory = inventory;
                    }

                    public String getAdditionNum() {
                        return additionNum;
                    }

                    public void setAdditionNum(String additionNum) {
                        this.additionNum = additionNum;
                    }

                    public int getAdditionFlag() {
                        return additionFlag;
                    }

                    public void setAdditionFlag(int additionFlag) {
                        this.additionFlag = additionFlag;
                    }

                    public String getFinishUrl() {
                        return finishUrl;
                    }

                    public void setFinishUrl(String finishUrl) {
                        this.finishUrl = finishUrl;
                    }

                    public String getFlagUrl() {
                        return flagUrl;
                    }

                    public void setFlagUrl(String flagUrl) {
                        this.flagUrl = flagUrl;
                    }
                }
            }

            public static class AdditionProductVOList {

                private String productMainId;
                private String productId;
                private String picUrl;
                private String name;
                private String spec;
                private String inventory;
                private String additionNum;
                private int additionFlag;
                private String finishUrl;
                private String flagUrl;

                public String getProductMainId() {
                    return productMainId;
                }

                public void setProductMainId(String productMainId) {
                    this.productMainId = productMainId;
                }

                public String getProductId() {
                    return productId;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSpec() {
                    return spec;
                }

                public void setSpec(String spec) {
                    this.spec = spec;
                }

                public String getInventory() {
                    return inventory;
                }

                public void setInventory(String inventory) {
                    this.inventory = inventory;
                }

                public String getAdditionNum() {
                    return additionNum;
                }

                public void setAdditionNum(String additionNum) {
                    this.additionNum = additionNum;
                }

                public int getAdditionFlag() {
                    return additionFlag;
                }

                public void setAdditionFlag(int additionFlag) {
                    this.additionFlag = additionFlag;
                }

                public String getFinishUrl() {
                    return finishUrl;
                }

                public void setFinishUrl(String finishUrl) {
                    this.finishUrl = finishUrl;
                }

                public String getFlagUrl() {
                    return flagUrl;
                }

                public void setFlagUrl(String flagUrl) {
                    this.flagUrl = flagUrl;
                }
            }

        }

        public static class InValidListBean {
            /**
             * productName : 旺达-15
             * flagUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/afa2f351e7d74757bd59b740901440d1.png
             * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
             * businessType : 11
             * specProductList : [{"businessId":398,"inventory":"10箱","cartId":12941,"picUrl":null,"spec":"150g*4个*18包/箱","productDescVOList":[{"price":"220.00","priceStr":"￥220.00/箱","unit":15,"highNum":1,"productNum":5,"unitDesc":"10盒/箱","productCombinationPriceId":null,"oldPrice":"280.00/箱"}]}]
             * additionProductVOList : null
             * valid : false
             */
            private String priceStr;
            private String productName;
            private String flagUrl;
            private String defaultPic;
            private int businessType;
            private Object additionProductVOList;
            private boolean valid;
            private List<SpecProductListBeanX> specProductList;

            public String getPriceStr() {
                return priceStr;
            }

            public void setPriceStr(String priceStr) {
                this.priceStr = priceStr;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getFlagUrl() {
                return flagUrl;
            }

            public void setFlagUrl(String flagUrl) {
                this.flagUrl = flagUrl;
            }

            public String getDefaultPic() {
                return defaultPic;
            }

            public void setDefaultPic(String defaultPic) {
                this.defaultPic = defaultPic;
            }

            public int getBusinessType() {
                return businessType;
            }

            public void setBusinessType(int businessType) {
                this.businessType = businessType;
            }

            public Object getAdditionProductVOList() {
                return additionProductVOList;
            }

            public void setAdditionProductVOList(Object additionProductVOList) {
                this.additionProductVOList = additionProductVOList;
            }

            public boolean isValid() {
                return valid;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public List<SpecProductListBeanX> getSpecProductList() {
                return specProductList;
            }

            public void setSpecProductList(List<SpecProductListBeanX> specProductList) {
                this.specProductList = specProductList;
            }

            public static class SpecProductListBeanX {
                /**
                 * businessId : 398
                 * inventory : 10箱
                 * cartId : 12941
                 * picUrl : null
                 * spec : 150g*4个*18包/箱
                 * productDescVOList : [{"price":"220.00","priceStr":"￥220.00/箱","unit":15,"highNum":1,"productNum":5,"unitDesc":"10盒/箱","productCombinationPriceId":null,"oldPrice":"280.00/箱"}]
                 */

                private int businessId;
                private String inventory;
                private int cartId;
                private Object picUrl;
                private String spec;
                private List<ProductDescVOListBeanX> productDescVOList;

                public int getBusinessId() {
                    return businessId;
                }

                public void setBusinessId(int businessId) {
                    this.businessId = businessId;
                }

                public String getInventory() {
                    return inventory;
                }

                public void setInventory(String inventory) {
                    this.inventory = inventory;
                }

                public int getCartId() {
                    return cartId;
                }

                public void setCartId(int cartId) {
                    this.cartId = cartId;
                }

                public Object getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(Object picUrl) {
                    this.picUrl = picUrl;
                }

                public String getSpec() {
                    return spec;
                }

                public void setSpec(String spec) {
                    this.spec = spec;
                }

                public List<ProductDescVOListBeanX> getProductDescVOList() {
                    return productDescVOList;
                }

                public void setProductDescVOList(List<ProductDescVOListBeanX> productDescVOList) {
                    this.productDescVOList = productDescVOList;
                }

                public static class ProductDescVOListBeanX {
                    /**
                     * price : 220.00
                     * priceStr : ￥220.00/箱
                     * unit : 15
                     * highNum : 1
                     * productNum : 5
                     * unitDesc : 10盒/箱
                     * productCombinationPriceId : null
                     * oldPrice : 280.00/箱
                     */

                    private String price;
                    private String priceStr;
                    private int unit;
                    private int highNum;
                    private int productNum;
                    private String unitDesc;
                    private Object productCombinationPriceId;
                    private String oldPrice;

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }

                    public String getPriceStr() {
                        return priceStr;
                    }

                    public void setPriceStr(String priceStr) {
                        this.priceStr = priceStr;
                    }

                    public int getUnit() {
                        return unit;
                    }

                    public void setUnit(int unit) {
                        this.unit = unit;
                    }

                    public int getHighNum() {
                        return highNum;
                    }

                    public void setHighNum(int highNum) {
                        this.highNum = highNum;
                    }

                    public int getProductNum() {
                        return productNum;
                    }

                    public void setProductNum(int productNum) {
                        this.productNum = productNum;
                    }

                    public String getUnitDesc() {
                        return unitDesc;
                    }

                    public void setUnitDesc(String unitDesc) {
                        this.unitDesc = unitDesc;
                    }

                    public Object getProductCombinationPriceId() {
                        return productCombinationPriceId;
                    }

                    public void setProductCombinationPriceId(Object productCombinationPriceId) {
                        this.productCombinationPriceId = productCombinationPriceId;
                    }

                    public String getOldPrice() {
                        return oldPrice;
                    }

                    public void setOldPrice(String oldPrice) {
                        this.oldPrice = oldPrice;
                    }
                }
            }
        }
    }
}
