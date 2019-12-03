package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.CheckPayPwdModel;

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

public class CheckPayPwdAPI {
    private interface CheckPayPwdService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CHECKPAYPWD)
        Observable<CheckPayPwdModel> getData(@Field("outTradeNo") String outTradeNo, @Field("password") String password);
    }

    public static Observable<CheckPayPwdModel> requestData(Context context, String outTradeNo, String passWord) {
        CheckPayPwdService service = RestHelper.getBaseRetrofit(context).create(CheckPayPwdService.class);
        return service.getData(outTradeNo, passWord);
    }
}
