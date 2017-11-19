package com.example.halong.myapplication.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by halong on 2017/11/17.
 */

public class SharedPreferencesUtil {
    private static SharedPreferences sharedPreferences;

    private SharedPreferencesUtil() {
    }

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences("mytest", Context.MODE_PRIVATE);
    }

    public static void saveString(String key, String s) {
        sharedPreferences.edit().putString(key, s).commit();
    }

    public static String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public static void saveInt(String key, int i) {
        sharedPreferences.edit().putInt(key, i);
    }


    public static int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

}
