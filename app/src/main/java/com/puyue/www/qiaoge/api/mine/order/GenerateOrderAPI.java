package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.GenerateOrderModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${daff}
 * on 2018/9/25
 * 备注
 */
public class GenerateOrderAPI {
    public interface GenerateOrderService {
        /**
         * orderDeliveryType：送货方式 0：配送单，1：自提单，默认0
         * deliverTimeName：时间段名称（上午/下午）,
         * deliverTimeStart：起始时间,
         * deliverTimeEnd：截止时间,
         * pickUserName：提货人,
         * pickPhone：联系方式,
         * pickUpTime：提货时间（年月日类型的）
         */

        @FormUrlEncoded
        @POST(AppInterfaceAddress.CART_GENERATEORDER)
        Observable<GenerateOrderModel> setParams(@Field("activityBalanceVOStr") String activityBalanceVOStr,
                                                 @Field("normalProductBalanceVOStr") String normalProductBalanceVOStr,
                                                 @Field("cartListStr") String cartListStr,
                                                 @Field("giftDetailNo") String giftDetailNo,
                                                 @Field("memo") String memo,
                                                 @Field("deliverTimeStart") String deliverTimeStart,
                                                 @Field("deliverTimeEnd") String deliverTimeEnd,
                                                 @Field("deliverTimeName") String deliverTimeName,
                                                 @Field("orderDeliveryType") int orderDeliveryType,
                                                 @Field("pickUserName") String pickUserName,
                                                 @Field("pickPhone") String pickPhone,
                                                 @Field("pickUpTime") String pickUpTime
        );

    }

    public static Observable<GenerateOrderModel> requestGenerateOrder(Context context, String activityBalanceVOStr, String normalProductBalanceVOStr, String cartListStr, String giftDetailNo, String memo, String deliverTimeStart, String deliverTimeEnd, String deliverTimeName,int orderDeliveryType,String pickUserName,String pickPhone,String pickUpTime) {
        Observable<GenerateOrderModel> generateOrderModelbservable = RestHelper.getBaseRetrofit(context).create(GenerateOrderService.class).setParams(activityBalanceVOStr, normalProductBalanceVOStr,
                cartListStr, giftDetailNo, memo, deliverTimeStart, deliverTimeEnd, deliverTimeName,orderDeliveryType,pickUserName,pickPhone,pickUpTime);
        return generateOrderModelbservable;
    }


}
