package com.puyue.www.qiaoge.model.mine.order;

/**
 * Created by ${daff}
 * on 2018/10/25
 * 备注
 */
public class VipPayModel {

    /**
     * code : 1
     * message : 成功
     * data : {"outTradeNo":"8812018102500000034","title":"购买1个月会员","payToken":"_input_charset=\"utf-8\"&body=\"购买1个月会员\"&notify_url=\"http://116.62.67.230:8082/qiaoge/common/vip/notify/alipayNotify\"&out_trade_no=\"8812018102500000034\"&partner=\"2088721203069640\"&payment_type=\"1\"&seller_id=\"13024298999@163.com\"&service=\"mobile.securitypay.pay\"&subject=\"购买1个月会员\"&timeout_express=\"12m\"&total_fee=\"0.01\"&sign=\"aCiI7F0FUg4ohe5YyeYRZEln%2FkskQT%2BonOz6LmcwtqSteqj15ruwEPwfkdrjNuo8a45Z8bvgmRpEXhpm68iVB0%2BLHoqX%2BwfwTv5gSPDmws7LzGGBdDA15277zWv9saWPKDO4IDdIRUTWHOUyAL3GYnE6wQS9UoA3c6Roq4pVKXA%3D\"&sign_type=\"RSA\"","payType":2}
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
         * outTradeNo : 8812018102500000034
         * title : 购买1个月会员
         * payToken : _input_charset="utf-8"&body="购买1个月会员"&notify_url="http://116.62.67.230:8082/qiaoge/common/vip/notify/alipayNotify"&out_trade_no="8812018102500000034"&partner="2088721203069640"&payment_type="1"&seller_id="13024298999@163.com"&service="mobile.securitypay.pay"&subject="购买1个月会员"&timeout_express="12m"&total_fee="0.01"&sign="aCiI7F0FUg4ohe5YyeYRZEln%2FkskQT%2BonOz6LmcwtqSteqj15ruwEPwfkdrjNuo8a45Z8bvgmRpEXhpm68iVB0%2BLHoqX%2BwfwTv5gSPDmws7LzGGBdDA15277zWv9saWPKDO4IDdIRUTWHOUyAL3GYnE6wQS9UoA3c6Roq4pVKXA%3D"&sign_type="RSA"
         * payType : 2
         */

        private String outTradeNo;
        private String title;
        private String payToken;
        private int payType;

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPayToken() {
            return payToken;
        }

        public void setPayToken(String payToken) {
            this.payToken = payToken;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }
    }
}
