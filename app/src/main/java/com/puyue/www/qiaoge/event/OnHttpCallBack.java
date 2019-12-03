package com.puyue.www.qiaoge.event;

/**
 * Created by win7 on 2018/7/24.
 */

public interface OnHttpCallBack<T> {
    void onSuccessful(T t);
    void onFaild(String errorMsg);
}