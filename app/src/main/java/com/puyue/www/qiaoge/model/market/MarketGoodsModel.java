package com.puyue.www.qiaoge.model.market;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MarketGoodsModel extends BaseModel {

    /**
     * data : {"pageNum":10,"pageSize":1,"startRow":9,"pages":3,"total":3,"list":[{"productId":49,"productName":"鸡肉","spec":"200*10","price":"￥800/箱","monthSalesVolume":100,"inventory":150,"flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg"},{"productId":48,"productName":"鸡肉","spec":"200*10","price":"￥900/箱","monthSalesVolume":10,"inventory":1000,"flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg"},{"productId":46,"productName":"鸡肉","spec":"200*10","price":"￥100/箱","monthSalesVolume":50,"inventory":1000,"flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg"}],"hasPrePage":true,"hasNextPage":true}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * pageNum : 10
         * pageSize : 1
         * startRow : 9
         * pages : 3
         * total : 3
         * list : [{"productId":49,"productName":"鸡肉","spec":"200*10","price":"￥800/箱","monthSalesVolume":100,"inventory":150,"flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg"},{"productId":48,"productName":"鸡肉","spec":"200*10","price":"￥900/箱","monthSalesVolume":10,"inventory":1000,"flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg"},{"productId":46,"productName":"鸡肉","spec":"200*10","price":"￥100/箱","monthSalesVolume":50,"inventory":1000,"flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg"}]
         * hasPrePage : true
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
             * productId : 49
             * productName : 鸡肉
             * spec : 200*10
             * price : ￥800/箱
             * monthSalesVolume : 100
             * inventory : 150
             * flag : 1
             * imgUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg
             */


            /**
             * "productId": 5,
             * "productName": "【瑞发德】整肉香辣鸡腿堡（10个/包）",
             * "spec": "00",
             * "showPrice": "0.43",
             * "monthSalesVolume": "销量:205",
             * "inventory": "余量:2939箱",
             * "flag": 1,
             * "imgUrl": "https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/303113e3ccb04299a48a7c90bfe56873.jpg",
             * "collectionIcon": "https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png",
             * "type": "批发",
             * "price": "￥0.43/包",
             * "productUnitName": "包",
             * "productUnit": 11,
             * "productCombinationPriceId": 242,
             * "num": 1,
             * "oldPrice": "",
             * "prodTypeUrl": "https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/d00211ff54fe47eab63e232e32252e17.png",
             * "minMaxPrice": "￥0.43-1.72",
             */
            public int productId;
            public String productName;
            public String spec;
            public String price;
            public String monthSalesVolume;
            public String inventory;
            public int flag;
            public String imgUrl;
            public int productCombinationPriceId;
            public String type;
            public String prodTypeUrl;
            public String productUnitName;
            public String showPrice;
            public String minMaxPrice;
            public List<PriceList> minMaxPriceList;

            public String cartNum;


            public int businessType;

            public String deductDesc;

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
