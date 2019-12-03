package com.puyue.www.qiaoge.model.home;

import com.puyue.www.qiaoge.model.market.MarketGoodsModel;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/18.
 */

public class GetProductListModel {


    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":9,"startRow":0,"pages":2,"total":11,"list":[{"productId":46,"productName":"鸡肉","spec":"200*10","price":"￥100/箱","monthSalesVolume":"月销:50","inventory":"库存:1000","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":47,"productName":"包心菜","spec":"200*10","price":"￥800/箱","monthSalesVolume":"月销:0","inventory":"库存:150","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":48,"productName":"鸡肉","spec":"200*10","price":"￥900/箱","monthSalesVolume":"月销:10","inventory":"库存:1000","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":49,"productName":"鸡肉","spec":"200*10","price":"￥800/箱","monthSalesVolume":"月销:100","inventory":"库存:150","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":60,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":667,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":668,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":669,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":670,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"}],"hasPrePage":false,"hasNextPage":true}
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
         * pageSize : 9
         * startRow : 0
         * pages : 2
         * total : 11
         * list : [{"productId":46,"productName":"鸡肉","spec":"200*10","price":"￥100/箱","monthSalesVolume":"月销:50","inventory":"库存:1000","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":47,"productName":"包心菜","spec":"200*10","price":"￥800/箱","monthSalesVolume":"月销:0","inventory":"库存:150","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":48,"productName":"鸡肉","spec":"200*10","price":"￥900/箱","monthSalesVolume":"月销:10","inventory":"库存:1000","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":49,"productName":"鸡肉","spec":"200*10","price":"￥800/箱","monthSalesVolume":"月销:100","inventory":"库存:150","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":60,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":667,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":668,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":669,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":670,"productName":"铁针","spec":"200*10","price":"￥200/箱","monthSalesVolume":"月销:0","inventory":"库存:15","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"}]
         * hasPrePage : false
         * hasNextPage : true
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
             * productId : 46
             * productName : 鸡肉
             * spec : 200*10
             * price : ￥100/箱
             * monthSalesVolume : 月销:50
             * inventory : 库存:1000
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
            public String type;
            public int productCombinationPriceId;
            public String showPrice;
            public String oldPrice;
            public String productUnitName;
            public String num;

            public String minMaxPrice;
            public List<PriceList> minMaxPriceList;

            public String cartNum;

            public String prodTypeUrl;

            public int businessType;

            public static class PriceList {
                /**
                 * "productCombinationPriceId": 242,
                 * "unitName": "包",
                 * "price": "0.43",
                 * "productUnit": 11
                 */

                public int productCombinationPriceId;
                public String unitName;
                public String price;
                public int productUnit;
                public int cartNum;
            }

        }
    }
}
