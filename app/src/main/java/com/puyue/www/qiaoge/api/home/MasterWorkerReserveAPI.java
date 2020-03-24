package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.MasterWorkerReserveModel;

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

public class MasterWorkerReserveAPI {
    private interface MasterWorkerReserveService{
        @FormUrlEncoded
        @POST(AppInterfaceAddress.MASTERWORKERRESERVE)
        Observable<MasterWorkerReserveModel> getData(@Field("masterWorkerId") int masterWorkerId, @Field("startDate") String startDate, @Field("endDate") String endDate);
    }
    public static Observable<MasterWorkerReserveModel> requestData(Context context,int masterWorkerId,String startDate,String endDate){
        MasterWorkerReserveService service = RestHelper.getBaseRetrofit(context).create(MasterWorkerReserveService.class);
        return service.getData(masterWorkerId, startDate, endDate);
    }
}
