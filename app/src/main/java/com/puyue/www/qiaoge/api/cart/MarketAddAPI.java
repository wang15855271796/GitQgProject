package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.CartAddReduceModel;
import com.puyue.www.qiaoge.model.cart.MarketAddCartModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/6/28
 */
public class MarketAddAPI {
    public interface MarketAddCartService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CARTADDPRODUCT)
        Observable<MarketAddCartModel> setParams(
                @Field("businessId") int businessId,
                @Field("direction") String direction,
                @Field("productCombinationPriceId") int productCombinationPriceId,
                @Field("businessType") int businessType);//int 1
    }

    public static Observable<MarketAddCartModel> requestCartPostChangeOrderDetail(Context context, int businessId, String direction, int productCombinationPriceId,int businessType) {
        Observable<MarketAddCartModel> stringObservable = RestHelper.getBaseRetrofit(context).create(MarketAddCartService.class).setParams( businessId, direction, productCombinationPriceId,businessType);
//        return changeOrderAddressModelObservable;
        return stringObservable;
    }
}
