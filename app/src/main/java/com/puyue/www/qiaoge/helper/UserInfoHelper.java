package com.puyue.www.qiaoge.helper;


import android.content.Context;


import com.puyue.www.qiaoge.constant.AppConstant;

import org.apache.commons.lang3.StringUtils;

public class UserInfoHelper {

    private static final String USER_INFO = "user_info";//文件名
    private static final String USER_INFO_IS_FIRST = "user_info_is_first";//是否第一次启动
    private static final String USER_INFO_USER_ID = "user_info_user_id";//userId
    private static final String USER_TEL = "user_tel";//用户手机号
    private static final String USER_INFO_USER_CELL = "user_info_user_cell";//dianh
    private static final String USER_SEARCH_HISTORY = "user_search_history";//用户搜索记录
    private static final String USER_SEARCH_CONTENT = "user_search_content";//用户搜索内容
    private static final String USER_TYPE = "user_type";//用户是批发用户还是零售用户
    private static final String USER_LOGIN_HOME_REFRESH = "user_login_home_refresh";//用户切换登录状态之后首页是否刷新数据了
    private static final String USER_LOGIN_MARKET_REFRESH = "user_login_market_refresh";//用户切换登录状态之后商城页是否刷新了数据
    private static final String USER_WALLET_ACCOUNT = "user_wallet_account";//获取用户账号余额
    private static final String USER_WALLET_NUM = "user_wallet_num";
    private static final String SPIKE_FLAG = "spike_flag";//获取秒杀活动的flag
    private static final String SPIKE_POSITION = "spike_position";//获取秒杀活动的flag

    private static final String WALLETSTATUS = "wallet_status";//温馨提示充值状态
    private static final String DATE = "date";//结算提示一次记录时间判断


    private static final String ORDERID = "orderId";

    private static final String CHANGEFLAG = "changeFlag";
    private static final String AreaName = "areaName";
    private static final String USERMINUTE = "user_minute";//记录弹窗

    private static final String FORGETPASSWORD = "forget_password";//退货规格

    private static final String ORDERAMOUNT = "order_amount";//订单金额

    private static final String REMARK = "remark";//订单留言


    private  static  final String GUIDE ="guide";

    /**
     * 极光推送id
     *
     */
    private  static  final String REGISTIONID ="registion_id";

    /**
     *存值上传下载量
     */
    private  static  final String LOADAMOUNT="load_amount";

    private  static  final String DRIVERID ="driver_id";//司机id
    /**
     * 订单类型
     */
    private static final String ORDERDELIVERYTYPE ="orderDeliveryType";

    /**
     *城市名字
     */
private static final String CITYNAME="city_name";

    /**
     *网络ip
     */
    private static final String PHONEIP= "phone_ip";

    /**
     *mac地址
     */

    private static final String MACLOCATION= "mac_login";

    /**
     *登录地址
     */
private static final String LOCATIONADRESS= "location_address";

private static final String ISREGISTER ="is_register";

