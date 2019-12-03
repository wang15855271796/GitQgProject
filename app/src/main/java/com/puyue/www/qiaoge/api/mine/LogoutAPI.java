package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/18.
 */

public class LogoutAPI {
    public interface LogoutService {
        @GET(AppInterfaceAddress.LOG_OUT)
        Observable<BaseModel> setParams();
    }

    public static Observable<BaseModel> requestLogout(Context context) {
        Observable<BaseModel> logoutObservable = RestHelper.getBaseRetrofit(context).create(LogoutService.class).setParams();
        return logoutObservable;
    }
}
