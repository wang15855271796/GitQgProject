package com.puyue.www.qiaoge.model.mine.order;

import java.util.List;

/**
 * Created by ${王文博} on 2019/8/21
 */
public class GetEvaDetailModel {

    /**
     * code : 1
     * message : 成功
     * data : {"driverName":"杜天浩","sendTime":"2019-08-06 08:35:48","businessType":20,"commentContent":null,"replayPic":null,"level":null,"replayContent":null,"commentDate":null,"replayDate":null,"list":[{"businessId":40,"businessType":null,"commentContent":"¥：¥2828","replayPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/b8d6888897694982a0d75a51fc9319d0.jpg,https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/b8d6888897694982a0d75a51fc9319d0.jpg,http://116.62.67.230:8082/pics/1882019041600000001.png,https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","level":"5","replayContent":"222","commentDate":"2019-08-09 10:32:25","replayDate":"2019-08-09 11:12:20"},{"businessId":332,"businessType":null,"commentContent":"2882","replayPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/b8d6888897694982a0d75a51fc9319d0.jpg","level":"5","replayContent":"11","commentDate":"2019-08-09 10:32:25","replayDate":"2019-08-09 15:03:31"}]}
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
         * driverName : 杜天浩
         * sendTime : 2019-08-06 08:35:48
         * businessType : 20
         * commentContent : null
         * replayPic : null
         * level : null
         * replayContent : null
         * commentDate : null
         * replayDate : null
         * list : [{"businessId":40,"businessType":null,"commentContent":"¥：¥2828","replayPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/b8d6888897694982a0d75a51fc9319d0.jpg,https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/b8d6888897694982a0d75a51fc9319d0.jpg,http://116.62.67.230:8082/pics/1882019041600000001.png,https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","level":"5","replayContent":"222","commentDate":"2019-08-09 10:32:25","replayDate":"2019-08-09 11:12:20"},{"businessId":332,"businessType":null,"commentContent":"2882","replayPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/b8d6888897694982a0d75a51fc9319d0.jpg","level":"5","replayContent":"11","commentDate":"2019-08-09 10:32:25","replayDate":"2019-08-09 15:03:31"}]
         */

        public int driverId;
        private String driverName;
        private String sendTime;
        private int businessType;
        private String commentContent;
        private Object replayPic;
        private String level;
        private Object replayContent;
        private Object commentDate;
        private Object replayDate;
        private List<ListBean> list;

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public Object getReplayPic() {
            return replayPic;
        }

        public void setReplayPic(Object replayPic) {
            this.replayPic = replayPic;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public Object getReplayContent() {
            return replayContent;
        }

        public void setReplayContent(Object replayContent) {
            this.replayContent = replayContent;
        }

        public Object getCommentDate() {
            return commentDate;
        }

        public void setCommentDate(Object commentDate) {
            this.commentDate = commentDate;
        }

        public Object getReplayDate() {
            return replayDate;
        }

        public void setReplayDate(Object replayDate) {
            this.replayDate = replayDate;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * businessId : 40
             * businessType : null
             * commentContent : ¥：¥2828
             * replayPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/b8d6888897694982a0d75a51fc9319d0.jpg,https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/active/b8d6888897694982a0d75a51fc9319d0.jpg,http://116.62.67.230:8082/pics/1882019041600000001.png,https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
             * level : 5
             * replayContent : 222
             * commentDate : 2019-08-09 10:32:25
             * replayDate : 2019-08-09 11:12:20
             */

            private int businessId;
            private Object businessType;
            private String commentContent;
            private String replayPic;
            private String level;
            private String replayContent;
            private String commentDate;
            private String replayDate;

            public String productName;
            public String productUrl;


            public int getBusinessId() {
                return businessId;
            }

            public void setBusinessId(int businessId) {
                this.businessId = businessId;
            }

            public Object getBusinessType() {
                return businessType;
            }

            public void setBusinessType(Object businessType) {
                this.businessType = businessType;
            }

            public String getCommentContent() {
                return commentContent;
            }

            public void setCommentContent(String commentContent) {
                this.commentContent = commentContent;
            }

            public String getReplayPic() {
                return replayPic;
            }

            public void setReplayPic(String replayPic) {
                this.replayPic = replayPic;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getReplayContent() {
                return replayContent;
            }

            public void setReplayContent(String replayContent) {
                this.replayContent = replayContent;
            }

            public String getCommentDate() {
                return commentDate;
            }

            public void setCommentDate(String commentDate) {
                this.commentDate = commentDate;
            }

            public String getReplayDate() {
                return replayDate;
            }

            public void setReplayDate(String replayDate) {
                this.replayDate = replayDate;
            }
        }
    }
}
