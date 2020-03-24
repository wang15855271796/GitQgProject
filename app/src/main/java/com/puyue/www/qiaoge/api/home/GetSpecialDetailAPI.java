package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetProductDetailModel;
import com.puyue.www.qiaoge.model.home.SpecialGoodModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/4/11
 */
public class GetSpecialDetailAPI {
    private interface getSpecialDetail {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SPECIALOFFERDETAIL)
        Observable<SpecialGoodModel> getData(@Field("activeId") int activeId);
    }

    public static Observable<SpecialGoodModel> requestData(Context context, int activeId) {
        getSpecialDetail service = RestHelper.getBaseRetrofit(context).create(getSpecialDetail.class);
        return service.getData(activeId);
    }
}
