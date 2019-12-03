package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.TeamActiveQueryModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */

public class TeamActiveQueryAPI {
    private interface TeamActiveQueryService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.TEAMACTIVEQUERY)
        Observable<TeamActiveQueryModel> getData(@Field("clientType") String clientType, @Field("version") String version);
    }

    public static Observable<TeamActiveQueryModel> requestData(Context context,String version,String clientType) {
        TeamActiveQueryService service = RestHelper.getBaseRetrofit(context).create(TeamActiveQueryService.class);
        return service.getData(version,clientType);
    }
}
