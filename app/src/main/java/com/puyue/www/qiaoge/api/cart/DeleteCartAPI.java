package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/4.
 */

public class DeleteCartAPI {
    public interface DeleteCartService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.DELETE_CART)
        Observable<BaseModel> setParams(@Field("cartIds") String cartIds);
    }

    public static Observable<BaseModel> requestDeleteCart(Context context, String cartIds) {
        Observable<BaseModel> deleteCartObservable = RestHelper.getBaseRetrofit(context).create(DeleteCartService.class).setParams(cartIds);
        return deleteCartObservable;
    }
}
