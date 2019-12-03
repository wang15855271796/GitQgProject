package com.puyue.www.qiaoge.api.mine.subaccount;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.SearchListModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/8/16
 */
public class SearchAccountAPI {


    public interface SearchListService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.WALLETSEARCH)
        Observable<SearchListModel> setParam(@Field("showType") int showType);
    }

    public static Observable<SearchListModel> requestSearchList(Context context,int showType) {
        Observable<SearchListModel> searchListModelObservable = RestHelper.getBaseRetrofit(context).create(SearchListService.class).setParam(showType);
        return searchListModelObservable;

    }


}
