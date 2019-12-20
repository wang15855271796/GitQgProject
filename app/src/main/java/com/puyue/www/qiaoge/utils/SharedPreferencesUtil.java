package com.puyue.www.qiaoge.utils;

import android.content.Context;

public class SharedPreferencesUtil {
	public static final String SAFE_TEXT = "safeText";
	public static final String BALANCE = "balance";
	public static final String BIND_CARD = "bindCard";
	public static final String VERIFY = "verify";
	public static final String PAY_ERRO_COUNT = "payErroCount";
	public static final String QUOTA_URL = "quotaUrl";
	public static final String PAYMENT_URL = "paymentUrl";
	public static final String REALNAME = "realname";
	public static final String SESSION_UPDATE_TIME = "session_update_time"; //session上次更新时间
	public static final String SESSION_EXPIRED_TIME = "expiredTime"; //session剩余时间，单位为秒, 字段名与服务端保持一致
	public static final String DEVICEID = "deviceId";
	public static final String BINDPAYPASSWORD = "bindPayPassword";
	public static final String MOBILE = "mobile";
	public static final String HOTLINE = "hotline";
	public static final String USER_PHOTO = "userphoto_url";
	public static final String USERSTATUS = "userStatus";


	public static String getString(Context context, String key) {
		return context.getSharedPreferences("user", Context.MODE_PRIVATE)
				.getString(key, "");
	}

	public static int getInt(Context context, String key) {
		return context.getSharedPreferences("user", Context.MODE_PRIVATE)
				.getInt(key, 0);
	}

	public static void saveInt(Context context, String key, int value) {
		context.getSharedPreferences("user", Context.MODE_PRIVATE).edit()
				.putInt(key, value).commit();
	}

	public static void saveString(Context context, String key, String value) {
		context.getSharedPreferences("user", Context.MODE_PRIVATE).edit()
				.putString(key, value).commit();
	}

	public static void saveLong(Context context, String key, long value) {
		context.getSharedPreferences("user", Context.MODE_PRIVATE).edit()
				.putLong(key, value).commit();
	}

	public static long getLong(Context context, String key) {
		return context.getSharedPreferences("user", Context.MODE_PRIVATE)
				.getLong(key, -1L);
	}

	public static void saveFloat(Context context, String key, float value) {
		context.getSharedPreferences("user", Context.MODE_PRIVATE).edit()
				.putFloat(key, value).commit();
	}

	public static float getFloat(Context context, String key) {
		return context.getSharedPreferences("user", Context.MODE_PRIVATE)
				.getFloat(key, 0);
	}

	public static void saveBoolean(Context context, String key, Boolean value) {
		context.getSharedPreferences("user", Context.MODE_PRIVATE).edit()
				.putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key) {
		return context.getSharedPreferences("user", Context.MODE_PRIVATE)
				.getBoolean(key, false);
	}

	public static void saveBooleanForAPP(Context context, String key,
			Boolean value) {
		if (context != null){
			context.getSharedPreferences("app", Context.MODE_PRIVATE).edit()
					.putBoolean(key, value).commit();
		}

	}

	public static boolean getBooleanForAPP(Context context, String key) {
		return context.getSharedPreferences("app", Context.MODE_PRIVATE)
				.getBoolean(key, false);
	}

	public static void saveStringForApp(Context context, String key,
			String value) {
		context.getSharedPreferences("app", Context.MODE_PRIVATE).edit()
				.putString(key, value).commit();
	}

	public static String getStringForApp(Context context, String key) {
		return context.getSharedPreferences("app", Context.MODE_PRIVATE)
				.getString(key, "");
	}

	public static void saveIntForApp(Context context, String key,
										int value) {
		context.getSharedPreferences("app", Context.MODE_PRIVATE).edit()
				.putInt(key, value).commit();
	}

	public static int getIntForApp(Context context, String key) {
		return context.getSharedPreferences("app", Context.MODE_PRIVATE)
				.getInt(key, -1);
	}

	public static void clean(Context context) {
		context.getSharedPreferences("user", Context.MODE_PRIVATE).edit()
				.clear().commit();
	}

	public static void cleanForApp(Context context,String key) {
		context.getSharedPreferences("app", Context.MODE_PRIVATE).edit().remove(key)
				.commit();
	}
}
