package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class GetEquipmentCommentListByPageModel {


    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":1,"total":1,"list":[{"id":17,"customerPhone":"15659559506","customerName":"name20","commonOrderId":"9313bf089443405785a97fa87dc8a3a9","businessId":10,"commentContent":"兔兔旅途我弄","replayContent":null,"commentDate":"2018-05-15 14:15:33","replayDate":""}],"hasPrePage":false,"hasNextPage":false}
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
         * total : 1
         * list : [{"id":17,"customerPhone":"15659559506","customerName":"name20","commonOrderId":"9313bf089443405785a97fa87dc8a3a9","businessId":10,"commentContent":"兔兔旅途我弄","replayContent":null,"commentDate":"2018-05-15 14:15:33","replayDate":""}]
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
             * id : 17
             * customerPhone : 15659559506
             * customerName : name20
             * commonOrderId : 9313bf089443405785a97fa87dc8a3a9
             * businessId : 10
             * commentContent : 兔兔旅途我弄
             * replayContent : null
             * commentDate : 2018-05-15 14:15:33
             * replayDate :
             */

            public int id;
            public String customerPhone;
            public String customerName;
            public String commonOrderId;
            public int businessId;
            public String commentContent;
            public String replayContent;
            public String commentDate;
            public String replayDate;
        }
    }
}
