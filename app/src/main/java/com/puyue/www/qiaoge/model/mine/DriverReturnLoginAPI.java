package com.puyue.www.qiaoge.model.mine;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;

import com.puyue.www.qiaoge.view.DriverHelper;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/6/15
 */
public class DriverReturnLoginAPI {
    private interface DriverReturnService {

        @POST(AppInterfaceAddress.DRIVERLOGOUT)
        Observable<BaseModel> getData();
    }

    public static Observable<BaseModel> requestData(Context context) {
        DriverReturnService service = DriverHelper.getBaseRetrofit(context).create(DriverReturnService.class);
        return service.getData( );
    }
}
