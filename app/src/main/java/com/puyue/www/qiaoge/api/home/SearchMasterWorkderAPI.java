package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.SearchMasterWorkderModel;

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

public class SearchMasterWorkderAPI {
    private interface SearchMasterWorkderService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SEARCHMASTERWORKDER)
        Observable<SearchMasterWorkderModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("name") String name, @Field("searchType") String searchType, @Field("startDate") String startDate, @Field("endDate") String endDate);
    }

    public static Observable<SearchMasterWorkderModel> requestData(Context context, int pageNum, int pageSize, String name, String searchType, String startDate, String endDate) {
        SearchMasterWorkderService service = RestHelper.getBaseRetrofit(context).create(SearchMasterWorkderService.class);
        return service.getData(pageNum, pageSize, name, searchType, startDate, endDate);
    }
}
