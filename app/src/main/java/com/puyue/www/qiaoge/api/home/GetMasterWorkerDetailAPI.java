package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetMasterWorkerDetailModel;

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

public class GetMasterWorkerDetailAPI {
    private interface GetMasterWorkerDetailService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETMASTERWORKERDETAIL)
        Observable<GetMasterWorkerDetailModel> getData(@Field("masterWorkerId") int masterWorkerId);
    }

    public static Observable<GetMasterWorkerDetailModel> requestData(Context context, int masterWorkerId) {
        GetMasterWorkerDetailService service = RestHelper.getBaseRetrofit(context).create(GetMasterWorkerDetailService.class);
        return service.getData(masterWorkerId);
    }

}
