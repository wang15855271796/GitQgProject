package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetEquipmentCommentListByPageModel;

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

public class GetEquipmentCommentListByPageAPI {
    private interface GetEquipmentCommentListByPageService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GETEQUIPMENTCOMMENTLISTBYPAGE)
        Observable<GetEquipmentCommentListByPageModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize, @Field("equipmentId") int equipmentId);
    }

    public static Observable<GetEquipmentCommentListByPageModel> requestData(Context context, int pageNum, int pageSize, int equipmentId) {
        GetEquipmentCommentListByPageService service = RestHelper.getBaseRetrofit(context).create(GetEquipmentCommentListByPageService.class);
        return service.getData(pageNum, pageSize, equipmentId);
    }
}
