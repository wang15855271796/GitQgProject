package com.puyue.www.qiaoge.api.driver;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;

import com.puyue.www.qiaoge.view.DriverHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/6/15
 */
public class DriverSendLocationAPI {
    private interface SendLocationService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SENDLOCAITON)
        Observable<BaseModel> getData(@Field("longitude") double longitude, @Field("latitude") double latitude);
    }

    public static Observable<BaseModel> requestData(Context context, double longitude, double latitude) {
        SendLocationService service = DriverHelper.getBaseRetrofit(context).create(SendLocationService.class);
        return service.getData( longitude, latitude);
    }
}
