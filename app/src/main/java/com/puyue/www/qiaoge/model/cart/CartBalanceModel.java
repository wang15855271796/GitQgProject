package com.puyue.www.qiaoge.model.cart;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/5/5.
 */

public class CartBalanceModel extends BaseModel {

    /**
     * data : {"orderId":"","gmtCreate":"","payDate":"","confirmDate":"","finishDate":"","addressVO":{"id":29,"userId":302,"userName":"韩铝熙","contactPhone":"17369639769","provinceCode":"330000","provinceName":"浙江省","cityCode":"330100","cityName":"杭州市","areaCode":"330108","areaName":"滨江区","detailAddress":"地址详细。","isDefault":1,"shopName":"京东"},"orderStatus":null,"orderStatusName":"","remark":"","dateList":null,"hasSetPayPwd":true,"hasDefaultAddressFlag":true,"canReturnGoods":true,"cannotReturnGoodsMsg":"","totalAmount":"79.00","prodAmount":"69.0","deliveryFee":"10.00","sendAmount":"100.00","orderType":null,"totalNum":3,"productVOList":[{"productId":11,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/f3b1ef3516aa4a20bc6c4228d1d2aaea.jpg","name":"肉串组合套餐","spec":"一箱","costPrice":"","averagePrice":"","amount":"69.0","productNum":3,"businessType":3,"checkStatus":null,"productDescVOList":[{"detailDesc":"￥23.00/组","totalNum":"X3","productNum":3,"productCombinationPriceId":0,"productUnit":"组","price":"23.00","unit":null,"newDesc":"￥23.00*3组"}],"startTime":"","endTime":"","days":0,"price":null,"returnProductMainId":null,"oldPrice":null,"prodTypeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/13798e3054d34a8fa805612e7babb11c.png"}],"applyReturnDate":"","agreeDate":"","checkDate":"","feedbackReson":"","checkStatus":null,"checkStatusName":"","returnReson":"","actuallyReturnProductDetail":"","actuallyreturnAmount":"","deductDetail":null,"offerAmount":"0","proActAmount":"79.00","teamAmount":"79.00","killAmount":"10.00","normalAmount":"10.00"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderId :
         * gmtCreate :
         * payDate :
         * confirmDate :
         * finishDate :
         * addressVO : {"id":29,"userId":302,"userName":"韩铝熙","contactPhone":"17369639769","provinceCode":"330000","provinceName":"浙江省","cityCode":"330100","cityName":"杭州市","areaCode":"330108","areaName":"滨江区","detailAddress":"地址详细。","isDefault":1,"shopName":"京东"}
         * orderStatus : null
         * orderStatusName :
         * remark :
         * dateList : null
         * hasSetPayPwd : true
         * hasDefaultAddressFlag : true
         * canReturnGoods : true
         * cannotReturnGoodsMsg :
         * totalAmount : 79.00
         * prodAmount : 69.0
         * deliveryFee : 10.00
         * sendAmount : 100.00
         * orderType : null
         * totalNum : 3
         * productVOList : [{"productId":11,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/f3b1ef3516aa4a20bc6c4228d1d2aaea.jpg","name":"肉串组合套餐","spec":"一箱","costPrice":"","averagePrice":"","amount":"69.0","productNum":3,"businessType":3,"checkStatus":null,"productDescVOList":[{"detailDesc":"￥23.00/组","totalNum":"X3","productNum":3,"productCombinationPriceId":0,"productUnit":"组","price":"23.00","unit":null,"newDesc":"￥23.00*3组"}],"startTime":"","endTime":"","days":0,"price":null,"returnProductMainId":null,"oldPrice":null,"prodTypeUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/13798e3054d34a8fa805612e7babb11c.png"}]
         * applyReturnDate :
         * agreeDate :
         * checkDate :
         * feedbackReson :
         * checkStatus : null
         * checkStatusName :
         * returnReson :
         * actuallyReturnProductDetail :
         * actuallyreturnAmount :
         * deductDetail : null
         * offerAmount : 0
         * proActAmount : 79.00
         * teamAmount : 79.00
         * killAmount : 10.00
         * normalAmount : 10.00
         * openVip true:首次，false续费
         * vipDesc 满减金额
         *
         * pickUserName 提货人
         *pickPhone  联系方式
         *wareAddress 提货地址
         *sendStartTime 提货时间
         *wareName  提货仓库名称
         * customerPhone
         */
        Double toRechargeAmount;
        boolean toRecharge;
        public String pickUserName;
        public String pickPhone;
        public String wareAddress;
        public String sendStartTime;
        public String wareName;
        public String customerPhone;
        private boolean openVip;
        private double vipDesc;
        private String orderId;
        private String gmtCreate;
        private String payDate;
        private String confirmDate;
        private String finishDate;
        private AddressVOBean addressVO;
        private Object orderStatus;
        private String orderStatusName;
        private String remark;
        private Object dateList;
        private boolean hasSetPayPwd;
        private boolean hasDefaultAddressFlag;
        private boolean canReturnGoods;
        private String cannotReturnGoodsMsg;
        private String totalAmount;
        private String prodAmount;
        private String deliveryFee;
        private String sendAmount;
        private Object orderType;
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
        private DeductDetailBean deductDetail;
        private String offerAmount;
        private String proActAmount;
        private String teamAmount;
        private String killAmount;
        private String normalAmount;
        private boolean isVip;
        public boolean addressOk;

