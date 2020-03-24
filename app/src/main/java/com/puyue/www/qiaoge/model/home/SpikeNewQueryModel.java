package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王文博} on 2019/4/12
 */
public class SpikeNewQueryModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"dateTime":"2019-04-10 09:23:10","dateDesc":"2019-04-10","timeDesc":"09:23","flag":1,"secKill":{"currentTime":1554859390749,"startTime":1554858000000,"endTime":1554998400000,"flag":1,"kills":[{"title":"巧克力大福","price":"￥5/箱","oldPrice":"￥20/箱","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/f0096337fcc946acb28e750278280d28.jpg","activeId":1,"soldOut":0},{"title":"批发多单位商品","price":"￥20/箱","oldPrice":"￥160/箱","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/3cd5997e6be6481b8f6b1da0bac48183.jpg","activeId":2,"soldOut":0},{"title":"蓉黄桃大福","price":"￥2/箱","oldPrice":"￥1.76/箱","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/635adcc8f9984ab486fcc3f0893fc249.jpg","activeId":3,"soldOut":0}],"activeId":null,"businessType":2}},{"dateTime":"2019-04-11 00:00:00","dateDesc":"2019-04-11","timeDesc":"00:00","flag":0,"secKill":{"currentTime":1554859390749,"startTime":1554912000000,"endTime":1555084800000,"flag":0,"kills":[{"title":"泰森吮指棒棒腿法规和香辣味-1.2kg【翘歌】萨达（的撒多撒....）","price":"￥100/箱","oldPrice":"","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/227abee25ad24bd1ad9751da5234dcb0.jpg","activeId":4,"soldOut":0},{"title":"黄金蛋","price":"￥50/箱","oldPrice":"￥3.03/箱","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/1d69eccc1d5a4023b05e0469c9804707.jpg","activeId":5,"soldOut":0}],"activeId":null,"businessType":2}}]
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
         * dateTime : 2019-04-10 09:23:10
         * dateDesc : 2019-04-10
         * timeDesc : 09:23
         * flag : 1
         * secKill : {"currentTime":1554859390749,"startTime":1554858000000,"endTime":1554998400000,"flag":1,"kills":[{"title":"巧克力大福","price":"￥5/箱","oldPrice":"￥20/箱","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/f0096337fcc946acb28e750278280d28.jpg","activeId":1,"soldOut":0},{"title":"批发多单位商品","price":"￥20/箱","oldPrice":"￥160/箱","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/3cd5997e6be6481b8f6b1da0bac48183.jpg","activeId":2,"soldOut":0},{"title":"蓉黄桃大福","price":"￥2/箱","oldPrice":"￥1.76/箱","pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/635adcc8f9984ab486fcc3f0893fc249.jpg","activeId":3,"soldOut":0}],"activeId":null,"businessType":2}
         */
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        private String dateTime;
        private String dateDesc;
        private String timeDesc;
        private int flag;
        private long currentTime;
        private long startTime;
        private long endTime;
        private int activeId;


        public long getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(long currentTime) {
            this.currentTime = currentTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getActiveId() {
            return activeId;
        }

        public void setActiveId(int activeId) {
            this.activeId = activeId;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getDateDesc() {
            return dateDesc;
        }

        public void setDateDesc(String dateDesc) {
            this.dateDesc = dateDesc;
        }

        public String getTimeDesc() {
            return timeDesc;
        }

        public void setTimeDesc(String timeDesc) {
            this.timeDesc = timeDesc;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        }
    }

