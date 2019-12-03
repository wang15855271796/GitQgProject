package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.CartListModel;
import com.puyue.www.qiaoge.model.cart.CartsListModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/4.
 */

public class CartListAPI {
    public interface CartListService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GET_CART_LIST)
        Observable<CartListModel> setParams(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize);
    }

    public static Observable<CartListModel> requestCartList(Context context, int pageNum, int pageSize) {
        Observable<CartListModel> cartListModelObservable = RestHelper.getBaseRetrofit(context).create(CartListService.class).setParams(pageNum, pageSize);
        return cartListModelObservable;
    }

//    public interface CartListService {
//        @POST(AppInterfaceAddress.GET_CART_LIST)
//        Observable<CartListModel> getData();
//    }
//
//    public static Observable<CartListModel> requestCartList(Context context) {
//        CartListService service = RestHelper.getBaseRetrofit(context).create(CartListService.class);
//        return service.getData();
//    }

    public interface CartListServices {
        @POST(AppInterfaceAddress.GET_CART_LISTS)
        Observable<CartsListModel> getData();
    }

    public static Observable<CartsListModel> requestCartLists(Context context) {
        CartListServices service = RestHelper.getBaseRetrofit(context).create(CartListServices.class);
        return service.getData();
    }
}
