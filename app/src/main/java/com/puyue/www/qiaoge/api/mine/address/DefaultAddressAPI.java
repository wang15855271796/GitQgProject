package com.puyue.www.qiaoge.api.mine.address;

import android.content.Context;
import android.widget.FrameLayout;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/23.
 */

public class DefaultAddressAPI {
    public interface DefaultAddressService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.EDIT_DEFAULT_ADDRESS)
        Observable<BaseModel> setParams(@Field("id") int id,
                                        @Field("orderId") String orderId);
    }

    public static Observable<BaseModel> requestEditDefaultAddress(Context context, int id,String orderId) {
        Observable<BaseModel> editDefaultAddressObservable = RestHelper.getBaseRetrofit(context).create(DefaultAddressService.class).setParams(id,orderId);
        return editDefaultAddressObservable;
    }
}
