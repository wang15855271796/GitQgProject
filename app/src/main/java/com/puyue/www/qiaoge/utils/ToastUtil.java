package com.puyue.www.qiaoge.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ${王涛} on 2019/12/4
 */
public class ToastUtil {
    public static void showSuccessMsg(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) return;
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
