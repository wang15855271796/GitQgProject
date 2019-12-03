package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.GetWalletAmountModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/28.
 */

public class GetWalletAmountAPI {
    private interface GetWalletAmountService {
        @GET(AppInterfaceAddress.GETWALLETAMOUNT)
        Observable<GetWalletAmountModel> getData();
    }

    public static Observable<GetWalletAmountModel> requestData(Context context) {
        GetWalletAmountService service = RestHelper.getBaseRetrofit(context).create(GetWalletAmountService.class);
        return service.getData();
    }
}
