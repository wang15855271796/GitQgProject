package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.CartGetReductModel;
import com.puyue.www.qiaoge.model.mine.order.ChangeOrderAddressModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${daff}
 * on 2018/10/26
 */
public class CartGetReductDescAPI  {
    public interface CartGetReductDescService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CARTGETREDUCTDESC)
        Observable<CartGetReductModel> setParams(@Field("amount") double amount);
    }

    public static Observable<CartGetReductModel> requestCartGetReductDesc(Context context, double amount) {
        Observable<CartGetReductModel> changeOrderAddressModelObservable = RestHelper.getBaseRetrofit(context).create(CartGetReductDescService.class).setParams(amount);
        return changeOrderAddressModelObservable;
    }
}
