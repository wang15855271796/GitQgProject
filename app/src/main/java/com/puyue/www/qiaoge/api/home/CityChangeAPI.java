package com.puyue.www.qiaoge.api.home;

import android.content.Context;


import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.IsShowModel;
import com.puyue.www.qiaoge.model.home.CityChangeModel;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/8/8
 */
public class CityChangeAPI {
    public interface CityChangeService{
        @POST(AppInterfaceAddress.MODIFYCITY)
        Observable<CityChangeModel> setParam();
    }

    public static Observable<CityChangeModel>  requestCity(Context context){
        Observable<CityChangeModel> cityChangeModelObservable = RestHelper.getBaseRetrofit(context).create(CityChangeService.class).setParam();
        return cityChangeModelObservable;

    }

    public interface IsShowCityService{
        @POST(AppInterfaceAddress.Is_Show)
        Observable<IsShowModel> setParam();
    }

    public static Observable<IsShowModel>  isShow(Context context){
        Observable<IsShowModel> cityChangeModelObservable = RestHelper.getBaseRetrofit(context).create(IsShowCityService.class).setParam();
        return cityChangeModelObservable;

    }



}
