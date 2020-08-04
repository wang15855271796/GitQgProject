package com.puyue.www.qiaoge.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.chuanglan.shanyan_sdk.OneKeyLoginManager;
import com.chuanglan.shanyan_sdk.listener.OneKeyLoginListener;
import com.chuanglan.shanyan_sdk.listener.OpenLoginAuthListener;
import com.puyue.www.qiaoge.activity.ConfigUtils;
import com.puyue.www.qiaoge.activity.mine.login.LoginActivity;
import com.puyue.www.qiaoge.activity.mine.login.RegisterMessageActivity;
import com.puyue.www.qiaoge.activity.mine.login.RegisterStep1Activity;
import com.puyue.www.qiaoge.api.home.GetCustomerPhoneAPI;
import com.puyue.www.qiaoge.api.home.OneRegisterModel;
import com.puyue.www.qiaoge.helper.AppHelper;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by ${王涛} on 2020/5/25
 */
public class LoginUtil {

    private static String token1;

    public static void initRegister(Context context) {
        OneKeyLoginManager.getInstance().setAuthThemeConfig(ConfigUtils.getCJSConfig(context));
        openLoginActivity(context);
    }

    private static void openLoginActivity(Context context) {
        //开始拉取授权页
        OneKeyLoginManager.getInstance().openLoginAuth(false, new OpenLoginAuthListener() {
            @Override
            public void getOpenLoginAuthStatus(int code, String result) {

                if (1000 == code) {
                    Log.e("VVV", "拉起授权页成功： code==" + code + "   result==" + result);
                } else {
                    Log.e("VVV", "拉起授权页失败： code==" + code + "   result==" + result);
                    Intent intent = new Intent(context,RegisterMessageActivity.class);
                    context.startActivity(intent);
                }
            }
        }, new OneKeyLoginListener() {
            @Override
            public void getOneKeyLoginStatus(int code, String result) {

                if (1011 == code) {
                    Log.e("VVV", "用户点击授权页返回： code==" + code + "   result==" + result);
                    return;
                } else if (1000 == code) {
                    Log.e("VVV", "用户点击登录获取token成功： code==" + code + "   result==" + result);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(result);
                        token1 = jsonObject.getString("token");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    checks(token1,context);
                } else {
                    Log.e("VVV", "用户点击登录获取token失败： code==" + code + "   result==" + result);
                }
            }
        });
    }

    private static void checks(String result,Context context) {
        GetCustomerPhoneAPI.getData(context,result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OneRegisterModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(OneRegisterModel baseModel) {
                        if (baseModel.success) {
                            Intent intent = new Intent(context,RegisterStep1Activity.class);
                            intent.putExtra("phone",baseModel.data);
                            intent.putExtra("token1",result);
                            context.startActivity(intent);
                            OneKeyLoginManager.getInstance().finishAuthActivity();
                        } else {
                            AppHelper.showMsg(context, baseModel.message);
                            OneKeyLoginManager.getInstance().finishAuthActivity();
                        }
                    }
                });
    }
}
