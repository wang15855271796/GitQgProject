package com.puyue.www.qiaoge.helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Created by GuoGai on 2016/7/26.
 */
public class DeviceHelper {
    public static final String DEVICEID = "869677021716155";
    /**
     * 获取DeviceId（需要添加"android.permission.READ_PHONE_STATE"权限）
     *
     * @param context
     * @return DeviceId
     */
    public static String getDeviceId(Context context) {
        String deviceId = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(deviceId) || deviceId.equals("9774d56d682e549c")) {
            //当用户无法获取deviceId时,使用自己写成的固定的
//            UUID uuid = UUID.randomUUID();
//            String uniqueId = uuid.toString();
//            deviceId = uniqueId.replace("-", "");
            deviceId = DEVICEID;
        }
        return deviceId;
    }

    public static void copyText(Context context, String text) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(text);
            } else {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));
            }
        } catch (Exception ex) {
            //Issue: Copy crash in Android 4.3 when clipboard listener attached
        }
    }
}
