package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.GetPayResultModel;

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

public class GetPayResultAPI {
    private interface GetPayResultService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETPAYRESULT)
        Observable<GetPayResultModel> getData(@Field("outTradeNo") String outTradeNo);
    }

    public static Observable<GetPayResultModel> requestData(Context context, String outTradeNo) {
        GetPayResultService service = RestHelper.getBaseRetrofit(context).create(GetPayResultService.class);
        return service.getData(outTradeNo);
    }

}
