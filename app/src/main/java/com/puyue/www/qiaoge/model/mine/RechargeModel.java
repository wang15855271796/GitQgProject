package com.puyue.www.qiaoge.model.mine;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/27.
 */

public class RechargeModel {

    /**
     * code : 1
     * message : 成功
     * data : {"outTradeNo":"36e6a4a9aeba430f97f7e5b381a5a806","title":"充值","payToken":"_input_charset=\"utf-8\"&body=\"充值\"&notify_url=\"http://116.62.67.230:8088/common/notify/alipayNotify\"&out_trade_no=\"36e6a4a9aeba430f97f7e5b381a5a806\"&partner=\"2088721203069640\"&payment_type=\"1\"&seller_id=\"13024298999@163.com\"&service=\"mobile.securitypay.pay\"&subject=\"充值\"&total_fee=\"0.01\"&sign=\"J1ehBBIEnVCe%2F%2FoiulQklx5j9QsydC%2FoGE%2Fw%2FtbXoUQ7OL%2BLinFtjAxFqyjrR4WY7on65%2BPgbyFXgKTmk3Wzw6BwYDBMHRsWwyntbHU%2BGqFclDOKujxtnlRCQLmxcHfg54V%2BX5IOqne59AlZXeAWbCBqjn9Cb0TdBzLNwRrr8ik%3D\"&sign_type=\"RSA\"","payType":2}
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
         * outTradeNo : 36e6a4a9aeba430f97f7e5b381a5a806
         * title : 充值
         * payToken : _input_charset="utf-8"&body="充值"&notify_url="http://116.62.67.230:8088/common/notify/alipayNotify"&out_trade_no="36e6a4a9aeba430f97f7e5b381a5a806"&partner="2088721203069640"&payment_type="1"&seller_id="13024298999@163.com"&service="mobile.securitypay.pay"&subject="充值"&total_fee="0.01"&sign="J1ehBBIEnVCe%2F%2FoiulQklx5j9QsydC%2FoGE%2Fw%2FtbXoUQ7OL%2BLinFtjAxFqyjrR4WY7on65%2BPgbyFXgKTmk3Wzw6BwYDBMHRsWwyntbHU%2BGqFclDOKujxtnlRCQLmxcHfg54V%2BX5IOqne59AlZXeAWbCBqjn9Cb0TdBzLNwRrr8ik%3D"&sign_type="RSA"
         * payType : 2
         */

        public String outTradeNo;
        public String title;
        public String payToken;
        public int payType;
    }
}
