package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.RechargeModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/27.
 */

public class RechargeAPI {
    private interface RechargeService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.RECHARGE)
        Observable<RechargeModel> getData(@Field("amount") double amount, @Field("rechargeType") byte rechargeType);
    }

    public static Observable<RechargeModel> requestData(Context context, double amount, byte rechargeType) {
        RechargeService service = RestHelper.getBaseRetrofit(context).create(RechargeService.class);
        return service.getData(amount, rechargeType);
    }
}
