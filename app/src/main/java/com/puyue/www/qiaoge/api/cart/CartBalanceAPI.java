package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.CartBalanceModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/5.
 */

public class CartBalanceAPI {
    public interface CartBalanceService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CART_BALANCE)
        Observable<CartBalanceModel> setParams(@Field("normalProductBalanceVOStr") String normalProductBalanceVOStr,
                                               @Field("activityBalanceVOStr") String activityBalanceVOStr,
                                               @Field("cartListStr") String cartListStr,
                                               @Field("giftDetailNo") String giftDetailNo,
                                               @Field("type") int type,
                                               @Field("orderDeliveryType") int orderDeliveryType);




        ;
    }

    public static Observable<CartBalanceModel> requestCartBalance(Context context, String normalProductBalanceVOStr, String activityBalanceVOStr, String cartListStr,String giftDetailNo,int type,int orderDeliveryType) {
        Observable<CartBalanceModel> cartBalanceModelObservable = RestHelper.getBaseRetrofit(context).create(CartBalanceService.class).setParams(normalProductBalanceVOStr, activityBalanceVOStr, cartListStr, giftDetailNo,type,orderDeliveryType);
        return cartBalanceModelObservable;
    }
}
