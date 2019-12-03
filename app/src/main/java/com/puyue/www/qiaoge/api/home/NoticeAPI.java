package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.NoticeListModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/9.
 */

public class NoticeAPI {
    public interface NoticeService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GET_INDEX_NOTICE_PAGE)
        Observable<NoticeListModel> setParams(@Field("pageNum") int pageNum, @Query("pageSize") int pageSize);
    }

    public static Observable<NoticeListModel> requestNotice(Context context, int pageNum, int pageSize) {
        Observable<NoticeListModel> noticeListModelObservable = RestHelper.getBaseRetrofit(context).create(NoticeService.class).setParams(pageNum, pageSize);
        return noticeListModelObservable;
    }
}
