package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/19.
 */

public class SearchEquipmentModel {

    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":1,"total":2,"list":[{"id":2,"name":"设备2","spec":"规格2","price":"20.00","number":100,"totalReservation":20,"mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/9a3bb39678504d72bf8ad98035413d3d.jpg"},{"id":1,"name":"设备1","spec":"规格1","price":"100.00","number":18,"totalReservation":11,"mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/9a3bb39678504d72bf8ad98035413d3d.jpg"}],"hasPrePage":false,"hasNextPage":false}
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
         * total : 2
         * list : [{"id":2,"name":"设备2","spec":"规格2","price":"20.00","number":100,"totalReservation":20,"mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/9a3bb39678504d72bf8ad98035413d3d.jpg"},{"id":1,"name":"设备1","spec":"规格1","price":"100.00","number":18,"totalReservation":11,"mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/9a3bb39678504d72bf8ad98035413d3d.jpg"}]
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
             * id : 2
             * name : 设备2
             * spec : 规格2
             * price : 20.00
             * number : 100
             * totalReservation : 20
             * mainPicUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/9a3bb39678504d72bf8ad98035413d3d.jpg
             */

            public int id;
            public String name;
            public String spec;
            public String price;
            public int number;
            public int totalReservation;
            public String mainPicUrl;
            public String inventory;//库存,剩余量
            public String monthSalesVolume;//月销,预约量
//            public String price;//价格
        }
    }
}
