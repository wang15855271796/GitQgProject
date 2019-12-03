package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.OrderEvaluateModel;
import com.puyue.www.qiaoge.model.mine.order.VipPayResultModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${daff}
 * on 2018/10/25
 * 备注
 */
public class VipPayResultAPI  {

    public interface VipPayResultService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.VIPPAYRESULT)
        Observable<VipPayResultModel> setParams(@Field("outTradeNo") String outTradeNo);
    }

    public static Observable<VipPayResultModel> requestOrderComment(Context context, String outTradeNo) {
        Observable<VipPayResultModel> orderEvaluateModelObservable = RestHelper.getBaseRetrofit(context).create(VipPayResultService.class).setParams(outTradeNo);
        return orderEvaluateModelObservable;
    }
}
