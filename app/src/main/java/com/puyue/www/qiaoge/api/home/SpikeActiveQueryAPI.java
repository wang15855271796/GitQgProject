package com.puyue.www.qiaoge.api.home;

import android.content.Context;

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
        Observable<SpikeActiveQueryModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize,
                                                  @Field("firstKindId") String firstKindId, @Field("secondKindId") String secondKindId,
                                                  @Field("sales") byte sales, @Field("proName")String proName);
        //默认0不选，  1销售量 ，2价格升序,3价格降序
    }

    public static Observable<SpikeActiveQueryModel> requestData(Context context, int pageNum, int pageSize, String firstKindId, String secondKindId, byte sales,String proName) {
        SpikeActiveQueryService spikeActiveQueryService = RestHelper.getBaseRetrofit(context).create(SpikeActiveQueryService.class);
        return spikeActiveQueryService.getData(pageNum, pageSize, firstKindId, secondKindId, sales,proName);
    }
}
