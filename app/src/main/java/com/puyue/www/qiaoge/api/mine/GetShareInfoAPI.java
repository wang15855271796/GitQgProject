package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.GetMyBalanceModle;
import com.puyue.www.qiaoge.model.mine.GetShareInfoModle;

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
public class GetShareInfoAPI {

    public interface GetShareInfoService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.COMMONSHARGETSHAREINFO)
        Observable<GetShareInfoModle> setParams(@Field("businessId") int businessId,
                                                @Field("businessType") int businessType);
    }

    public static Observable<GetShareInfoModle> requestGetShareInfoService(Context context, int businessId, int businessType) {
        Observable<GetShareInfoModle> getShareInfoServicebservable = RestHelper.getBaseRetrofit(context).create(GetShareInfoService.class).setParams(businessId, businessType);
        return getShareInfoServicebservable;
    }

}
