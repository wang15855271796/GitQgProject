package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetDeliverTimeModel;



import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/7/29
 * orderId, deliverTimeName, deliverTimeStart, deliverTimeEnd
 */
public class NotifyDeliverTimeOrderAPI {
    public   interface NotifyTimeService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.UPDATEDELIVERTIME)
        Observable<BaseModel> setparams(@Field("orderId") String orderId,
                                        @Field("deliverTimeName") String deliverTimeName,
                                        @Field("deliverTimeStart") String deliverTimeStart,
                                        @Field("deliverTimeEnd") String deliverTimeEnd);

    }

    public static Observable<BaseModel> notifyDeliverTime(Context context, String orderId, String deliverTimeName, String deliverTimeStart, String deliverTimeEnd) {


        Observable<BaseModel> setparams = RestHelper.getBaseRetrofit(context).create(NotifyTimeService.class).setparams(orderId, deliverTimeName, deliverTimeStart, deliverTimeEnd);

        return setparams;
    }


}
