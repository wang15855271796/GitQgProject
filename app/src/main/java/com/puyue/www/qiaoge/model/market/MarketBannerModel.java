package com.puyue.www.qiaoge.model.market;

import java.util.List;

/**
 * Created by ${王文博} on 2019/5/21
 */
public class MarketBannerModel {

    /**
     * code : 1
     * message : 成功
     * data : [{"pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/cf426adff3894c0a97694d7d7d022d8f.jpg","toPage":"vip"},{"pic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/93c4b2e033874eb88656424669da0dd0.jpg","toPage":"wallet"}]
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
         * pic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/cf426adff3894c0a97694d7d7d022d8f.jpg
         * toPage : vip
         */

        private String pic;
        private String toPage;

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getToPage() {
            return toPage;
        }

        public void setToPage(String toPage) {
            this.toPage = toPage;
        }
    }
}
