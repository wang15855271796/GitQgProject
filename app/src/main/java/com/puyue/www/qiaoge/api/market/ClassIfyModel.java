package com.puyue.www.qiaoge.api.market;

import java.util.List;

public class ClassIfyModel {
    /**
     * code : 1
     * message : 成功
     * data : [{"name":"一级分类","firstId":64,"imgUrl":null,"secondClassify":[{"name":"分类1","secondId":65},{"name":"分类2","secondId":66}]}]
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
         * name : 一级分类
         * firstId : 64
         * imgUrl : null
         * secondClassify : [{"name":"分类1","secondId":65},{"name":"分类2","secondId":66}]
         */

        private String name;
        private int firstId;
        private String imgUrl;
        private List<SecondClassifyBean> secondClassify;

        @Override
        public String toString() {
            return "DataBean{" +
                    "name='" + name + '\'' +
                    ", firstId=" + firstId +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", secondClassify=" + secondClassify +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFirstId() {
            return firstId;
        }

        public void setFirstId(int firstId) {
            this.firstId = firstId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public List<SecondClassifyBean> getSecondClassify() {
            return secondClassify;
        }

        public void setSecondClassify(List<SecondClassifyBean> secondClassify) {
            this.secondClassify = secondClassify;
        }

        public static class SecondClassifyBean {
            /**
             * name : 分类1
             * secondId : 65
             */

            private String name;
            private int secondId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSecondId() {
                return secondId;
            }

            public void setSecondId(int secondId) {
                this.secondId = secondId;
            }

            @Override
            public String toString() {
                return "SecondClassifyBean{" +
                        "name='" + name + '\'' +
                        ", secondId=" + secondId +
                        '}';
            }
        }
    }
}
