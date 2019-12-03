package com.puyue.www.qiaoge.api.mine.message;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.UpdateMessageStateModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/17.
 */

public class UpdateMessageStateAPI {
    public interface UpdateMessageStateService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.UPDATE_MESSAGE_STATE)
        Observable<UpdateMessageStateModel> setParams(@Field("id") int id);
    }

    public static Observable<UpdateMessageStateModel> requestUpdateMessageState(Context context, int id) {
        Observable<UpdateMessageStateModel> updateMessageStateModelObservable = RestHelper.getBaseRetrofit(context).create(UpdateMessageStateService.class).setParams(id);
        return updateMessageStateModelObservable;
    }
}
