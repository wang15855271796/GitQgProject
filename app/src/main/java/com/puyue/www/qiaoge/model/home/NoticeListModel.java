package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */

public class NoticeListModel {

    /**
     * code : 1
     * message : 成功
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":1,"total":2,"list":[{"id":2,"title":"系统公告","desc":null,"num":1,"url":"http://116.62.67.230:8082/h5/notice/20180706/43cdd186e48a415ea65aa4577515bd80.html","gmtCreate":"2018-07-06 10:19:09","creater":"admin管理员"},{"id":1,"title":"商业广告","desc":null,"num":1,"url":null,"gmtCreate":"2018-07-03 16:14:18","creater":"admin管理员"}],"hasPrePage":false,"hasNextPage":false}
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
         * total : 2
         * list : [{"id":2,"title":"系统公告","desc":null,"num":1,"url":"http://116.62.67.230:8082/h5/notice/20180706/43cdd186e48a415ea65aa4577515bd80.html","gmtCreate":"2018-07-06 10:19:09","creater":"admin管理员"},{"id":1,"title":"商业广告","desc":null,"num":1,"url":null,"gmtCreate":"2018-07-03 16:14:18","creater":"admin管理员"}]
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
             * title : 系统公告
             * desc : null
             * num : 1
             * url : http://116.62.67.230:8082/h5/notice/20180706/43cdd186e48a415ea65aa4577515bd80.html
             * gmtCreate : 2018-07-06 10:19:09
             * creater : admin管理员
             */

            public int id;
            public String title;
            public Object desc;
            public int num;
            public String url;
            public String gmtCreate;
            public String creater;
        }
    }
}
