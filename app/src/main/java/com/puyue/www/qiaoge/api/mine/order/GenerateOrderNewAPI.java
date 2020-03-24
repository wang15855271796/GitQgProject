package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.GenerateOrderModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${daff}
 * on 2018/11/14
 * 备注 测试用的
 */
public class GenerateOrderNewAPI {
    public interface GenerateOrderStringService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CART_GENERATEORDER)
        Observable<String> setParams(@Field("activityBalanceVOStr") String activityBalanceVOStr,
                                     @Field("normalProductBalanceVOStr") String normalProductBalanceVOStr,
                                     @Field("equipmentBalanceVOStr") String equipmentBalanceVOStr,
                                     @Field("cartListStr") String cartListStr,
                                     @Field("giftDetailNo") String giftDetailNo,
                                     @Field("memo") String memo);

    }

    public static Observable<String> requestGenerateOrder(Context context, String activityBalanceVOStr, String normalProductBalanceVOStr,
                                                                      String equipmentBalanceVOStr, String cartListStr, String giftDetailNo,String memo) {
//        Observable<GenerateOrderModel> generateOrderModelbservable = RestHelper.getBaseRetrofit(context).create(GenerateOrderService.class).setParams(activityBalanceVOStr, normalProductBalanceVOStr, equipmentBalanceVOStr,
//                cartListStr, giftDetailNo,memo);

        Observable<String> stringObservable = RestHelper.getStringBaseRetrofit(context).create(GenerateOrderStringService.class).setParams(activityBalanceVOStr, normalProductBalanceVOStr, equipmentBalanceVOStr, cartListStr, giftDetailNo, memo);

        return stringObservable;
    }


}
