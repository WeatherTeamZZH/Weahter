package com.ok100.weather.gb.share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.ok100.weather.R;


public class WeatherBannerFragment extends BaseFragment {
    private String url;
    private ImageView pic_img;
    private final static int COMPLETED = 0x11;
    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {//拍照处理
                Bitmap bitmap = (Bitmap) msg.obj;
                pic_img.setImageBitmap(bitmap);
            }
        }
    };


    public static WeatherBannerFragment getInstance(String url) {
        WeatherBannerFragment weatherBannerFragment = new WeatherBannerFragment();
        weatherBannerFragment.url = url;
        return weatherBannerFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weather_banner_layout;
    }

    @Override
    public void init(View view) {
        pic_img = view.findViewById(R.id.pic_img);
        pic_img.setImageBitmap(create(url));
    }

    public static Bitmap create(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return bitmap;
    }
}
