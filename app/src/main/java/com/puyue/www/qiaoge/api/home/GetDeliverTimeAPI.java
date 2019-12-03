package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetDeliverTimeModel;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/7
 */
public class GetDeliverTimeAPI {
    public interface GetDeliverTimeService {

        @GET(AppInterfaceAddress.GETDELIVERTIME)
        Observable<GetDeliverTimeModel> setParams(@Query("areaCode") String areaCode);
    }

    public static Observable<GetDeliverTimeModel> requestDeliverTime(Context context , String areaCode) {


        Observable<GetDeliverTimeModel> getDeliverTime = RestHelper.getBaseRetrofit(context).create(GetDeliverTimeService.class).setParams(areaCode);
        return getDeliverTime;
    }
}
