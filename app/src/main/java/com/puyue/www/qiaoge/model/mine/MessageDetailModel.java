package com.puyue.www.qiaoge.model.mine;

import com.puyue.www.qiaoge.base.BaseModel;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MessageDetailModel extends BaseModel {

    /**
     * data : {"id":3,"title":"4月优惠季","openPage":0,"content":"你买我就送,4月购不停","toPage":null,"gmtCreate":1524276702000,"createUserId":1}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * id : 3
         * title : 4月优惠季
         * openPage : 0
         * content : 你买我就送,4月购不停
         * toPage : null
         * gmtCreate : 1524276702000
         * createUserId : 1
         */

        public int id;
        public String title;
        public int openPage;
        public String content;
        public String toPage;
        public String gmtCreateTime;
        public int createUserId;
    }
}
