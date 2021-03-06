package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetAllCommentListByPageModel;

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

public class GetAllCommentListByPageAPI {
    private interface GetAllCommentListByPageService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETALLCOMMENTLISTBYPAGE)
        Observable<GetAllCommentListByPageModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("businessId") int businessId, @Field("businessType") byte businessType);
    }

    public static Observable<GetAllCommentListByPageModel> requestData(Context context, int pageNum, int pageSize, int businessId, byte businessType) {
        GetAllCommentListByPageService service = RestHelper.getBaseRetrofit(context).create(GetAllCommentListByPageService.class);
        return service.getData(pageNum, pageSize, businessId, businessType);
    }
}
