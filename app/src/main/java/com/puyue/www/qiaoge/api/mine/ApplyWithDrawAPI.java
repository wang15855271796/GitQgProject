package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ApplyWithDrawModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/28.
 */

public class ApplyWithDrawAPI {
    private interface ApplyWithDrawService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.APPLYWITHDRAW)
        Observable<ApplyWithDrawModel> getData(@Field("amount") double amount, @Field("withdrawType") byte withdrawType,
                                               @Field("name") String name, @Field("contactPhone") String contactPhone,
                                               @Field("openBankName") String openBankName, @Field("account") String account,
                                               @Field("actualAmount") double actualAmount);
    }

    public static Observable<ApplyWithDrawModel> requestData(Context context, double amount, byte withdrawType, String name,
                                                             String contactPhone, String openBankName, String account, double actualAmount) {
        ApplyWithDrawService service = RestHelper.getBaseRetrofit(context).create(ApplyWithDrawService.class);
        return service.getData(amount, withdrawType, name, contactPhone, openBankName, account, actualAmount);
    }
}
