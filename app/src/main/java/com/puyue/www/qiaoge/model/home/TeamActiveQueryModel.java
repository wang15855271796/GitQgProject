package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */

public class TeamActiveQueryModel {


    /**
     * code : 1
     * message : 成功
     * data : [{"currentTime":1577092580232,"startTime":0,"endTime":0,"actives":[{"activeId":614,"activeType":11,"activeName":"脚本测试商品（无动）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","spec":"500g","price":"￥10.25","oldPrice":"11.28","progress":"0","remainNum":"剩余9587箱"}]}]
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
         * currentTime : 1577092580232
         * startTime : 0
         * endTime : 0
         * actives : [{"activeId":614,"activeType":11,"activeName":"脚本测试商品（无动）","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","spec":"500g","price":"￥10.25","oldPrice":"11.28","progress":"0","remainNum":"剩余9587箱"}]
         */

        private long currentTime;
        private long startTime;
        private long endTime;
        String title;
        private List<ActivesBean> actives;

        @Override
        public String toString() {
            return "DataBean{" +
                    "currentTime=" + currentTime +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", title='" + title + '\'' +
                    ", actives=" + actives +
                    '}';
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(long currentTime) {
            this.currentTime = currentTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public List<ActivesBean> getActives() {
            return actives;
        }

        public void setActives(List<ActivesBean> actives) {
            this.actives = actives;
        }

        public static class ActivesBean {
            /**
             * activeId : 614
             * activeType : 11
             * activeName : 脚本测试商品（无动）
             * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
             * spec : 500g
             * price : ￥10.25
             * oldPrice : 11.28
             * progress : 0
             * remainNum : 剩余9587箱
             */
            private int saleDone;
            private int activeId;
            private int activeType;
            private String activeName;
            private String defaultPic;
            private String spec;
            private String price;
            private String oldPrice;
            private String progress;
            private String remainNum;
            String discount;

            @Override
            public String toString() {
                return "ActivesBean{" +
                        "saleDone=" + saleDone +
                        ", activeId=" + activeId +
                        ", activeType=" + activeType +
                        ", activeName='" + activeName + '\'' +
                        ", defaultPic='" + defaultPic + '\'' +
                        ", spec='" + spec + '\'' +
                        ", price='" + price + '\'' +
                        ", oldPrice='" + oldPrice + '\'' +
                        ", progress='" + progress + '\'' +
                        ", remainNum='" + remainNum + '\'' +
                        ", discount='" + discount + '\'' +
                        '}';
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public int getSaleDone() {
                return saleDone;
            }

            public void setSaleDone(int saleDone) {
                this.saleDone = saleDone;
            }

            public int getActiveId() {
                return activeId;
            }

            public void setActiveId(int activeId) {
                this.activeId = activeId;
            }

            public int getActiveType() {
                return activeType;
            }

            public void setActiveType(int activeType) {
                this.activeType = activeType;
            }

            public String getActiveName() {
                return activeName;
            }

            public void setActiveName(String activeName) {
                this.activeName = activeName;
            }

            public String getDefaultPic() {
                return defaultPic;
            }

            public void setDefaultPic(String defaultPic) {
                this.defaultPic = defaultPic;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(String oldPrice) {
                this.oldPrice = oldPrice;
            }

            public String getProgress() {
                return progress;
            }

            public void setProgress(String progress) {
                this.progress = progress;
            }

            public String getRemainNum() {
                return remainNum;
            }

            public void setRemainNum(String remainNum) {
                this.remainNum = remainNum;
            }
        }
    }
}
