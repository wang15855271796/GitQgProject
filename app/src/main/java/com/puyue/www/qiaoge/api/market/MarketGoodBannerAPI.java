package com.puyue.www.qiaoge.api.market;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.market.MarketBannerModel;
import com.puyue.www.qiaoge.model.market.MarketClassifyModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/21
 */
public class MarketGoodBannerAPI {
    public interface MarketGoodsBannerService {

        @POST(AppInterfaceAddress.CLASSIFYBANNER)
        Observable<MarketBannerModel> setParams();
    }

    public static Observable<MarketBannerModel> requestMarketBanner(Context context) {
        Observable<MarketBannerModel> marketGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(MarketGoodsBannerService.class).setParams();
        return marketGoodsModelObservable;
    }
}
