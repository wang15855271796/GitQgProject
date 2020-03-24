package com.puyue.www.qiaoge.api.market;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.helper.StringHelper;
import com.puyue.www.qiaoge.model.market.MarketGoodsModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/19.
 */

public class MarketGoodsAPI {
    public interface MarketGoodsService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.MARKET_GOODS)
        Observable<MarketGoodsModel> setParams(@Field("pageNum") int pageNum,
                                               @Field("pageSize") int pageSize,
                                               @Field("firstId") int firstId,
                                               @Field("classifyId") int classifyId,
                                               @Field("productType") String productType);
    }

    public static Observable<MarketGoodsModel> requestMarketGoods(Context context, int pageNum, int pageSize, int firstId, int classifyId, String productType) {
        Observable<MarketGoodsModel> marketGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(MarketGoodsService.class).setParams(pageNum, pageSize, firstId, classifyId, productType);
        return marketGoodsModelObservable;
    }

    public interface SelectionGoodsService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.QUERYCLASSIFYPRODUCT)
        Observable<MarketGoodsModel> setParams(@Field("pageNum") int pageNum,
                                               @Field("pageSize") int pageSize,
                                               @Field("firstId") int firstId,
                                               @Field("classifyId") int classifyId,
                                               @Field("saleVolume") String saleVolume,
                                               @Field("priceUp") String priceUp,
                                               @Field("newProduct") String newProduct,
                                               @Field("brandName") String brandName,
                                               @Field("minPrice") String minPrice,
                                               @Field("maxPrice") String maxPrice);
    }

    public static Observable<MarketGoodsModel> requestSelectionGoods(Context context, int pageNum, int pageSize, int firstId, int classifyId, String saleVolume, String priceUp, String newProduct,String brandName,String minPrice,String maxPrice) {
        Observable<MarketGoodsModel> marketGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(SelectionGoodsService.class).setParams(pageNum, pageSize, firstId, classifyId, saleVolume,priceUp,newProduct,brandName,minPrice,maxPrice);
        return marketGoodsModelObservable;
    }


}
