package com.puyue.www.qiaoge.api.mine.login;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/7/9
 */
public class AuthPhoneAPI {
    public interface AuthPhoneService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.AUTHMOBILE)
        Observable<BaseModel> setParams(@Field("phone") String phone, @Field("accessCode") String accessCode);
    }

    public static Observable<BaseModel> requestAuthPhone(Context context, String phone, String accessCode) {
        Observable<BaseModel> sendCodeObservable = RestHelper.getBaseRetrofit(context).create(AuthPhoneService.class).setParams(phone, accessCode);
        return sendCodeObservable;
    }
}
