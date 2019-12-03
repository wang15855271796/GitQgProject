package com.puyue.www.qiaoge.api.mine.login;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.login.CheckPasswordCodeModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/19.
 */

public class CheckCommonCodeAPI {
    public interface CheckCommonCodeService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CHECK_COMMON_CODE)
        Observable<CheckPasswordCodeModel> setParams(@Field("phone") String phone,
                                                     @Field("verifyCode") String verifyCode,
                                                     @Field("type") int type);
    }

    public static Observable<CheckPasswordCodeModel> requestCodeRight(Context context, String phone, String verifyCode, int type) {
        Observable<CheckPasswordCodeModel> checkPasswordCodeModelObservable = RestHelper.getBaseRetrofit(context).create(CheckCommonCodeService.class).setParams(phone, verifyCode, type);
        return checkPasswordCodeModelObservable;
    }
}
