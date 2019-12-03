package com.puyue.www.qiaoge.api.mine.subaccount;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.SubAccountModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SubAccountListAPI {
    public interface SubAccountListService {
        @GET(AppInterfaceAddress.GET_SUB_USER)
        Observable<SubAccountModel> setParams();
    }

    public static Observable<SubAccountModel> requestSubAccountList(Context context) {
        Observable<SubAccountModel> subAccountModelObservable = RestHelper.getBaseRetrofit(context).create(SubAccountListService.class).setParams();
        return subAccountModelObservable;
    }
}
