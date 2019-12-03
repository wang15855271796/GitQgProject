package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetAddressModel;
import com.puyue.www.qiaoge.model.home.PopupViewHomeModel;
import com.puyue.www.qiaoge.model.home.SpikeActiveQueryModel;



import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/9
 */
public class SendLocationAPI {

    private interface sendLocationService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETUSERADDRESS)
        Observable<GetAddressModel> setParam(@Field("lastSignAddress") String lastSignAddress);
    }


    public static Observable<GetAddressModel> requestData(Context context, String lastSignAddress) {
        sendLocationService spikeActiveQueryService = RestHelper.getBaseRetrofit(context).create(sendLocationService.class);
        return spikeActiveQueryService.setParam(lastSignAddress);
    }
}
