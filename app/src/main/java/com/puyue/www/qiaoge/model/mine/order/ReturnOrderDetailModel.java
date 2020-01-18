package com.puyue.www.qiaoge.model.mine.order;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/15
 */
public class ReturnOrderDetailModel {

    /**
     * code : 1
     * message : 成功
     * data : {"offerAmount":"767.20","products":[{"productName":"【翘歌】五花肉串（20串/包）","spec":"500","flagUrl":null,"businessType":1,"businessId":3,"details":[{"price":"260","num":2,"totalPrice":"520.00","unitId":4,"desc":"￥260/困*2困","returnUnits":[{"unitId":5,"unitName":"个"},{"unitId":4,"unitName":"困"}]}],"returnNum":""},{"productName":"昶烨香辣鸡肉排（0.9kg/包）","spec":"30g*33条*12包/箱","flagUrl":null,"businessType":1,"businessId":78,"details":[{"price":"26.17","num":1,"totalPrice":"26.17","unitId":96,"desc":"￥26.17/包*1包","returnUnits":[{"unitId":96,"unitName":"包"}]}],"returnNum":""},{"productName":"雄丰香港撒尿肉丸（牛肉风味2.5kg/包）","spec":"0防守打法1234567890123456789012345678901234567892652612561465/箱","flagUrl":null,"businessType":1,"businessId":41,"details":[{"price":"1.03","num":1,"totalPrice":"1.03","unitId":49,"desc":"￥1.03/箱*1箱","returnUnits":[{"unitId":49,"unitName":"箱"}]}],"returnNum":""},{"productName":"老潼关肉夹馍（10片/包）","spec":"商品规格测试123456789（）【】商品规格测试123456789","flagUrl":null,"businessType":1,"businessId":69,"details":[{"price":"30","num":1,"totalPrice":"30.00","unitId":80,"desc":"￥30/箱*1箱","returnUnits":[{"unitId":80,"unitName":"箱"}]}],"returnNum":""},{"productName":"【文畅】鱼肉花（白-2.5kg/包）","spec":"121","flagUrl":null,"businessType":1,"businessId":32,"details":[{"price":"5","num":1,"totalPrice":"5.00","unitId":40,"desc":"￥5/箱*1箱","returnUnits":[{"unitId":40,"unitName":"箱"}]}],"returnNum":""},{"productName":"【鱼极】香港撒尿牛肉丸(120g)","spec":"20只/箱","flagUrl":null,"businessType":1,"businessId":72,"details":[{"price":"140","num":1,"totalPrice":"140.00","unitId":85,"desc":"￥140/箱*1箱","returnUnits":[{"unitId":85,"unitName":"箱"}]}],"returnNum":""},{"productName":"组合单位5","spec":"123456/箱","flagUrl":null,"businessType":1,"businessId":101,"details":[{"price":"40","num":1,"totalPrice":"40.00","unitId":141,"desc":"￥40/箱*1箱","returnUnits":[{"unitId":141,"unitName":"箱"},{"unitId":142,"unitName":"包"},{"unitId":143,"unitName":"支"}]}],"returnNum":""}]}
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
         * offerAmount : 767.20
         * products : [{"productName":"【翘歌】五花肉串（20串/包）","spec":"500","flagUrl":null,"businessType":1,"businessId":3,"details":[{"price":"260","num":2,"totalPrice":"520.00","unitId":4,"desc":"￥260/困*2困","returnUnits":[{"unitId":5,"unitName":"个"},{"unitId":4,"unitName":"困"}]}],"returnNum":""},{"productName":"昶烨香辣鸡肉排（0.9kg/包）","spec":"30g*33条*12包/箱","flagUrl":null,"businessType":1,"businessId":78,"details":[{"price":"26.17","num":1,"totalPrice":"26.17","unitId":96,"desc":"￥26.17/包*1包","returnUnits":[{"unitId":96,"unitName":"包"}]}],"returnNum":""},{"productName":"雄丰香港撒尿肉丸（牛肉风味2.5kg/包）","spec":"0防守打法1234567890123456789012345678901234567892652612561465/箱","flagUrl":null,"businessType":1,"businessId":41,"details":[{"price":"1.03","num":1,"totalPrice":"1.03","unitId":49,"desc":"￥1.03/箱*1箱","returnUnits":[{"unitId":49,"unitName":"箱"}]}],"returnNum":""},{"productName":"老潼关肉夹馍（10片/包）","spec":"商品规格测试123456789（）【】商品规格测试123456789","flagUrl":null,"businessType":1,"businessId":69,"details":[{"price":"30","num":1,"totalPrice":"30.00","unitId":80,"desc":"￥30/箱*1箱","returnUnits":[{"unitId":80,"unitName":"箱"}]}],"returnNum":""},{"productName":"【文畅】鱼肉花（白-2.5kg/包）","spec":"121","flagUrl":null,"businessType":1,"businessId":32,"details":[{"price":"5","num":1,"totalPrice":"5.00","unitId":40,"desc":"￥5/箱*1箱","returnUnits":[{"unitId":40,"unitName":"箱"}]}],"returnNum":""},{"productName":"【鱼极】香港撒尿牛肉丸(120g)","spec":"20只/箱","flagUrl":null,"businessType":1,"businessId":72,"details":[{"price":"140","num":1,"totalPrice":"140.00","unitId":85,"desc":"￥140/箱*1箱","returnUnits":[{"unitId":85,"unitName":"箱"}]}],"returnNum":""},{"productName":"组合单位5","spec":"123456/箱","flagUrl":null,"businessType":1,"businessId":101,"details":[{"price":"40","num":1,"totalPrice":"40.00","unitId":141,"desc":"￥40/箱*1箱","returnUnits":[{"unitId":141,"unitName":"箱"},{"unitId":142,"unitName":"包"},{"unitId":143,"unitName":"支"}]}],"returnNum":""}]
         */

