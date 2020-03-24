package com.puyue.www.qiaoge.api.mine.coupon;

import android.content.Context;

import com.puyue.www.qiaoge.api.home.NoticeAPI;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.NoticeListModel;
import com.puyue.www.qiaoge.model.mine.coupons.queryUserDeductByStateModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${daff} on 2018/9/20
 *
 * 我的优惠卷
 */
public class MyCouponsAPI {


    public interface  MyCoupons {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.QUERY_USER_DEDUCTBYSTATE)
        Observable<queryUserDeductByStateModel> setParams(@Field("pageNum") int pageNum, @Query("pageSize") int pageSize, @Query("state") String state);
    }

    public static Observable<queryUserDeductByStateModel> requestCoupons (Context context, int pageNum, int pageSize,String state) {
        Observable<queryUserDeductByStateModel> queryUserDeductByStateModelObservable = RestHelper.getBaseRetrofit(context).create(MyCoupons.class).setParams(pageNum, pageSize,state);
        return queryUserDeductByStateModelObservable;
    }

}
