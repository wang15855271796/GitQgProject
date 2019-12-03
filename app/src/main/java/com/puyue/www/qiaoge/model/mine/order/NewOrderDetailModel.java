package com.puyue.www.qiaoge.model.mine.order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${daff}
 * on 2018/10/20
 * 备注
 */
public class NewOrderDetailModel  {


    /**
     * code : 1
     * message : 成功
     * data : {"orderId":"61486bcd33204a03b7c35e9645931551","gmtCreate":"2018-09-27 16:36:21","payDate":"2018-09-27 16:39:13","confirmDate":"2018-10-20 10:55:05","finishDate":"2018-10-20 10:56:41","addressVO":{"id":32,"userId":326,"userName":"方慧仪","contactPhone":"17369639769","provinceCode":"340000","provinceName":"安徽省","cityCode":"340100","cityName":"合肥市","areaCode":"340101","areaName":"","detailAddress":"地址","isDefault":1,"shopName":"糖醋排骨汤"},"orderStatus":5,"orderStatusName":"待评价","remark":"林俊杰","dateList":["下单时间 : 2018-09-27 16:36:21","付款时间 : 2018-09-27 16:39:13","发货时间 : 2018-10-20 10:55:05","成交时间 : 2018-10-20 10:56:41"],"payChannel":"微信支付","hasSetPayPwd":true,"hasDefaultAddressFlag":true,"canReturnGoods":true,"cannotReturnGoodsMsg":"","totalAmount":"3000","prodAmount":"3000","deliveryFee":"0","sendAmount":"100","orderType":9,"totalNum":1,"productVOList":[{"productId":12,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/86075f6541d74b57961ce911350bcf69.jpg","name":"1511213","spec":"箱","costPrice":"0.00","averagePrice":"0.00","amount":"3000.0","productNum":0,"businessType":1,"checkStatus":null,"productDescVOList":[{"detailDesc":"￥1000.00/1箱","totalNum":"X3","productNum":3,"productCombinationPriceId":46,"productUnit":"箱","price":"1000.00","unit":18,"newDesc":"￥1000.00*3箱"}],"startTime":"","endTime":"","days":0,"price":"3000.0","returnProductMainId":null,"oldPrice":null,"prodTypeUrl":null}],"applyReturnDate":"","agreeDate":"","checkDate":"","feedbackReson":"","checkStatus":null,"checkStatusName":"","returnReson":"","actuallyReturnProductDetail":"","actuallyreturnAmount":"","returnProductNum":1,"deductDetail":null,"offerAmount":"0","proActAmount":null,"teamAmount":null,"killAmount":null,"normalAmount":null,"deductDesc":null,"isVip":false,"notVipDesc":"","offerIsOpen":true,"vipReduct":"0","normalReduct":"0","vipReductDesc":"","normalReductDesc":""}
     * success : true
     * error : false
     */

    private int code;
    private String message;
    private DataBean data;
    private boolean success;
    private boolean error;

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

    public static class DataBean {
        /**
         * orderId : 61486bcd33204a03b7c35e9645931551
         * gmtCreate : 2018-09-27 16:36:21
         * payDate : 2018-09-27 16:39:13
         * confirmDate : 2018-10-20 10:55:05
         * finishDate : 2018-10-20 10:56:41
         * addressVO : {"id":32,"userId":326,"userName":"方慧仪","contactPhone":"17369639769","provinceCode":"340000","provinceName":"安徽省","cityCode":"340100","cityName":"合肥市","areaCode":"340101","areaName":"","detailAddress":"地址","isDefault":1,"shopName":"糖醋排骨汤"}
         * orderStatus : 5
         * orderStatusName : 待评价
         * remark : 林俊杰
         * dateList : ["下单时间 : 2018-09-27 16:36:21","付款时间 : 2018-09-27 16:39:13","发货时间 : 2018-10-20 10:55:05","成交时间 : 2018-10-20 10:56:41"]
         * payChannel : 微信支付
         * hasSetPayPwd : true
         * hasDefaultAddressFlag : true
         * canReturnGoods : true
         * cannotReturnGoodsMsg :
         * totalAmount : 3000
         * prodAmount : 3000
         * deliveryFee : 0
         * sendAmount : 100
         * orderType : 9
         * totalNum : 1
         * productVOList : [{"productId":12,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/86075f6541d74b57961ce911350bcf69.jpg","name":"1511213","spec":"箱","costPrice":"0.00","averagePrice":"0.00","amount":"3000.0","productNum":0,"businessType":1,"checkStatus":null,"productDescVOList":[{"detailDesc":"￥1000.00/1箱","totalNum":"X3","productNum":3,"productCombinationPriceId":46,"productUnit":"箱","price":"1000.00","unit":18,"newDesc":"￥1000.00*3箱"}],"startTime":"","endTime":"","days":0,"price":"3000.0","returnProductMainId":null,"oldPrice":null,"prodTypeUrl":null}]
         * applyReturnDate :
         * agreeDate :
         * checkDate :
         * feedbackReson :
         * checkStatus : null
         * checkStatusName :
         * returnReson :
         * actuallyReturnProductDetail :
         * actuallyreturnAmount :
         * returnProductNum : 1
         * deductDetail : null
         * offerAmount : 0
         * proActAmount : null
         * teamAmount : null
         * killAmount : null
         * normalAmount : null
         * deductDesc : null
         * isVip : false
         * notVipDesc :
         * offerIsOpen : true
         * vipReduct : 0
         * normalReduct : 0
         * vipReductDesc :
         * normalReductDesc :
         */

