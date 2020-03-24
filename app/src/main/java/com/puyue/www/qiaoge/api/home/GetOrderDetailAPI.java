package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.order.NewOrderDetailModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class GetOrderDetailAPI {
    private interface GetOrderDetailService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETORDERDETAIL)
        Observable<GetOrderDetailModel> getData(@Field("orderId") String orderId,
                                                @Field("orderStatus") String orderState,
                                                @Field("returnProductMainId") String returnProductMainId
        );
    }

    public static Observable<GetOrderDetailModel> requestData(Context context, String orderId, String orderState, String returnProductMainId) {
        GetOrderDetailService service = RestHelper.getBaseRetrofit(context).create(GetOrderDetailService.class);
        return service.getData(orderId, orderState, returnProductMainId);
    }




}
