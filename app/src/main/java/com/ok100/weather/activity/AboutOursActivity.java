package com.ok100.weather.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.utils.ActivityBarSettingUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutOursActivity extends BaseActivity {


    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    public int getLayoutID() {
        return R.layout.activity_about_ours;
    }

    @Override
    public void InitView() {
        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        if(title.contains("隐私")){
            MobclickAgent.onEvent(AboutOursActivity.this, "click_Useragment");
        }else if(title.contains("注册")){
            MobclickAgent.onEvent(AboutOursActivity.this, "click_agment");
        }else if(title.contains("关于")){
            MobclickAgent.onEvent(AboutOursActivity.this, "click_AboutUS");
        }
        setTitle(title, true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(AboutOursActivity.this ,true);
        mWebView.loadUrl(url);
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

}
