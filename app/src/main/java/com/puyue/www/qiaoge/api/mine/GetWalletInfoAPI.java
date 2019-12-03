package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.api.mine.coupon.MyCouponsAPI;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.wallet.GetWalletInfoModel;
import retrofit2.http.POST;

import rx.Observable;

/**
 * @author daff
 * @date 2018/9/22.
 * 备注 钱包页面
 */
public class GetWalletInfoAPI {

    public interface GetWalletInfoService {

        @POST(AppInterfaceAddress.GET_WALLET_INFO)
        Observable<GetWalletInfoModel> setParams();
    }

    public static Observable<GetWalletInfoModel> requestGetWalletInfo(Context context) {
        Observable<GetWalletInfoModel> getWalletInfo = RestHelper.getBaseRetrofit(context).create(GetWalletInfoService.class).setParams();
        return getWalletInfo;
    }


}
