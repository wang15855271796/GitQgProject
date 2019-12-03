package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.MyOrderNumModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/8.
 */

public class MyOrderNumAPI {
    public interface MyOrderNumService {
        @GET(AppInterfaceAddress.USER_MY_COUNTER)
        Observable<MyOrderNumModel> setParams();
    }

    public static Observable<MyOrderNumModel> requestOrderNum(Context context) {
        Observable<MyOrderNumModel> myOrderNumModelObservable = RestHelper.getBaseRetrofit(context).create(MyOrderNumService.class).setParams();
        return myOrderNumModelObservable;
    }
}
