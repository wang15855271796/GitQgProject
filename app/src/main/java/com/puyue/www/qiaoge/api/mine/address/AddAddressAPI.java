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
 * Created by Administrator on 2018/4/21.
 */

public class AddAddressAPI {
    public interface AddAddressService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.EDIT_ADDRESS)
        Observable<BaseModel> setParams(@Field("userName") String userName,
                                        @Field("contactPhone") String contactPhone,
                                        @Field("proviceCode") String proviceCode,
                                        @Field("cityCode") String cityCode,
                                        @Field("areaCode") String areaCode,
                                        @Field("isDefault") int isDefault,
                                        @Field("detailAddress") String detailAddress,
                                        @Field("shopName") String shopName,
                                        @Field("id") String id, @Field("orderId") String orderId);
    }

    public static Observable<BaseModel> requestAddAddress(Context context, String userName, String contactPhone, String proviceCode, String cityCode, String areaCode, int isDefault, String detailAddress, String shopName, String id,String orderId) {
        Observable<BaseModel> addAddressObservable = RestHelper.getBaseRetrofit(context).create(AddAddressService.class).setParams(userName, contactPhone, proviceCode, cityCode, areaCode, isDefault, detailAddress, shopName, id,orderId);
        return addAddressObservable;
    }
}
