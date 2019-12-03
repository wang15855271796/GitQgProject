package com.puyue.www.qiaoge.listener;

import android.view.View;

/**
 * Created by GuoGai on 2016/10/31.
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {

    private long mMillis;
    private int mViewID;
    // default click interval 500ms
    private int mInterval = 500;

    public NoDoubleClickListener() {
    }

    public NoDoubleClickListener(int interval) {
        mInterval = interval;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mViewID) {
            if (System.currentTimeMillis() - mMillis > mInterval) {
                onNoDoubleClick(v);
                mMillis = System.currentTimeMillis();
                mViewID = v.getId();
            }
        } else {
            mMillis = System.currentTimeMillis();
            mViewID = v.getId();
            onNoDoubleClick(v);
        }
    }

    public void setInterval(int interval) {
        mInterval = interval;
    }

    public abstract void onNoDoubleClick(View view);

}