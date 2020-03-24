package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.UpdateUserInvitationModel;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by win7 on 2018/7/9.
 */

public class UpdateUserInvitationAPI {
    private interface UpdateUserInvitationService {
        @POST(AppInterfaceAddress.UPDATEUSERINVITATION)
        Observable<UpdateUserInvitationModel> setParams(@Query("invitationCode") String invitationCode,
                                                        @Query("shopTypeId") int shopTypeId);
    }

    public static Observable<UpdateUserInvitationModel> requestData(Context context, String invitationCode, int shopTypeId) {
        UpdateUserInvitationService service = RestHelper.getBaseRetrofit(context).create(UpdateUserInvitationService.class);
        return service.setParams(invitationCode,shopTypeId);
    }
}
