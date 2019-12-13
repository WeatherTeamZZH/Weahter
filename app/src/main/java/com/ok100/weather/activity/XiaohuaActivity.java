package com.ok100.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.R;
import com.ok100.weather.adapter.XiaohuaAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.SettingBean;
import com.ok100.weather.bean.XiaohuaBean;
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

public class XiaohuaActivity extends BaseActivity implements ReturnDataView {

    XiaohuaAdapter mAdapter;
    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;

    public String shijianchuo = "0";
    public int page = 1;

    @Override
    public int getLayoutID() {
        return R.layout.activity_xiaohua;
    }

    @Override
    public void InitView() {
        setTitle("抖乐笑话", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        shijianchuo = TimeUtils.getShijianchuo10();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XiaohuaActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleview.setLayoutManager(linearLayoutManager);
        mAdapter = new XiaohuaAdapter(XiaohuaActivity.this);

        mRecycleview.setAdapter(mAdapter);
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(XiaohuaActivity.this, true);
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
        MobclickAgent.onEvent(XiaohuaActivity.this, "XiaohuaActivity");
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
        XiaohuaBean xiaohuaBean = (XiaohuaBean) o;
        if(xiaohuaBean!=null){
            mAdapter.setNewData(xiaohuaBean.getResult().getData());
        }

    }

    @Override
    public void showError(String msg) {

    }

    public void http(){
        UserInfoIconPresenterImpl userInfoIconPresenter = new UserInfoIconPresenterImpl(XiaohuaActivity.this);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sort","desc");
        hashMap.put("page",page+"");
        hashMap.put("pagesize","20");
        hashMap.put("time",shijianchuo+"");
        hashMap.put("key", Urls.XiaohuaAppkey);
        userInfoIconPresenter.getXiaohua(XiaohuaActivity.this,hashMap);
    }
}
