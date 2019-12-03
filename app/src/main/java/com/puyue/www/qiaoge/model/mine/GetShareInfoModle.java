package com.puyue.www.qiaoge.model.mine;

/**
 * Created by ${daff}
 * on 2018/10/24
 * 备注
 */
public class GetShareInfoModle {

    /**
     * code : 1
     * message : 成功
     * data : {"title":"wjh测试商品","desc":"我在翘歌烧烤看到了这个商品，廉价又好吃，快来和我一起购买吧","icon":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/672016fead5a4884a5f5dbbf25cd7e0d.jpg","pageUrl":"http://116.62.67.230:8082/apph5/html/share.html?bussinessId=48&bussinessType=1"}
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
         * title : wjh测试商品
         * desc : 我在翘歌烧烤看到了这个商品，廉价又好吃，快来和我一起购买吧
         * icon : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/product/672016fead5a4884a5f5dbbf25cd7e0d.jpg
         * pageUrl : http://116.62.67.230:8082/apph5/html/share.html?bussinessId=48&bussinessType=1
         */

        private String title;
        private String desc;
        private String icon;
        private String pageUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }
    }
}
