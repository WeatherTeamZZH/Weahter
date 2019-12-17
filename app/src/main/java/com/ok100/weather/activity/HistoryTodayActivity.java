package com.ok100.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.R;
import com.ok100.weather.adapter.HistoryTodayAdapter;
import com.ok100.weather.adapter.SettingAdapter;
import com.ok100.weather.adapter.XiaohuaAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.HistoryTodayBean;
import com.ok100.weather.bean.SettingBean;
import com.ok100.weather.gh.MineCenterActivity;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.Urls;
import com.ok100.weather.presenter.UserInfoIconPresenterImpl;
import com.ok100.weather.utils.ActivityBarSettingUtils;
import com.ok100.weather.utils.TimeUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryTodayActivity extends BaseActivity implements ReturnDataView {


    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;

    HistoryTodayAdapter mAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_history_today;
    }

    @Override
    public void InitView() {
        setTitle("历史的今天", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryTodayActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleview.setLayoutManager(linearLayoutManager);
        mAdapter = new HistoryTodayAdapter(HistoryTodayActivity.this);

        mRecycleview.setAdapter(mAdapter);
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(HistoryTodayActivity.this, true);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent;
                SettingBean bean;
                switch (position) {
                    case 1:

                        break;
                }
            }
        });

        MobclickAgent.onEvent(HistoryTodayActivity.this, "HistoryTodayActivity");
        MobclickAgent.onEvent(HistoryTodayActivity.this, "HistoryTodayActivity","HistoryTodayActivityLabel");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {
        http();
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void returnData(String responseCode, Object o) {
        HistoryTodayBean resultBean = (HistoryTodayBean) o;
        if(resultBean!=null){
            mAdapter.setNewData(resultBean.getResult());
        }

    }

    @Override
    public void showError(String msg) {

    }

    public void http(){
        UserInfoIconPresenterImpl userInfoIconPresenter = new UserInfoIconPresenterImpl(HistoryTodayActivity.this);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", Urls.HistoryTodayAppkey);
        hashMap.put("v","1.0");
        hashMap.put("month", TimeUtils.getMonth()+"");
        hashMap.put("day",TimeUtils.getDay()+"");
        userInfoIconPresenter.getHisttoryTodatData(HistoryTodayActivity.this,hashMap);
    }
}
