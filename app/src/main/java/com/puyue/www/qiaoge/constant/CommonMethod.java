package com.puyue.www.qiaoge.constant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.puyue.www.qiaoge.model.UserModel;


import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("deprecation")
public class CommonMethod {
    /*
     * 获取用户信息
     */
    public static UserModel getUserModel(Context context) {
        try {
            return (UserModel) CommonMethod.getObject(context, UserModel.class, AppConstant.USERMODEL);
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * 获取网络连接类型(wifi或者移动网络)
     */
    public static String getNetType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            if (activeInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return "wifi";
            } else if (activeInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return "mobile";
            } else {
                return "";
            }
        }
        return null;
    }

    /*
     * 获取本机IP地址
     */
    public static String getIpAdress(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiNetworkInfo.isConnected()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            String ip = (ipAddress & 0xFF) + "." + ((ipAddress >> 8) & 0xFF) + "." + ((ipAddress >> 16) & 0xFF) + "."
                    + (ipAddress >> 24 & 0xFF);
            return ip;
        } else if (wifiNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            return inetAddress.getHostAddress().toString();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /*
     * 获取APP版本号
     */
    public static String getVersionInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return String.valueOf(packInfo.versionCode);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 获取版本名称
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 获取本机的IMEI
     */
    public static String getIMEI(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = mTelephonyMgr.getDeviceId();
        if (TextUtils.isEmpty(imei)) {
            return null;
        } else {
            return imei;
        }
    }

    /*
     * 获取Mac address
     */
    public static String getMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getMacAddress();
    }

    /*
     * 获取设备id
     */
    public static String getDeviceId(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String uuid;
            String deviceID = null;
            String simID = null;
            String androidID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
            try {
                deviceID = tm.getDeviceId();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                simID = tm.getSimSerialNumber();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if ("9774d56d682e549c".equals(androidID) || androidID == null) {
                androidID = "";
            }

            if (deviceID == null) {
                deviceID = "";
            }

            if (simID == null) {
                simID = "";
            }

            uuid = androidID + deviceID + simID;
            uuid = String.format("%32s", uuid).replace(' ', '0');
            uuid = uuid.substring(0, 32);
            return uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * 是否满足正则表达式
     */
    public static Boolean isFormatValid(String pattern, String value) {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.matches()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * 格式化金额
     */
    public static String getBigDecimal(Object value) {
        BigDecimal bigDecimal = null;
        try {
            if (value instanceof String) {
                bigDecimal = new BigDecimal(value.toString());
            } else if (value instanceof Double) {
                bigDecimal = new BigDecimal(String.valueOf(value));
            }
            NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CHINA);// 建立货币格式化引用
            currency.setMaximumFractionDigits(2);
            return currency.format(bigDecimal).replace("￥", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (String) value;
    }

    /*
     * 判断sd卡是否挂载
     */
    public static boolean isSdCardMounted() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 创建文件
     */
    public static File createDIR(String dirpath) {
        File dir = null;
        try {
            if (isSdCardMounted()) {
                dir = new File(Environment.getExternalStorageDirectory() + "/" + dirpath);
                if (!dir.exists()) {
                    dir.mkdir();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dir;
    }

    /*
     * 根据名称获取class
     */
    public static Class<?> getClass(String className) {
        Class<?> c = null;
        if (className != null) {
            try {
                c = Class.forName(className);
                return c;
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
        return null;
    }

    /*
     * 获取SharedPreferences
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        if (AppConstant.preferences == null) {
            AppConstant.preferences = context.getSharedPreferences("dz", Context.MODE_PRIVATE);
        }
        return AppConstant.preferences;
    }

    /*
     * 存储对象
     */
    public static void saveObject(Context context, Object object, String key) {
        Editor prefsEditor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        prefsEditor.putString(key, json);
        prefsEditor.commit();
    }

    /*
     * 获取对象
     */
    public static Object getObject(Context context, Class<?> type, String key) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(key, null);
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }

    public static void saveData(String key, String value, Context context) {
        Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveData(String key, int value, Context context) {
        Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public static void removeData(String key, Context context) {
        Editor editor = getSharedPreferences(context).edit();
        editor.remove(key);
        editor.commit();
    }

    public static String getData(String key, String defaultValue, Context context) {
        return getSharedPreferences(context).getString(key, defaultValue);
    }

    public static int getData(String key, int defaultValue, Context context) {
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static void saveBoolData(String key, Boolean value, Context context) {
        Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static Boolean getBoolData(String key, Boolean defaultValue, Context context) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    /**
     * 将String型格式化,比如想要将2011-11-11格式化成2011年11月11日,就StringPattern("2011-11-11",
     * "yyyy-MM-dd","yyyy年MM月dd日").
     *
     * @param date       String 想要格式化的日期
     * @param oldPattern String 想要格式化的日期的现有格式
     * @param newPattern String 想要格式化成什么格式
     * @return String
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFormatDate(String date, String oldPattern, String newPattern) {
        if (date == null || date.equals("null") || oldPattern == null || newPattern == null)
            return "";
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern); // 实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern); // 实例化模板对象
        Date d = null;
        try {
            d = sdf1.parse(date); // 将给定的字符串中的日期提取出来
        } catch (Exception e) { // 如果提供的字符串格式有错误，则进行异常处理
            return date;
        }
        try {
            return sdf2.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
