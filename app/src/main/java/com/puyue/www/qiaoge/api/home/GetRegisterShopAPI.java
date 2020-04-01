package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetCustomerPhoneModel;
import com.puyue.www.qiaoge.model.home.GetRegisterShopModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/6
 */
public class GetRegisterShopAPI {
    private interface GetRegisterShop {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.REGISTERSHOPTYPE)
        Observable<GetRegisterShopModel> getData(@Field("invitationCode") String invitationCode);
    }

    public static Observable<GetRegisterShopModel> requestData(Context context,String invitationCode) {
        GetRegisterShop service = RestHelper.getBaseRetrofit(context).create(GetRegisterShop.class);
        return service.getData(invitationCode);
    }
}
