package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.CopyToCartModel;
import com.puyue.www.qiaoge.model.mine.order.GetOrderDriverModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/28
 */
public class GetOrderMapMessageAPI {


    public interface GetOrderMapService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETORDERMAP)
        Observable<GetOrderDriverModel> setParams(@Field("orderId") String orderId);
    }

    public static Observable<GetOrderDriverModel> requestDriverMe(Context context, String orderId) {
        Observable<GetOrderDriverModel> copyToCartModelObservable = RestHelper.getBaseRetrofit(context).create(GetOrderMapService.class).setParams(orderId);
        return copyToCartModelObservable;
    }



}
