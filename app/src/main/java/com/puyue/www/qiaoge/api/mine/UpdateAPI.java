package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.UpdateModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/13.
 */

public class UpdateAPI {
    public interface UpdateService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.AND_VERSION)
        Observable<UpdateModel> setParams(@Field("version") String version);
    }

    public static Observable<UpdateModel> requestUpdate(Context context, String version) {
        Observable<UpdateModel> updateModelObservable = RestHelper.getBaseRetrofit(context).create(UpdateService.class).setParams(version);
        return updateModelObservable;
    }
}
