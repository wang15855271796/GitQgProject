package com.puyue.www.qiaoge.api.mine.login;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.login.ChangeLoginPhoneModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/16.
 */

public class ChangeLoginPhoneAPI {
    public interface ChangeLoginPhoneService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.UPDATE_BIND_PHONE)
        Observable<ChangeLoginPhoneModel> setParams(@Field("phone") String phone,
                                                    @Field("verifyCode") String verifyCode);
    }

    public static Observable<ChangeLoginPhoneModel> requestChangeLoginPhone(Context context, String phone, String verifyCode) {
        Observable<ChangeLoginPhoneModel> changeLoginPhoneModelObservable = RestHelper.getBaseRetrofit(context).create(ChangeLoginPhoneService.class).setParams(phone, verifyCode);
        return changeLoginPhoneModelObservable;
    }
}
