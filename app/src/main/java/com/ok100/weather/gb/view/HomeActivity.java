package com.ok100.weather.gb.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ok100.weather.R;
import com.ok100.weather.gb.adepter.BitmapFragmentpageAdepter;
import com.ok100.weather.gb.share.UIUtil;
import com.ok100.weather.gb.share.WeatherBannerFragment;
import com.ok100.weather.gb.share.transformer.ScaleInTransformer;
import com.ok100.weather.gb.stickercamera.app.camera.CameraManager;
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
    private List<String> urlBeanList = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();
    private ViewPager share_vp;
    private BitmapFragmentpageAdepter bitmapFragmentpageAdepter = null;
    private DisplayMetrics metric = new DisplayMetrics();
    private JSONObject jsonObject = new JSONObject();
    private JSONArray jsonArray = new JSONArray();
    private List<Bitmap> bitmaps = new ArrayList<>();
    private final static int ALBUM_COMPLETED = 0x12;
    private final static int SAVE_COMPLETED = 0x13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        initView();
        request(-1);


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

    private void sethh() {
        urlBeanList.clear();
        try {
            if (!TextUtils.isEmpty(ImageUtils.getLocalListData())) {

                JSONObject jsonObject = new JSONObject(ImageUtils.getLocalListData());
                String data = jsonObject.getString("data");
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    String dataStr = jsonArray.getString(i);
                    urlBeanList.add(dataStr);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        mFragment.clear();
        sethh();
        for (String mData : urlBeanList) {
            mFragment.add(WeatherBannerFragment.getInstance(mData));
        }
        bitmapFragmentpageAdepter.notifyDataSetChanged();
    }

    private void getLocalListData() {

        try {
            if (!TextUtils.isEmpty(ImageUtils.getLocalListData())) {
                setData();
            } else {
                noDataSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void noDataSet() {
        try {
            Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg);
            Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg1);
            Bitmap bmp3 = BitmapFactory.decodeResource(getResources(), R.mipmap.main_bg2);
            bitmaps.add(bmp1);
            bitmaps.add(bmp2);
            bitmaps.add(bmp3);

            for (int i = 0; i < bitmaps.size(); i++) {
                String imagePath = ImageUtils.saveBitmapToFile(FileUtils.getInst().getSystemPhotoPathAdd(), true,
                        combineBitmap(bitmaps.get(i), getBitmap()), "wwttrr" + i);
                Message msg = new Message();
                msg.what = ALBUM_COMPLETED;
                msg.obj = imagePath;
                mHandler.sendMessage(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
//
        try {
            String imagePath = ImageUtils.saveBitmapToFile(FileUtils.getInst().getSystemPhotoPathAdd(), true,
                    combineBitmap(bitmap, getBitmap()), "wwttrr" + share_vp.getCurrentItem());
            Message msg = new Message();
            msg.what = SAVE_COMPLETED;
            msg.obj = imagePath;
            mHandler.sendMessage(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        } else if (type == -1) {
            getLocalListData();
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

    //todo 根据不可见view生成bitmap
    private Bitmap getBitmap() {
        Bitmap bitmap = null;
        LayoutInflater factorys = LayoutInflater.from(this);
        final View textEntryView = factorys.inflate(R.layout.ll_bitmap, null);
        textEntryView.setDrawingCacheEnabled(true);
        textEntryView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        textEntryView.layout(0, 0, textEntryView.getMeasuredWidth(), textEntryView.getMeasuredHeight());
        bitmap = Bitmap.createBitmap(textEntryView.getDrawingCache());
        textEntryView.setDrawingCacheEnabled(false);
        return bitmap;
    }

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

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == ALBUM_COMPLETED) {//拍照处理
                try {
                    String mData = (String) msg.obj;
                    jsonArray.put(mData);
                    if (jsonArray.length() == 3) {
                        jsonObject.put("data", jsonArray.toString());
                        ImageUtils.setLocalBitmapListData(jsonObject.toString());
                        initData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (msg.what == SAVE_COMPLETED) {
                urlBeanList.clear();
                try {
                    String mData = (String) msg.obj;
                    mFragment.set(share_vp.getCurrentItem(), WeatherBannerFragment.getInstance(mData));
                    bitmapFragmentpageAdepter.notifyDataSetChanged();
                    if (!TextUtils.isEmpty(ImageUtils.getLocalListData())) {
                        JSONObject jsonObject = new JSONObject(ImageUtils.getLocalListData());
                        String data = jsonObject.getString("data");
                        JSONArray jsonArray = new JSONArray(data);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String dataStr = jsonArray.getString(i);
                            if (dataStr.equals(mData)) {
                                urlBeanList.add(mData);
                            } else {
                                urlBeanList.add(dataStr);
                            }
                        }
                        ImageUtils.setLocalBitmapListData(jsonObject.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}

