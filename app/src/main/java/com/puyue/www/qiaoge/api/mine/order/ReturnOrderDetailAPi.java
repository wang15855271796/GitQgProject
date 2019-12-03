package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ReturnGoodsModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/15
 */
public class ReturnOrderDetailAPi {
    public interface ReturnOrderService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.RETURNORDERTYPE)
        Observable<ReturnOrderDetailModel> setParams(@Field("orderId") String orderId
        );
    }

    public static Observable<ReturnOrderDetailModel> requestReturnOrder(Context context, String orderId) {
        Observable<ReturnOrderDetailModel> returnGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(ReturnOrderService.class).setParams(orderId);
        return returnGoodsModelObservable;
    }
}
