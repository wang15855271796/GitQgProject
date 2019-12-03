package com.puyue.www.qiaoge.model.mine;

import java.util.List;

/**
 * Created by ${daff}
 * on 2018/10/24
 * 备注
 */
public class GetMyBalanceModle  {


    /**
     * code : 1
     * message : 成功
     * data : {"amount":"478.00","commission":"0.00","amountDesc":"送3张优惠券","banner":[{"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ca32449044e4478b8eb318c83496e62e.png","bannerDetailUrl":"http://shaokao.qiaogeshipin.cn/h5/activity/20180616/19b0284b07de4b9eb57f11f03260f497.html"}],"commissionUrl":"http://116.62.67.230:8082/apph5/html/commissIncome.html"}
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
         * amount : 478.00
         * commission : 0.00
         * amountDesc : 送3张优惠券
         * banner : [{"bannerUrl":"https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ca32449044e4478b8eb318c83496e62e.png","bannerDetailUrl":"http://shaokao.qiaogeshipin.cn/h5/activity/20180616/19b0284b07de4b9eb57f11f03260f497.html"}]
         * commissionUrl : http://116.62.67.230:8082/apph5/html/commissIncome.html
         */

        private String amount;
        private String commission;
        private String amountDesc;
        private String commissionUrl;
        private List<BannerBean> banner;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getAmountDesc() {
            return amountDesc;
        }

        public void setAmountDesc(String amountDesc) {
            this.amountDesc = amountDesc;
        }

        public String getCommissionUrl() {
            return commissionUrl;
        }

        public void setCommissionUrl(String commissionUrl) {
            this.commissionUrl = commissionUrl;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class BannerBean {
            /**
             * bannerUrl : https://barbecue-img.oss-cn-hangzhou.aliyuncs.com/image/ca32449044e4478b8eb318c83496e62e.png
             * bannerDetailUrl : http://shaokao.qiaogeshipin.cn/h5/activity/20180616/19b0284b07de4b9eb57f11f03260f497.html
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
