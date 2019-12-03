package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.AccountCenterModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/18.
 */

public class AccountCenterAPI {
    public interface AccountCenterService {
        @GET(AppInterfaceAddress.ACCOUNT_CENTER)
        Observable<AccountCenterModel> setParams();
    }

    public static Observable<AccountCenterModel> requestAccountCenter(Context context) {
        Observable<AccountCenterModel> accountCenterModelObservable = RestHelper.getBaseRetrofit(context).create(AccountCenterService.class).setParams();
        return accountCenterModelObservable;
    }
}
