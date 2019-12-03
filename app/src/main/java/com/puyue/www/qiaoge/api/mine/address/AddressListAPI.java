package com.puyue.www.qiaoge.api.mine.address;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.address.AddressModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/20.
 */

public class AddressListAPI {
    public interface AddressListService {
        @GET(AppInterfaceAddress.GET_ADDRESS_LIST)
        Observable<AddressModel> setParams();
    }

    public static Observable<AddressModel> requestAddressModel(Context context) {
        Observable<AddressModel> addressModelObservable = RestHelper.getBaseRetrofit(context).create(AddressListService.class).setParams();
        return addressModelObservable;
    }
}