        public boolean isAddressOk() {
            return addressOk;
        }

        public void setAddressOk(boolean addressOk) {
            this.addressOk = addressOk;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "toRechargeAmount=" + toRechargeAmount +
                    ", toRecharge=" + toRecharge +
                    ", pickUserName='" + pickUserName + '\'' +
                    ", pickPhone='" + pickPhone + '\'' +
                    ", wareAddress='" + wareAddress + '\'' +
                    ", sendStartTime='" + sendStartTime + '\'' +
                    ", wareName='" + wareName + '\'' +
                    ", customerPhone='" + customerPhone + '\'' +
                    ", openVip=" + openVip +
                    ", vipDesc=" + vipDesc +
                    ", orderId='" + orderId + '\'' +
                    ", gmtCreate='" + gmtCreate + '\'' +
                    ", payDate='" + payDate + '\'' +
                    ", confirmDate='" + confirmDate + '\'' +
                    ", finishDate='" + finishDate + '\'' +
                    ", addressVO=" + addressVO +
                    ", orderStatus=" + orderStatus +
                    ", orderStatusName='" + orderStatusName + '\'' +
                    ", remark='" + remark + '\'' +
                    ", dateList=" + dateList +
                    ", hasSetPayPwd=" + hasSetPayPwd +
                    ", hasDefaultAddressFlag=" + hasDefaultAddressFlag +
                    ", canReturnGoods=" + canReturnGoods +
                    ", cannotReturnGoodsMsg='" + cannotReturnGoodsMsg + '\'' +
                    ", totalAmount='" + totalAmount + '\'' +
                    ", prodAmount='" + prodAmount + '\'' +
                    ", deliveryFee='" + deliveryFee + '\'' +
                    ", sendAmount='" + sendAmount + '\'' +
                    ", orderType=" + orderType +
                    ", totalNum=" + totalNum +
                    ", applyReturnDate='" + applyReturnDate + '\'' +
                    ", agreeDate='" + agreeDate + '\'' +
                    ", checkDate='" + checkDate + '\'' +
                    ", feedbackReson='" + feedbackReson + '\'' +
                    ", checkStatus=" + checkStatus +
                    ", checkStatusName='" + checkStatusName + '\'' +
                    ", returnReson='" + returnReson + '\'' +
                    ", actuallyReturnProductDetail='" + actuallyReturnProductDetail + '\'' +
                    ", actuallyreturnAmount='" + actuallyreturnAmount + '\'' +
                    ", deductDetail=" + deductDetail +
                    ", offerAmount='" + offerAmount + '\'' +
                    ", proActAmount='" + proActAmount + '\'' +
                    ", teamAmount='" + teamAmount + '\'' +
                    ", killAmount='" + killAmount + '\'' +
                    ", normalAmount='" + normalAmount + '\'' +
                    ", isVip=" + isVip +
                    ", addressOk=" + addressOk +
                    ", notVipDesc='" + notVipDesc + '\'' +
                    ", offerIsOpen=" + offerIsOpen +
                    ", vipReduct='" + vipReduct + '\'' +
                    ", normalReduct='" + normalReduct + '\'' +
                    ", vipReductDesc='" + vipReductDesc + '\'' +
                    ", normalReductDesc='" + normalReductDesc + '\'' +
                    ", vipCenterUrl='" + vipCenterUrl + '\'' +
                    ", sysCurrentTime='" + sysCurrentTime + '\'' +
                    ", orderOverTime='" + orderOverTime + '\'' +
                    ", deductDesc='" + deductDesc + '\'' +
                    ", productVOList=" + productVOList +
                    '}';
        }

