package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.CouponModel;
import com.puyue.www.qiaoge.model.home.HomeNewRecommendModel;
import com.puyue.www.qiaoge.model.home.IndexHomeModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;
import com.puyue.www.qiaoge.model.home.SpikeNewQueryModel;
import com.puyue.www.qiaoge.model.mine.order.HomeBaseModel;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/17.
 */

public class IndexHomeAPI {
    private interface IndexHomeService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.INDEXHOME)
        Observable<IndexHomeModel> getData(
                @Field("version") String version,
                @Field("clientType") String clientType);
    }

    public static Observable<IndexHomeModel> requestData(Context context, String version, String clientType) {
        IndexHomeService service = RestHelper.getBaseRetrofit(context).create(IndexHomeService.class);
        return service.getData(version, clientType);
    }


    private interface IndexHomeServices {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.INDEXHOME)
        Observable<HomeBaseModel> getData(
                @Field("version") String version,
                @Field("clientType") String clientType);
    }

    public static Observable<HomeBaseModel> getBaseList(Context context, String version, String clientType) {
        IndexHomeServices service = RestHelper.getBaseRetrofit(context).create(IndexHomeServices.class);
        return service.getData(version, clientType);
    }



    private interface IndexRecommendService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.HOMENEW)
        Observable<HomeNewRecommendModel> getData(
                @Field("pageNum") String pageNum,
                @Field("pageSize") String pageSize);
    }

    public static Observable<HomeNewRecommendModel> getRecommendData(Context context,  String pageNum, String pageSize) {
        IndexRecommendService service = RestHelper.getBaseRetrofit(context).create(IndexRecommendService.class);
        return service.getData(pageNum, pageSize);
    }

    /**
     * 首页banner
     */

    private interface BannerService {
        @POST(AppInterfaceAddress.BANNER)
        Observable<BannerModel> getData();

    }

    public static Observable<BannerModel> getBanner(Context context) {
        BannerService spikeActiveQueryService = RestHelper.getBaseRetrofit(context).create(BannerService.class);
        return spikeActiveQueryService.getData();
    }


    /**
     * 必买清单
     */

    private interface MustService {
        @POST(AppInterfaceAddress.INDEXMUST)
        Observable<ProductNormalModel> getData();

    }

    public static Observable<ProductNormalModel> getMust(Context context) {
        MustService spikeActiveQueryService = RestHelper.getBaseRetrofit(context).create(MustService.class);
        return spikeActiveQueryService.getData();
    }

    /**
     * 首页其他信息
     */
    private interface RecommendService {
        @POST(AppInterfaceAddress.INDEXINFO)
        Observable<IndexInfoModel> getData();

    }

    public static Observable<IndexInfoModel> getIndexInfo(Context context) {
        RecommendService spikeActiveQueryService = RestHelper.getBaseRetrofit(context).create(RecommendService.class);
        return spikeActiveQueryService.getData();
    }

    /**
     * 首页司机信息
     */
    private interface DriverService {
        @POST(AppInterfaceAddress.DISTRIBUTE)
        Observable<DriverInfo> getData();

    }

    public static Observable<DriverInfo> getDriverInfo(Context context) {
        DriverService spikeActiveQueryService = RestHelper.getBaseRetrofit(context).create(DriverService.class);
        return spikeActiveQueryService.getData();
    }

    /**
     * 折扣、组合数据
     */
    private interface CouponService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.COUPONINFO)
        Observable<CouponModel> getData(
                @Field("activityType") String activityType);
    }

    public static Observable<CouponModel> getCouponList(Context context,  String activityType) {
        CouponService service = RestHelper.getBaseRetrofit(context).create(CouponService.class);
        return service.getData(activityType);
    }

}
