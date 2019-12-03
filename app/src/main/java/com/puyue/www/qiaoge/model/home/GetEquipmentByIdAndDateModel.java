package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class GetEquipmentByIdAndDateModel {

    /**
     * code : 1
     * message : 成功
     * data : {"id":1,"name":"设备1","spec":"规格1","price":"100.00","number":85,"totalReservation":3,"deposit":"100.00","picUrlList":["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg"],"desc":"规格1规格1说明","mainPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","monthSalesVolume":10,"totalSale":100,"sellPrice":"1000.00"}
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
         * id : 1
         * name : 设备1
         * spec : 规格1
         * price : 100.00
         * number : 85
         * totalReservation : 3
         * deposit : 100.00
         * picUrlList : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg"]
         * desc : 规格1规格1说明
         * mainPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/879fdf10a6a247479f4649cdeb65145a.jpg
         * monthSalesVolume : 10
         * totalSale : 100
         * sellPrice : 1000.00
         */

        public int id;
        public String name;
        public String spec;
        public String price;
        public int number;
        public int totalReservation;
        public String deposit;
        public String desc;
        public String mainPic;
        public int monthSalesVolume;
        public int totalSale;
        public String sellPrice;
        public List<String> picUrlList;
    }
}
