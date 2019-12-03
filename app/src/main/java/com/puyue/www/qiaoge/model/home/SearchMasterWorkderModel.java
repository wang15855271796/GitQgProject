package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/20.
 */

public class SearchMasterWorkderModel {

    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":1,"total":3,"list":[{"id":1,"name":"师傅","price":"500.00","totalReservation":0,"masterWorkerDesc":"好师傅","mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"},{"id":2,"name":"shifu","price":"500.00","totalReservation":0,"masterWorkerDesc":"好师傅","mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg"},{"id":4,"name":"郝师傅","price":"500.00","totalReservation":0,"masterWorkerDesc":"好师傅","mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"}],"hasPrePage":false,"hasNextPage":false}
     * error : false
     * success : true
     */

    public int code;
    public String message;
    public DataBean data;
    public boolean error;
    public boolean success;
    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * startRow : 0
         * pages : 1
         * total : 3
         * list : [{"id":1,"name":"师傅","price":"500.00","totalReservation":0,"masterWorkerDesc":"好师傅","mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"},{"id":2,"name":"shifu","price":"500.00","totalReservation":0,"masterWorkerDesc":"好师傅","mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg"},{"id":4,"name":"郝师傅","price":"500.00","totalReservation":0,"masterWorkerDesc":"好师傅","mainPicUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"}]
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
             * id : 1
             * name : 师傅
             * price : 500.00
             * totalReservation : 0
             * masterWorkerDesc : 好师傅
             * mainPicUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg
             */

            public int id;
            public String name;
            public String price;
            public int totalReservation;
            public String masterWorkerDesc;
            public String mainPicUrl;
            public String inventory;//库存,剩余量
            public String monthSalesVolume;//月销,预约量
//            public String price;//价格
        }
    }
}
