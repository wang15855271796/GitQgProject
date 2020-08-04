package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.GetWallertRecordByPageModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/28.
 */

public class GetWallertRecordByPageAPI {
    private interface GetWallertRecordByPageService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETWALLERTRECORDBYPAGE)
        Observable<GetWallertRecordByPageModel> getData(@Field("types") String types,
                                                        @Field("year") String year,
                                                        @Field("month") String month,
                                                        @Field("phone") String phone,
                                                        @Field("showType") int showType,
                                                        @Field("walletRecordChannelType") String walletRecordChannelType);

    }

    public static Observable<GetWallertRecordByPageModel> requestData(Context context, String types, String year, String month, String phone, int showType, String walletRecordChannelType) {
        GetWallertRecordByPageService service = RestHelper.getBaseRetrofit(context).create(GetWallertRecordByPageService.class);
        return service.getData(types, year, month, phone, showType, walletRecordChannelType);
    }
}
