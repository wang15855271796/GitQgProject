package com.puyue.www.qiaoge.api.mine.subaccount;

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
 * Created by Administrator on 2018/4/20.
 */

public class SubAccountAddAPI {
    public interface SubAccountAddService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.ADD_SUB_USER)
        Observable<BaseModel> setParams(@Field("subLoginPhone") String subLoginPhone,
                                        @Field("subLoginUserName") String subLoginUserName,
                                        @Field("subLoginPwd") String subLoginPwd,
                                        @Field("verifyCode") String verifyCode,
                                        @Field("version") String version);
    }

    public static Observable<BaseModel> requestAddSubAccount(Context context, String subLoginPhone, String subLoginUserName, String subLoginPwd, String verifyCode,String version) {
        Observable<BaseModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(SubAccountAddService.class).setParams(subLoginPhone, subLoginUserName, subLoginPwd,verifyCode,version);
        return addSubAccountObservable;
    }
}
