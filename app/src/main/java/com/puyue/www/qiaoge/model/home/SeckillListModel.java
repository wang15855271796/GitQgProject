package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * 杭州融科网络
 * 刘燕创建 on 2019/4/13.
 * 描述：
 */
public class SeckillListModel {

    /**
     * code : 1
     * message : 成功
     * data : {"currentTime":1555135746247,"startTime":1555119528000,"endTime":1556553600000,"flag":1,"kills":[{"title":"小章鱼","price":"￥102.22/箱","oldPrice":"","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/93d2238a04c24ded966dd295d2a0f14c.jpg","activeId":14,"spec":"规格：15/箱","soldOut":0,"sales":"销量：2","progress":"5"},{"title":"秋刀鱼","price":"￥12.22/箱","oldPrice":"￥35/箱","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/837cffdcc0e4437db4b0e3fb13a1d616.jpg","activeId":15,"spec":"规格：584/箱","soldOut":0,"sales":"销量：2","progress":"10"}],"activeId":null,"businessType":2}
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
         * currentTime : 1555135746247
         * startTime : 1555119528000
         * endTime : 1556553600000
         * flag : 1
         * kills : [{"title":"小章鱼","price":"￥102.22/箱","oldPrice":"","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/93d2238a04c24ded966dd295d2a0f14c.jpg","activeId":14,"spec":"规格：15/箱","soldOut":0,"sales":"销量：2","progress":"5"},{"title":"秋刀鱼","price":"￥12.22/箱","oldPrice":"￥35/箱","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/837cffdcc0e4437db4b0e3fb13a1d616.jpg","activeId":15,"spec":"规格：584/箱","soldOut":0,"sales":"销量：2","progress":"10"}]
         * activeId : null
         * businessType : 2
         */

        public long currentTime;
        public long startTime;
        public long endTime;
        public int flag;
        public int activeId;
        public int businessType;
        public List<KillsBean> kills;
        public int warnMe;
        public static class KillsBean {
            /**
             * title : 小章鱼
             * price : ￥102.22/箱
             * oldPrice :
             * pic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/93d2238a04c24ded966dd295d2a0f14c.jpg
             * activeId : 14
             * spec : 规格：15/箱
             * soldOut : 0
             * sales : 销量：2
             * progress : 5
             */

            public String title;
            public String price;
            public String oldPrice;
            public String pic;
            public int activeId;
            public String spec;
            public int soldOut;
            public String sales;
            public String progress;
            public String flagUrl;
        }
    }
}
