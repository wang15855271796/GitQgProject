package com.puyue.www.qiaoge.api.mine.login;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.login.RegisterAgreementModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/18.
 */

public class RegisterAgreementAPI {
    public interface RegisterAgreementService {
        @GET(AppInterfaceAddress.REGISTER_AGREEMENT)
        Observable<RegisterAgreementModel> setParams();
    }

    public static Observable<RegisterAgreementModel> requestRegisterAgreement(Context context) {
        Observable<RegisterAgreementModel> registerAgreementModelObservable = RestHelper.getBaseRetrofit(context).create(RegisterAgreementService.class).setParams();
        return registerAgreementModelObservable;
    }
}
