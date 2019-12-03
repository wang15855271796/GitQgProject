package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.NewReturnOrderModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/8/28
 */
public class NewReturnOderAPI {

    public interface NewReturnOrderService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.NEWRETURNDETAIL)
        Observable<NewReturnOrderModel> setparam(@Field("returnProductMainId") String returnProductMainId);


    }

    public static Observable<NewReturnOrderModel> requestNewReturnDetail(Context context, String returnProductMainId) {

        Observable<NewReturnOrderModel> setparam = RestHelper.getBaseRetrofit(context).create(NewReturnOrderService.class).setparam(returnProductMainId);
        return setparam;


    }
}
