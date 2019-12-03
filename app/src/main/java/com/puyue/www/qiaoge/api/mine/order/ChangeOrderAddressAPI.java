package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ChangeOrderAddressModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/14.
 */

public class ChangeOrderAddressAPI {
    public interface ChangeOrderAddressService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CHANGE_ANOTHER_ADDRESS)
        Observable<ChangeOrderAddressModel> setParams(@Field("orderId") String orderId,
                                                      @Field("addressId") int addressId);
    }

    public static Observable<ChangeOrderAddressModel> requestChangeOrderAddress(Context context, String orderId, int addressId) {
        Observable<ChangeOrderAddressModel> changeOrderAddressModelObservable = RestHelper.getBaseRetrofit(context).create(ChangeOrderAddressService.class).setParams(orderId, addressId);
        return changeOrderAddressModelObservable;
    }
}
