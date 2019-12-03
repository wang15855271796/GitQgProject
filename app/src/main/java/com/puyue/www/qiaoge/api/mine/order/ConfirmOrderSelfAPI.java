package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.GetOrderDriverModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/7/25
 */
public class ConfirmOrderSelfAPI {

    public interface ConfirmSelfOrderService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SELFORDERRECEIVE)
        Observable<BaseModel> setParams(@Field("orderId") String orderId);
    }

    public static Observable<BaseModel> requestDriverMe(Context context, String orderId) {
        Observable<BaseModel> copyToCartModelObservable = RestHelper.getBaseRetrofit(context).create(ConfirmSelfOrderService.class).setParams(orderId);
        return copyToCartModelObservable;
    }
}
