package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.GetOrderDriverModel;
import com.puyue.www.qiaoge.model.mine.order.GetTimeOrderModel;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/7/22
 */
public class GetOrderDeliverTimeAPI {

    public interface QuerySelfTimeService {
        @GET(AppInterfaceAddress.QUERYSELFORDERTIME)
        Observable<GetTimeOrderModel> setParams();
    }

    public static Observable<GetTimeOrderModel> requestOrderSelfTime(Context context) {
        Observable<GetTimeOrderModel> myOrderNumModelObservable = RestHelper.getBaseRetrofit(context).create(QuerySelfTimeService.class).setParams();
        return myOrderNumModelObservable;
    }

}
