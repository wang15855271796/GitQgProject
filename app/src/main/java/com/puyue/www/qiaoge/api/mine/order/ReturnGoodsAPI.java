package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ReturnGoodsModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/10.
 */

public class ReturnGoodsAPI {
    public interface ReturnGoodsService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.APPLAY_RETURN_GOODS)
        Observable<ReturnGoodsModel> setParams(@Field("orderId") String orderId,
                                               @Field("orderType") int orderType,
                                               @Field("reason") String reason,
                                               @Field("returnCombinationVOListStr") String returnCombinationVOListStr,
                                               @Field("returnProductMainId") String returnProductMainId);
    }

    public static Observable<ReturnGoodsModel> requestReturnGoods(Context context, String orderId, int orderType, String reason, String returnCombinationVOListStr, String returnProductMainId) {
        Observable<ReturnGoodsModel> returnGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(ReturnGoodsService.class).setParams(orderId, orderType, reason, returnCombinationVOListStr, returnProductMainId);
        return returnGoodsModelObservable;
    }
}
