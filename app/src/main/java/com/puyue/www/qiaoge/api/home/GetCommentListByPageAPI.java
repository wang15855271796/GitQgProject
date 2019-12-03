package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetCommentListByPageModel;

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

public class GetCommentListByPageAPI {
    private interface GetCommentListByPageService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETCOMMENTLISTBYPAGE)
        Observable<GetCommentListByPageModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("masterWorkerId") int masterWorkerId);
    }

    public static Observable<GetCommentListByPageModel> requestData(Context context, int pageNum, int pageSize, int masterWorkerId) {
        GetCommentListByPageService service = RestHelper.getBaseRetrofit(context).create(GetCommentListByPageService.class);
        return service.getData(pageNum, pageSize, masterWorkerId);
    }
}
