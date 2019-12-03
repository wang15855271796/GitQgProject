package com.puyue.www.qiaoge.api.cart;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.cart.AddCartGoodModel;
import com.puyue.www.qiaoge.model.cart.AddMountReduceModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/7
 */
public class AddMountChangeTwoAPI {
    public interface AddMountChangeService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.ADDCARTJUDGE)
        Observable<AddCartGoodModel> setParams(@Field("businessType") int businessType,
                                               @Field("businessId") int businessId,
                                               @Field("num") int num,
                                               @Field("productCombinationPriceId") int productCombinationPriceId);//int 1
    }

    public static Observable<AddCartGoodModel> AddMountChangeService(Context context, int businessType, int businessId, int num, int productCombinationPriceId) {
        Observable<AddCartGoodModel> stringObservable = RestHelper.getBaseRetrofit(context).create(AddMountChangeService.class).setParams(businessType, businessId, num, productCombinationPriceId);
        return stringObservable;
    }

    public interface AddMountChangeServices {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.ADDCARTJUDGES)
        Observable<AddCartGoodModel> setParams(@Field("businessType") int businessType,
                                               @Field("businessId") int businessId,
                                               @Field("num") int num,
                                               @Field("productCombinationPriceId") int productCombinationPriceId);//int 1
    }

    public static Observable<AddCartGoodModel> AddMountChangeServices(Context context, int businessType, int businessId, int num, int productCombinationPriceId) {
        Observable<AddCartGoodModel> stringObservable = RestHelper.getBaseRetrofit(context).create(AddMountChangeServices.class).setParams(businessType, businessId, num, productCombinationPriceId);
        return stringObservable;
    }
}
