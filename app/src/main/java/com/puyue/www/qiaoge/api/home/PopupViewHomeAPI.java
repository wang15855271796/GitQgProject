package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.MasterWorkerReserveModel;
import com.puyue.www.qiaoge.model.home.PopupViewHomeModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${daff}
 * on 2018/10/25
 * 备注
 */
public class PopupViewHomeAPI  {
    private interface PopupViewHomeService{
        @FormUrlEncoded
        @POST(AppInterfaceAddress.AUTHPOPVIEW)
        Observable<PopupViewHomeModel> getData(@Field("id") int id);
    }
    public static Observable<PopupViewHomeModel> requestData(Context context, int id){
        PopupViewHomeService  service = RestHelper.getBaseRetrofit(context).create(PopupViewHomeService.class);
        return service.getData(id);
    }
}
