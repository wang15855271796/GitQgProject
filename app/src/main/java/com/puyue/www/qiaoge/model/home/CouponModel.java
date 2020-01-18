package com.puyue.www.qiaoge.model.home;

import java.util.List;

/**
 * Created by ${王涛} on 2020/1/5
 */
public class CouponModel {

    /**
     * code : 1
     * message : 成功
     * data : {"title":"特惠专区","desc":"特惠优选，最值得购买","currentTime":1576029132825,"startTime":1575820800000,"endTime":1576080000000,"actives":[{"activeId":611,"activeType":11,"activeName":"成本小数2","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","price":"￥20/包","oldPrice":"","flag":0,"soldOutPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//f03a8af175424e33a0cd42b36cc3fab7.png"}]}
     * error : false
     * success : true
     */

    private int code;
    private String message;
    private DataBean data;
    private boolean error;
    private boolean success;

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

    public static class DataBean {
        /**
         * title : 特惠专区  3团购 2秒杀
         * desc : 特惠优选，最值得购买
         * currentTime : 1576029132825
         * startTime : 1575820800000
         * endTime : 1576080000000
         * actives : [{"activeId":611,"activeType":11,"activeName":"成本小数2","defaultPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png","price":"￥20/包","oldPrice":"","flag":0,"soldOutPic":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//f03a8af175424e33a0cd42b36cc3fab7.png"}]
         */
        String discount;
        private String title;
        private String desc;
        private long currentTime;
        private long startTime;
        private long endTime;
        private List<ActivesBean> actives;

        @Override
        public String toString() {
            return "DataBean{" +
                    "discount='" + discount + '\'' +
                    ", title='" + title + '\'' +
                    ", desc='" + desc + '\'' +
                    ", currentTime=" + currentTime +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", actives=" + actives +
                    '}';
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

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

        public List<ActivesBean> getActives() {
            return actives;
        }

        public void setActives(List<ActivesBean> actives) {
            this.actives = actives;
        }

        public static class ActivesBean {
            /**
             * activeId : 611
             * activeType : 11
             * activeName : 成本小数2
             * defaultPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/common-img/5103acef0de94f9b967e6220cbe754bf2.png
             * price : ￥20/包
             * oldPrice :
             * flag : 0
             * soldOutPic : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image//f03a8af175424e33a0cd42b36cc3fab7.png
             */

            private int activeId;
            private int activeType;
            private String activeName;
            private String defaultPic;
            private String price;
            private String oldPrice;
            private int flag;
            private String soldOutPic;
            String discount;

            @Override
            public String toString() {
                return "ActivesBean{" +
                        "activeId=" + activeId +
                        ", activeType=" + activeType +
                        ", activeName='" + activeName + '\'' +
                        ", defaultPic='" + defaultPic + '\'' +
                        ", price='" + price + '\'' +
                        ", oldPrice='" + oldPrice + '\'' +
                        ", flag=" + flag +
                        ", soldOutPic='" + soldOutPic + '\'' +
                        ", discount='" + discount + '\'' +
                        '}';
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
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

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public String getSoldOutPic() {
                return soldOutPic;
            }

            public void setSoldOutPic(String soldOutPic) {
                this.soldOutPic = soldOutPic;
            }
        }
    }
}
