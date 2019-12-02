package com.ok100.weather.utils;

import android.app.Activity;
import android.view.View;

/**
 * @Description: This is ActivityBarSetting
 * @Author: ZHANGZH
 * @Time: 2019/11/15 17:40
 * @Email: qq.com
 * @org: OK100
 */
public class ActivityBarSettingUtils {
    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
