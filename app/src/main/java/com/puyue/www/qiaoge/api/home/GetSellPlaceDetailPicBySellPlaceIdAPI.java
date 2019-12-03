package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetSellPlaceDetailPicBySellPlaceIdModel;

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

public class GetSellPlaceDetailPicBySellPlaceIdAPI {
    private interface GetSellPlaceDetailPicBySellPlaceIdService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETSELLPLACEDETAILPICBYSELLPLACEID)
        Observable<GetSellPlaceDetailPicBySellPlaceIdModel> getData(@Field("sellPlaceId") int sellPlaceId);
    }

    public static Observable<GetSellPlaceDetailPicBySellPlaceIdModel> requestData(Context context, int sellPlaceId) {
        GetSellPlaceDetailPicBySellPlaceIdService service = RestHelper.getBaseRetrofit(context).create(GetSellPlaceDetailPicBySellPlaceIdService.class);
        return service.getData(sellPlaceId);
    }
}
