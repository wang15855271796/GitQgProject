package com.puyue.www.qiaoge.model.cart;

import com.puyue.www.qiaoge.base.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class GetOrderDetailModel {

    /**
     * code : 1
     * message : 成功
     * data : {"orderId":"65cfd8edfdb746a88aebf69372958e56","gmtCreate":"2018-04-26 20:31:26","payDate":"2018-05-05 11:51:30","confirmDate":"2018-05-05 11:51:21","finishDate":"2018-05-05 11:50:39","addressVO":{"id":21,"userId":27,"userName":"林俊杰","contactPhone":"15867121265","provinceCode":"330000","provinceName":"浙江省","cityCode":"330100","cityName":"杭州市","areaCode":"330106","areaName":"西湖区","detailAddress":"中旅紫金名门3202","isDefault":1,"shopName":"中旅店"},"orderStatus":2,"orderStatusName":"待发货","remark":"","dateList":["创建时间 : 2018-04-26 20:31:26","付款时间 : 2018-05-05 11:51:30","发货时间 : 2018-05-05 11:51:21","成交时间 : 2018-05-05 11:50:39"],"hasSetPayPwd":true,"hasDefaultAddressFlag":true,"canReturnGoods":false,"cannotReturnGoodsMsg":"已确认不能申请退货","totalAmount":"1500.00","orderType":5,"totalNum":0,"productVOList":[{"productId":1,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg","name":"师傅","spec":null,"amount":"1500.0","productNum":0,"businessType":5,"checkStatus":null,"productDescVOList":[{"detailDesc":"￥500.00/天","totalNum":"X3","productNum":3,"productCombinationPriceId":null,"productUnit":"天","price":"500.00"}]}],"applyReturnDate":"","agreeDate":"","checkDate":"","feedbackReson":"","checkStatus":null,"checkStatusName":""}
     * success : true
     * error : false
     */

    public int code;
    public String message;
    public DataBean data;
    public boolean success;
    public boolean error;

    public static class DataBean {
        /**
         * orderId : 65cfd8edfdb746a88aebf69372958e56
         * gmtCreate : 2018-04-26 20:31:26
         * payDate : 2018-05-05 11:51:30
         * confirmDate : 2018-05-05 11:51:21
         * finishDate : 2018-05-05 11:50:39
         * addressVO : {"id":21,"userId":27,"userName":"林俊杰","contactPhone":"15867121265","provinceCode":"330000","provinceName":"浙江省","cityCode":"330100","cityName":"杭州市","areaCode":"330106","areaName":"西湖区","detailAddress":"中旅紫金名门3202","isDefault":1,"shopName":"中旅店"}
         * orderStatus : 2
         * orderStatusName : 待发货
         * remark :
         * dateList : ["创建时间 : 2018-04-26 20:31:26","付款时间 : 2018-05-05 11:51:30","发货时间 : 2018-05-05 11:51:21","成交时间 : 2018-05-05 11:50:39"]
         * hasSetPayPwd : true
         * hasDefaultAddressFlag : true
         * canReturnGoods : false
         * cannotReturnGoodsMsg : 已确认不能申请退货
         * totalAmount : 1500.00
         * orderType : 5
         * totalNum : 0
         * productVOList : [{"productId":1,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg","name":"师傅","spec":null,"amount":"1500.0","productNum":0,"businessType":5,"checkStatus":null,"productDescVOList":[{"detailDesc":"￥500.00/天","totalNum":"X3","productNum":3,"productCombinationPriceId":null,"productUnit":"天","price":"500.00"}]}]
         * applyReturnDate :
         * agreeDate :
         * checkDate :
         * feedbackReson :
         * checkStatus : null
         * checkStatusName :
         * deliveryFee=0
         * sendAmount=100.00
         * prodAmount=6.00
         * <p>
         * 订单详情
         * deductDesc 优惠券详情
         * vipReductDesc 会员减免信息
         * sendAmountStr 配送费信息
         * normalReductDesc 满减活动信息
         * waitSendReceiveTime 接收时间
         * customerPhone 客服号码
         * <p>
         * 待付款订单修改地址
         * setDefaultAddressById
         * 调用修改地址接口时 传订单orderId=订单orderId
         * <p>
         * "applyReturnDate": "",
         * "agreeDate": "",
         * "checkDate": "",
         * "feedbackReson": "",
         * pickUserName: 提货人,
         * pickPhone: 联系方式,
         * wareAddress: 自提点地址,
         * sendStartTime: 提货时间（年月日）,
         * wareName:自提点名称
         * deliverTimeName：时间段名称（上午/下午）,
         * deliverTimeStart：起始时间,
         * deliverTimeEnd：截止时间
         */


        public String pickUserName;

        public String pickPhone;
        public String wareAddress;
        public String wareName;
        public String sendStartTime;

        public String sendAmount;
        public String prodAmount;
        public String deliveryFee;
        public String orderId;
        public String gmtCreate;
        public String payDate;
        public String confirmDate;
        public String finishDate;
        public AddressVOBean addressVO;
        public int orderStatus;
        public String orderStatusName;
        public String remark;
        public boolean hasSetPayPwd;
        public boolean hasDefaultAddressFlag;
        public boolean canReturnGoods;
        public String cannotReturnGoodsMsg;
        public String totalAmount;
        public int orderType;
        public int totalNum;
        public String applyReturnDate;
        public String agreeDate;
        public String checkDate;
        public String returnReson;
        public String feedbackReson;
        public int checkStatus;
        public String checkStatusName;
        public String actuallyReturnProductDetail;
        public String actuallyreturnAmount;
        public List<String> dateList;
        public List<ProductVOListBean> productVOList;
        public String vipReductDesc;
        public String returnProductNum;
        public String amount;
        public String price;
        public String vipReduct;
        public String normalReduct;
        public int payChannelType;
        public String actuallyReturn;
        public long sysCurrentTime;
        public long orderOverTime;
        public String offerAmount;


        public String deliverTimeStart;
        public String deliverTimeEnd;
        public String deliverTimeName;


        public String deductDesc;
        public String sendAmountStr;
        public String normalReductDesc;
        public String waitSendReceiveTime;
        public String customerPhone;

        public String driverName;
        public String driverPhone;


        public static class AddressVOBean {
            /**
             * id : 21
             * userId : 27
             * userName : 林俊杰
             * contactPhone : 15867121265
             * provinceCode : 330000
             * provinceName : 浙江省
             * cityCode : 330100
             * cityName : 杭州市
             * areaCode : 330106
             * areaName : 西湖区
             * detailAddress : 中旅紫金名门3202
             * isDefault : 1
             * shopName : 中旅店
             */

            public int id;
            public int userId;
            public String userName;
            public String contactPhone;
            public String provinceCode;
            public String provinceName;
            public String cityCode;
            public String cityName;
            public String areaCode;
            public String areaName;
            public String detailAddress;
            public int isDefault;
            public String shopName;
        }

        public static class ProductVOListBean implements Serializable {
            /**
             * productId : 1
             * picUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg
             * name : 师傅
             * spec : null
             * amount : 1500.0
             * productNum : 0
             * businessType : 5
             * checkStatus : null
             * productDescVOList : [{"detailDesc":"￥500.00/天","totalNum":"X3","productNum":3,"productCombinationPriceId":null,"productUnit":"天","price":"500.00"}]
             */
            public int productMainId;
            public int productId;
            public String picUrl;
            public String name;
            public String spec;
            public String amount;
            public int productNum;
            public int businessType;
            public int checkStatus;
            public List<ProductDescVOListBean> productDescVOList;
            public String oldPrice;
            public String returnNum;
            public String prodTypeUrl;
            public int onShelves;
            public static class ProductDescVOListBean implements Serializable {
                /**
                 * detailDesc : ￥500.00/天
                 * totalNum : X3
                 * productNum : 3
                 * productCombinationPriceId : null
                 * productUnit : 天
                 * price : 500.00
                 */

                public String detailDesc;
                public String totalNum;
                public int productNum;
                public int productCombinationPriceId;
                public String productUnit;
                public String price;
                public String newDesc;
                public String afterPrice;
                public String totalPrice;

                public String getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(String totalPrice) {
                    this.totalPrice = totalPrice;
                }
                public String getAfterPrice() {
                    return afterPrice;
                }

                public void setAfterPrice(String afterPrice) {
                    this.afterPrice = afterPrice;
                }

                @Override
                public String toString() {
                    return "ProductVOListBean{" +
                            "afterPrice='" + afterPrice + '\'' +
                            '}';
                }

            }
        }
    }
}
