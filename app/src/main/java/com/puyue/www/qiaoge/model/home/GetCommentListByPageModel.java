package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class GetCommentListByPageModel {
    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":1,"total":1,"list":[{"id":3,"customerPhone":"15700191306","customerName":"cy","commonOrderId":"1","businessId":1,"commentContent":"1342453253245","replayContent":"回复2","commentDate":"2018-04-16 19:49:05","replayDate":"2018-04-16 19:49:12"}],"hasPrePage":false,"hasNextPage":false}
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
         * list : [{"id":3,"customerPhone":"15700191306","customerName":"cy","commonOrderId":"1","businessId":1,"commentContent":"1342453253245","replayContent":"回复2","commentDate":"2018-04-16 19:49:05","replayDate":"2018-04-16 19:49:12"}]
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
             * id : 3
             * customerPhone : 15700191306
             * customerName : cy
             * commonOrderId : 1
             * businessId : 1
             * commentContent : 1342453253245
             * replayContent : 回复2
             * commentDate : 2018-04-16 19:49:05
             * replayDate : 2018-04-16 19:49:12
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
