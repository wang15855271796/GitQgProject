package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.api.PostLoadAmountAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.CancleModel;
import com.puyue.www.qiaoge.model.CancleReasonModel;
import com.puyue.www.qiaoge.model.home.ClickCollectionModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王涛} on 2020/6/4
 */
public class CancleAPI {
    private interface CancleService {

    @FormUrlEncoded
    @POST(AppInterfaceAddress.Is_Cancle)
    Observable<CancleModel> getData(@Field("cancelReason") String cancelReason);
}

    public static Observable<CancleModel> requestData(Context context,String cancelReason) {
        CancleService service = RestHelper.getBaseRetrofit(context).create(CancleService.class);
        return service.getData(cancelReason);
    }


    /**
     * 注销原因
     */
    private interface CancleReasonService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.Cancle_Reason)
        Observable<CancleReasonModel> getData(@Field("sysKey") String sysKey);
    }

    public static Observable<CancleReasonModel> getList(Context context, String sysKey) {
        CancleReasonService service = RestHelper.getBaseRetrofit(context).create(CancleReasonService.class);
        return service.getData(sysKey);
    }

}
