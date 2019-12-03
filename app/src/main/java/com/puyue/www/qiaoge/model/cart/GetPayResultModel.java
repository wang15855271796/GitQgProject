package com.puyue.www.qiaoge.model.cart;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/27.
 */

public class GetPayResultModel {


    /**
     * code : 1
     * message : 成功
     * data : {"result":true,"message":"支付成功","otherMessage":"本次购物一共获得181积分，积分可以在\n积分商城兑换优惠券","vo":{"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ef3eae9b65cc488cac6c07ca1ba5adf5.png","bannerDetailUrl":"http://116.62.67.230:8082/apph5/html/invite.html"}}
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
         * result : true
         * message : 支付成功
         * otherMessage : 本次购物一共获得181积分，积分可以在
         积分商城兑换优惠券
         * vo : {"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ef3eae9b65cc488cac6c07ca1ba5adf5.png","bannerDetailUrl":"http://116.62.67.230:8082/apph5/html/invite.html"}
         */

        private boolean result;
        private String message;
        private String otherMessage;
        private VoBean vo;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getOtherMessage() {
            return otherMessage;
        }

        public void setOtherMessage(String otherMessage) {
            this.otherMessage = otherMessage;
        }

        public VoBean getVo() {
            return vo;
        }

        public void setVo(VoBean vo) {
            this.vo = vo;
        }

        public static class VoBean {
            /**
             * bannerUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ef3eae9b65cc488cac6c07ca1ba5adf5.png
             * bannerDetailUrl : http://116.62.67.230:8082/apph5/html/invite.html
             */

            private String bannerUrl;
            private String bannerDetailUrl;

            public String getBannerUrl() {
                return bannerUrl;
            }

            public void setBannerUrl(String bannerUrl) {
                this.bannerUrl = bannerUrl;
            }

            public String getBannerDetailUrl() {
                return bannerDetailUrl;
            }

            public void setBannerDetailUrl(String bannerDetailUrl) {
                this.bannerDetailUrl = bannerDetailUrl;
            }
        }
    }
}
