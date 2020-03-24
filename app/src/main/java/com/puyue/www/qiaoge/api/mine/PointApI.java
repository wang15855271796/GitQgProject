package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.wallet.GetWalletInfoModel;
import com.puyue.www.qiaoge.model.mine.wallet.MinerIntegralModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${daff}
 * on 2018/10/23
 * 备注 我的积分
 */
public class PointApI {
    public interface PointService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.POINT_MYPOINT)
        Observable<MinerIntegralModel> setParams(@Field("pageNum") int pageNum,
                                                 @Field("pageSize") int pageSize);
    }

    public static Observable<MinerIntegralModel> requestPointService(Context context,int pageNum,int pageSize ) {
        Observable<MinerIntegralModel> getPointService = RestHelper.getBaseRetrofit(context).create(PointService.class).setParams(pageNum,pageSize);
        return getPointService;
    }

}
