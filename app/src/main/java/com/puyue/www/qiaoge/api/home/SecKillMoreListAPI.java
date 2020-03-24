package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.SeckillListModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 杭州融科网络
 * 刘燕创建 on 2019/4/13.
 * 描述：
 */
public class SecKillMoreListAPI {
    private interface SecKillMoreListQueryService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.SECKILLISTQUERY)
        Observable<SeckillListModel> getData(@Field("activeId") int activeId);
        //默认0不选，  1销售量 ，2价格升序,3价格降序
    }

    public static Observable<SeckillListModel> requestMoreListData(Context context, int activeId) {
        SecKillMoreListQueryService spikeActiveQueryService = RestHelper.getBaseRetrofit(context).create(SecKillMoreListQueryService.class);
        return spikeActiveQueryService.getData(activeId);
    }
}
