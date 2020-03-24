package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderSucModel;

import org.json.JSONArray;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 描述：
 */
public class ReturnOrderApi {
    public interface returnOrderService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.RETURNORDER)
        Observable<ReturnOrderSucModel> setParams(@Field("orderId") String orderId,
                                                  @Field("returnReasonType") String returnReasonType,
                                                  @Field("returnReason") String returnReason,
                                                  @Field("returnAmount") String returnAmount,
                                                  @Field("returnPic") String returnPic,
                                                  @Field("returnProducts") JSONArray returnProducts,
                                                  @Field("returnChannel") String returnChannel);
    }

    public static Observable<ReturnOrderSucModel> requestReturnOrder(Context context, String orderId, String returnReasonType, String returnReason, String returnAmount, String returnPic, JSONArray returnProducts,String returnChannel) {
        Observable<ReturnOrderSucModel> loginModelObservable = RestHelper.getBaseRetrofit(context).create(returnOrderService.class).setParams(orderId, returnReasonType, returnReason, returnAmount,returnPic,returnProducts,returnChannel);
        return loginModelObservable;
    }
}
