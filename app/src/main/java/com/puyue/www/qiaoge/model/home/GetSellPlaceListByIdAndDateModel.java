package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/18.
 */

public class GetSellPlaceListByIdAndDateModel {

    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":10,"pageSize":1,"startRow":9,"pages":4,"total":4,"list":[{"id":1,"scenicSpotId":1,"name":"风景区1场地1","price":"100.00","totalReservation":100,"latitude":"30.2211018525","longitude":"120.1629638672","picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"},{"id":2,"scenicSpotId":1,"name":"风景区1场地2","price":"100.00","totalReservation":100,"latitude":"30.2211018525","longitude":"120.1629638672","picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg"},{"id":3,"scenicSpotId":1,"name":"风景区1场地3","price":"100.00","totalReservation":100,"latitude":"30.2211018525","longitude":"120.1629638672","picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"},{"id":4,"scenicSpotId":1,"name":"风景区1场地4","price":"100.00","totalReservation":100,"latitude":"30.2211018525","longitude":"120.1629638672","picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg"}],"hasPrePage":true,"hasNextPage":true}
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
         * pageNum : 10
         * pageSize : 1
         * startRow : 9
         * pages : 4
         * total : 4
         * list : [{"id":1,"scenicSpotId":1,"name":"风景区1场地1","price":"100.00","totalReservation":100,"latitude":"30.2211018525","longitude":"120.1629638672","picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"},{"id":2,"scenicSpotId":1,"name":"风景区1场地2","price":"100.00","totalReservation":100,"latitude":"30.2211018525","longitude":"120.1629638672","picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg"},{"id":3,"scenicSpotId":1,"name":"风景区1场地3","price":"100.00","totalReservation":100,"latitude":"30.2211018525","longitude":"120.1629638672","picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"},{"id":4,"scenicSpotId":1,"name":"风景区1场地4","price":"100.00","totalReservation":100,"latitude":"30.2211018525","longitude":"120.1629638672","picUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg"}]
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
             * id : 1
             * scenicSpotId : 1
             * name : 风景区1场地1
             * price : 100.00
             * totalReservation : 100
             * latitude : 30.2211018525
             * longitude : 120.1629638672
             * picUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg
             */

            public int id;
            public int scenicSpotId;
            public String name;
            public String price;
            public int totalReservation;
            public String latitude;
            public String longitude;
            public String picUrl;
        }
    }
}
