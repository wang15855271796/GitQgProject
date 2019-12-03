package com.puyue.www.qiaoge.listener;

import android.view.View;

/**
 * TODO 把每次的验证码都传出去
 * Created by GuoGai on 2016/12/26.
 */
public abstract class AuthCodeClickListener implements View.OnClickListener {
    private String mAuthCode;

    public AuthCodeClickListener() {

    }


    @Override
    public void onClick(View view) {
        AuthCodeClick(view, mAuthCode);
    }

    public abstract void AuthCodeClick(View view, String authCode);

    private void setAuthCode() {

    }
}
