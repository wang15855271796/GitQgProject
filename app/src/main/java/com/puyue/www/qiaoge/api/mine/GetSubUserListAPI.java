package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.GetSubUserListModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * If I become novel would you like ?
 * Created by WinSinMin on 2018/4/28.
 */

public class GetSubUserListAPI {
    private interface GetSubUserListService{
        @GET(AppInterfaceAddress.GETSUBUSERLIST)
        Observable<GetSubUserListModel> getData();
    }
    public static Observable<GetSubUserListModel> requestData(Context context){
        GetSubUserListService service = RestHelper.getBaseRetrofit(context).create(GetSubUserListService.class);
        return service.getData();
    }
}
