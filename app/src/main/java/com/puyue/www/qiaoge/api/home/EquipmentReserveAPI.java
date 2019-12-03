package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.EquipmentReserveModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/25.
 *
 */

public class EquipmentReserveAPI {
    private interface EquipmentReserveService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.EQUIPMENTRESERVE)
        Observable<EquipmentReserveModel> getData(@Field("equipmentId") int equipmentId, @Field("startDate") String startDate, @Field("endDate") String endDate, @Field("num") int num);
    }

    public static Observable<EquipmentReserveModel> requestData(Context context, int equipmentId, String startDate, String endDate, int num) {
        EquipmentReserveService service = RestHelper.getBaseRetrofit(context).create(EquipmentReserveService.class);
        return service.getData(equipmentId, startDate, endDate, num);
    }
}
