package com.puyue.www.qiaoge.api.mine.address;

import android.content.Context;

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

public class DeleteAddressAPI {
    public interface DeleteAddressService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.DELETE_ADDRESS)
        Observable<BaseModel> setParams(@Field("id") int id);
    }

    public static Observable<BaseModel> requestDeleteAddress(Context context, int id) {
        Observable<BaseModel> deleteAddressObservable = RestHelper.getBaseRetrofit(context).create(DeleteAddressService.class).setParams(id);
        return deleteAddressObservable;
    }
}
