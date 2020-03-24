package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/7/25
 */
public class PostModifyMesAPI {
    public interface ModifySelfMegService{
        @FormUrlEncoded
        @POST(AppInterfaceAddress.MODIFYORDERINFO)
        Observable<BaseModel> setParams(@Field("orderId") String orderId,
                                        @Field("pickUpName") String pickUpName,
                                        @Field("pickUpPhone") String pickUpPhone,
                                        @Field("startTime") String startTime,
                                        @Field("deliverTimeStart") String deliverTimeStart,
                                        @Field("deliverTimeEnd") String deliverTimeEnd,
                                        @Field("deliverTimeName") String deliverTimeName);
    }

    public static Observable<BaseModel> requestModifyMsg(Context context, String orderId, String pickUpName, String pickUpPhone, String startTime, String deliverTimeStart, String deliverTimeEnd, String deliverTimeName) {
        Observable<BaseModel> copyToCartModelObservable = RestHelper.getBaseRetrofit(context).create(ModifySelfMegService.class).setParams(orderId,pickUpName,pickUpPhone,startTime,deliverTimeStart,deliverTimeEnd,deliverTimeName);
        return copyToCartModelObservable;
}}
