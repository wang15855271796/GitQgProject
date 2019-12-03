package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.wallet.OrderReturnSelectReasonModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/15
 */
public class ReturnSelectReasonAPI {
    public interface ReturnOrderReasonService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.QUERYORDERRETURNTYPE)
        Observable<OrderReturnSelectReasonModel> setParams(@Field("orderStatus") String orderStatus
        );
    }

    public static Observable<OrderReturnSelectReasonModel> requestReturnOrderReason(Context context, String orderStatus) {
        Observable<OrderReturnSelectReasonModel> returnGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(ReturnOrderReasonService.class).setParams(orderStatus);
        return returnGoodsModelObservable;
    }
}
