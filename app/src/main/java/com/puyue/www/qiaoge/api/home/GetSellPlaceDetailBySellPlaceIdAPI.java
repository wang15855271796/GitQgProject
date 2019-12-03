package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetSellPlaceDetailBySellPlaceIdModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class GetSellPlaceDetailBySellPlaceIdAPI {
    private interface GetSellPlaceDetailBySellPlaceIdService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETSELLPLACEDETAILBYSELLPLACEID)
        Observable<GetSellPlaceDetailBySellPlaceIdModel> getData(@Field("sellPlaceId") int sellPlaceId);
    }

    public static Observable<GetSellPlaceDetailBySellPlaceIdModel> requestData(Context context, int sellPlaceId) {
        GetSellPlaceDetailBySellPlaceIdService service = RestHelper.getBaseRetrofit(context).create(GetSellPlaceDetailBySellPlaceIdService.class);
        return service.getData(sellPlaceId);
    }
}
