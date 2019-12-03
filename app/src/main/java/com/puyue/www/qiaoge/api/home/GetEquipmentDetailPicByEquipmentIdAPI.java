package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetEquipmentDetailPicByEquipmentIdModel;

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

public class GetEquipmentDetailPicByEquipmentIdAPI {
    private interface GetEquipmentDetailPicByEquipmentIdService{
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETEQUIPMENTDETAILPICBYEQUIPMENTID)
        Observable<GetEquipmentDetailPicByEquipmentIdModel> getData(@Field("equipmentId") int equipmentId);
    }
    public static Observable<GetEquipmentDetailPicByEquipmentIdModel> requestData(Context context,int equipmentId){
        GetEquipmentDetailPicByEquipmentIdService service = RestHelper.getBaseRetrofit(context).create(GetEquipmentDetailPicByEquipmentIdService.class);
        return service.getData(equipmentId);
    }
}
