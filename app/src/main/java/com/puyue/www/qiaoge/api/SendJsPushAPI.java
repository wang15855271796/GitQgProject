package com.puyue.www.qiaoge.api;

import android.content.Context;

import com.puyue.www.qiaoge.api.cart.GetCartNumAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/6/17
 */
public class SendJsPushAPI {
    private interface SendJsService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SENDJGUESRID)
        Observable<BaseModel> setParams(@Field("registrationId") String registrationId);
    }

    public static Observable<BaseModel> requestData(Context context,String registrationId) {
        SendJsService service = RestHelper.getBaseRetrofit(context).create(SendJsService.class);
        return service.setParams(registrationId);
    }
}
