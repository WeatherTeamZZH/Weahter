package com.ok100.weather.gb.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ok100.weather.R;
import com.ok100.weather.gb.adepter.BitmapFragmentpageAdepter;
import com.ok100.weather.gb.share.UIUtil;
import com.ok100.weather.gb.share.WeatherBannerFragment;
import com.ok100.weather.gb.share.transformer.ScaleInTransformer;
import com.ok100.weather.gb.stickercamera.app.camera.CameraManager;
import com.ok100.weather.gb.util.BitmapUtils;
import com.ok100.weather.gb.util.FileUtils;
import com.ok100.weather.gb.util.ImageUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Fragment> mFragment = new ArrayList<>();
    private ViewPager share_vp;
    private BitmapFragmentpageAdepter bitmapFragmentpageAdepter = null;
    private DisplayMetrics metric = new DisplayMetrics();
    private List<Bitmap> bitmaps = new ArrayList<>();
    private int heigth, width;

    private View shareView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        EventBus.getDefault().register(this);
        //天气温度
        BitmapUtils.weather =  getIntent().getStringExtra("weather");
        BitmapUtils.temp =getIntent().getStringExtra("temp");
        BitmapUtils.city =  getIntent().getStringExtra("city");
        BitmapUtils.week = getIntent().getStringExtra("week");
        BitmapUtils.mouth = getIntent().getStringExtra("mouth");

        getWindowManager().getDefaultDisplay().getMetrics(metric);
        initView();
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        width = outMetrics.widthPixels;
        heigth = outMetrics.heightPixels;
        getLocalListData();

        shareView = View.inflate(this, R.layout.share_out_layout, null);


    }

    private void initView() {
        findViewById(R.id.cance_rela).setOnClickListener(this);
        findViewById(R.id.exchange_album_rela).setOnClickListener(this);
        findViewById(R.id.wx_friend_img).setOnClickListener(this);
        findViewById(R.id.wx_img).setOnClickListener(this);
        findViewById(R.id.qq_img).setOnClickListener(this);
        findViewById(R.id.wb_img).setOnClickListener(this);
        share_vp = findViewById(R.id.share_vp);

        setPagerMargin(UIUtil.dip2px(this, 35),
                UIUtil.dip2px(this, 10));

        share_vp.setPageTransformer(true, new ScaleInTransformer());
        bitmapFragmentpageAdepter = new BitmapFragmentpageAdepter(getSupportFragmentManager(), mFragment);
        share_vp.setAdapter(bitmapFragmentpageAdepter);
    }

    @SuppressLint("CheckResult")
    private void request(int type) {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.requestEach(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) {
                        if (permission.granted) {
                            share(type);
                        }
                    }
                });
    }

    private void getLocalListData() {

        Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), R.mipmap.share_bg1);
        Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.mipmap.share_bg2);
        Bitmap bmp3 = BitmapFactory.decodeResource(getResources(), R.mipmap.share_bg3);
        Bitmap bmp4 = BitmapFactory.decodeResource(getResources(), R.mipmap.share_bg4);
        Bitmap bmp5 = BitmapFactory.decodeResource(getResources(), R.mipmap.share_bg5);
        Bitmap bmp6 = BitmapFactory.decodeResource(getResources(), R.mipmap.share_bg6);
        Bitmap bmp7 = BitmapFactory.decodeResource(getResources(), R.mipmap.share_bg7);
        Bitmap bmp8 = BitmapFactory.decodeResource(getResources(), R.mipmap.share_bg8);
        Bitmap bmp9 = BitmapFactory.decodeResource(getResources(), R.mipmap.share_bg9);

        bitmaps.add(BitmapUtils.productPic(bmp1, HomeActivity.this, width, heigth));
        bitmaps.add(BitmapUtils.productPic(bmp2, HomeActivity.this, width, heigth));
        bitmaps.add(BitmapUtils.productPic(bmp3, HomeActivity.this, width, heigth));
        bitmaps.add(BitmapUtils.productPic(bmp4, HomeActivity.this, width, heigth));
        bitmaps.add(BitmapUtils.productPic(bmp5, HomeActivity.this, width, heigth));
        bitmaps.add(BitmapUtils.productPic(bmp6, HomeActivity.this, width, heigth));
        bitmaps.add(BitmapUtils.productPic(bmp7, HomeActivity.this, width, heigth));
        bitmaps.add(BitmapUtils.productPic(bmp8, HomeActivity.this, width, heigth));
        bitmaps.add(BitmapUtils.productPic(bmp9, HomeActivity.this, width, heigth));

        for (int i = 0; i < bitmaps.size(); i++) {
            mFragment.add(WeatherBannerFragment.getInstance(bitmaps.get(i)));
        }

        bitmapFragmentpageAdepter.notifyDataSetChanged();

    }

    public void setPagerMargin(int viewMargin, int pagerMargin) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) share_vp.getLayoutParams();
        layoutParams.setMargins(viewMargin, 0, viewMargin, 0);
        share_vp.setPageMargin(pagerMargin);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.cance_rela:
                finish();
                break;
            case R.id.wx_friend_img://微信朋友圈
                request(0);
                break;

            case R.id.wx_img://微信
                request(1);
                break;

            case R.id.qq_img://qq
                request(2);
                break;

            case R.id.wb_img://wb
                request(3);
                break;

            case R.id.exchange_album_rela:
                requestLocation();

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(Bitmap bitmap) {
        mFragment.set(share_vp.getCurrentItem(), WeatherBannerFragment.getInstance(bitmap));
        bitmapFragmentpageAdepter.notifyDataSetChanged();
    }

    //todo 分享
    private void share(int type) {

        ShareAction shareAction = new ShareAction(HomeActivity.this);
        if (type == 0) {//微信朋友圈
            shareAction.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE);//传入平台
            shareViewBit(shareAction, share_vp.getCurrentItem());
        } else if (type == 1) {//微信
            shareAction.setPlatform(SHARE_MEDIA.WEIXIN);//
            shareViewBit(shareAction, share_vp.getCurrentItem());
        } else if (type == 2) {//QQ
            shareAction.setPlatform(SHARE_MEDIA.QQ);//
            shareViewBit(shareAction, share_vp.getCurrentItem());
        } else if (type == -1) {
            getLocalListData();
        } else {//wb
            shareAction.setPlatform(SHARE_MEDIA.SINA);//
            shareViewBit(shareAction, share_vp.getCurrentItem());
        }

    }

    //todo 分享图片view
    private void shareViewBit(ShareAction shareAction, int position) {

        WeatherBannerFragment weatherBannerFragment = (WeatherBannerFragment) mFragment.get(position);
        if (TextUtils.isEmpty(weatherBannerFragment.getContent_et().getText().toString().trim())) {
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        ImageView pic_img = shareView.findViewById(R.id.pic_img);
        EditText content_et = shareView.findViewById(R.id.content_et);
        EditText write_et = shareView.findViewById(R.id.write_et);

//        String url = weatherBannerFragment.getUrl();
        Bitmap bitmap = weatherBannerFragment.getBitmap();
//        pic_img.setImageBitmap(BitmapUtils.create(url));
        pic_img.setImageBitmap(bitmap);

        String et = weatherBannerFragment.getContent_et().getText().toString().trim();
        String et1 = weatherBannerFragment.getWrite_et().getText().toString().trim();
        content_et.setText(et);
        write_et.setText(et1);

        Bitmap bmp1 = BitmapUtils.getViewBitmap(shareView, width, heigth);

        UMImage image = new UMImage(HomeActivity.this, bmp1);//bitmap文件
        shareAction.withMedia(image);
        shareAction.setCallback(shareListener);//回调监听器
        shareAction.share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(HomeActivity.this, "分享成功", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(HomeActivity.this, "分享失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(HomeActivity.this, "取消分享", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("CheckResult")
    private void requestLocation() {
        List<String> stringList = new ArrayList<>();
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.requestEach(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) {
                        if (permission.granted) {
                            stringList.add("1");
                        } else {

                        }
                    }
                });
        if (stringList.size() >= 3) {
            String wetaher = getIntent().getStringExtra("weather");
            String temp = getIntent().getStringExtra("temp");
            String city = getIntent().getStringExtra("city");
            CameraManager.getInst().openCamera(HomeActivity.this,TextUtils.isEmpty(temp)?"":temp,TextUtils.isEmpty(wetaher)?"":wetaher,TextUtils.isEmpty(city)?"":city);
        }
    }

}

