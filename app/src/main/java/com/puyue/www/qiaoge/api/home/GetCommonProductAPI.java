package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetCommonProductModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/20.
 */

public class GetCommonProductAPI {
    private interface GetCommonProductService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETCOMMONPRODUCT)
        Observable<GetCommonProductModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("firstId") String firstId,
                                                  @Field("classifyId") String classifyId, @Field("salesVolume") String salesVolume,
                                                  @Field("priceSorting") String priceSorting,
                                                  @Field("productName") String productName);
    }

    public static Observable<GetCommonProductModel> requestData(Context context, int pageNum, int pageSize, String firstId, String classifyId, String salesVolume, String priceSorting,String productName) {
        GetCommonProductService service = RestHelper.getBaseRetrofit(context).create(GetCommonProductService.class);
        return service.getData(pageNum, pageSize, firstId, classifyId, salesVolume, priceSorting,productName);
    }
}
