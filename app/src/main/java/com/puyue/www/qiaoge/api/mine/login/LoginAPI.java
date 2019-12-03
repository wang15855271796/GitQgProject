package com.puyue.www.qiaoge.api.mine.login;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.login.LoginModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/18.
 */

public class LoginAPI {
    public interface LoginService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.LOGIN)
        Observable<LoginModel> setParams(@Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("verifyCode") String verifyCode,
                                         @Field("loginType") int loginType);
    }

    public static Observable<LoginModel> requestLogin(Context context, String phone, String password, String verifyCode, int loginType) {
        Observable<LoginModel> loginModelObservable = RestHelper.getBaseRetrofit(context).create(LoginService.class).setParams(phone, password, verifyCode, loginType);
        return loginModelObservable;
    }
}
