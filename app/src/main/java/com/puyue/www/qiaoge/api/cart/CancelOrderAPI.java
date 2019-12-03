package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.CancelOrderModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/27.
 */

public class CancelOrderAPI {
    private interface CancelOrderService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CANCELORDER)
        Observable<CancelOrderModel> getData(@Field("orderId") String orderId);
    }

    public static Observable<CancelOrderModel> requestData(Context context, String orderId) {
        CancelOrderService service = RestHelper.getBaseRetrofit(context).create(CancelOrderService.class);
        return service.getData(orderId);
    }
}
