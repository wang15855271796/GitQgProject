package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.OrderPayModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/26.
 */

public class OrderPayAPI {
    private interface OrderPayService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.ORDERPAY)
        Observable<OrderPayModel> getData(@Field("orderId") String orderId,
                                          @Field("payChannel") byte payChannel,
                                          @Field("payAmount") double payAmount,
                                          @Field("remark") String remark);
    }

    public static Observable<OrderPayModel> requestData(Context context, String orderId, byte payChannel, double payAmount, String remark) {
        OrderPayService service = RestHelper.getBaseRetrofit(context).create(OrderPayService.class);
        return service.getData(orderId, payChannel, payAmount, remark);
    }
}
