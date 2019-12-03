package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetSumPriceModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/8/17
 */
public class GetSumPriceAPI {

    private interface GetPriceService {

        @FormUrlEncoded
        @POST(AppInterfaceAddress.WALLETSUMPRICE)
        Observable<GetSumPriceModel> setparams(@Field("types") String types,
                                               @Field("year") String year,
                                               @Field("month") String month,
                                               @Field("phone") String phone,
                                               @Field("showType") int showType,
                                               @Field("walletRecordChannelType") String walletRecordChannelType);


    }

    public static Observable<GetSumPriceModel> requestSumPrice(Context context, String types, String year, String month, String phone, int showType, String walletRecordChannelType) {

        Observable<GetSumPriceModel> setparams = RestHelper.getBaseRetrofit(context).create(GetPriceService.class).setparams(types, year, month, phone, showType, walletRecordChannelType);
        return setparams;


    }

}
