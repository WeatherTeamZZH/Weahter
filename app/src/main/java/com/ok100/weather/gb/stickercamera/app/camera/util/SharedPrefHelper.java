package com.ok100.weather.gb.stickercamera.app.camera.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {

    private static final String spFileName = "wtr";//SharedPreference的文件名

    /**
     * 获取SharedPreference文件中指定字段
     */
    public static Boolean getBoolean(Context context, String field) {
        //一个MODE常量MODE_PRIVATE表示私有，其他应用不能访问
        return context.getSharedPreferences(spFileName, 0).getBoolean(field, false);
    }

    /**
     * 获取SharedPreference文件中指定字段
     *
     * @param field    指定字段
     * @param defValue 默认
     */
    public static Boolean getBoolean(Context context, String field, Boolean defValue) {
        return context.getSharedPreferences(spFileName, 0).getBoolean(field, defValue);
    }

    /**
     * 写入SharedPreference文件中指定字段的
     *
     * @param context
     * @param field   指定字段
     * @param value   要写入的新
     */
    public static void putBoolean(Context context, String field, Boolean value) {
        SharedPreferences.Editor localEditor = context.getSharedPreferences(spFileName, 0).edit();
        localEditor.putBoolean(field, value);
        localEditor.commit();
    }

    /**
     * 获取SharedPreference文件中指定字段的
     *
     * @param paramContext
     * @param paramString  指定字段
     * @return
     */
    public static String getString(Context paramContext, String paramString) {
        return paramContext.getSharedPreferences(spFileName, 0).getString(
                paramString, "");
    }

    /**
     * 获取SharedPreference文件中指定字段的
     *
     * @param paramContext
     * @param paramString1 指定字段
     * @param paramString2 设置默认
     * @return
     */
    public static String getString(Context paramContext, String paramString1,
                                   String paramString2) {
        return paramContext.getSharedPreferences(spFileName, 0).getString(
                paramString1, paramString2);
    }

    /**
     * 写入SharedPreference文件中指定字段的
     *
     * @param paramContext
     * @param paramString1 指定字段
     * @param paramString2 要写入的
     */
    public static void putString(Context paramContext, String paramString1,
                                 String paramString2) {
        SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(spFileName, 0).edit();
        localEditor.putString(paramString1, paramString2);
        localEditor.commit();
    }

    public static void putInt(Context paramContext, String paramString1,
                              int paramString2) {
        SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(spFileName, 0).edit();
        localEditor.putInt(paramString1, paramString2);
        localEditor.commit();
    }

    public static int getInt(Context paramContext, String paramString1,
                             int paramString2) {
        return paramContext.getSharedPreferences(spFileName, 0).getInt(
                paramString1, paramString2);
    }

    /**
     * @param paramContext
     * @param paramString1 要删除的指定字段
     */
    public static void removedString(Context paramContext, String paramString1) {
        SharedPreferences.Editor localEditor = paramContext
                .getSharedPreferences(spFileName, 0).edit();
        localEditor.remove(paramString1);
        localEditor.commit();
    }

    public static void removeAll(Context paramContext) {
        SharedPreferences.Editor localEditor = paramContext
                .getSharedPreferences(spFileName, 0).edit();
        localEditor.clear();
        localEditor.commit();
    }
}
