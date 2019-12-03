package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/21.
 */

public class GetMasterWorkerByIdAndDateModel {

    /**
     * code : 1
     * message : 成功
     * data : {"id":1,"name":"师傅","price":"500.00","totalReservation":0,"masterWorkerDesc":"好师傅","picUrlList":["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/6f97cfd6444e4b4c9755f11f8d32bd8a.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"]}
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
         * name : 师傅
         * price : 500.00
         * totalReservation : 0
         * masterWorkerDesc : 好师傅
         * picUrlList : ["https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/497d359402fb4071b0e13fc63843fea3.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/6f97cfd6444e4b4c9755f11f8d32bd8a.jpg","https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/masterworker/0c658325bb0b402ea3c280bd3d931dbe.jpg"]
         */

        public int id;
        public String name;
        public String price;
        public int totalReservation;
        public String masterWorkerDesc;
        public List<String> picUrlList;
    }
}
