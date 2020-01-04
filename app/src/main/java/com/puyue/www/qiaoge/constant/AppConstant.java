package com.puyue.www.qiaoge.constant;

import android.content.SharedPreferences;

/**
 * Created by GuoGai on 2016/10/31.
 */
public class AppConstant {
    public static final String APP_SHA_256 = "45BF8C0F40CD37E3DB34CA435E94F90AEA6B73198926A5DBEF8C50FB3D94EEDA";
    public static final String USE_ID = "userId";
    public static final String TOKEN = "token";
    public static final String APP_TYPE = "clientType";//ANDROID
    public static final String VERSION = "version";
    public static final String SIGN = "sign";
    public static final String STIME = "stime";
    public static final String USERMODEL = "USERMODEL";
    public static final String PRODUCTCODE = "PRODUCTCODE";
    public static final String PHONENO = "phoneno";
    public static final String PLATFORMCODE = "platformCode";
    public static final String FORCE_LOG_OUT = "FORCE_LOG_OUT";
    public static final String SERVER_UPDATE = "server_update";
    public static final String NET_TIME_OUT = "SocketTimeoutException";
    public static final String SMS_TYPE_REGISTER = "LOGIN_VERIFY";
    public static final int ANOTHER_PLACE_LOGIN = -10000;

    public static SharedPreferences preferences;

    public static final String IMG = "https://yomi-huadong1.oss-cn-hangzhou.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720180329181612.jpg";
    public static final String ALL = "all";//全部订单
    public static final String PAYMENT = "payment";//待付款
    public static final String DELIVERY = "delivery";//待发货
    public static final String RECEIVED = "received";//待收货
    public static final String EVALUATED = "evaluated";//待评价
    public static final String RETURN = "return";//退货

    public static final String SEARCHWORD = "search_word";
    public static final String SEARCHTYPE = "sraech_type";
    public static final String HOME_SEARCH = "home_search_type";

    public static final String PAGETYPE = "page_type";//首页进入列表页
    /*新品上市:new
    秒杀活动:second
    热销商品:hot
    组合团购:group
    VIP会员:space
    分享有礼: share
    降价商品: reduction
    特别推荐:recommend
    常用清单:common

    特惠专区：special
      */
    public static final String SPECIAL="special";
    public static final String RETAIL = "retail";
    public static final String CONSULT ="messageNews";
    public static final String NEWTYPE = "new";
    public static final String SECONDTYPE = "second";
    public static final String HOTTYPE = "hotProd";
    public static final String GROUPTYPE = "group";
    public static final String VIPTYPE = "vip";
    public static final String SHARETYPE = "share";
    public static final String REDUCTIONTYPE = "reductProd";
    public static final String COMMONTYPE = "commonBuy";
    public static final String RECOMMEND = "recommend";
    public static final String ACTIVEID = "activeId";
    public static final String ORDERID = "orderId";
    public static final String ORDERSTATE = "orderState";
    public static final String RETURNPRODUCTMAINID = "returnProductMainId";
    public static final String PAYCHANNAL = "paychannal";
    public static final String OUTTRADENO = "outTradeNo";
    public static final String PAYSTATE = "payState";
    public static final String ACCOUNTTYPE = "accountType";
    public static final String LONGITUDE = "longitude";//经度
    public static final String LATITLATITUDE = "latitlatitude";//纬度
    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    public static final String USER_TYPE_RETAIL = "1";//零售用户
    public static final String USER_TYPE_WHOLESALE = "2";//批发用户
    public static final String SERVICE_ACTION = "com.qiaoge.action";
    public static final String PAY_PASSWORD_ACTION = "pay.qiaoge.action";
    public static final String WHOLESALE = "wholesale";


    public static final String PHONEMODEL="phoneModel";
    public static final String SYSETEMMODEL="systemModel";
    public static final String ANDROIDMODEL="androidModel";


    public static final String  ORDERDELIVERYTYPE= "orderDeliveryType";
    public static final  String CITYNAME= "cityName";


    public static final  String PHONEIP= "loginIp";
    public static final  String MACIP= "loginMac";
    public static final  String LOCATIONADDRESS= "loginAddress";
}
