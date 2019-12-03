package com.puyue.www.qiaoge.model.home;

/**
 * Created by ${daff}
 * on 2018/10/25
 * 备注
 */
public class QueryHomePropupModel {

    /**
     * code : 1
     * message : 成功
     * data : {"propup":true,"homePropup":{"id":7,"title":"首页弹窗","effectStartTime":1539248067000,"effectEndTime":1540448505000,"showUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/9aa521923a6c4d528fa5d08cc6d2e940.png","toPage":"self","detailUrl":null,"pageUrl":"http://116.62.67.230:8082/apph5/html/purchaseRecord.html","deleteFlag":false,"gmtCreate":1540275972000,"gmtModify":1540275971000,"createUserId":1,"updateUserId":null,"memo":null}}
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
         * propup : true
         * homePropup : {"id":7,"title":"首页弹窗","effectStartTime":1539248067000,"effectEndTime":1540448505000,"showUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/9aa521923a6c4d528fa5d08cc6d2e940.png","toPage":"self","detailUrl":null,"pageUrl":"http://116.62.67.230:8082/apph5/html/purchaseRecord.html","deleteFlag":false,"gmtCreate":1540275972000,"gmtModify":1540275971000,"createUserId":1,"updateUserId":null,"memo":null}
         */

        private boolean propup;
        private HomePropupBean homePropup;

        public boolean isPropup() {
            return propup;
        }

        public void setPropup(boolean propup) {
            this.propup = propup;
        }

        public HomePropupBean getHomePropup() {
            return homePropup;
        }

        public void setHomePropup(HomePropupBean homePropup) {
            this.homePropup = homePropup;
        }

        public static class HomePropupBean {
            /**
             * id : 7
             * title : 首页弹窗
             * effectStartTime : 1539248067000
             * effectEndTime : 1540448505000
             * showUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/9aa521923a6c4d528fa5d08cc6d2e940.png
             * toPage : self
             * detailUrl : null
             * pageUrl : http://116.62.67.230:8082/apph5/html/purchaseRecord.html
             * deleteFlag : false
             * gmtCreate : 1540275972000
             * gmtModify : 1540275971000
             * createUserId : 1
             * updateUserId : null
             * memo : null
             */

            private int id;
            private String title;
            private long effectStartTime;
            private long effectEndTime;
            private String showUrl;
            private String toPage;
            private Object detailUrl;
            private String pageUrl;
            private boolean deleteFlag;
            private long gmtCreate;
            private long gmtModify;
            private int createUserId;
            private Object updateUserId;
            private Object memo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public long getEffectStartTime() {
                return effectStartTime;
            }

            public void setEffectStartTime(long effectStartTime) {
                this.effectStartTime = effectStartTime;
            }

            public long getEffectEndTime() {
                return effectEndTime;
            }

            public void setEffectEndTime(long effectEndTime) {
                this.effectEndTime = effectEndTime;
            }

            public String getShowUrl() {
                return showUrl;
            }

            public void setShowUrl(String showUrl) {
                this.showUrl = showUrl;
            }

            public String getToPage() {
                return toPage;
            }

            public void setToPage(String toPage) {
                this.toPage = toPage;
            }

            public Object getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(Object detailUrl) {
                this.detailUrl = detailUrl;
            }

            public String getPageUrl() {
                return pageUrl;
            }

            public void setPageUrl(String pageUrl) {
                this.pageUrl = pageUrl;
            }

            public boolean isDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(boolean deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public long getGmtCreate() {
                return gmtCreate;
            }

            public void setGmtCreate(long gmtCreate) {
                this.gmtCreate = gmtCreate;
            }

            public long getGmtModify() {
                return gmtModify;
            }

            public void setGmtModify(long gmtModify) {
                this.gmtModify = gmtModify;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public Object getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(Object updateUserId) {
                this.updateUserId = updateUserId;
            }

            public Object getMemo() {
                return memo;
            }

            public void setMemo(Object memo) {
                this.memo = memo;
            }
        }
    }
}
