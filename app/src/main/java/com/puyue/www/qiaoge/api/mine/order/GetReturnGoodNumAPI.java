package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;
import com.puyue.www.qiaoge.model.mine.order.ReturnSpecModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/23
 */
public class GetReturnGoodNumAPI {
    public interface GetReturnSpecService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.ONLINEPRODUCTAMOUTN)
        Observable<ReturnSpecModel> setParams(@Field("orderId") String orderId,
                                              @Field("businessId") int businessId,
                                              @Field("businessType") int businessType,
                                              @Field("returnNum") int returnNum,
                                              @Field("returnUnit") int returnUnit,
                                              @Field("priceId") int priceId,
                                              @Field("additionFlag") int additionFlag
        );
    }

    public static Observable<ReturnSpecModel> requestSpec(Context context, String orderId, int businessId, int businessType, int returnNum, int returnUnit, int priceId,int additionFlag) {
        Observable<ReturnSpecModel> myOrdersModelObservable = RestHelper.getBaseRetrofit(context).create(GetReturnSpecService.class).setParams(orderId, businessId, businessType, returnNum, returnUnit, priceId,additionFlag);
        return myOrdersModelObservable;
    }
}