    public static void saveIsRegister(Context context, String is_register) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, ISREGISTER, EncryptHelper.encrypt(AppConstant.APP_SHA_256, is_register));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getIsregister(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, ISREGISTER)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, ISREGISTER));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveLocation(Context context, String location_address) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, LOCATIONADRESS, EncryptHelper.encrypt(AppConstant.APP_SHA_256, location_address));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getLocationadress(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, LOCATIONADRESS)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, LOCATIONADRESS));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveMac(Context context, String mac_login) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, MACLOCATION, EncryptHelper.encrypt(AppConstant.APP_SHA_256, mac_login));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getMac(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, MACLOCATION)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, MACLOCATION));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }





    public static void savePhoneIp(Context context, String phone_ip) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, PHONEIP, EncryptHelper.encrypt(AppConstant.APP_SHA_256, phone_ip));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getPhoneip(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, PHONEIP)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, PHONEIP));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveCity(Context context, String cityName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, CITYNAME, EncryptHelper.encrypt(AppConstant.APP_SHA_256, cityName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getCity(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, CITYNAME)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, CITYNAME));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }
    public static void saveDeliverType(Context context, String returnName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, ORDERDELIVERYTYPE, EncryptHelper.encrypt(AppConstant.APP_SHA_256, returnName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getDeliverType(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, ORDERDELIVERYTYPE)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, ORDERDELIVERYTYPE));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveDriverId(Context context, String returnName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, DRIVERID, EncryptHelper.encrypt(AppConstant.APP_SHA_256, returnName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getDriverid(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, DRIVERID)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, DRIVERID));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveLoadAmount(Context context, String returnName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, LOADAMOUNT, EncryptHelper.encrypt(AppConstant.APP_SHA_256, returnName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getLoadAmount(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, LOADAMOUNT)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, LOADAMOUNT));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }




    public static void saveRegistionId(Context context, String returnName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, REGISTIONID, EncryptHelper.encrypt(AppConstant.APP_SHA_256, returnName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getRegistionid(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, REGISTIONID)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, REGISTIONID));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }








    public static void saveGuide(Context context, String returnName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, GUIDE, EncryptHelper.encrypt(AppConstant.APP_SHA_256, returnName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getGuide(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, GUIDE)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, GUIDE));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }
    public static void saveRemark(Context context, String returnName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, REMARK, EncryptHelper.encrypt(AppConstant.APP_SHA_256, returnName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getRemark(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, REMARK)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, REMARK));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }
    public static void saveOrderAmount(Context context, String returnName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, ORDERAMOUNT, EncryptHelper.encrypt(AppConstant.APP_SHA_256, returnName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getOrderamount(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, ORDERAMOUNT)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, ORDERAMOUNT));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }




    public static void saveForgetPas(Context context, String returnName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, FORGETPASSWORD, EncryptHelper.encrypt(AppConstant.APP_SHA_256, returnName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getForgetPas(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, FORGETPASSWORD)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, FORGETPASSWORD));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveUserMinute(Context context, String orderId) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USERMINUTE, EncryptHelper.encrypt(AppConstant.APP_SHA_256, orderId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserMinute(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USERMINUTE)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USERMINUTE));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveOrderId(Context context, String orderId) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, ORDERID, EncryptHelper.encrypt(AppConstant.APP_SHA_256, orderId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getOrderId(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, ORDERID)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, ORDERID));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }


    public static void saveDate(Context context, String date) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, DATE, EncryptHelper.encrypt(AppConstant.APP_SHA_256, date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveAreaName(Context context, String areaName) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, AreaName, EncryptHelper.encrypt(AppConstant.APP_SHA_256, areaName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getAreaName(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, AreaName)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, AreaName));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }


    public static void saveChangeFlag(Context context, String changeFlag) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, CHANGEFLAG, EncryptHelper.encrypt(AppConstant.APP_SHA_256, changeFlag));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getChangeFlag(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, CHANGEFLAG)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, CHANGEFLAG));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }


    public static String getDate(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, DATE)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, DATE));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveWalletStatus(Context context, String status) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, WALLETSTATUS, EncryptHelper.encrypt(AppConstant.APP_SHA_256, status));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getWalletStatus(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, WALLETSTATUS)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, WALLETSTATUS));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }


    public static void saveSpikePosition(Context context, String position) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, SPIKE_POSITION, EncryptHelper.encrypt(AppConstant.APP_SHA_256, position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSpikePosition(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, SPIKE_POSITION)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, SPIKE_POSITION));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveSpikeFlag(Context context, String flag) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, SPIKE_FLAG, EncryptHelper.encrypt(AppConstant.APP_SHA_256, flag));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSpikeFlag(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, SPIKE_FLAG)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, SPIKE_FLAG));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveUserWalletNum(Context context, String num) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_WALLET_NUM, EncryptHelper.encrypt(AppConstant.APP_SHA_256, num));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserWalletNum(Context context) {
        if ((StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_WALLET_NUM)))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_WALLET_NUM));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveUserWallet(Context context, String account) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_WALLET_ACCOUNT, EncryptHelper.encrypt(AppConstant.APP_SHA_256, account));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserWalletAccount(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_WALLET_ACCOUNT))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_WALLET_ACCOUNT));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveUserCell(Context context, String cell) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_INFO_USER_CELL, EncryptHelper.encrypt(AppConstant.APP_SHA_256, cell));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserCell(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_INFO_USER_CELL))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_INFO_USER_CELL));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveUserIsFirst(Context context, String userIsFirst) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_INFO_IS_FIRST, EncryptHelper.encrypt(AppConstant.APP_SHA_256, userIsFirst));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserIsFirst(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_INFO_IS_FIRST))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_INFO_IS_FIRST));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveUserId(Context context, String useId) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_INFO_USER_ID, EncryptHelper.encrypt(AppConstant.APP_SHA_256, useId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserId(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_INFO_USER_ID))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_INFO_USER_ID));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveUserTel(Context context, String userTel) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_TEL, EncryptHelper.encrypt(AppConstant.APP_SHA_256, userTel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserTel(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_TEL))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_TEL));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    //保存用户搜索记录
    public static void saveUserHistory(Context context, String userHistory) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_SEARCH_HISTORY, EncryptHelper.encrypt(AppConstant.APP_SHA_256, userHistory));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取用户搜索记录
    public static String getUserSearchHistory(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_SEARCH_HISTORY))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_SEARCH_HISTORY));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    //保存用户搜索内容
    public static void saveUserContent(Context context, String userContent) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_SEARCH_CONTENT, EncryptHelper.encrypt(AppConstant.APP_SHA_256, userContent));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取用户搜索内容
    public static String getUserSearchContent(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_SEARCH_CONTENT))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_SEARCH_CONTENT));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    //清除用户搜索记录
    public static void cleanUserSearchHistory(Context context) {
        PreferenceHelper.clearData(context, USER_INFO, USER_SEARCH_HISTORY);
    }

    public static void saveUserType(Context context, String type) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_TYPE, EncryptHelper.encrypt(AppConstant.APP_SHA_256, type));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserType(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_TYPE))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_TYPE));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveUserHomeRefresh(Context context, String homeRefresh) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_LOGIN_HOME_REFRESH, EncryptHelper.encrypt(AppConstant.APP_SHA_256, homeRefresh));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserHomeRefresh(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_LOGIN_HOME_REFRESH))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_LOGIN_HOME_REFRESH));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void saveUserMarketRefresh(Context context, String marketRefresh) {
        try {
            PreferenceHelper.saveData(context, USER_INFO, USER_LOGIN_MARKET_REFRESH, EncryptHelper.encrypt(AppConstant.APP_SHA_256, marketRefresh));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUserMarketRefresh(Context context) {
        if (StringUtils.isBlank(PreferenceHelper.getData(context, USER_INFO, USER_LOGIN_MARKET_REFRESH))) {
            return "";
        } else {
            try {
                return EncryptHelper.decrypt(AppConstant.APP_SHA_256, PreferenceHelper.getData(context, USER_INFO, USER_LOGIN_MARKET_REFRESH));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }
}
