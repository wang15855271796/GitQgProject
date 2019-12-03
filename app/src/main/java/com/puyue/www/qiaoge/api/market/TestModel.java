package com.puyue.www.qiaoge.api.market;

import java.util.List;

/**
 * Created by ${王涛} on 2019/10/28
 */
public class TestModel {


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

        private String sendAmount;
        private List<ValidListBean> validList;
        private List<?> inValidList;

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

        public List<?> getInValidList() {
            return inValidList;
        }

        public void setInValidList(List<?> inValidList) {
            this.inValidList = inValidList;
        }

        public static class ValidListBean {

            private String productName;
            private String flagUrl;
            private String defaultPic;
            private int businessType;
            private int productMainId;
            private int businessId;
            private boolean valid;
            private List<SpecProductListBean> specProductList;

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

            public static class SpecProductListBean {
                /**
                 * businessId : 501
                 * inventory : 库存：50包
                 * cartId : 16539
                 * picUrl : null
                 * spec : 500g1kg*8包/箱
                 * productDescVOList : [{"price":"1.00","priceStr":"￥1.00/包","unit":954,"highNum":1,"productNum":1,"unitDesc":"","productCombinationPriceId":null,"oldPrice":"5.00/包"}]
                 * additionProductVOList : null
                 */

                private int businessId;
                private String inventory;
                private int cartId;
                private Object picUrl;
                private String spec;
                private List<AdditionProductVOList> additionProductVOList;
                private List<ProductDescVOListBean> productDescVOList;

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

                public List<AdditionProductVOList> getAdditionProductVOList() {
                    return additionProductVOList;
                }

                public void setAdditionProductVOList(List<AdditionProductVOList> additionProductVOList) {
                    this.additionProductVOList = additionProductVOList;
                }

                public List<ProductDescVOListBean> getProductDescVOList() {
                    return productDescVOList;
                }

                public void setProductDescVOList(List<ProductDescVOListBean> productDescVOList) {
                    this.productDescVOList = productDescVOList;
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
                public static class ProductDescVOListBean {
                    /**
                     * price : 1.00
                     * priceStr : ￥1.00/包
                     * unit : 954
                     * highNum : 1
                     * productNum : 1
                     * unitDesc :
                     * productCombinationPriceId : null
                     * oldPrice : 5.00/包
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
