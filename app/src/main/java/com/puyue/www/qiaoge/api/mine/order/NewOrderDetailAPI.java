package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.GetOrderDetailModel;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${daff}
 * on 2018/10/20
 * 备注
 */
public class NewOrderDetailAPI {




    private interface NewOrderDetailService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETORDERDETAIL)
        Observable<GetOrderDetailModel> getData(@Field("orderId") String orderId,
                                                @Field("orderStatus") String orderState,
                                                @Field("returnProductMainId") String returnProductMainId);
    }

    public static Observable<GetOrderDetailModel> requestNewData(Context context, String orderId, String orderState, String returnProductMainId) {
        NewOrderDetailService service = RestHelper.getBaseRetrofit(context).create(NewOrderDetailService.class);
        return service.getData(orderId, orderState, returnProductMainId);
    }
}
