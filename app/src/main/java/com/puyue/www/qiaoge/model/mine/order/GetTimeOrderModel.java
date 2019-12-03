package com.puyue.www.qiaoge.model.mine.order;

import java.util.List;

/**
 * Created by ${王文博} on 2019/7/22
 */
public class GetTimeOrderModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"dateTime":"2019-07-19","monthDay":"07-19","detailTime":[{"name":"下午","startTime":"13:00","endTime":"18:00"}]},{"dateTime":"2019-07-20","monthDay":"07-20","detailTime":[{"name":"上午","startTime":"6:00","endTime":"11:00"},{"name":"下午","startTime":"13:00","endTime":"18:00"}]},{"dateTime":"2019-07-21","monthDay":"07-21","detailTime":[{"name":"上午","startTime":"6:00","endTime":"11:00"},{"name":"下午","startTime":"13:00","endTime":"18:00"}]},{"dateTime":"2019-07-22","monthDay":"07-22","detailTime":[{"name":"上午","startTime":"6:00","endTime":"11:00"},{"name":"下午","startTime":"13:00","endTime":"18:00"}]}]
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private boolean error;
    private boolean success;
    private List<DataBean> data;

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

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dateTime : 2019-07-19
         * monthDay : 07-19
         * detailTime : [{"name":"下午","startTime":"13:00","endTime":"18:00"}]
         */

        private String dateTime;
        private String monthDay;
        private List<DetailTimeBean> detailTime;

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getMonthDay() {
            return monthDay;
        }

        public void setMonthDay(String monthDay) {
            this.monthDay = monthDay;
        }

        public List<DetailTimeBean> getDetailTime() {
            return detailTime;
        }

        public void setDetailTime(List<DetailTimeBean> detailTime) {
            this.detailTime = detailTime;
        }

        public static class DetailTimeBean {
            /**
             * name : 下午
             * startTime : 13:00
             * endTime : 18:00
             */

            private String name;
            private String startTime;
            private String endTime;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }
        }
    }
}
