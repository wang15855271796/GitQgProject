package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.WalletDetailModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/8/17
 */
public class GetWalletDetailAPI {
    public interface GetWalletDetailService {

        @FormUrlEncoded
        @POST(AppInterfaceAddress.WALLETDETAIL)
        Observable<WalletDetailModel> setparam(@Field("id") int id);

    }


    public static Observable<WalletDetailModel> requestWalletDetail(Context context, int id) {
        Observable<WalletDetailModel> setparam = RestHelper.getBaseRetrofit(context).create(GetWalletDetailService.class).setparam(id);

        return setparam;
    }


}
