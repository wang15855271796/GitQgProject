package com.puyue.www.qiaoge.model.mine.wallet;

/**
 * 创建 on 2019/5/29.
 * 描述：
 */
public class AddressModel {

    /**
     * status : 0
     * result : {"location":{"lng":116.30694088743103,"lat":40.057338773497534},"precise":1,"confidence":80,"comprehension":100,"level":"道路"}
     */

    public int status;
    public ResultBean result;

    public static class ResultBean {
        /**
         * location : {"lng":116.30694088743103,"lat":40.057338773497534}
         * precise : 1
         * confidence : 80
         * comprehension : 100
         * level : 道路
         */

        public LocationBean location;
        public int precise;
        public int confidence;
        public int comprehension;
        public String level;

        public static class LocationBean {
            /**
             * lng : 116.30694088743103
             * lat : 40.057338773497534
             */

            public double lng;
            public double lat;
        }
    }
}
