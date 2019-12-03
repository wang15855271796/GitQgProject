package com.puyue.www.qiaoge.model.mine.order;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class MyOrdersModel extends BaseModel{

    /**
     * data : {"pageNum":1,"pageSize":20,"startRow":0,"pages":1,"total":2,"list":[{"orderId":"7839be8a3d3a4833903f381b65fbdeb6","orderStatus":9,"orderStatusName":"归还中","totalAmount":"300.00","payAmount":"300.00","totalNum":1,"gmtCreate":"2018-05-08 17:35:56","recoverDate":null,"productVOList":[{"productId":0,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"设备1","spec":"规格1","amount":"","productNum":0,"businessType":null,"checkStatus":null,"productDescVOList":null}]},{"orderId":"65ae0ec5a259451f9ce7f32a53461fad","orderStatus":9,"orderStatusName":"归还中","totalAmount":"300.00","payAmount":"300.00","totalNum":1,"gmtCreate":"2018-05-08 17:30:56","recoverDate":null,"productVOList":[{"productId":0,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"设备1","spec":"规格1","amount":"","productNum":0,"businessType":null,"checkStatus":null,"productDescVOList":null}]}],"hasPrePage":false,"hasNextPage":false}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 20
         * startRow : 0
         * pages : 1
         * total : 2
         * list : [{"orderId":"7839be8a3d3a4833903f381b65fbdeb6","orderStatus":9,"orderStatusName":"归还中","totalAmount":"300.00","payAmount":"300.00","totalNum":1,"gmtCreate":"2018-05-08 17:35:56","recoverDate":null,"productVOList":[{"productId":0,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"设备1","spec":"规格1","amount":"","productNum":0,"businessType":null,"checkStatus":null,"productDescVOList":null}]},{"orderId":"65ae0ec5a259451f9ce7f32a53461fad","orderStatus":9,"orderStatusName":"归还中","totalAmount":"300.00","payAmount":"300.00","totalNum":1,"gmtCreate":"2018-05-08 17:30:56","recoverDate":null,"productVOList":[{"productId":0,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"设备1","spec":"规格1","amount":"","productNum":0,"businessType":null,"checkStatus":null,"productDescVOList":null}]}]
         * hasPrePage : false
         * hasNextPage : false
         */

        public int pageNum;
        public int pageSize;
        public int startRow;
        public int pages;
        public int total;
        public boolean hasPrePage;
        public boolean hasNextPage;
        public List<ListBean> list;

        public static class ListBean {
            /**
             * orderId : 7839be8a3d3a4833903f381b65fbdeb6
             * orderStatus : 9
             * orderStatusName : 归还中
             * totalAmount : 300.00
             * payAmount : 300.00
             * totalNum : 1
             * gmtCreate : 2018-05-08 17:35:56
             * recoverDate : null
             * productVOList : [{"productId":0,"picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","name":"设备1","spec":"规格1","amount":"","productNum":0,"businessType":null,"checkStatus":null,"productDescVOList":null}]
             */

            public String orderId;
            public int orderStatus;
            public String orderStatusName;
            public String totalAmount;
            public String payAmount;
            public int totalNum;
            public String gmtCreate;
            public String recoverDate;
            public String returnProductMainId;
            public List<ProductVOListBean> productVOList;
            public  String  returnOrderState;
            public String actuallyReturn;
            public String subUserBuy;

            public static class ProductVOListBean {
                /**
                 * productId : 0
                 * picUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg
                 * name : 设备1
                 * spec : 规格1
                 * amount :
                 * productNum : 0
                 * businessType : null
                 * checkStatus : null
                 * productDescVOList : null
                 */

                public int productId;
                public String picUrl;
                public String name;
                public String spec;
                public String amount;
                public int productNum;
                public int businessType;
                public String checkStatus;
                public String productDescVOList;

            }
        }
    }
}
