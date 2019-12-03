package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetEquipmentByIdAndDateModel;

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

public class GetEquipmentByIdAndDateAPI {
    private interface GetEquipmentByIdAndDateService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETEQUIPMENTBYIDANDDATE)
        Observable<GetEquipmentByIdAndDateModel> getData(@Field("equipmentId") int equipmentId);
    }

    public static Observable<GetEquipmentByIdAndDateModel> requestData(Context context, int equipmentId) {
        GetEquipmentByIdAndDateService service = RestHelper.getBaseRetrofit(context).create(GetEquipmentByIdAndDateService.class);
        return service.getData(equipmentId);
    }
}
