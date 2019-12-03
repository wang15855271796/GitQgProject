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

public class ChangePayPasswordAPI {
    public interface ChangePayPasswordService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CHANGE_PAY_PASSWORD)
        Observable<BaseModel> setParams(@Field("phone") String phone,
                                        @Field("type") int type,
                                        @Field("verifyCode") String verifyCode,
                                        @Field("password") String pwd);
    }

    public static Observable<BaseModel> requestChangePayPassword(Context context, String phone, int type, String verifyCode, String pwd) {
        Observable<BaseModel> changePayPasswordObservable = RestHelper.getBaseRetrofit(context).create(ChangePayPasswordService.class).setParams(phone, type, verifyCode, pwd);
        return changePayPasswordObservable;
    }
}
