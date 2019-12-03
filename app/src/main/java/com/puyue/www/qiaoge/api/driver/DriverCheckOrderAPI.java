package com.puyue.www.qiaoge.api.driver;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;

import com.puyue.www.qiaoge.model.driver.DriverCheckModel;
import com.puyue.www.qiaoge.view.DriverHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/6/17
 */
public class DriverCheckOrderAPI {
    private interface DriverCheckService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.DRIVERCHECK)
        Observable<DriverCheckModel> getData(@Field("orderId") String orderId);
    }

    public static Observable<DriverCheckModel> requestData(Context context,String orderId) {
        DriverCheckService service = DriverHelper.getBaseRetrofit(context).create(DriverCheckService.class);
        return service.getData(orderId);
    }
}
