package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.SearchEquipmentModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/19.
 */

public class SearchEquipmentAPI {
    private interface SearchEquipmentService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SEARCHEQUIPMENT)
        Observable<SearchEquipmentModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("name") String name, @Field("searchType") String searchType,
                                                 @Field("productFirstTypeId") String productFirstTypeId, @Field("productSecondTypeId") String productSecondTypeId);
    }

    public static Observable<SearchEquipmentModel> requestData(Context context, int pageNum, int pageSize, String name, String searchType, String productFirstTypeId, String productSecondTypeId) {
        SearchEquipmentService searchEquipmentService = RestHelper.getBaseRetrofit(context).create(SearchEquipmentService.class);
        return searchEquipmentService.getData(pageNum, pageSize, name, searchType, productFirstTypeId, productSecondTypeId);
    }
}
