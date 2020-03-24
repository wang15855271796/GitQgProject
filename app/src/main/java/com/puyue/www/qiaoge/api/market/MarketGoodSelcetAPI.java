package com.puyue.www.qiaoge.api.market;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/21
 */
public class MarketGoodSelcetAPI {

//    public interface MarketGoodSelectService {
//        @FormUrlEncoded
//        @POST(AppInterfaceAddress.QUERYCLASSIFYPRODUCT)
//        Observable<MarketGoodsModel> setParams(@Field("pageNum") int pageNum,
//                                               @Field("pageSize") int pageSize,
//                                               @Field("firstId") int firstId,
//                                               @Field("secondId") int secondId,
//                                               @Field("saleVolume") String saleVolume,
//                                               @Field("priceUp") String priceUp,
//                                               @Field("newProduct") String newProduct,
//                                               @Field("brandName") String brandName,
//                                               @Field("minPrice")  String minPrice,
//                                               @Field("maxPrice")  String maxPrice);
//    }
//
//    public static Observable<MarketGoodsModel> requestMarketGoods(Context context, int pageNum, int pageSize, int firstId, int secondId, String saleVolume, String priceUp, String newProduct, String  brandName, String minPrice, String maxPrice) {
//        Observable<MarketGoodsModel> marketGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(MarketGoodSelectService.class).setParams(pageNum, pageSize, firstId, secondId, saleVolume,priceUp,newProduct,brandName,minPrice,maxPrice);
//        return marketGoodsModelObservable;
//    }

    public interface MarketRight {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CLASSIFY_RIGHT)
        Observable<MarketRightModel> setParams(@Field("pageNum") int pageNum,
                                               @Field("pageSize") int pageSize,
                                               @Field("firstId") int firstId,
                                               @Field("secondId") int secondId,
                                               @Field("saleVolume") String saleVolume,
                                               @Field("priceUp") String priceUp,
                                               @Field("newProduct") String newProduct,
                                               @Field("brandName") String brandName,
                                               @Field("minPrice") String minPrice,
                                               @Field("maxPrice") String maxPrice);
    }

    public static Observable<MarketRightModel> getClassifyRight(Context context, int pageNum, int pageSize, int firstId, int secondId, String saleVolume, String priceUp, String newProduct, String  brandName, String minPrice, String maxPrice) {
        Observable<MarketRightModel> marketGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(MarketRight.class).setParams(pageNum, pageSize, firstId, secondId, saleVolume,priceUp,newProduct,brandName,minPrice,maxPrice);
        return marketGoodsModelObservable;
    }


    /**
     * 分类精选列表
     */
    public interface MarketRights {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.CLASSIFY_RIGHT)
        Observable<MarketRightModel> setParams(@Field("pageNum") int pageNum,
                                               @Field("pageSize") int pageSize,
                                               @Field("firstId") int firstId);
    }

    public static Observable<MarketRightModel> getClassifyRights(Context context, int pageNum, int pageSize, int firstId) {
        Observable<MarketRightModel> marketGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(MarketRights.class).setParams(pageNum, pageSize, firstId);
        return marketGoodsModelObservable;
    }

}
