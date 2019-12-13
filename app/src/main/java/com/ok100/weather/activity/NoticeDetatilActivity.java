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

public class NoticeDetatilActivity extends BaseActivity {


    @BindView(R.id.webview)
    WebView mWebview;

    private String url = "";

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
