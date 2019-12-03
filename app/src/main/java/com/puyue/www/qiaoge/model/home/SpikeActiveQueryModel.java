package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */

public class SpikeActiveQueryModel {

    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":1,"total":8,"list":[{"activeId":23,"productId":789,"activeTitle":"我是秒杀","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/38b11874976b4e0da0fea33e8ccda5d4.jpg","picCarousel":[],"picDetail":[],"oldPrice":"￥500.00","price":"￥200.00","inventory":"","monthSalesVolume":"","intrduction":null,"originPlace":null,"currentTime":1526301509000,"startTime":1526054400000,"endTime":1526313600000,"type":"STARTED","progress":"0","specification":"规格：80g*20串*6包/箱","origin":"","instructions":"","combinationPrice":"","prodList":[],"saleDoneUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","saleFinshUrl":"","flag":false,"available":true}],"hasPrePage":false,"hasNextPage":false}
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
         * total : 8
         * list : [{"activeId":23,"productId":789,"activeTitle":"我是秒杀","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/38b11874976b4e0da0fea33e8ccda5d4.jpg","picCarousel":[],"picDetail":[],"oldPrice":"￥500.00","price":"￥200.00","inventory":"","monthSalesVolume":"","intrduction":null,"originPlace":null,"currentTime":1526301509000,"startTime":1526054400000,"endTime":1526313600000,"type":"STARTED","progress":"0","specification":"规格：80g*20串*6包/箱","origin":"","instructions":"","combinationPrice":"","prodList":[],"saleDoneUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","saleFinshUrl":"","flag":false,"available":true}]
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
             * activeId : 23
             * productId : 789
             * activeTitle : 我是秒杀
             * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/38b11874976b4e0da0fea33e8ccda5d4.jpg
             * picCarousel : []
             * picDetail : []
             * oldPrice : ￥500.00
             * price : ￥200.00
             * inventory :
             * monthSalesVolume :
             * intrduction : null
             * originPlace : null
             * currentTime : 1526301509000
             * startTime : 1526054400000
             * endTime : 1526313600000
             * type : STARTED
             * progress : 0
             * specification : 规格：80g*20串*6包/箱
             * origin :
             * instructions :
             * combinationPrice :
             * prodList : []
             * saleDoneUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png
             * saleFinshUrl :
             * flag : false
             * available : true
             */

            public int activeId;
            public int productId;
            public String activeTitle;
            public String defaultPic;
            public String oldPrice;
            public String price;
            public String inventory;
            public String monthSalesVolume;
            public Object intrduction;
            public Object originPlace;
            public long currentTime;
            public long startTime;
            public long endTime;
            public String type;
            public String progress;
            public String specification;
            public String origin;
            public String instructions;
            public String combinationPrice;
            public String saleDoneUrl;
            public String saleFinshUrl;
            public boolean flag;
            public boolean available;
            public List<?> picCarousel;
            public List<?> picDetail;
            public List<?> prodList;
        }
    }
}
