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

public class SubAccountDeleteAPI {
    public interface SubAccountDeleteService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.DELETE_SUB_USER)
        Observable<BaseModel> setParams(@Field("subId") String subLoginPhone);
    }

    public static Observable<BaseModel> requestDeleteSubAccount(Context context, String subId) {
        Observable<BaseModel> deleteSubAccountObservable = RestHelper.getBaseRetrofit(context).create(SubAccountDeleteService.class).setParams(subId);
        return deleteSubAccountObservable;
    }
}
