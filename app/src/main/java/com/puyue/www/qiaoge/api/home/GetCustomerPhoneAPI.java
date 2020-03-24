package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by win7 on 2018/7/9.
 */

public class GetCustomerPhoneAPI {
    private interface GetCustomerPhoneService {
        @GET(AppInterfaceAddress.GETCUSTOMERPHONE)
        Observable<GetCustomerPhoneModel> setParams();
    }

    public static Observable<GetCustomerPhoneModel> requestData(Context context) {
        GetCustomerPhoneService service = RestHelper.getBaseRetrofit(context).create(GetCustomerPhoneService.class);
        return service.setParams();
    }

    public interface ChuangLanService {
        @GET(AppInterfaceAddress.CHAUNGLAN)
        Observable<OneRegisterModel> setParams(@Query("accessCode") String accessCode);
    }

    public static Observable<OneRegisterModel> getData(Context context , String accessCode) {
        Observable<OneRegisterModel> getDeliverTime = RestHelper.getBaseRetrofit(context).create(ChuangLanService.class).setParams(accessCode);
        return getDeliverTime;
    }


    public interface CodeService {
        @GET(AppInterfaceAddress.CHECK_CODE)
        Observable<BaseModel> setParams(@Query("invitationCode") String invitationCode);
    }

    public static Observable<BaseModel> checkCode(Context context , String invitationCode) {
        Observable<BaseModel> getDeliverTime = RestHelper.getBaseRetrofit(context).create(CodeService.class).setParams(invitationCode);
        return getDeliverTime;
    }

}
