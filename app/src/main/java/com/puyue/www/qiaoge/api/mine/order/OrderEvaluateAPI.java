package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.OrderEvaluateModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/10.
 */

public class OrderEvaluateAPI {
    public interface OrderEvaluateService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.COMMENT_ORDER)
        Observable<OrderEvaluateModel> setParams(@Field("orderId") String orderId,
                                                 @Field("businessIds") String businessIds,
                                                 @Field("contents") String contents,
                                                 @Field("businessTypes") String businessTypes,
                                                 @Field("levels") String levels,
                                                 @Field("pictures") String pictures);
    }

    public static Observable<OrderEvaluateModel> requestOrderComment(Context context, String orderId, String businessIds, String contents, String businessTypes,String levels,String pictures) {
        Observable<OrderEvaluateModel> orderEvaluateModelObservable = RestHelper.getBaseRetrofit(context).create(OrderEvaluateService.class).setParams(orderId, businessIds, contents, businessTypes,levels,pictures);
        return orderEvaluateModelObservable;
    }
}
