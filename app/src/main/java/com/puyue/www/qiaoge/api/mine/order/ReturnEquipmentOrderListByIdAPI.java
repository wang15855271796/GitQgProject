package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ReturnEquipmentOrderListByIdModel;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/5/8.
 */

public class ReturnEquipmentOrderListByIdAPI {
    private interface ReturnEquipmentOrderListByIdService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.RETURNEQUIPMENTORDERLISTBYID)
        Observable<ReturnEquipmentOrderListByIdModel> getData(@Field("orderIds") String orderIds);
    }

    public static Observable<ReturnEquipmentOrderListByIdModel> requestData(Context context, String orderIds) {
        ReturnEquipmentOrderListByIdService service = RestHelper.getBaseRetrofit(context).create(ReturnEquipmentOrderListByIdService.class);
        return service.getData(orderIds);
    }
}
