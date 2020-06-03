package com.puyue.www.qiaoge.api.mine.subaccount;

import android.content.Context;

import com.puyue.www.qiaoge.api.PostLoadAmountAPI;
import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.event.SubAccountListModel;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.AccountDetailModel;
import com.puyue.www.qiaoge.model.mine.MessageModel;


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

    public interface SubAccountAddService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.ADD_SUB_USER)
        Observable<BaseModel> setParams(@Field("phone") String subLoginPhone,
                                        @Field("name") String name,
                                        @Field("pwd") String subLoginPwd,
                                        @Field("verifyCode") String verifyCode,
                                        @Field("inPoint") String inPoint,
                                        @Field("inBalance") String inBalance,
                                        @Field("inGift") String inGift,@Field("amountLimit") String amount_limit,@Field("amount") String amount
                ,@Field("notification") String notification,@Field("warnAmount") String warnAmount);
    }

    public static Observable<BaseModel> requestAddSubAccount(Context context, String phone, String name, String pwd, String verifyCode,String inPoint,String inBalance,String inGift,String amountLimit,String amount,String notification,String warnAmount) {
        Observable<BaseModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(SubAccountAddService.class).setParams(phone, name, pwd,verifyCode,inPoint,inBalance,inGift,amountLimit,amount,notification,warnAmount);
        return addSubAccountObservable;
    }


    /**
     * 编辑子账户
     */
    public interface EditService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.Edit_SUB_USER)
        Observable<BaseModel> setParams(@Field("subId") String subId,
                                        @Field("inPoint") String inPoint,
                                        @Field("inBalance") String inBalance,
                                        @Field("inGift") String inGift,@Field("amountLimit") String amount_limit,@Field("amount") String amount
                ,@Field("notification") String notification,@Field("warnAmount") String warnAmount);
    }

    public static Observable<BaseModel> editAccount(Context context, String subId,String inPoint,String inBalance,String inGift,String amountLimit,String amount,String notification,String warnAmount) {
        Observable<BaseModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(EditService.class).setParams(subId,inPoint,inBalance,inGift,amountLimit,amount,notification,warnAmount);
        return addSubAccountObservable;
    }



    /**
     * 子账户详情
     */
    public interface SubService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.GET_SUB_ACCOUNT)
        Observable<AccountDetailModel> setParams(@Field("subId") String subId);
    }

    public static Observable<AccountDetailModel> getSubAccount(Context context, String subId) {
        Observable<AccountDetailModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(SubService.class).setParams(subId);
        return addSubAccountObservable;
    }

    /**
     * 删除子账户
     */

    public interface DeleteService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.DELETE_SUB_USER)
        Observable<AccountDetailModel> setParams(@Field("subId") String subId);
    }

    public static Observable<AccountDetailModel> deleteAccount(Context context, String subId) {
        Observable<AccountDetailModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(DeleteService.class).setParams(subId);
        return addSubAccountObservable;
    }


    /**
     * 子账户列表
     */

    public interface ListService {
        @FormUrlEncoded
        @POST(AppInterfaceAddress.Sub_Account_list)
        Observable<SubAccountListModel> setParams(@Field("pageNum") int pageNum,@Field("pageSize") int pageSize);
    }

    public static Observable<SubAccountListModel> listAccount(Context context, int pageNum,int pageSize) {
        Observable<SubAccountListModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(ListService.class).setParams(pageNum,pageSize);
        return addSubAccountObservable;
    }

    /**
     * 子账户已读消息
     */
    private interface MessageReadService {

        @POST(AppInterfaceAddress.Sub_Account_Message)
        Observable<BaseModel> setParams();
    }

    public static Observable<BaseModel> read(Context context) {
        MessageReadService service = RestHelper.getBaseRetrofit(context).create(MessageReadService.class);
        return service.setParams();
    }

    /**
     *获取未读消息数量
     */

    private interface MessageUnReadService {

        @POST(AppInterfaceAddress.Message_Unread)
        Observable<MessageModel> setParams();
    }

    public static Observable<MessageModel> unRead(Context context) {
        MessageUnReadService service = RestHelper.getBaseRetrofit(context).create(MessageUnReadService.class);
        return service.setParams();
    }

    /**
     * 子账号消息 - 消息设为已读
     */
    private interface MessageReadedService {

        @FormUrlEncoded
        @POST(AppInterfaceAddress.Message_Read)
        Observable<MessageModel> setParams(@Field("id") int id);
    }

    public static Observable<MessageModel> readed(Context context, int id) {
        Observable<MessageModel> addSubAccountObservable = RestHelper.getBaseRetrofit(context).create(MessageReadedService.class).setParams(id);
        return addSubAccountObservable;
    }

}
