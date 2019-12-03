package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetMasterWorkerByIdAndDateModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/21.
 */

public class GetMasterWorkerByIdAndDateAPI {
    private interface GetMasterWorkerByIdAndDateService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETMASTERWORKERBYIDANDDATE)
        Observable<GetMasterWorkerByIdAndDateModel> getData(@Field("masterWorkerId") int masterWorkerId, @Field("startDate") String startDate, @Field("endDate") String endDate);
    }

    public static Observable<GetMasterWorkerByIdAndDateModel> requestData(Context context, int masterWorkerId, String starDate, String endDate) {
        GetMasterWorkerByIdAndDateService service = RestHelper.getBaseRetrofit(context).create(GetMasterWorkerByIdAndDateService.class);
        return service.getData(masterWorkerId, starDate, endDate);
    }
}
