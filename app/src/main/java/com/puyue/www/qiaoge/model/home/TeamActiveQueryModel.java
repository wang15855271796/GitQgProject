package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */

public class TeamActiveQueryModel {

    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":1,"total":3,"list":[{"activeId":22,"productId":797,"activeTitle":"大团购啊","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/51c9b43296d349d1abe72c24cbf85003.jpg","picCarousel":[],"picDetail":[],"oldPrice":"","price":"￥3000.00/组","inventory":"库存：0","monthSalesVolume":"月售：0","intrduction":null,"originPlace":null,"currentTime":null,"startTime":null,"endTime":null,"type":"","progress":"","specification":"","origin":"","instructions":"","combinationPrice":"￥3000.00/组","prodList":[],"saleDoneUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","saleFinshUrl":"","flag":false,"available":false}],"hasPrePage":false,"hasNextPage":false}
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
         * total : 3
         * list : [{"activeId":22,"productId":797,"activeTitle":"大团购啊","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/51c9b43296d349d1abe72c24cbf85003.jpg","picCarousel":[],"picDetail":[],"oldPrice":"","price":"￥3000.00/组","inventory":"库存：0","monthSalesVolume":"月售：0","intrduction":null,"originPlace":null,"currentTime":null,"startTime":null,"endTime":null,"type":"","progress":"","specification":"","origin":"","instructions":"","combinationPrice":"￥3000.00/组","prodList":[],"saleDoneUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","saleFinshUrl":"","flag":false,"available":false}]
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
             * activeId : 22
             * productId : 797
             * activeTitle : 大团购啊
             * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/51c9b43296d349d1abe72c24cbf85003.jpg
             * picCarousel : []
             * picDetail : []
             * oldPrice :
             * price : ￥3000.00/组
             * inventory : 库存：0
             * monthSalesVolume : 月售：0
             * intrduction : null
             * originPlace : null
             * currentTime : null
             * startTime : null
             * endTime : null
             * type :
             * progress :
             * specification :
             * origin :
             * instructions :
             * combinationPrice : ￥3000.00/组
             * prodList : []
             * saleDoneUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png
             * saleFinshUrl :
             * flag : false
             * available : false
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
            public Object currentTime;
            public Object startTime;
            public Object endTime;
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
