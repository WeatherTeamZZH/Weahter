package com.ok100.weather.gb.share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ok100.weather.R;
import com.ok100.weather.gb.util.BitmapUtils;


public class WeatherBannerFragment extends BaseFragment {
    private String url;
    private ImageView pic_img;
    private EditText content_et, write_et;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public EditText getContent_et() {
        return content_et;
    }

    public EditText getWrite_et() {
        return write_et;
    }

    public String getUrl() {
        return url;
    }

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

    public static WeatherBannerFragment getInstance(Bitmap bitmap) {
        WeatherBannerFragment weatherBannerFragment = new WeatherBannerFragment();
        weatherBannerFragment.bitmap = bitmap;
        return weatherBannerFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_weather_banner_layout;
    }

    @Override
    public void init(View view) {
        pic_img = view.findViewById(R.id.pic_img);
        content_et = view.findViewById(R.id.content_et);
        write_et = view.findViewById(R.id.write_et);
        TextView tv_mouth = view.findViewById(R.id.tv_mouth);
        TextView tv_city = view.findViewById(R.id.tv_city);
        tv_mouth.setText(TextUtils.isEmpty(BitmapUtils.mouth)?"":BitmapUtils.mouth);
        tv_city.setText(TextUtils.isEmpty(BitmapUtils.city)?"":BitmapUtils.city);

//        pic_img.setImageBitmap(create(url));
        pic_img.setImageBitmap(bitmap);
    }

    public static Bitmap create(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return bitmap;
    }
}
