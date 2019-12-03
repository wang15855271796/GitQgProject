package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.CartAddReduceModel;
import com.puyue.www.qiaoge.model.cart.CartPointModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/4/23
 */
public class CartPointAPI {
    public interface CartPointService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.POINT)
        Observable<CartPointModel> setParams(@Field("amount") double amount,
                                             @Field("token") String token

        );//int 1
    }

    public static Observable<CartPointModel> requestCartPoint(Context context, double amount, String token) {
        Observable<CartPointModel> stringObservable = RestHelper.getBaseRetrofit(context).create(CartPointService.class).setParams(amount, token);

        return stringObservable;
    }
}
