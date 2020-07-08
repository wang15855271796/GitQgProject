package com.puyue.www.qiaoge.model.cart;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/26.
 */

public class OrderPayModel {


    /**
     * code : 1
     * message : 成功
     * data : {"outTradeNo":"8d36e99baa0d41829bbb04e06d492afe","title":"团购","payToken":"_input_charset=\"utf-8\"&body=\"团购\"&notify_url=\"null\"&out_trade_no=\"8d36e99baa0d41829bbb04e06d492afe\"&partner=\"2088721203069640\"&payment_type=\"1\"&seller_id=\"13024298999@163.com\"&service=\"mobile.securitypay.pay\"&subject=\"团购\"&total_fee=\"80.0\"&sign=\"J8g9fIaXUJbvN4HXKrMW4sLtaX4nZz0tTwlBtKmDHr1cCx%2BLmkXXgHNRtcNY77v6lI%2FsIvbuc9os0id%2Beu%2B72AxGyPzGOfQ6%2FiHFfxOxvmAJobMKvougn3bDS8RJv0I4KHP0ZuCMq0W7TyR2Ry8vN3RcSyv8%2FqzQib6gesedQHk%3D\"&sign_type=\"RSA\"","payType":2}
     * success : true
     * error : false
     */

    public int code;
    public String message;
    public DataBean data;
    public boolean success;
    public boolean error;

    public static class DataBean {
        /**
         * outTradeNo : 8d36e99baa0d41829bbb04e06d492afe
         * title : 团购
         * payToken : _input_charset="utf-8"&body="团购"&notify_url="null"&out_trade_no="8d36e99baa0d41829bbb04e06d492afe"&partner="2088721203069640"&payment_type="1"&seller_id="13024298999@163.com"&service="mobile.securitypay.pay"&subject="团购"&total_fee="80.0"&sign="J8g9fIaXUJbvN4HXKrMW4sLtaX4nZz0tTwlBtKmDHr1cCx%2BLmkXXgHNRtcNY77v6lI%2FsIvbuc9os0id%2Beu%2B72AxGyPzGOfQ6%2FiHFfxOxvmAJobMKvougn3bDS8RJv0I4KHP0ZuCMq0W7TyR2Ry8vN3RcSyv8%2FqzQib6gesedQHk%3D"&sign_type="RSA"
         * payType : 2
         */

        public String outTradeNo;
        public String title;
        public String payToken;
        public int payType;
        public String appPayRequest;
    }
}
