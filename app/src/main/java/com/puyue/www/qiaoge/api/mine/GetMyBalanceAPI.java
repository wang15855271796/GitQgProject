package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.api.market.MarketGoodsAPI;
import com.puyue.www.qiaoge.api.mine.subaccount.SubAccountListAPI;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.market.MarketGoodsModel;
import com.puyue.www.qiaoge.model.mine.GetMyBalanceModle;
import com.puyue.www.qiaoge.model.mine.GetSubUserListModel;
import com.puyue.www.qiaoge.model.mine.SubAccountModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${daff}
 * on 2018/10/24
 * 备注
 */
public class GetMyBalanceAPI {
    public interface GetMyBalanceService {

        @GET(AppInterfaceAddress.WALLETGETMYBALANCEINFO)
        Observable<GetMyBalanceModle> setParams();
    }

    public static Observable<GetMyBalanceModle> requestGetMyBalance(Context context) {
        Observable<GetMyBalanceModle> getMyBalanceObservable = RestHelper.getBaseRetrofit(context).create(GetMyBalanceService.class).setParams();
        return getMyBalanceObservable;
    }



}
