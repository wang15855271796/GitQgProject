package com.puyue.www.qiaoge.api.market;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.market.MarketBannerModel;
import com.puyue.www.qiaoge.model.market.MarketSelectGoodModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/21
 */
public class MarketGoodNameAPI {
    public interface MarketGoodsNameService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETPRODUCTBRAND)
        Observable<MarketSelectGoodModel> setParams(
                @Field("firstId") int firstId,
                @Field("secondId") int secondId,
                @Field("brandName") String brandName);
    }

    public static Observable<MarketSelectGoodModel> requestMarketName(Context context, int firstId, int secondId,String brandName) {
        Observable<MarketSelectGoodModel> marketGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(MarketGoodsNameService.class).setParams( firstId, secondId,brandName);
        return marketGoodsModelObservable;
    }


}
