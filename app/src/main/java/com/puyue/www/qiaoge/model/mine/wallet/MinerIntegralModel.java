package com.puyue.www.qiaoge.model.mine.wallet;

import java.util.List;

/**
 * Created by ${daff}
 * on 2018/10/19
 * 备注 我的积分
 */
public class MinerIntegralModel  {

    /**
     * code : 1
     * message : 成功
     * data : {"point":"1035.00","pointMallUrl":"http://116.62.67.230:8082/apph5/html/integrationStore.html","appPointVOList":[{"typeDesc":"商城兑换","time":"2018-10-23 13:34:28","pointChangeDesc":"-2"},{"typeDesc":"商城兑换","time":"2018-10-22 22:05:50","pointChangeDesc":"-2"},{"typeDesc":"商城兑换","time":"2018-10-22 20:59:09","pointChangeDesc":"-2"},{"typeDesc":"商城兑换","time":"2018-10-22 19:45:34","pointChangeDesc":"-1"},{"typeDesc":"商城兑换","time":"2018-10-22 16:28:41","pointChangeDesc":"-88"},{"typeDesc":"人工兑换","time":"2018-10-22 16:28:26","pointChangeDesc":"-50"},{"typeDesc":"消费","time":"2018-10-22 16:27:50","pointChangeDesc":"+105"},{"typeDesc":"消费","time":"2018-10-22 16:27:03","pointChangeDesc":"+1055"},{"typeDesc":"消费","time":"2018-10-22 16:26:29","pointChangeDesc":"+20"},{"typeDesc":"消费","time":"2018-10-22 16:25:12","pointChangeDesc":"-10"}],"next":true}
     * success : true
     * error : false
     */

    private int code;
    private String message;
    private DataBean data;
    private boolean success;
    private boolean error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public static class DataBean {
        /**
         * point : 1035.00
         * pointMallUrl : http://116.62.67.230:8082/apph5/html/integrationStore.html
         * appPointVOList : [{"typeDesc":"商城兑换","time":"2018-10-23 13:34:28","pointChangeDesc":"-2"},{"typeDesc":"商城兑换","time":"2018-10-22 22:05:50","pointChangeDesc":"-2"},{"typeDesc":"商城兑换","time":"2018-10-22 20:59:09","pointChangeDesc":"-2"},{"typeDesc":"商城兑换","time":"2018-10-22 19:45:34","pointChangeDesc":"-1"},{"typeDesc":"商城兑换","time":"2018-10-22 16:28:41","pointChangeDesc":"-88"},{"typeDesc":"人工兑换","time":"2018-10-22 16:28:26","pointChangeDesc":"-50"},{"typeDesc":"消费","time":"2018-10-22 16:27:50","pointChangeDesc":"+105"},{"typeDesc":"消费","time":"2018-10-22 16:27:03","pointChangeDesc":"+1055"},{"typeDesc":"消费","time":"2018-10-22 16:26:29","pointChangeDesc":"+20"},{"typeDesc":"消费","time":"2018-10-22 16:25:12","pointChangeDesc":"-10"}]
         * next : true
         */

        private String point;
        private String pointMallUrl;
        private boolean next;
        private List<AppPointVOListBean> appPointVOList;

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getPointMallUrl() {
            return pointMallUrl;
        }

        public void setPointMallUrl(String pointMallUrl) {
            this.pointMallUrl = pointMallUrl;
        }

        public boolean isNext() {
            return next;
        }

        public void setNext(boolean next) {
            this.next = next;
        }

        public List<AppPointVOListBean> getAppPointVOList() {
            return appPointVOList;
        }

        public void setAppPointVOList(List<AppPointVOListBean> appPointVOList) {
            this.appPointVOList = appPointVOList;
        }

        public static class AppPointVOListBean {
            /**
             * typeDesc : 商城兑换
             * time : 2018-10-23 13:34:28
             * pointChangeDesc : -2
             */

            private String typeDesc;
            private String time;
            private String pointChangeDesc;

            public String getTypeDesc() {
                return typeDesc;
            }

            public void setTypeDesc(String typeDesc) {
                this.typeDesc = typeDesc;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getPointChangeDesc() {
                return pointChangeDesc;
            }

            public void setPointChangeDesc(String pointChangeDesc) {
                this.pointChangeDesc = pointChangeDesc;
            }
        }
    }
}
