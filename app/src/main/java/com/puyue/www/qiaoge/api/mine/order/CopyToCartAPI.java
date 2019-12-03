package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.CopyToCartModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/15.
 */

public class CopyToCartAPI {
    public interface CopyToCartService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.COPY_TO_CART)
        Observable<CopyToCartModel> setParams(@Field("orderId") String orderId);
    }

    public static Observable<CopyToCartModel> requestCopyToCart(Context context, String orderId) {
        Observable<CopyToCartModel> copyToCartModelObservable = RestHelper.getBaseRetrofit(context).create(CopyToCartService.class).setParams(orderId);
        return copyToCartModelObservable;
    }
}