        private String offerAmount;
        private List<ProductsBean> products;


        public List<ReturnChannelBean> returnChannel;


        public String getOfferAmount() {
            return offerAmount;
        }

        public void setOfferAmount(String offerAmount) {
            this.offerAmount = offerAmount;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ReturnChannelBean {

            public String channelValue;//渠道value值
            public String channelText;//渠道展示文本

        }

        public static class ProductsBean {
            /**
             * productName : 【翘歌】五花肉串（20串/包）
             * spec : 500
             * flagUrl : null
             * businessType : 1
             * businessId : 3
             * details : [{"price":"260","num":2,"totalPrice":"520.00","unitId":4,"desc":"￥260/困*2困","returnUnits":[{"unitId":5,"unitName":"个"},{"unitId":4,"unitName":"困"}]}]
             * returnNum :
             * additionFlag  0普通 1赠品
             */
            private String defaultPic;

            public String getDefaultPic() {
                return defaultPic;
            }

            public void setDefaultPic(String defaultPic) {
                this.defaultPic = defaultPic;
            }

            public int additionFlag;
            private String productName;
            private String spec;
            private Object flagUrl;
            private int businessType;
            private int businessId;
            private String returnNum;
            private List<DetailsBean> details;


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

            public Object getFlagUrl() {
                return flagUrl;
            }

            public void setFlagUrl(Object flagUrl) {
                this.flagUrl = flagUrl;
            }

            public int getBusinessType() {
                return businessType;
            }

            public void setBusinessType(int businessType) {
                this.businessType = businessType;
            }

            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public String getReturnNum() {
                return returnNum;
            }

            public void setReturnNum(String returnNum) {
                this.returnNum = returnNum;
            }

            public List<DetailsBean> getDetails() {
                return details;
            }

            public void setDetails(List<DetailsBean> details) {
                this.details = details;
            }

            public static class DetailsBean {
                /**
                 * price : 260
                 * num : 2
                 * totalPrice : 520.00
                 * unitId : 4
                 * desc : ￥260/困*2困
                 * returnUnits : [{"unitId":5,"unitName":"个"},{"unitId":4,"unitName":"困"}]
                 * deductPrice 优惠均价
                 */
                private int businessType;
                private int businessId;
                private String price;
                private int num;
                private String totalPrice;
                private double itemPrice;
                private int unitId;
                private String desc;
                private List<ReturnUnitsBean> returnUnits;
                private int priceId;
                private int itemNum;
                private int itemUnitId;
                String afterPrice;

                public String getAfterPrice() {
                    return afterPrice;
                }

                public void setAfterPrice(String afterPrice) {
                    this.afterPrice = afterPrice;
                }

                public String getDeductPrice() {
                    return deductPrice;
                }

                public void setDeductPrice(String deductPrice) {
                    this.deductPrice = deductPrice;
                }

                private String deductPrice;

                public int getItemUnitId() {
                    return itemUnitId;
                }

                public void setItemUnitId(int itemUnitId) {
                    this.itemUnitId = itemUnitId;
                }

                public int getItemNum() {
                    return itemNum;
                }

                public void setItemNum(int itemNum) {
                    this.itemNum = itemNum;
                }

                public double getItemPrice() {
                    return itemPrice;
                }

                public void setItemPrice(double itemPrice) {
                    this.itemPrice = itemPrice;
                }

                public int getPriceId() {
                    return priceId;
                }

                public void setPriceId(int priceId) {
                    this.priceId = priceId;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(String totalPrice) {
                    this.totalPrice = totalPrice;
                }

                public int getUnitId() {
                    return unitId;
                }

                public void setUnitId(int unitId) {
                    this.unitId = unitId;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public int getBusinessType() {
                    return businessType;
                }

                public void setBusinessType(int businessType) {
                    this.businessType = businessType;
                }

                public int getBusinessId() {
                    return businessId;
                }

                public void setBusinessId(int businessId) {
                    this.businessId = businessId;
                }

                public List<ReturnUnitsBean> getReturnUnits() {
                    return returnUnits;
                }

                public void setReturnUnits(List<ReturnUnitsBean> returnUnits) {
                    this.returnUnits = returnUnits;
                }

                public static class ReturnUnitsBean {
                    /**
                     * unitId : 5
                     * unitName : 个
                     */

                    private int unitId;
                    private String unitName;

                    public int getUnitId() {
                        return unitId;
                    }

                    public void setUnitId(int unitId) {
                        this.unitId = unitId;
                    }

                    public String getUnitName() {
                        return unitName;
                    }

                    public void setUnitName(String unitName) {
                        this.unitName = unitName;
                    }
                }
            }
        }
    }
}
