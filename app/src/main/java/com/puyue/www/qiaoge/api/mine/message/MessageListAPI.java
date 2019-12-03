package com.puyue.www.qiaoge.api.mine.message;

import android.content.Context;

import com.puyue.www.qiaoge.base.BaseModel;
import com.puyue.www.qiaoge.constant.AppInterfaceAddress;
import com.puyue.www.qiaoge.helper.RestHelper;
import com.puyue.www.qiaoge.model.mine.MessageListModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/24.
 */

public class MessageListAPI {
    public interface MessageListService {
        @GET(AppInterfaceAddress.MESSAGE_CENTER)
        Observable<MessageListModel> setParams(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);
    }

    public static Observable<MessageListModel> requestMessageList(Context context, int pageNum, int pageSize) {
        Observable<MessageListModel> messageListObserable = RestHelper.getBaseRetrofit(context).create(MessageListService.class).setParams(pageNum, pageSize);
        return messageListObserable;
    }
}
