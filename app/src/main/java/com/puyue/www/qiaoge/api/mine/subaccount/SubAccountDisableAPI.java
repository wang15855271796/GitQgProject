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
 * Created by Administrator on 2018/4/19.
 */
public class SubAccountDisableAPI {
    public interface SubAccountDisableService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.DISABLE_SUB_USER)
        Observable<BaseModel> setParams(@Field("subLoginPhone") String subLoginPhone);
    }

    public static Observable<BaseModel> requestDisableSubAccount(Context context,String subLoginPhone) {
        Observable<BaseModel> disableSubAccountObservable = RestHelper.getBaseRetrofit(context).create(SubAccountDisableService.class).setParams(subLoginPhone);
        return disableSubAccountObservable;
    }
}
