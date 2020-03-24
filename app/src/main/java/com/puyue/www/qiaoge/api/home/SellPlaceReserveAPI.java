package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.SellPlaceReserveModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/25.
 */

public class SellPlaceReserveAPI {
    private interface SellPlaceReserveService{
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SELLPLACERESERVE)
        Observable<SellPlaceReserveModel> getData(@Field("sellPlaceId") int sellPlaceId, @Field("startDate") String startDate, @Field("endDate") String endDate);
    }
    public static Observable<SellPlaceReserveModel> requestData(Context context,int sellPlaceId,String startDate,String endDate){
        SellPlaceReserveService service = RestHelper.getBaseRetrofit(context).create(SellPlaceReserveService.class);
        return service.getData(sellPlaceId,startDate,endDate);
    }
}
