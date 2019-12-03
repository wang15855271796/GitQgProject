package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.HomeNewRecommendModel;
import com.puyue.www.qiaoge.model.home.IndexHomeModel;
import com.puyue.www.qiaoge.model.mine.order.HomeBaseModel;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */

public class IndexHomeAPI {
    private interface IndexHomeService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.INDEXHOME)
        Observable<IndexHomeModel> getData(
                @Field("version") String version,
                @Field("clientType") String clientType);
    }

    public static Observable<IndexHomeModel> requestData(Context context, String version, String clientType) {
        IndexHomeService service = RestHelper.getBaseRetrofit(context).create(IndexHomeService.class);
        return service.getData(version, clientType);
    }


    private interface IndexHomeServices {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.INDEXHOME)
        Observable<HomeBaseModel> getData(
                @Field("version") String version,
                @Field("clientType") String clientType);
    }

    public static Observable<HomeBaseModel> getBaseList(Context context, String version, String clientType) {
        IndexHomeServices service = RestHelper.getBaseRetrofit(context).create(IndexHomeServices.class);
        return service.getData(version, clientType);
    }



    private interface IndexRecommendService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.HOMENEW)
        Observable<HomeNewRecommendModel> getData(
                @Field("pageNum") String pageNum,
                @Field("pageSize") String pageSize);
    }

    public static Observable<HomeNewRecommendModel> getRecommendData(Context context,  String pageNum, String pageSize) {
        IndexRecommendService service = RestHelper.getBaseRetrofit(context).create(IndexRecommendService.class);
        return service.getData(pageNum, pageSize);
    }
}