        private String orderId;
        private String gmtCreate;
        private String payDate;
        private String confirmDate;
        private String finishDate;
        private AddressVOBean addressVO;
        private int orderStatus;
        private String orderStatusName;
        private String remark;
        private String payChannel;
        private boolean hasSetPayPwd;
        private boolean hasDefaultAddressFlag;
        private boolean canReturnGoods;
        private String cannotReturnGoodsMsg;
        private String totalAmount;
        private String prodAmount;
        private String deliveryFee;
        private String sendAmount;
        private int orderType;
        private int totalNum;
        private String applyReturnDate;
        private String agreeDate;
        private String checkDate;
        private String feedbackReson;
        private Object checkStatus;
        private String checkStatusName;
        private String returnReson;
        private String actuallyReturnProductDetail;
        private String actuallyreturnAmount;
        private int returnProductNum;
        private String deductDetail;
        private String offerAmount;
        private String proActAmount;
        private String teamAmount;
        private String killAmount;
        private String normalAmount;
        private String deductDesc;
        private boolean isVip;
        private String notVipDesc;
        private boolean offerIsOpen;
        private String vipReduct;
        private String normalReduct;
        private String vipReductDesc;
        private String normalReductDesc;
        private List<String> dateList;
        private List<ProductVOListBean> productVOList;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getConfirmDate() {
            return confirmDate;
        }

        public void setConfirmDate(String confirmDate) {
            this.confirmDate = confirmDate;
        }

        public String getFinishDate() {
            return finishDate;
        }

        public void setFinishDate(String finishDate) {
            this.finishDate = finishDate;
        }

        public AddressVOBean getAddressVO() {
            return addressVO;
        }

