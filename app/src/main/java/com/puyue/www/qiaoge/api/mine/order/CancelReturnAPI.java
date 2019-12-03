package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/8/28
 */
public class CancelReturnAPI {

    public interface CancelOrderService{
        @FormUrlEncoded
        @POST(AppInterfaceAddress.USERCANCELORDER)
        Observable<BaseModel> setParam(@Field("returnMainId") String returnMainId);


    }


    public static Observable<BaseModel> requestCancelOrder(Context context,String returnMainId){

        Observable<BaseModel> baseModelObservable = RestHelper.getBaseRetrofit(context).create(CancelOrderService.class).setParam(returnMainId);
        return baseModelObservable;

    }


}
