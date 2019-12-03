package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/21.
 */

public class FeedbackAPI {
    public interface FeedbackService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.FEEDBACK)
        Observable<BaseModel> setParams(@Field("content") String content,
                                        @Field("name") String name,
                                        @Field("phone") String phone,
                                        @Field("qqOrEmali") String qqOrEmali,
                                        @Field("wechat") String wechat);
    }

    public static Observable<BaseModel> requestFeedback(Context context, String content, String name, String phone, String qqOrEmali, String wechat) {
        Observable<BaseModel> feedbackObservable = RestHelper.getBaseRetrofit(context).create(FeedbackService.class).setParams(content, name, phone, qqOrEmali, wechat);
        return feedbackObservable;
    }

}
