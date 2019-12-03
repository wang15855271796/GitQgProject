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
 * Created by Administrator on 2018/4/19.
 */

public class ChangeLoginPasswordAPI {
    public interface ChangeLoginPasswordService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CHANGE_LOGIN_PASSWORD)
        Observable<BaseModel> setParams(@Field("phone") String phone,
                                        @Field("verifyCode") String verifyCode,
                                        @Field("password") String password);
    }
    public static Observable<BaseModel> requestChangeLoginPassword(Context context,String phone, String verifyCode,String password){
        Observable<BaseModel> changeLoginPasswordObservable = RestHelper.getBaseRetrofit(context).create(ChangeLoginPasswordService.class).setParams(phone, verifyCode, password);
        return changeLoginPasswordObservable;
    }
}
