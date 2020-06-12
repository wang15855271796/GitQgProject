package com.puyue.www.qiaoge.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.puyue.www.qiaoge.R;

/**
 * Created by ${王涛} on 2020/6/11
 */
public class TestDialog extends Dialog {
    public TestDialog(Context mContext) {
        super(mContext, R.style.promptDialog);
        setContentView(R.layout.test2);
    }
}
