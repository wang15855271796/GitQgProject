package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.CartAddReduceModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public class CartPostChangeOrderDetailAPI {
    public interface CartPostChangeOrderDetailService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CARTPOSTCHANGEORDERDETAIL)
        Observable<CartAddReduceModel> setParams(@Field("businessType") int businessType,
                                                 @Field("businessId") int businessId,
                                                 @Field("direction") String direction,
                                                 @Field("productCombinationPriceId") int productCombinationPriceId);//int 1
    }

    public static Observable<CartAddReduceModel> requestCartPostChangeOrderDetail(Context context, int businessType,int businessId,String direction,int productCombinationPriceId) {
        Observable<CartAddReduceModel> stringObservable = RestHelper.getBaseRetrofit(context).create(CartPostChangeOrderDetailService.class).setParams(businessType, businessId, direction, productCombinationPriceId);
//        return changeOrderAddressModelObservable;
        return stringObservable;
    }
}
