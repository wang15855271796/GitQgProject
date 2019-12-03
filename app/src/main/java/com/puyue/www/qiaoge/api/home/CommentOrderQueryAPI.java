package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.CommentOrderQueryModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/20.
 */

public class CommentOrderQueryAPI {
    private interface CommentOrderQueryService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.COMMENTORDERQUERY)
        Observable<CommentOrderQueryModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("businessId") int businessId, @Field("businessType") byte businessType);
    }

    public static Observable<CommentOrderQueryModel> requestData(Context context, int pageNum, int pageSize, int businessId, byte businessType) {
        CommentOrderQueryService service = RestHelper.getBaseRetrofit(context).create(CommentOrderQueryService.class);
        return service.getData(pageNum, pageSize, businessId, businessType);
    }
}
