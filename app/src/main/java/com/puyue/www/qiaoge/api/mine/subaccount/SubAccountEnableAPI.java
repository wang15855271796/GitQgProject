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

public class SubAccountEnableAPI {
    public interface SubAccountEnableService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.RECOVER_SUB_USER)
        Observable<BaseModel> setParams(@Field("subLoginPhone") String subLoginPhone);
    }

    public static Observable<BaseModel> requestEnableSubAccount(Context context, String subLoginPhone) {
        Observable<BaseModel> enableSubAccountObservable = RestHelper.getBaseRetrofit(context).create(SubAccountEnableService.class).setParams(subLoginPhone);
        return enableSubAccountObservable;
    }
}
