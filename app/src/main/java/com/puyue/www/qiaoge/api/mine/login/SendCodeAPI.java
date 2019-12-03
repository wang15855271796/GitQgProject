package com.puyue.www.qiaoge.api.mine.login;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/18.
 */

public class SendCodeAPI {
    public interface SendCodeService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SEND_AUTH_CODE)
        Observable<BaseModel> setParams(@Field("phone") String phone, @Field("type") int type);
    }

    public static Observable<BaseModel> requestSendCode(Context context, String phone, int type) {
        Observable<BaseModel> sendCodeObservable = RestHelper.getBaseRetrofit(context).create(SendCodeService.class).setParams(phone, type);
        return sendCodeObservable;
    }

    public interface SendCodeServices {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CHECKMESSAGE)
        Observable<BaseModel> setParams(@Field("phone") String phone, @Field("verifyCode") String verifyCode);
    }

    public static Observable<BaseModel> requestSendCodes(Context context, String phone, String verifyCode) {
        Observable<BaseModel> sendCodeObservable = RestHelper.getBaseRetrofit(context).create(SendCodeServices.class).setParams(phone, verifyCode);
        return sendCodeObservable;
    }

}
