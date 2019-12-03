package com.puyue.www.qiaoge.adapter.mine;

import android.content.Context;

import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.wallet.AddressModel;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * 创建 on 2019/5/29.
 * 描述：
 */
public class AddressApi {
    public interface AddressService {

        @GET("geocoder/v2/")
        Observable<AddressModel> setParams(@Query("address") String address,
                                           @Query("output") String output,
                                           @Query("ak") String ak,
                                           @Query("mcode") String mcode);
    }

    public static Observable<AddressModel> requestAddress(Context context, String address, String output, String ak, String mcode) {
        Observable<AddressModel> feedbackObservable = RestHelper.getBaiDuRetrofit(context).create(AddressService.class).setParams(address, output, ak, mcode);
        return feedbackObservable;
    }
}
