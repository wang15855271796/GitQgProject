package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.SpikeActiveQueryByIdModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/19.
 */

public class SpikeActiveQueryByIdAPI {
    private interface SpikeActiveQueryByIdService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SPIKEACTIVEQUERYBYID)
        Observable<SpikeActiveQueryByIdModel> getData(@Field("activeId") int activeId);
    }

    public static Observable<SpikeActiveQueryByIdModel> requestData(Context context, int activeId) {
        SpikeActiveQueryByIdService service = RestHelper.getBaseRetrofit(context).create(SpikeActiveQueryByIdService.class);
        return service.getData(activeId);
    }
}
