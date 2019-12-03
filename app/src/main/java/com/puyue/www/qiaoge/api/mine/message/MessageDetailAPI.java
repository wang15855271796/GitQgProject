package com.puyue.www.qiaoge.api.mine.message;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.MessageDetailModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MessageDetailAPI {
    public interface MessageDetailService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.MESSAGE_DETAIL)
        Observable<MessageDetailModel> setParams(@Field("id") int id);
    }

    public static Observable<MessageDetailModel> requestMessageDetail(Context context, int id) {
        Observable<MessageDetailModel> messageDetailObservable = RestHelper.getBaseRetrofit(context).create(MessageDetailService.class).setParams(id);
        return messageDetailObservable;
    }
}
