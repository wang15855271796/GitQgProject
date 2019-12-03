package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.SpikeActiveQueryModel;
import com.puyue.www.qiaoge.model.home.SpikeNewQueryModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/4/12
 */
public class SpikeNewActiveQueryAPI {
    private interface SpikeNewActiveQueryService {

        @POST(AppInterfaceAddress.SECKILLMORE)
        Observable<SpikeNewQueryModel> getData();

    }

    public static Observable<SpikeNewQueryModel> requestData(Context context) {
        SpikeNewActiveQueryService spikeActiveQueryService = RestHelper.getBaseRetrofit(context).create(SpikeNewActiveQueryService.class);
        return spikeActiveQueryService.getData();
    }
}
