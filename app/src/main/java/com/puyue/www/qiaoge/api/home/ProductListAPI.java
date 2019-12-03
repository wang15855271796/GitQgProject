package com.puyue.www.qiaoge.api.home;

import android.content.Context;

import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.home.GetCommonProductModel;
import com.puyue.www.qiaoge.model.home.GetProductListModel;
import com.puyue.www.qiaoge.model.home.ProductNormalModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ${王涛} on 2019/11/4
 */
public class ProductListAPI {
    private interface NormalProductService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.NORMALLIST)
        Observable<ProductNormalModel> getData(@Field("pageNum") int pageNum, @Field("pageSize") int pageSize,
                                               @Field("productType") String productType,
                                               @Field("productName") String productName);
    }

    public static Observable<ProductNormalModel> requestData(Context context, int pageNum, int pageSize, String productType,String productName) {
        NormalProductService service = RestHelper.getBaseRetrofit(context).create(NormalProductService.class);
        return service.getData(pageNum, pageSize,productType,productName);
    }
}
