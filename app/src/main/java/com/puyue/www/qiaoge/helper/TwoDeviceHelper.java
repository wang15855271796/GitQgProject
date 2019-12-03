package com.puyue.www.qiaoge.helper;

import android.content.Context;

import com.puyue.www.qiaoge.event.LogoutEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2018/4/3.
 */
/**
 * 主要用于四个Tab
 * Created by GuoGai on 2016/10/28.
 */
public class TwoDeviceHelper {
    /**
     * 退出登录并且跳转到首页
     *
     * @param context
     */
    public static void logoutAndToHome(Context context) {
        //清空UserId
        UserInfoHelper.saveUserId(context, "");
        UserInfoHelper.saveUserType(context, "");
        UserInfoHelper.saveUserHomeRefresh(context, "");
        UserInfoHelper.saveUserMarketRefresh(context, "");
        EventBus.getDefault().post(new LogoutEvent());
    }
}
