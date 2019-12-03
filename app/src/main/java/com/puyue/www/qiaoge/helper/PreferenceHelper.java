package com.puyue.www.qiaoge.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GuoGai on 2016/12/22.
 */
public class PreferenceHelper {
    public PreferenceHelper() {
    }

    public static void saveData(Context context, String name, String key, String content) {
        SharedPreferences preferences = context.getSharedPreferences(name, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, content);
        editor.commit();
    }

    public static String getData(Context context, String name, String key) {
        SharedPreferences preferences = context.getSharedPreferences(name, 0);
        return preferences.getString(key, (String) null);
    }

    public static void saveData(Context context, String name, String key, boolean content) {
        SharedPreferences preferences = context.getSharedPreferences(name, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, content);
        editor.commit();
    }

    public static boolean getData(Context context, String name, String key, boolean defValue) {
        SharedPreferences preferences = context.getSharedPreferences(name, 0);
        return preferences.getBoolean(key, defValue);
    }

    public static void clearData(Context context, String name, String key) {
        SharedPreferences preferences = context.getSharedPreferences(name, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, "");
        editor.commit();
    }

    public static void clearData(Context context, String name) {
        SharedPreferences preferences = context.getSharedPreferences(name, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
