package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetPlaceCommentListByPageModel;

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

public class GetPlaceCommentListByPageAPI {
    private interface GetPlaceCommentListByPageService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETPLACECOMMENTLISTBYPAGE)
        Observable<GetPlaceCommentListByPageModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("sellPlaceId") int sellPlaceId);
    }

    public static Observable<GetPlaceCommentListByPageModel> requestData(Context context, int pageNum, int pageSize, int sellPlaceId) {
        GetPlaceCommentListByPageService service = RestHelper.getBaseRetrofit(context).create(GetPlaceCommentListByPageService.class);
        return service.getData(pageNum, pageSize, sellPlaceId);
    }
}
