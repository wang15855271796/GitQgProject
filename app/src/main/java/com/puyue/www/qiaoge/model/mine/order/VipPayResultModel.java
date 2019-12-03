package com.puyue.www.qiaoge.model.mine.order;

/**
 * Created by ${daff}
 * on 2018/10/25
 * 备注
 */
public class VipPayResultModel {

    /**
     * code : 1
     * message : 成功
     * data : {"tradeStatus":"TRADE_SUCCESS","payMsg":"支付成功","errorMsg":"已为您开通12个月VIP会员","vo":{"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ef3eae9b65cc488cac6c07ca1ba5adf5.png","bannerDetailUrl":"http://116.62.67.230:8082/apph5/html/invite.html"}}
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
         * tradeStatus : TRADE_SUCCESS
         * payMsg : 支付成功
         * errorMsg : 已为您开通12个月VIP会员
         * vo : {"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ef3eae9b65cc488cac6c07ca1ba5adf5.png","bannerDetailUrl":"http://116.62.67.230:8082/apph5/html/invite.html"}
         */

        private String tradeStatus;
        private String payMsg;
        private String errorMsg;
        private VoBean vo;

        public String getTradeStatus() {
            return tradeStatus;
        }

        public void setTradeStatus(String tradeStatus) {
            this.tradeStatus = tradeStatus;
        }

        public String getPayMsg() {
            return payMsg;
        }

        public void setPayMsg(String payMsg) {
            this.payMsg = payMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
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
