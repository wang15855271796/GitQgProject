package com.puyue.www.qiaoge.api;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/6/18
 */
public class PostLoadAmountAPI {
    private interface SendLoadService {

        @POST(AppInterfaceAddress.LOADAMOUNT)
        Observable<BaseModel> setParams();
    }

    public static Observable<BaseModel> requestData(Context context) {
        SendLoadService service = RestHelper.getBaseRetrofit(context).create(SendLoadService.class);
        return service.setParams();
    }
}
