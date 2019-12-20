package com.ok100.weather.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.constant.ConstantCode;
import com.ok100.weather.constant.GGPositionId;
import com.ok100.weather.utils.ActivityBarSettingUtils;
import com.ok100.weather.utils.GGDemoUtil;
import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.ads.nativ.NativeAD;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.qq.e.comm.constants.AdPatternType;
import com.qq.e.comm.util.AdError;
import com.qq.e.comm.util.GDTLogger;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeDetatilActivity extends BaseActivity implements NativeAD.NativeAdListener , UnifiedBannerADListener {


    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.bannerContainer)
    ViewGroup bannerContainer;

    private String url = "";

    private String posid = "1090594484618755";
    private NativeADDataRef adItem;
    private NativeAD nativeAD;

    UnifiedBannerView bv;
    String posId;

    @Override
    public int getLayoutID() {
        return R.layout.activity_notice_detatil;
    }

    @Override
    public void InitView() {
        setTitle("新闻详情", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        url = getIntent().getStringExtra("url");
        registerBack();
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(NoticeDetatilActivity.this, true);
        mWebview.loadUrl(url);
        MobclickAgent.onEvent(NoticeDetatilActivity.this, "look_newList");
//        adItem.onExposured(this.findViewById(R.id.view));

//        loadAD();

        doRefreshBanner();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onADLoaded(List<NativeADDataRef> list) {
        Log.e("listlistlist",list.toString());
//        Toast.makeText(this, "原生广告加载成功"+list.size(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onADStatusChanged(NativeADDataRef nativeADDataRef) {

    }

    @Override
    public void onADError(NativeADDataRef nativeADDataRef, AdError adError) {

    }

    @Override
    public void onNoAD(AdError adError) {

    }

    @Override
    public void onADReceive() {

    }

    @Override
    public void onADExposure() {

    }

    @Override
    public void onADClosed() {

    }

    @Override
    public void onADClicked() {

    }

    @Override
    public void onADLeftApplication() {

    }

    @Override
    public void onADOpenOverlay() {

    }

    @Override
    public void onADCloseOverlay() {

    }


    public void loadAD() {
//        nativeAD = new NativeAD(this, ConstantCode.GGAPPID, posid, this);
        nativeAD = new NativeAD(this, "1101152570", "5010320697302671", this);
        ArrayList<String> categories = new ArrayList<String>();
        //添加类目信息
//        for (String s : $.id(R.id.nativeCategories).getText().toString().split(",")) {
//            if (!TextUtils.isEmpty(s)) {
//                categories.add(s);
//            }
//        }
        nativeAD.setCategories(categories);
        int count = 5; // 一次拉取的广告条数：范围1-10
        nativeAD.loadAD(count);
        GGDemoUtil.hideSoftInput(this);
    }


    private void doRefreshBanner() {
        GGDemoUtil.hideSoftInput(this);
        getBanner().loadAD();
    }


    private UnifiedBannerView getBanner() {
        if(this.bv != null){
            bannerContainer.removeView(bv);
            bv.destroy();
        }
        String posId = getPosID();
        this.posId = posId;
        this.bv = new UnifiedBannerView(this, ConstantCode.GGAPPID, posId, this);
        bv.setRefresh(5);

        // 不需要传递tags使用下面构造函数
        // this.bv = new UnifiedBannerView(this, Constants.APPID, posId, this);
        bannerContainer.addView(bv, getUnifiedBannerLayoutParams());
        return this.bv;
    }

    private String getPosID() {
        return GGPositionId.BANNER_POS_ID;
    }

    /**
     * banner2.0规定banner宽高比应该为6.4:1 , 开发者可自行设置符合规定宽高比的具体宽度和高度值
     *
     * @return
     */
    private FrameLayout.LayoutParams getUnifiedBannerLayoutParams() {
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        return new FrameLayout.LayoutParams(screenSize.x,  Math.round(screenSize.x / 6.4F));
    }

    /**
     * 展示原生广告时，一定要先调用onExposured接口曝光广告，否则将无法调用onClicked点击接口
     */
//    public void showAD() {
//        if (adItem.getAdPatternType() == AdPatternType.NATIVE_3IMAGE) {
//            GDTLogger.d("show three img ad.");
//            findViewById(R.id.native_3img_ad_container).setVisibility(View.VISIBLE);
//            findViewById(R.id.native_ad_container).setVisibility(View.INVISIBLE);
//            $.id(R.id.img_1).image(adItem.getImgList().get(0), false, true);
//            $.id(R.id.img_2).image(adItem.getImgList().get(1), false, true);
//            $.id(R.id.img_3).image(adItem.getImgList().get(2), false, true);
//            $.id(R.id.native_3img_title).text(adItem.getTitle());
//            $.id(R.id.native_3img_desc).text(adItem.getDesc());
//        } else if (adItem.getAdPatternType() == AdPatternType.NATIVE_2IMAGE_2TEXT) {
//            GDTLogger.d("show two img ad.");
//            findViewById(R.id.native_3img_ad_container).setVisibility(View.INVISIBLE);
//            findViewById(R.id.native_ad_container).setVisibility(View.VISIBLE);
//            $.id(R.id.img_logo).image(adItem.getIconUrl(), false, true);
//            $.id(R.id.img_poster).image(adItem.getImgUrl(), false, true);
//            $.id(R.id.text_name).text(adItem.getTitle());
//            $.id(R.id.text_desc).text(adItem.getDesc());
//        } else if (adItem.getAdPatternType() == AdPatternType.NATIVE_1IMAGE_2TEXT) {
//            GDTLogger.d("show one img ad.");
//            findViewById(R.id.native_3img_ad_container).setVisibility(View.INVISIBLE);
//            findViewById(R.id.native_ad_container).setVisibility(View.VISIBLE);
//            $.id(R.id.img_logo).image( adItem.getImgUrl(), false, true);
//            $.id(R.id.img_poster).clear();
//            $.id(R.id.text_name).text(adItem.getTitle());
//            $.id(R.id.text_desc).text(adItem.getDesc());
//        }
//        $.id(R.id.btn_download).text(getADButtonText());
//        adItem.onExposured(this.findViewById(R.id.nativeADContainer)); // 需要先调用曝光接口
//        $.id(R.id.btn_download).clicked(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adItem.onClicked(view); // 点击接口
//            }
//        });
//    }
}
