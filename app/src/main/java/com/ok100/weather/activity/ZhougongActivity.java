package com.ok100.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.adapter.MyCityAdapter2;
import com.ok100.weather.adapter.ZhougongAdapter;
import com.ok100.weather.adapter.ZhougongTitleAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.bean.SettingBean;
import com.ok100.weather.bean.ZhougongBean;
import com.ok100.weather.bean.ZhougongStalyBean;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.Urls;
import com.ok100.weather.presenter.UserInfoIconPresenterImpl;
import com.ok100.weather.utils.ActivityBarSettingUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhougongActivity extends BaseActivity implements ReturnDataView {

    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;

    public ZhougongAdapter mAdapter;
    @BindView(R.id.edittext)
    EditText mEdittext;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.recycleview_title)
    RecyclerView mRecycleviewTitle;

    ZhougongTitleAdapter myPindaoAdapter2 ;

    @Override
    public int getLayoutID() {
        return R.layout.activity_zhougong;
    }

    @Override
    public void InitView() {
        setTitle("周工解梦", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        initReycyleviewTilte();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ZhougongActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleview.setLayoutManager(linearLayoutManager);
        mAdapter = new ZhougongAdapter(ZhougongActivity.this);

        mRecycleview.setAdapter(mAdapter);
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(ZhougongActivity.this, true);
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
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http();
            }
        });
        MobclickAgent.onEvent(ZhougongActivity.this, "ZhougongActivity");
        MobclickAgent.onEvent(ZhougongActivity.this, "ZhougongActivity","ZhougongActivityLabel");
    }

    private void initReycyleviewTilte() {
        mRecycleviewTitle.setLayoutManager(new GridLayoutManager(ZhougongActivity.this, 3));
        myPindaoAdapter2 = new ZhougongTitleAdapter();
        mRecycleviewTitle.setAdapter(myPindaoAdapter2);
        myPindaoAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DefultGridViewBean bean = (DefultGridViewBean) adapter.getData().get(position);
                String name = bean.getName();
                mEdittext.setText(name);
                http();
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {
        httpInit();
    }

    @Override
    public void onClick(View v) {

    }

    public void httpInit() {
        UserInfoIconPresenterImpl userInfoIconPresenter = new UserInfoIconPresenterImpl(ZhougongActivity.this);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", Urls.ZhouGongAppkey);
        userInfoIconPresenter.getZhougongStateData(ZhougongActivity.this, hashMap);
        myPindaoAdapter2.setNewData(DataBean.getZhougongDeat());
    }

    public void http() {
        String string = mEdittext.getText().toString();
        UserInfoIconPresenterImpl userInfoIconPresenter = new UserInfoIconPresenterImpl(ZhougongActivity.this);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", Urls.ZhouGongAppkey);
        hashMap.put("q", string);
        userInfoIconPresenter.getZhougongData(ZhougongActivity.this, hashMap);
    }

    @Override
    public void returnData(String responseCode, Object o) {
        switch (responseCode){
            case "getZhougongData":
                ZhougongBean bean = (ZhougongBean) o;
                if (bean != null) {
                    if((bean.getResult()==null)||bean.getResult().size()==0){
                        mAdapter.setNewData(bean.getResult());
                        mRecycleviewTitle.setVisibility(View.VISIBLE);
                        mRecycleview.setVisibility(View.GONE);
                    }else {
                        mAdapter.setNewData(bean.getResult());
                        mRecycleviewTitle.setVisibility(View.GONE);
                        mRecycleview.setVisibility(View.VISIBLE);
                    }

                }

                break;
            case "getZhougongStateData":
                ZhougongStalyBean zhougongStalyBean = (ZhougongStalyBean) o;
                ArrayList<DefultGridViewBean> defultGridViewBeans = new ArrayList<>();
                if (zhougongStalyBean != null) {
                    for(int i=0 ;i<zhougongStalyBean.getResult().size();i++){
                        DefultGridViewBean defultGridViewBean = new DefultGridViewBean();
                        defultGridViewBean.setName(zhougongStalyBean.getResult().get(i).getName());
                        defultGridViewBeans.add(defultGridViewBean);
                    }
                }
//                myPindaoAdapter2.setNewData(defultGridViewBeans);
                break;

        }

    }

    @Override
    public void showError(String msg) {

    }

}
