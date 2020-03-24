package com.puyue.www.qiaoge.api.mine.login;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.FVHelper;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.login.RegisterModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/18.
 */

public class RegisterAPI {
    public interface RegisterService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.REGISTER)
        Observable<RegisterModel> setParams(
                @Field("phone") String phone,
                @Field("accessCode") String accessCode,
                @Field("password") String password,
                @Field("verifyCode") String verifyCode,
                @Field("invitationCode") String invitationCode,
                @Field("inviteCode") String inviteCode, @Field("shopTypeId") String shopTypeId);
    }

    public static Observable<RegisterModel> requestRegister(Context context, String phone, String accessCode, String password,String verifyCode, String invitationCode,String inviteCode,String shopTypeId) {
        Observable<RegisterModel> registerModelObservable = RestHelper.getBaseRetrofit(context).create(RegisterService.class).setParams(phone, accessCode, password,verifyCode, invitationCode,inviteCode,shopTypeId);
        return registerModelObservable;
    }
}
