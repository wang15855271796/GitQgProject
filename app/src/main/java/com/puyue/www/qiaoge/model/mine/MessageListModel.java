package com.puyue.www.qiaoge.model.mine;

import com.puyue.www.qiaoge.base.BaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MessageListModel extends BaseModel {

    /**
     * data : {"pageNum":1,"pageSize":10,"startRow":0,"pages":0,"total":7,"list":[{"id":3,"title":"4月优惠季","openPage":0,"content":"你买我就送,4月购不停","toPage":null,"gmtCreate":1524276702000,"gmtCreateTime":"2018-04-21 10:11:42","looked_status":0},{"id":4,"title":"5月","openPage":1,"content":"新品上架，抢先尝鲜","toPage":1,"gmtCreate":1524277209000,"gmtCreateTime":"2018-04-21 10:20:09","looked_status":0},{"id":5,"title":"6月","openPage":1,"content":"团购满100立减50","toPage":2,"gmtCreate":1524277634000,"gmtCreateTime":"2018-04-21 10:27:14","looked_status":0},{"id":6,"title":"七月既望","openPage":1,"content":"1元限量秒杀","toPage":1,"gmtCreate":1524277713000,"gmtCreateTime":"2018-04-21 10:28:33","looked_status":0},{"id":7,"title":"八月未央","openPage":1,"content":"飘香季，再推新品","toPage":0,"gmtCreate":1524277821000,"gmtCreateTime":"2018-04-21 10:30:21","looked_status":0},{"id":8,"title":"纵享barbecue","openPage":0,"content":"免费租赁设备","toPage":null,"gmtCreate":1524278066000,"gmtCreateTime":"2018-04-21 10:34:26","looked_status":0},{"id":9,"title":"回馈老客户","openPage":0,"content":"将从用户中随机抽取10名，免费使用场地一天","toPage":null,"gmtCreate":1524278150000,"gmtCreateTime":"2018-04-21 10:35:50","looked_status":0}],"hasPrePage":false,"hasNextPage":false}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * startRow : 0
         * pages : 0
         * total : 7
         * list : [{"id":3,"title":"4月优惠季","openPage":0,"content":"你买我就送,4月购不停","toPage":null,"gmtCreate":1524276702000,"gmtCreateTime":"2018-04-21 10:11:42","looked_status":0},{"id":4,"title":"5月","openPage":1,"content":"新品上架，抢先尝鲜","toPage":1,"gmtCreate":1524277209000,"gmtCreateTime":"2018-04-21 10:20:09","looked_status":0},{"id":5,"title":"6月","openPage":1,"content":"团购满100立减50","toPage":2,"gmtCreate":1524277634000,"gmtCreateTime":"2018-04-21 10:27:14","looked_status":0},{"id":6,"title":"七月既望","openPage":1,"content":"1元限量秒杀","toPage":1,"gmtCreate":1524277713000,"gmtCreateTime":"2018-04-21 10:28:33","looked_status":0},{"id":7,"title":"八月未央","openPage":1,"content":"飘香季，再推新品","toPage":0,"gmtCreate":1524277821000,"gmtCreateTime":"2018-04-21 10:30:21","looked_status":0},{"id":8,"title":"纵享barbecue","openPage":0,"content":"免费租赁设备","toPage":null,"gmtCreate":1524278066000,"gmtCreateTime":"2018-04-21 10:34:26","looked_status":0},{"id":9,"title":"回馈老客户","openPage":0,"content":"将从用户中随机抽取10名，免费使用场地一天","toPage":null,"gmtCreate":1524278150000,"gmtCreateTime":"2018-04-21 10:35:50","looked_status":0}]
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
             * title : 4月优惠季
             * openPage : 0
             * content : 你买我就送,4月购不停
             * toPage : null
             * gmtCreate : 1524276702000
             * gmtCreateTime : 2018-04-21 10:11:42
             * looked_status : 0,1
             */

            public int id;
            public String title;
            public int openPage;
            public String content;
            public int toPage;
            public long gmtCreate;
            public String gmtCreateTime;
            public int looked_status;
        }
    }
}
