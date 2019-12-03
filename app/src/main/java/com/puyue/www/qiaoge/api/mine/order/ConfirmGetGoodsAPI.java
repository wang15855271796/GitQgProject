package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ConfirmGetGoodsModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/10.
 */
public class ConfirmGetGoodsAPI {
    public interface ConfirmGetGoodsService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CONFIRM_GET_GOODS)
        Observable<ConfirmGetGoodsModel> setParams(@Field("orderId") String orderId);
    }

    public static Observable<ConfirmGetGoodsModel> reuqestConfirmGetGoods(Context context, String orderId) {
        Observable<ConfirmGetGoodsModel> confirmGetGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(ConfirmGetGoodsService.class).setParams(orderId);
        return confirmGetGoodsModelObservable;
    }
}
