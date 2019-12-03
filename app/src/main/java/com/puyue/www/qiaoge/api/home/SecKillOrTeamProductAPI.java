package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.SecKillOrTeamProductModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/25.
 */

public class SecKillOrTeamProductAPI {
    private interface SecKillOrTeamProductService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SECKILLORTEAMPRODUCT)
        Observable<SecKillOrTeamProductModel> getData(@Field("activityId") int activityId, @Field("totalNum") int totalNum, @Field("totalAmount") double totalAmount, @Field("orderType") byte orderType);
    }

    public static Observable<SecKillOrTeamProductModel> requestData(Context context, int activityId, int totalNum, double totalAmount, byte orderType) {
        SecKillOrTeamProductService service = RestHelper.getBaseRetrofit(context).create(SecKillOrTeamProductService.class);
        return service.getData(activityId, totalNum, totalAmount, orderType);
    }
}
