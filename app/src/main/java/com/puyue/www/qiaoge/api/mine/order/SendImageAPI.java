package com.puyue.www.qiaoge.api.mine.order;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.order.ReturnOrderDetailModel;
import com.puyue.www.qiaoge.model.mine.order.SendImageModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${王文博} on 2019/5/28
 */
public class SendImageAPI {
    public interface SendImageService {
        @Multipart
        @POST(AppInterfaceAddress.UPLOADMESSAGEIMG)
        Observable<SendImageModel> setParams(@Part() List<MultipartBody.Part> parts);
    }

    public static Observable<SendImageModel> requestImgDetail(Context context, List<MultipartBody.Part> parts) {
        Observable<SendImageModel> returnGoodsModelObservable = RestHelper.getBaseRetrofit(context).create(SendImageService.class).setParams(parts);
        return returnGoodsModelObservable;
    }
}
