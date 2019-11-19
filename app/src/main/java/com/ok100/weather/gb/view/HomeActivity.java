package com.ok100.weather.gb.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ok100.weather.R;
import com.ok100.weather.gb.adepter.BitmapFragmentpageAdepter;
import com.ok100.weather.gb.share.UIUtil;
import com.ok100.weather.gb.share.WeatherBannerFragment;
import com.ok100.weather.gb.share.transformer.ScaleInTransformer;
import com.ok100.weather.gb.stickercamera.app.camera.CameraManager;
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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Bitmap> urlBeanList = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();
    private ViewPager share_vp;
    private BitmapFragmentpageAdepter bitmapFragmentpageAdepter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
        findViewById(R.id.cance_rela).setOnClickListener(this);
        findViewById(R.id.exchange_album_rela).setOnClickListener(this);
        findViewById(R.id.wx_friend_img).setOnClickListener(this);
        findViewById(R.id.wx_img).setOnClickListener(this);
        findViewById(R.id.qq_img).setOnClickListener(this);
        findViewById(R.id.wb_img).setOnClickListener(this);
        share_vp = findViewById(R.id.share_vp);
    }

    @SuppressLint("CheckResult")
    private void request(int type) {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.requestEach( Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) {
                        if (permission.granted) {
                            share(type);
                        }
                    }
                });
    }

    private void initData() {

        Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg);
        Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg1);
        Bitmap bmp3 = BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg2);
        urlBeanList.add(bmp1);
        urlBeanList.add(bmp2);
        urlBeanList.add(bmp3);
        urlBeanList.add(bmp1);
        urlBeanList.add(bmp2);
        urlBeanList.add(bmp3);

        for (Bitmap s : urlBeanList) {
            mFragment.add(WeatherBannerFragment.getInstance(s));
        }

        setPagerMargin(UIUtil.dip2px(this, 35),
                UIUtil.dip2px(this, 10));

        share_vp.setPageTransformer(true, new ScaleInTransformer());
        bitmapFragmentpageAdepter = new BitmapFragmentpageAdepter(getSupportFragmentManager(), mFragment);
        share_vp.setAdapter(bitmapFragmentpageAdepter);
    }

    public void setPagerMargin(int viewMargin, int pagerMargin) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) share_vp.getLayoutParams();
        layoutParams.setMargins(viewMargin, 0, viewMargin, 0);
        share_vp.setPageMargin(pagerMargin);
    }

    public static Bitmap create(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        return bitmap;
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
                CameraManager.getInst().openCamera(HomeActivity.this);
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
        mFragment.add(0, WeatherBannerFragment.getInstance(bitmap));
        bitmapFragmentpageAdepter.notifyDataSetChanged();
        bitmapFragmentpageAdepter.getCount();
        share_vp.setCurrentItem(0);
    }

    //todo 分享
    private void share(int type) {
        Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg);
        UMImage image = new UMImage(HomeActivity.this, bmp1);//bitmap文件
        ShareAction shareAction = new ShareAction(HomeActivity.this);
        if (type == 0) {//微信朋友圈
            shareAction.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE);//传入平台
        } else if (type == 1) {//微信
            shareAction.setPlatform(SHARE_MEDIA.WEIXIN);//
        } else if (type == 2) {//QQ
            shareAction.setPlatform(SHARE_MEDIA.QQ);//
        } else {//wb
            shareAction.setPlatform(SHARE_MEDIA.SINA);//
        }
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
}

