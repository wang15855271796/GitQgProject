package com.puyue.www.qiaoge.api.mine.subaccount;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.SubAccountModel;
import com.puyue.www.qiaoge.model.mine.order.IntellGencyModel;
import com.puyue.www.qiaoge.model.mine.order.MineCenterModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/6/12
 */
public class MineAccountAPI {

    public interface MineAccountAddService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CLOSEINFO)
        Observable<MineCenterModel> setParams(@Field("day") int day,
                                              @Field("giftNo") String giftNo);
    }

    public static Observable<MineCenterModel> requestMineAccount(Context context, int day, String giftNo) {
        Observable<MineCenterModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(MineAccountAddService.class).setParams(day, giftNo);
        return addSubAccountObservable;
    }


    public interface IntellGencyService {
        @GET(AppInterfaceAddress.IntellGency)
        Observable<IntellGencyModel> setParams();
    }

    public static Observable<IntellGencyModel> getData(Context context) {
        Observable<IntellGencyModel> intellGencyModelObservable = RestHelper.getBaseRetrofit(context).create(IntellGencyService.class).setParams();
        return intellGencyModelObservable;
    }

}
