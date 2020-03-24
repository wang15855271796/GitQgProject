package com.puyue.www.qiaoge.api.market;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.market.MarketAlreadyGoodModel;
import com.puyue.www.qiaoge.model.market.MarketSelectGoodModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/21
 */
public class MarketAlreadyGoodAPI {

    public interface MarketGoodsAlreadyService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETALREADYPRODUCT)
        Observable<MarketAlreadyGoodModel> setParams(
                @Field("firstId") int firstId,
                @Field("secondId") int secondId
        );
    }

    public static Observable<MarketAlreadyGoodModel> requestMarketAlready(Context context,  int firstId, int secondId) {
        Observable<MarketAlreadyGoodModel> marketGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(MarketGoodsAlreadyService.class).setParams( firstId, secondId);
        return marketGoodsModelObservable;
    }
}
