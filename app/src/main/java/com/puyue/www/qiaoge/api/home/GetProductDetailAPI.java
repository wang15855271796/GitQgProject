package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.ExchangeProductModel;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class GetProductDetailAPI {
    private interface GetProductDetailService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETPRODUCTDETAIL)
        Observable<GetProductDetailModel> getData(@Field("productMainId") int productId);
    }

    public static Observable<GetProductDetailModel> requestData(Context context, int productMainId) {
        GetProductDetailService service = RestHelper.getBaseRetrofit(context).create(GetProductDetailService.class);
        return service.getData(productMainId);
    }


    private interface GetExchageProductService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.EXCHANGEPRODUCT)
        Observable<ExchangeProductModel> getData(@Field("productId") int productId, @Field("businessType") int businessType);
    }

    public static Observable<ExchangeProductModel> getExchangeList(Context context, int productId,int businessType) {
        GetExchageProductService service = RestHelper.getBaseRetrofit(context).create(GetExchageProductService.class);
        return service.getData(productId,businessType);
    }



    private interface GetProductDetailServicse {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETPRODUCTDETAIL)
        Observable<GetProductDetailModel> getData(@Field("productMainId") int productMainId);
    }

    public static Observable<GetProductDetailModel> requestDatas(Context context, int productMainId) {
        GetProductDetailServicse service = RestHelper.getBaseRetrofit(context).create(GetProductDetailServicse.class);
        return service.getData(productMainId);
    }

}
