package com.puyue.www.qiaoge.api.mine.collection;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.collection.DeleteCollectionModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/9.
 */

public class DeleteCollectionAPI {
    public interface DeleteCollectionService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.DELETE_COMMON_COLLECT)
        Observable<DeleteCollectionModel> setParams(@Field("ids") String ids);
    }

    public static Observable<DeleteCollectionModel> requestDeleteCollection(Context context, String ids) {
        Observable<DeleteCollectionModel> deleteCollectionModelObservable = RestHelper.getBaseRetrofit(context).create(DeleteCollectionService.class).setParams(ids);
        return deleteCollectionModelObservable;
    }
}
