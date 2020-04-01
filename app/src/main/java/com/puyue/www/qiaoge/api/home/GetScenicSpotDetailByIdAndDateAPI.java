package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.AddressBean;
import com.puyue.www.qiaoge.model.home.CityChangeModel;
import com.puyue.www.qiaoge.model.home.GetScenicSpotDetailByIdAndDateModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/23.
 */

public class GetScenicSpotDetailByIdAndDateAPI {
    private interface GetScenicSpotDetailByIdAndDateService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETSCENICSPOTDETAILBYIDANDDATE)
        Observable<GetScenicSpotDetailByIdAndDateModel> getData(@Field("spotId") int spotId, @Field("startDate") String startDate, @Field("endDate") String endDate);
    }

    public static Observable<GetScenicSpotDetailByIdAndDateModel> requestData(Context context, int spotId, String startDate, String endDate) {
        GetScenicSpotDetailByIdAndDateService service = RestHelper.getBaseRetrofit(context).create(GetScenicSpotDetailByIdAndDateService.class);
        return service.getData(spotId, startDate, endDate);
    }

    public interface ShopListService{

        @POST(AppInterfaceAddress.SHOP_TYPE_LIST)
        Observable<AddressBean> setParam();
    }

    public static Observable<AddressBean>getShopList(Context context){
        Observable<AddressBean> cityChangeModelObservable = RestHelper.getBaseRetrofit(context).create(ShopListService.class).setParam();
        return cityChangeModelObservable;

    }
}
