package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.ClickCollectionModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/24.
 */

public class ClickCollectionAPI {
    private interface ClickCollectionService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CLICKCOLLECTION)
        Observable<ClickCollectionModel> getData(@Field("businessId") int businessId, @Field("businessType") byte businessType, @Field("type") byte type);
    }

    public static Observable<ClickCollectionModel> requestData(Context context, int businessId, byte businessType, byte type) {
        ClickCollectionService service = RestHelper.getBaseRetrofit(context).create(ClickCollectionService.class);
        return service.getData(businessId, businessType, type);
    }
}
