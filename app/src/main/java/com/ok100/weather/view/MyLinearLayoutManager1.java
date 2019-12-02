package com.ok100.weather.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * @Description: This is MyLinearLayoutManager
 * @Author: ZHANGZH
 * @Time: 2019/11/8 16:03
 * @Email: qq.com
 * @org: OK100
 */
public class MyLinearLayoutManager1 extends LinearLayoutManager {

    public MyLinearLayoutManager1(Context context) {
        super(context, VERTICAL, false);
    }

    public MyLinearLayoutManager1(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }




}
