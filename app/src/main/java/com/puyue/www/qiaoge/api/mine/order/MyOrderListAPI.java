package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.MyOrdersModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/20.
 */

public class MyOrderListAPI {
    public interface MyOrderListService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GET_ORDER_LIST)
        Observable<MyOrdersModel> setParams(@Field("orderStatus") int orderStatus,
                                            @Field("pageNum") int pageNum,
                                            @Field("pageSize") int pageSize,
                                            @Field("orderDeliveryType") int orderDeliveryType);
    }

    public static Observable<MyOrdersModel> requestOrderList(Context context, int orderStatus, int pageNum, int pageSize,int orderDeliveryType) {
        Observable<MyOrdersModel> myOrdersModelObservable = RestHelper.getBaseRetrofit(context).create(MyOrderListService.class).setParams(orderStatus, pageNum, pageSize,orderDeliveryType);
        return myOrdersModelObservable;
    }
}
