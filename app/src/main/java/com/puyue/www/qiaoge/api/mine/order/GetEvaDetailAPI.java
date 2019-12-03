package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.GetEvaDetailModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/8/21
 */
public class GetEvaDetailAPI {

    public interface GetEvaluateDetailService {

        @FormUrlEncoded
        @POST(AppInterfaceAddress.EVALUATEDETAIL)
        Observable<GetEvaDetailModel> setparam(@Field("orderId") String orderId);


    }

    public static Observable<GetEvaDetailModel> requestEval(Context context, String orderId) {

        Observable<GetEvaDetailModel> setparam = RestHelper.getBaseRetrofit(context).create(GetEvaluateDetailService.class).setparam(orderId);
        return setparam;


    }

}
