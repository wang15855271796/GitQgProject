package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.NoticeListModel;
import com.puyue.www.qiaoge.model.home.QueryHomePropupModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${daff}
 * on 2018/10/25
 * 备注 弹窗部分
 */
public class QueryHomePropupAPI{
    public interface QueryHomePropupService {

        @GET(AppInterfaceAddress.AUTHQUERYHOMEPROPUP)
        Observable<QueryHomePropupModel> setParams();
    }

    public static Observable<QueryHomePropupModel> requestQueryHomePropup(Context context) {
        Observable<QueryHomePropupModel> noticeListModelObservable =
                RestHelper.getBaseRetrofit(context).create(QueryHomePropupService.class).setParams();
        return noticeListModelObservable;
    }
}
