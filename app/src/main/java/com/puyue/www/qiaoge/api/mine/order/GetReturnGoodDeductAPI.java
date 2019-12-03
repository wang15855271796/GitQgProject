package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ReturnDetuctAmountModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnSpecModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/23
 */
public class GetReturnGoodDeductAPI {
    public interface GetReturnDetuctService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.ORDERSUMDEDUCTAMOUNT)
        Observable<ReturnDetuctAmountModel> setParams(@Field("orderId") String orderId,
                                                      @Field("businessId") int businessId,
                                                      @Field("businessType") int businessType,
                                                      @Field("additionFlag") int additionFlag,
                                                      @Field("returnUnit") int returnUnit,
                                                      @Field("priceId") int priceId
        );
    }

    public static Observable<ReturnDetuctAmountModel> requestDetuctSpec(Context context, String orderId, int businessId, int businessType,int additionFlag ,int returnUnit ,int  priceId) {
        Observable<ReturnDetuctAmountModel> myOrdersModelObservable = RestHelper.getBaseRetrofit(context).create(GetReturnDetuctService.class).setParams(orderId, businessId, businessType,additionFlag,returnUnit,priceId);
        return myOrdersModelObservable;
    }
}
