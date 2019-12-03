package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetSellPlaceListByIdAndDateModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/18.
 */

public class GetSellPlaceListByIdAndDateAPI {
    private interface GetSellPlaceListByIdAndDateService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETSELLPLACELISTBYIDANDDATE)
        Observable<GetSellPlaceListByIdAndDateModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("spotId") int spotId, @Field("startDate") String startDate, @Field("endDate") String endDate);
    }

    public static Observable<GetSellPlaceListByIdAndDateModel> requestData(Context context, int pageNum, int pageSize, int spotId, String startDate, String endDate) {
        GetSellPlaceListByIdAndDateService service = RestHelper.getBaseRetrofit(context).create(GetSellPlaceListByIdAndDateService.class);
        return service.getData(pageNum, pageSize, spotId, startDate, endDate);
    }
}
