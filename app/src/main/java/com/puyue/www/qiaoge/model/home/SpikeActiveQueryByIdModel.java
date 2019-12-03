package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/19.
 */

public class SpikeActiveQueryByIdModel {

    /**
     * code : 1
     * message : 成功
     * data : {"activeId":23,"productId":789,"activeTitle":"我是秒杀","defaultPic":"","picCarousel":["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/38b11874976b4e0da0fea33e8ccda5d4.jpg"],"picDetail":["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/ad56565cd4fe4325a6107f9c0c6c51ae.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/2b9aa03fe7064d71ae9b0a426f67b372.jpg"],"oldPrice":"500.00","price":"200.00","inventory":"库存：22","monthSalesVolume":"月销：41","intrduction":"产品规格：80g*20支*6包/箱，80g*20支/包\n食用方法：碳烤或用食用油煎、炸3分钟左右至熟即可食用。","originPlace":"浙江省余杭区崇贤街道银杏路9号4幢3-4层","currentTime":1526348579000,"startTime":1526054400000,"endTime":1526313600000,"type":"OVER","progress":"0","specification":"80g*20串*6包/箱","origin":"浙江省余杭区崇贤街道银杏路9号4幢3-4层","instructions":"产品规格：80g*20支*6包/箱，80g*20支/包\n食用方法：碳烤或用食用油煎、炸3分钟左右至熟即可食用。","combinationPrice":"200.00/箱","prodList":[],"saleDoneUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/staticImg/collection_icon_soldout.png","saleFinshUrl":"","flag":false,"available":false}
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
         * activeId : 23
         * productId : 789
         * activeTitle : 我是秒杀
         * defaultPic :
         * picCarousel : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/38b11874976b4e0da0fea33e8ccda5d4.jpg"]
         * picDetail : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/ad56565cd4fe4325a6107f9c0c6c51ae.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/2b9aa03fe7064d71ae9b0a426f67b372.jpg"]
         * oldPrice : 500.00
         * price : 200.00
         * inventory : 库存：22
         * monthSalesVolume : 月销：41
         * intrduction : 产品规格：80g*20支*6包/箱，80g*20支/包
         食用方法：碳烤或用食用油煎、炸3分钟左右至熟即可食用。
         * originPlace : 浙江省余杭区崇贤街道银杏路9号4幢3-4层
         * currentTime : 1526348579000
         * startTime : 1526054400000
         * endTime : 1526313600000
         * type : OVER
         * progress : 0
         * specification : 80g*20串*6包/箱
         * origin : 浙江省余杭区崇贤街道银杏路9号4幢3-4层
         * instructions : 产品规格：80g*20支*6包/箱，80g*20支/包
         食用方法：碳烤或用食用油煎、炸3分钟左右至熟即可食用。
         * combinationPrice : 200.00/箱
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
        public String intrduction;
        public String originPlace;
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
        public String purchaseLimit;
        public List<String> picCarousel;
        public List<String> picDetail;
        public List<?> prodList;
        public String  unitName;

    }
}
