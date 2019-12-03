package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/20.
 */

public class GetCommonProductModel {

    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":1,"total":1,"list":[{"productId":48,"productName":"鸡肉","spec":"200*10","price":"￥800/箱","monthSalesVolume":"月销:10","inventory":"库存1000","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"}],"hasPrePage":false,"hasNextPage":false}
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
         * pageNum : 1
         * pageSize : 10
         * startRow : 0
         * pages : 1
         * total : 1
         * list : [{"productId":48,"productName":"鸡肉","spec":"200*10","price":"￥800/箱","monthSalesVolume":"月销:10","inventory":"库存1000","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"}]
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
             * productId : 48
             * productName : 鸡肉
             * spec : 200*10
             * price : ￥800/箱
             * monthSalesVolume : 月销:10
             * inventory : 库存1000
             * flag : 1
             * imgUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg
             * collectionIcon : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png
             */

            public int productId;
            public String productName;
            public String spec;
            public String price;
            public String monthSalesVolume;
            public String inventory;
            public int flag;
            public String imgUrl;
            public String collectionIcon;
            public  String type;
            public  int productCombinationPriceId;

        }
    }
}