        public boolean isOpenVip() {
            return openVip;
        }
        public void setOpenVip(boolean openVip) {
            this.openVip = openVip;
        }

        public Double getVipDesc() {
            return vipDesc;
        }

        public Double getToRechargeAmount() {
            return toRechargeAmount;
        }

        public void setToRechargeAmount(Double toRechargeAmount) {
            this.toRechargeAmount = toRechargeAmount;
        }

        public boolean isToRecharge() {
            return toRecharge;
        }

        public void setToRecharge(boolean toRecharge) {
            this.toRecharge = toRecharge;
        }

        public void setVipDesc(Double vipDesc) {
            this.vipDesc = vipDesc;
        }

        public void setDeductDetail(DeductDetailBean deductDetail) {
            this.deductDetail = deductDetail;
        }

        public boolean isVip() {
            return isVip;
        }

        public void setVip(boolean vip) {
            isVip = vip;
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

        public String getVipCenterUrl() {
            return vipCenterUrl;
        }

        public void setVipCenterUrl(String vipCenterUrl) {
            this.vipCenterUrl = vipCenterUrl;
        }

        public String getSysCurrentTime() {
            return sysCurrentTime;
        }

        public void setSysCurrentTime(String sysCurrentTime) {
            this.sysCurrentTime = sysCurrentTime;
        }

        public String getOrderOverTime() {
            return orderOverTime;
        }

        public void setOrderOverTime(String orderOverTime) {
            this.orderOverTime = orderOverTime;
        }

        private String notVipDesc;
        private boolean offerIsOpen;
        private String vipReduct;
        private String normalReduct;
        private String vipReductDesc;
        private String normalReductDesc;
        private String vipCenterUrl;
        private String sysCurrentTime;
        private String orderOverTime;


        public String getDeductDesc() {
            return deductDesc;
        }

        public void setDeductDesc(String deductDesc) {
            this.deductDesc = deductDesc;
        }

        private String deductDesc;
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

        public Object getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(Object orderStatus) {
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

        public Object getDateList() {
            return dateList;
        }

        public void setDateList(Object dateList) {
            this.dateList = dateList;
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

        public Object getOrderType() {
            return orderType;
        }

        public void setOrderType(Object orderType) {
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

        public DeductDetailBean getDeductDetail() {
            return deductDetail;
        }

        public void setDeductDetail(String DeductDetailBean) {
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

        public List<ProductVOListBean> getProductVOList() {
            return productVOList;
        }

        public void setProductVOList(List<ProductVOListBean> productVOList) {
            this.productVOList = productVOList;
        }

        public static class AddressVOBean {
            @Override
            public String toString() {
                return "AddressVOBean{" +
                        "id=" + id +
                        ", userId=" + userId +
                        ", userName='" + userName + '\'' +
                        ", contactPhone='" + contactPhone + '\'' +
                        ", provinceCode='" + provinceCode + '\'' +
                        ", provinceName='" + provinceName + '\'' +
                        ", cityCode='" + cityCode + '\'' +
                        ", cityName='" + cityName + '\'' +
                        ", areaCode='" + areaCode + '\'' +
                        ", areaName='" + areaName + '\'' +
                        ", detailAddress='" + detailAddress + '\'' +
                        ", isDefault=" + isDefault +
                        ", shopName='" + shopName + '\'' +
                        '}';
            }

            /**
             * id : 29
             * userId : 302
             * userName : 韩铝熙
             * contactPhone : 17369639769
             * provinceCode : 330000
             * provinceName : 浙江省
             * cityCode : 330100
             * cityName : 杭州市
             * areaCode : 330108
             * areaName : 滨江区
             * detailAddress : 地址详细。
             * isDefault : 1
             * shopName : 京东
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
            @Override
            public String toString() {
                return "ProductVOListBean{" +
                        "productId=" + productId +
                        ", picUrl='" + picUrl + '\'' +
                        ", name='" + name + '\'' +
                        ", spec='" + spec + '\'' +
                        ", costPrice='" + costPrice + '\'' +
                        ", averagePrice='" + averagePrice + '\'' +
                        ", amount='" + amount + '\'' +
                        ", productNum=" + productNum +
                        ", businessType=" + businessType +
                        ", checkStatus=" + checkStatus +
                        ", startTime='" + startTime + '\'' +
                        ", endTime='" + endTime + '\'' +
                        ", days=" + days +
                        ", price=" + price +
                        ", returnProductMainId=" + returnProductMainId +
                        ", oldPrice=" + oldPrice +
                        ", prodTypeUrl='" + prodTypeUrl + '\'' +
                        ", productDescVOList=" + productDescVOList +
                        ", cartAdditionProductVOList=" + cartAdditionProductVOList +
                        ", newDesc='" + newDesc + '\'' +
                        '}';
            }

            /**
             * productId : 11
             * picUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/f3b1ef3516aa4a20bc6c4228d1d2aaea.jpg
             * name : 肉串组合套餐
             * spec : 一箱
             * costPrice :
             * averagePrice :
             * amount : 69.0
             * productNum : 3
             * businessType : 3
             * checkStatus : null
             * productDescVOList : [{"detailDesc":"￥23.00/组","totalNum":"X3","productNum":3,"productCombinationPriceId":0,"productUnit":"组","price":"23.00","unit":null,"newDesc":"￥23.00*3组"}]
             * startTime :
             * endTime :
             * days : 0
             * price : null
             * returnProductMainId : null
             * oldPrice : null
             * prodTypeUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/13798e3054d34a8fa805612e7babb11c.png
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
            private Object price;
            private Object returnProductMainId;
            private Object oldPrice;
            private String prodTypeUrl;
            private List<ProductDescVOListBean> productDescVOList;
            public List<AdditionProductVOListBean> cartAdditionProductVOList;


            public static class AdditionProductVOListBean {
                public String picUrl;
                public String name;//赠品名称
                public String spec;//规格
                public String inventory;//余量
                public String additionNum;//赠送数量
                public int additionFlag;//1，正常赠送，2，已赠完
                public String flagUrl;
                public String finishUrl;
            }
            public String getNewDesc() {
                return newDesc;
            }

            public void setNewDesc(String newDesc) {
                this.newDesc = newDesc;
            }

            private String newDesc;


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

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getReturnProductMainId() {
                return returnProductMainId;
            }

            public void setReturnProductMainId(Object returnProductMainId) {
                this.returnProductMainId = returnProductMainId;
            }

            public Object getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(Object oldPrice) {
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
                @Override
                public String toString() {
                    return "ProductDescVOListBean{" +
                            "detailDesc='" + detailDesc + '\'' +
                            ", totalNum='" + totalNum + '\'' +
                            ", productNum=" + productNum +
                            ", productCombinationPriceId=" + productCombinationPriceId +
                            ", productUnit='" + productUnit + '\'' +
                            ", price='" + price + '\'' +
                            ", unit=" + unit +
                            ", newDesc='" + newDesc + '\'' +
                            '}';
                }

                /**
                 * detailDesc : ￥23.00/组
                 * totalNum : X3
                 * productNum : 3
                 * productCombinationPriceId : 0
                 * productUnit : 组
                 * price : 23.00
                 * unit : null
                 * newDesc : ￥23.00*3组
                 */

                private String detailDesc;
                private String totalNum;
                private int productNum;
                private int productCombinationPriceId;
                private String productUnit;
                private String price;
                private Object unit;
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

                public Object getUnit() {
                    return unit;
                }

                public void setUnit(Object unit) {
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

        public static class DeductDetailBean {


            /**
             * giftName : 满200减10元
             * giftType : 满减券
             * amount : 10.0
             * amountStr : 10.00元
             * limitAmtStr : 满200.00元可用
             * limitAmt : 200.0
             * applyFrom : 人工积分兑换
             * dateTime : 2018-09-21-2018-12-21
             * role : ["不予秒杀，团购活动一起使用"]
             * overTimePic : null
             * usedPic : null
             * unAblePic : null
             * state : ENABLED
             * giftDetailNo : 112018092100000020
             */

            private String giftName;
            private String giftType;
            private double amount;
            private String amountStr;
            private String limitAmtStr;
            private double limitAmt;
            private String applyFrom;
            private String dateTime;
            private Object overTimePic;
            private Object usedPic;
            private Object unAblePic;
            private String state;
            private String giftDetailNo;
            private List<String> role;

            public String getGiftName() {
                return giftName;
            }

            public void setGiftName(String giftName) {
                this.giftName = giftName;
            }

            public String getGiftType() {
                return giftType;
            }

            public void setGiftType(String giftType) {
                this.giftType = giftType;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getAmountStr() {
                return amountStr;
            }

            public void setAmountStr(String amountStr) {
                this.amountStr = amountStr;
            }

            public String getLimitAmtStr() {
                return limitAmtStr;
            }

            public void setLimitAmtStr(String limitAmtStr) {
                this.limitAmtStr = limitAmtStr;
            }

            public double getLimitAmt() {
                return limitAmt;
            }

            public void setLimitAmt(double limitAmt) {
                this.limitAmt = limitAmt;
            }

            public String getApplyFrom() {
                return applyFrom;
            }

            public void setApplyFrom(String applyFrom) {
                this.applyFrom = applyFrom;
            }

            public String getDateTime() {
                return dateTime;
            }

            public void setDateTime(String dateTime) {
                this.dateTime = dateTime;
            }

            public Object getOverTimePic() {
                return overTimePic;
            }

            public void setOverTimePic(Object overTimePic) {
                this.overTimePic = overTimePic;
            }

            public Object getUsedPic() {
                return usedPic;
            }

            public void setUsedPic(Object usedPic) {
                this.usedPic = usedPic;
            }

            public Object getUnAblePic() {
                return unAblePic;
            }

            public void setUnAblePic(Object unAblePic) {
                this.unAblePic = unAblePic;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getGiftDetailNo() {
                return giftDetailNo;
            }

            public void setGiftDetailNo(String giftDetailNo) {
                this.giftDetailNo = giftDetailNo;
            }

            public List<String> getRole() {
                return role;
            }

            public void setRole(List<String> role) {
                this.role = role;
            }
        }


    }
}
