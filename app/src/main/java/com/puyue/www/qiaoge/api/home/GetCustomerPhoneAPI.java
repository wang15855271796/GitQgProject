package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetDeliverTimeModel;

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
        Observable<BaseModel> setParams(@Query("accessCode") String accessCode);
    }

    public static Observable<BaseModel> getData(Context context , String accessCode) {
        Observable<BaseModel> getDeliverTime = RestHelper.getBaseRetrofit(context).create(ChuangLanService.class).setParams(accessCode);
        return getDeliverTime;
    }

}
