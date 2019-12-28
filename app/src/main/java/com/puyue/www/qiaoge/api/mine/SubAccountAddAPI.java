package com.puyue.www.qiaoge.api.mine;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.AccountDetailModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/20.
 */

public class SubAccountAddAPI {
    /**
     * 添加子账户
     */

//    public interface SubAccountAddService {
//        @FormUrlEncoded
//        @POST(AppInterfaceAddress.ADD_SUB_USER)
//        Observable<BaseModel> setParams(@Field("subLoginPhone") String subLoginPhone,
//                                        @Field("name") String subLoginUserName,
//                                        @Field("subLoginPwd") String subLoginPwd,
//                                        @Field("verifyCode") String verifyCode,
//                                        @Field("inPoint") String inPoint,
//                                        @Field("inBalance") String inBalance,
//                                        @Field("inGift") String inGift);
//    }
//
//    public static Observable<BaseModel> requestAddSubAccount(Context context, String subLoginPhone, String subLoginUserName, String subLoginPwd, String verifyCode,String inPoint,String inBalance,String inGift) {
//        Observable<BaseModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(SubAccountAddService.class).setParams(subLoginPhone, subLoginUserName, subLoginPwd,verifyCode,inPoint,inBalance,inGift);
//        return addSubAccountObservable;
//    }
//
//
//    /**
//     * 编辑子账户
//     */
//    public interface EditService {
//        @FormUrlEncoded
//        @POST(AppInterfaceAddress.Edit_SUB_USER)
//        Observable<BaseModel> setParams(@Field("verifyCode") String subId,
//                                        @Field("inPoint") String inPoint,
//                                        @Field("inBalance") String inBalance,
//                                        @Field("inGift") String inGift);
//    }
//
//    public static Observable<BaseModel> editAccount(Context context, String subId,String inPoint,String inBalance,String inGift) {
//        Observable<BaseModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(EditService.class).setParams(subId,inPoint,inBalance,inGift);
//        return addSubAccountObservable;
//    }
//
//
//
//    /**
//     * 子账户详情
//     */
//    public interface SubService {
//        @FormUrlEncoded
//        @POST(AppInterfaceAddress.GET_SUB_ACCOUNT)
//        Observable<AccountDetailModel> setParams(@Field("subId") String subId);
//    }
//
//    public static Observable<AccountDetailModel> getSubAccount(Context context, String subId) {
//        Observable<AccountDetailModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(SubService.class).setParams(subId);
//        return addSubAccountObservable;
//    }
//
//    /**
//     * 删除子账户
//     */
//
//    public interface DeleteService {
//        @FormUrlEncoded
//        @POST(AppInterfaceAddress.DELETE_SUB_USER)
//        Observable<AccountDetailModel> setParams(@Field("subId") String subId);
//    }
//
//    public static Observable<AccountDetailModel> deleteAccount(Context context, String subId) {
//        Observable<AccountDetailModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(DeleteService.class).setParams(subId);
//        return addSubAccountObservable;
//    }
}
