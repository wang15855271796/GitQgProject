package com.puyue.www.qiaoge.api.market;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.market.MarketClassifyModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MarketGoodsClassifyAPI {
    public interface MarketGoodsService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.PRODUCTCLASSIFY)
        Observable<MarketClassifyModel> setParams(@Field("productType") String productType);
    }

    public static Observable<MarketClassifyModel> requestMarketGoods(Context context, String productType) {
        Observable<MarketClassifyModel> marketGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(MarketGoodsService.class).setParams(productType);
        return marketGoodsModelObservable;
    }


    private interface MarketsGoodSelectService {
        @POST(AppInterfaceAddress.CLASSIFY)
        Observable<ClassIfyModel> getData();
    }

    public static Observable<ClassIfyModel> getClassify(Context context) {
        MarketsGoodSelectService service = RestHelper.getBaseRetrofit(context).create(MarketsGoodSelectService.class);
        return service.getData();
    }
}
