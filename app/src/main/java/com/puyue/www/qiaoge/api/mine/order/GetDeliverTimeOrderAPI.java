package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetDeliverTimeModel;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/7/29
 */
public class GetDeliverTimeOrderAPI {
    public interface GetTimeService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETDELIVERORDERTIME)
        Observable<GetDeliverTimeModel> setParams(@Field("orderId") String orderId);
    }


    public static Observable<GetDeliverTimeModel> requsetOrderTime(Context context, String orderId) {

        Observable<GetDeliverTimeModel> getDeliverTimeModelObservable = RestHelper.getBaseRetrofit(context).create(GetTimeService.class).setParams(orderId);
        return getDeliverTimeModelObservable;


    }

}
