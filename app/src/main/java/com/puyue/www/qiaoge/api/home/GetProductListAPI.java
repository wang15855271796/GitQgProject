package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetProductListModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/18.
 */

public class GetProductListAPI {


    private interface GetProductListService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETPRODUCTLIST)
        Observable<GetProductListModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("productType") String productType,
                                                @Field("productName") String productName, @Field("firstId") String firstId, @Field("classifyId") String classifyId,
                                                @Field("salesVolume") String salesVolume, @Field("priceSorting") String priceSorting);
    }

    public static Observable<GetProductListModel> requestData(Context context, int pageNum, int pageSize, String productType, String productName, String firstId, String classifyID, String salesVolume, String priceSorting) {
        GetProductListService service = RestHelper.getBaseRetrofit(context).create(GetProductListService.class);
        return service.getData(pageNum, pageSize, productType, productName, firstId, classifyID, salesVolume, priceSorting);
    }


}
