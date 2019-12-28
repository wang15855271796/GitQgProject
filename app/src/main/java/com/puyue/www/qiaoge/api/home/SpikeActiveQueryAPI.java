package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.SpikeActiveQueryModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */

public class SpikeActiveQueryAPI {
    private interface SpikeActiveQueryService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SPIKEACTIVEQUERY)
        Observable<BaseModel> getData(@Field("activeId") int pageNum);
    }

    public static Observable<BaseModel> requestData(Context context, int activeId) {
        SpikeActiveQueryService spikeActiveQueryService = RestHelper.getBaseRetrofit(context).create(SpikeActiveQueryService.class);
        return spikeActiveQueryService.getData(activeId);
    }
}
