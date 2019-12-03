package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class TeamActiveQueryByIdModel {

    /**
     * code : 1
     * message : 成功
     * data : {"activeId":22,"productId":0,"activeTitle":"大团购啊","defaultPic":"","picCarousel":["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/e0ab4fdcdecb4fcbb6c20b65d7cf352c.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/51c9b43296d349d1abe72c24cbf85003.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/53b6f20d4bec466ea289f64aa8229c21.jpg"],"picDetail":["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/091c735e796647739aaad1f8704049ee.jpg"],"oldPrice":"","price":"3000.00","inventory":"库存:0","monthSalesVolume":"月售：0","intrduction":"放松放松放松地方","originPlace":"杭州","currentTime":null,"startTime":null,"endTime":null,"type":"","progress":"","specification":"20","origin":"杭州","instructions":"放松放松放松地方","combinationPrice":"3000.00/组","prodList":[{"productId":797,"productName":"宝马3系运动型","spec":"200","price":"￥11.00/箱","monthSalesVolume":"月销:0","inventory":"库存:200","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/411ca796e8574e29bfea8cdda0ab69f1.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":795,"productName":"宝马3系","spec":"200","price":"￥100.00/箱","monthSalesVolume":"月销:0","inventory":"库存:20","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/297bfe8a902147bb9a4f6f5772d8454b.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"}],"saleDoneUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","saleFinshUrl":"","flag":false,"available":false}
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
         * activeId : 22
         * productId : 0
         * activeTitle : 大团购啊
         * defaultPic :
         * picCarousel : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/e0ab4fdcdecb4fcbb6c20b65d7cf352c.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/51c9b43296d349d1abe72c24cbf85003.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/53b6f20d4bec466ea289f64aa8229c21.jpg"]
         * picDetail : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/sellPlace/091c735e796647739aaad1f8704049ee.jpg"]
         * oldPrice :
         * price : 3000.00
         * inventory : 库存:0
         * monthSalesVolume : 月售：0
         * intrduction : 放松放松放松地方
         * originPlace : 杭州
         * currentTime : null
         * startTime : null
         * endTime : null
         * type :
         * progress :
         * specification : 20
         * origin : 杭州
         * instructions : 放松放松放松地方
         * combinationPrice : 3000.00/组
         * prodList : [{"productId":797,"productName":"宝马3系运动型","spec":"200","price":"￥11.00/箱","monthSalesVolume":"月销:0","inventory":"库存:200","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/411ca796e8574e29bfea8cdda0ab69f1.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"},{"productId":795,"productName":"宝马3系","spec":"200","price":"￥100.00/箱","monthSalesVolume":"月销:0","inventory":"库存:20","flag":1,"imgUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/297bfe8a902147bb9a4f6f5772d8454b.jpg","collectionIcon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png"}]
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
        public String intrduction;
        public String originPlace;
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
        public List<String> picCarousel;
        public List<String> picDetail;
        public List<ProdListBean> prodList;
        public  String unitName;


        public static class ProdListBean {
            /**
             * productId : 797
             * productName : 宝马3系运动型
             * spec : 200
             * price : ￥11.00/箱
             * monthSalesVolume : 月销:0
             * inventory : 库存:200
             * flag : 1
             * imgUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/411ca796e8574e29bfea8cdda0ab69f1.jpg
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
        }
    }
}
