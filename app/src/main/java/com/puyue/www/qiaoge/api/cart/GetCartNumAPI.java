package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.GetCartNumModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by win7 on 2018/7/9.
 */

public class GetCartNumAPI {
    private interface GetCartNumService {
        @GET(AppInterfaceAddress.GETCARTNUM)
        Observable<GetCartNumModel> setParams();
    }

    public static Observable<GetCartNumModel> requestData(Context context) {
        GetCartNumService service = RestHelper.getBaseRetrofit(context).create(GetCartNumService.class);
        return service.setParams();
    }
}
