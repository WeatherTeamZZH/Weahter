package com.ok100.weather.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;

import com.ok100.weather.R;
import com.ok100.weather.adapter.MainTodaySuggestAdapter;
import com.ok100.weather.adapter.Weather15MianAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.gh.XingzuoActivity;
import com.ok100.weather.view.MyLinearLayoutManager;
import com.ok100.weather.view.MyNestedScrollView;
import com.ok100.weather.view.RecyclerScrollView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhouzhuo.zzweatherview.AirLevel;
import me.zhouzhuo.zzweatherview.WeatherModel;

public class ShareMainActivity extends BaseActivity {

    @BindView(R.id.recycle)
    RecyclerView mRecycle;
    @BindView(R.id.scrollview)
    ScrollView mScrollview;

    @Override
    public int getLayoutID() {
        return R.layout.activity_share_main;
    }

    @Override
    public void InitView() {
        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(ShareMainActivity.this);
        myLinearLayoutManager.setScrollEnabled(false);
        mRecycle.setLayoutManager(myLinearLayoutManager);

        MainTodaySuggestAdapter mainTodaySuggestAdapter = new MainTodaySuggestAdapter();
        mRecycle.setAdapter(mainTodaySuggestAdapter);
//        mRecycle.setNestedScrollingEnabled(false);
//        mScrollview.setEnabled(false);

        MobclickAgent.onEvent(ShareMainActivity.this, "ShareMainActivity");
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
