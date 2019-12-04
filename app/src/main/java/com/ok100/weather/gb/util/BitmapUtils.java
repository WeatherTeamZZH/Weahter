package com.ok100.weather.gb.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ok100.weather.R;
import com.ok100.weather.gb.share.ImageAdapterHView;
import com.ok100.weather.gb.share.UIUtil;
import com.ok100.weather.gb.stickercamera.app.camera.ui.CameraActivity;

public class BitmapUtils {

    //todo  view生成bitmap

    public static Bitmap getViewBitmap(View comBitmap, int width, int height) {
        Bitmap bitmap = null;
        if (comBitmap != null) {
            comBitmap.clearFocus();
            comBitmap.setPressed(false);

            boolean willNotCache = comBitmap.willNotCacheDrawing();
            comBitmap.setWillNotCacheDrawing(false);

            // Reset the drawing cache background color to fully transparent
            // for the duration of this operation
            int color = comBitmap.getDrawingCacheBackgroundColor();
            comBitmap.setDrawingCacheBackgroundColor(0);
            float alpha = comBitmap.getAlpha();
            comBitmap.setAlpha(1.0f);

            if (color != 0) {
                comBitmap.destroyDrawingCache();
            }

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            comBitmap.measure(widthSpec, heightSpec);
            comBitmap.layout(0, 0, width, height);

            comBitmap.buildDrawingCache();
            Bitmap cacheBitmap = comBitmap.getDrawingCache();
            if (cacheBitmap == null) {
                Log.e("view.ProcessImageToBlur", "failed getViewBitmap(" + comBitmap + ")", new RuntimeException());
                return null;
            }
            bitmap = Bitmap.createBitmap(cacheBitmap);
            // Restore the view
            comBitmap.setAlpha(alpha);
            comBitmap.destroyDrawingCache();
            comBitmap.setWillNotCacheDrawing(willNotCache);
            comBitmap.setDrawingCacheBackgroundColor(color);
        }
        return bitmap;
    }

    //todo 根据不可见view生成bitmap
    public static Bitmap getBitmap(Activity activity) {
        Bitmap bitmap = null;
        LayoutInflater factorys = LayoutInflater.from(activity);
        final View textEntryView = factorys.inflate(R.layout.ll_bitmap, null);
        textEntryView.setDrawingCacheEnabled(true);
        textEntryView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        textEntryView.layout(0, 0, textEntryView.getMeasuredWidth(), textEntryView.getMeasuredHeight());
        bitmap = Bitmap.createBitmap(textEntryView.getDrawingCache());
        textEntryView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    //todo 合并图片
    public static Bitmap combineBitmap(Bitmap background, Bitmap foreground) {
        if (background == null) {
            return null;
        }
        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        int fgWidth = foreground.getWidth();
        int fgHeight = foreground.getHeight();
        Bitmap newmap = Bitmap
                .createBitmap(bgWidth, bgHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newmap);
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(foreground, (bgWidth - fgWidth) / 2,
                (bgHeight - fgHeight) / 2, null);
        canvas.save();
        canvas.restore();
        return newmap;
    }

    public static Bitmap create(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return bitmap;
    }

    //todo  生成bitmap的标签
    public static Bitmap productLabelBit(Activity activity) {
        View view=View.inflate(activity,R.layout.ll_bitmap,null);
        LinearLayout child_ll=view.findViewById(R.id.child_ll);
        Bitmap viewBitmap = BitmapUtils.getViewBitmap(child_ll, UIUtil.dip2px(activity, 140), UIUtil.dip2px(activity, 180));
         return  viewBitmap;
    }

    public  static Bitmap mergeBitmap(Bitmap firstBitmap, Bitmap secondBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth(), firstBitmap.getHeight(),
                firstBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(firstBitmap, new Matrix(), null);
        canvas.drawBitmap(secondBitmap, 0, 0, null);
        return bitmap;
    }

    //这是一种模板  不需要合并图片   会出现 有些标签大小不一
    public static Bitmap productPic(Bitmap b,Activity activity,int width,int heigth) {
        View view = View.inflate(activity, R.layout.pro_pic_layout, null);
        ImageAdapterHView pic_img = view.findViewById(R.id.pic_img);
        pic_img.setImageBitmap(b);
        TextView tv_weather = view.findViewById(R.id.tv_weather);
        TextView tv_city = view.findViewById(R.id.tv_city);
        TextView tv_temp = view.findViewById(R.id.tv_temp);
        if(!TextUtils.isEmpty(temp)){
            tv_weather.setText(temp);
        }
        if(!TextUtils.isEmpty(city)){
            tv_city.setText(city);
        }
        if(!TextUtils.isEmpty(weather)){
            tv_temp.setText(weather);
        }
        Bitmap bmp1 = BitmapUtils.getViewBitmap(view, width, heigth);

        return bmp1;
    }

    public static String mouth,week,nongli,weather,temp ,city;
}
