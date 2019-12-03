package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;
import com.puyue.www.qiaoge.model.home.SpecialMoreGoodModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/4/11
 */
public class GetMoreSpecialAPI {
    public interface GetMoreSpecial {

        @GET(AppInterfaceAddress.SPECIALGOOD)
        Observable<SpecialMoreGoodModel> setParams();
    }

    public static Observable<SpecialMoreGoodModel> RequestMoreSpecial(Context context) {
        Observable<SpecialMoreGoodModel> noticeListModelObservable = RestHelper.getBaseRetrofit(context).create(GetMoreSpecial.class).setParams();
        return noticeListModelObservable;
    }
}
