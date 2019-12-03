package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.BuyProductModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class BuyProductAPI {
    private interface BuyProductService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.BUYPRODUCT)
        Observable<BuyProductModel> getData(@Field("productIdList") String productIdList, @Field("detailList") String detailList, @Field("totalAmount") String totalAmount, @Field("productType") byte productType, @Field("equipmentId") String equipmentId, @Field("num") String num);
    }

    public static Observable<BuyProductModel> requestData(Context context, String productIdList, String detailList, String totalAmount, byte productType, String equipmentId, String num) {
        BuyProductService service = RestHelper.getBaseRetrofit(context).create(BuyProductService.class);
        return service.getData(productIdList, detailList, totalAmount, productType, equipmentId, num);
    }
}