        public void setAddressVO(AddressVOBean addressVO) {
            this.addressVO = addressVO;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getOrderStatusName() {
            return orderStatusName;
        }

        public void setOrderStatusName(String orderStatusName) {
            this.orderStatusName = orderStatusName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
        }

        public boolean isHasSetPayPwd() {
            return hasSetPayPwd;
        }

        public void setHasSetPayPwd(boolean hasSetPayPwd) {
            this.hasSetPayPwd = hasSetPayPwd;
        }

        public boolean isHasDefaultAddressFlag() {
            return hasDefaultAddressFlag;
        }

        public void setHasDefaultAddressFlag(boolean hasDefaultAddressFlag) {
            this.hasDefaultAddressFlag = hasDefaultAddressFlag;
        }

        public boolean isCanReturnGoods() {
            return canReturnGoods;
        }

        public void setCanReturnGoods(boolean canReturnGoods) {
            this.canReturnGoods = canReturnGoods;
        }

        public String getCannotReturnGoodsMsg() {
            return cannotReturnGoodsMsg;
        }

        public void setCannotReturnGoodsMsg(String cannotReturnGoodsMsg) {
            this.cannotReturnGoodsMsg = cannotReturnGoodsMsg;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getProdAmount() {
            return prodAmount;
        }

        public void setProdAmount(String prodAmount) {
            this.prodAmount = prodAmount;
        }

        public String getDeliveryFee() {
            return deliveryFee;
        }

        public void setDeliveryFee(String deliveryFee) {
            this.deliveryFee = deliveryFee;
        }

        public String getSendAmount() {
            return sendAmount;
        }

        public void setSendAmount(String sendAmount) {
            this.sendAmount = sendAmount;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getApplyReturnDate() {
            return applyReturnDate;
        }

        public void setApplyReturnDate(String applyReturnDate) {
            this.applyReturnDate = applyReturnDate;
        }

        public String getAgreeDate() {
            return agreeDate;
        }

        public void setAgreeDate(String agreeDate) {
            this.agreeDate = agreeDate;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getFeedbackReson() {
            return feedbackReson;
        }

        public void setFeedbackReson(String feedbackReson) {
            this.feedbackReson = feedbackReson;
        }

        public Object getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(Object checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getCheckStatusName() {
            return checkStatusName;
        }

        public void setCheckStatusName(String checkStatusName) {
            this.checkStatusName = checkStatusName;
        }

        public String getReturnReson() {
            return returnReson;
        }

        public void setReturnReson(String returnReson) {
            this.returnReson = returnReson;
        }

        public String getActuallyReturnProductDetail() {
            return actuallyReturnProductDetail;
        }

        public void setActuallyReturnProductDetail(String actuallyReturnProductDetail) {
            this.actuallyReturnProductDetail = actuallyReturnProductDetail;
        }

        public String getActuallyreturnAmount() {
            return actuallyreturnAmount;
        }

        public void setActuallyreturnAmount(String actuallyreturnAmount) {
            this.actuallyreturnAmount = actuallyreturnAmount;
        }

        public int getReturnProductNum() {
            return returnProductNum;
        }

        public void setReturnProductNum(int returnProductNum) {
            this.returnProductNum = returnProductNum;
        }

        public String getDeductDetail() {
            return deductDetail;
        }

        public void setDeductDetail(String deductDetail) {
            this.deductDetail = deductDetail;
        }

        public String getOfferAmount() {
            return offerAmount;
        }

        public void setOfferAmount(String offerAmount) {
            this.offerAmount = offerAmount;
        }

        public String getProActAmount() {
            return proActAmount;
        }

        public void setProActAmount(String proActAmount) {
            this.proActAmount = proActAmount;
        }

        public String getTeamAmount() {
            return teamAmount;
        }

        public void setTeamAmount(String teamAmount) {
            this.teamAmount = teamAmount;
        }

        public String getKillAmount() {
            return killAmount;
        }

        public void setKillAmount(String killAmount) {
            this.killAmount = killAmount;
        }

        public String getNormalAmount() {
            return normalAmount;
        }

        public void setNormalAmount(String normalAmount) {
            this.normalAmount = normalAmount;
        }

        public String getDeductDesc() {
            return deductDesc;
        }

        public void setDeductDesc(String deductDesc) {
            this.deductDesc = deductDesc;
        }

        public boolean isIsVip() {
            return isVip;
        }

        public void setIsVip(boolean isVip) {
            this.isVip = isVip;
        }

        public String getNotVipDesc() {
            return notVipDesc;
        }

        public void setNotVipDesc(String notVipDesc) {
            this.notVipDesc = notVipDesc;
        }

        public boolean isOfferIsOpen() {
            return offerIsOpen;
        }

        public void setOfferIsOpen(boolean offerIsOpen) {
            this.offerIsOpen = offerIsOpen;
        }

        public String getVipReduct() {
            return vipReduct;
        }

        public void setVipReduct(String vipReduct) {
            this.vipReduct = vipReduct;
        }

        public String getNormalReduct() {
            return normalReduct;
        }

        public void setNormalReduct(String normalReduct) {
            this.normalReduct = normalReduct;
        }

        public String getVipReductDesc() {
            return vipReductDesc;
        }

        public void setVipReductDesc(String vipReductDesc) {
            this.vipReductDesc = vipReductDesc;
        }

        public String getNormalReductDesc() {
            return normalReductDesc;
        }

        public void setNormalReductDesc(String normalReductDesc) {
            this.normalReductDesc = normalReductDesc;
        }

        public List<String> getDateList() {
            return dateList;
        }

        public void setDateList(List<String> dateList) {
            this.dateList = dateList;
        }

        public List<ProductVOListBean> getProductVOList() {
            return productVOList;
        }

        public void setProductVOList(List<ProductVOListBean> productVOList) {
            this.productVOList = productVOList;
        }

        public static class AddressVOBean {
            /**
             * id : 32
             * userId : 326
             * userName : 方慧仪
             * contactPhone : 17369639769
             * provinceCode : 340000
             * provinceName : 安徽省
             * cityCode : 340100
             * cityName : 合肥市
             * areaCode : 340101
             * areaName :
             * detailAddress : 地址
             * isDefault : 1
             * shopName : 糖醋排骨汤
             */

            private int id;
            private int userId;
            private String userName;
            private String contactPhone;
            private String provinceCode;
            private String provinceName;
            private String cityCode;
            private String cityName;
            private String areaCode;
            private String areaName;
            private String detailAddress;
            private int isDefault;
            private String shopName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getContactPhone() {
                return contactPhone;
            }

            public void setContactPhone(String contactPhone) {
                this.contactPhone = contactPhone;
            }

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public String getCityCode() {
                return cityCode;
            }

            public void setCityCode(String cityCode) {
                this.cityCode = cityCode;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getDetailAddress() {
                return detailAddress;
            }

            public void setDetailAddress(String detailAddress) {
                this.detailAddress = detailAddress;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }
        }

        public static class ProductVOListBean {
            /**
             * productId : 12
             * picUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/86075f6541d74b57961ce911350bcf69.jpg
             * name : 1511213
             * spec : 箱
             * costPrice : 0.00
             * averagePrice : 0.00
             * amount : 3000.0
             * productNum : 0
             * businessType : 1
             * checkStatus : null
             * productDescVOList : [{"detailDesc":"￥1000.00/1箱","totalNum":"X3","productNum":3,"productCombinationPriceId":46,"productUnit":"箱","price":"1000.00","unit":18,"newDesc":"￥1000.00*3箱"}]
             * startTime :
             * endTime :
             * days : 0
             * price : 3000.0
             * returnProductMainId : null
             * oldPrice : null
             * prodTypeUrl : null
             */

            private int productId;
            private String picUrl;
            private String name;
            private String spec;
            private String costPrice;
            private String averagePrice;
            private String amount;
            private int productNum;
            private int businessType;
            private Object checkStatus;
            private String startTime;
            private String endTime;
            private int days;
            private String price;
            private String returnProductMainId;
            private String oldPrice;
            private String prodTypeUrl;
            private List<ProductDescVOListBean> productDescVOList;

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
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

            public String getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(String costPrice) {
                this.costPrice = costPrice;
            }

            public String getAveragePrice() {
                return averagePrice;
            }

            public void setAveragePrice(String averagePrice) {
                this.averagePrice = averagePrice;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getProductNum() {
                return productNum;
            }

            public void setProductNum(int productNum) {
                this.productNum = productNum;
            }

            public int getBusinessType() {
                return businessType;
            }

            public void setBusinessType(int businessType) {
                this.businessType = businessType;
            }

            public Object getCheckStatus() {
                return checkStatus;
            }

            public void setCheckStatus(Object checkStatus) {
                this.checkStatus = checkStatus;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
                this.days = days;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getReturnProductMainId() {
                return returnProductMainId;
            }

            public void setReturnProductMainId(String returnProductMainId) {
                this.returnProductMainId = returnProductMainId;
            }

            public String getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(String oldPrice) {
                this.oldPrice = oldPrice;
            }

            public String getProdTypeUrl() {
                return prodTypeUrl;
            }

            public void setProdTypeUrl(String prodTypeUrl) {
                this.prodTypeUrl = prodTypeUrl;
            }

            public List<ProductDescVOListBean> getProductDescVOList() {
                return productDescVOList;
            }

            public void setProductDescVOList(List<ProductDescVOListBean> productDescVOList) {
                this.productDescVOList = productDescVOList;
            }

            public static class ProductDescVOListBean {
                /**
                 * detailDesc : ￥1000.00/1箱
                 * totalNum : X3
                 * productNum : 3
                 * productCombinationPriceId : 46
                 * productUnit : 箱
                 * price : 1000.00
                 * unit : 18
                 * newDesc : ￥1000.00*3箱
                 */

                private String detailDesc;
                private String totalNum;
                private int productNum;
                private int productCombinationPriceId;
                private String productUnit;
                private String price;
                private int unit;
                private String newDesc;

                public String getDetailDesc() {
                    return detailDesc;
                }

                public void setDetailDesc(String detailDesc) {
                    this.detailDesc = detailDesc;
                }

                public String getTotalNum() {
                    return totalNum;
                }

                public void setTotalNum(String totalNum) {
                    this.totalNum = totalNum;
                }

                public int getProductNum() {
                    return productNum;
                }

                public void setProductNum(int productNum) {
                    this.productNum = productNum;
                }

                public int getProductCombinationPriceId() {
                    return productCombinationPriceId;
                }

                public void setProductCombinationPriceId(int productCombinationPriceId) {
                    this.productCombinationPriceId = productCombinationPriceId;
                }

                public String getProductUnit() {
                    return productUnit;
                }

                public void setProductUnit(String productUnit) {
                    this.productUnit = productUnit;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getUnit() {
                    return unit;
                }

                public void setUnit(int unit) {
                    this.unit = unit;
                }

                public String getNewDesc() {
                    return newDesc;
                }

                public void setNewDesc(String newDesc) {
                    this.newDesc = newDesc;
                }
            }
        }
    }
}
