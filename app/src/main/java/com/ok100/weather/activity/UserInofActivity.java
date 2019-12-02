package com.ok100.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.R;
import com.ok100.weather.adapter.SmallToolsAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.EventTitleMessage;
import com.ok100.weather.event.EventGotoNewsMessage;
import com.ok100.weather.gh.GlideCircleTransform;
import com.ok100.weather.gh.MineCenterActivity;
import com.ok100.weather.gh.XingzuoActivity;
import com.ok100.weather.utils.EmptyUtils;
import com.ok100.weather.utils.SPObj;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInofActivity extends BaseActivity {


    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;
    @BindView(R.id.recycleview1)
    RecyclerView mRecycleview1;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.iv_qiandao)
    TextView mIvQiandao;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_xiaoxi)
    TextView mTvXiaoxi;
    @BindView(R.id.tv_zhuti)
    TextView mTvZhuti;
    @BindView(R.id.tv_yijian)
    TextView mTvYijian;
    @BindView(R.id.ll_notice_more)
    LinearLayout mLlNoticeMore;
    @BindView(R.id.iv_goto_maincenter)
    ImageView mIvGotoMaincenter;
    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.llnearlayout1)
    LinearLayout llnearlayout1;
    @BindView(R.id.llnearlayout3)
    LinearLayout llnearlayout3;
    @BindView(R.id.llnearlayout2)
    LinearLayout llnearlayout2;

    private SPObj spObj;

    @Override
    public int getLayoutID() {
        return R.layout.activity_user_inof;
    }

    @Override
    public void InitView() {
        spObj = new SPObj(getContext(), "gh");
        NestedScrollView viewById = findViewById(R.id.nestedScrollView);
        viewById.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (spObj.getObject("isLogin", Boolean.class) != null && spObj.getObject("isLogin", Boolean.class)) {
            Glide.with(getContext()).load(spObj.getObject("imgurl", String.class)).transform(new GlideCircleTransform(getContext())).into(mIvGotoMaincenter);
            tvPhone.setText(EmptyUtils.EmptyString(spObj.getObject("phone", String.class), "未绑定"));
            tvScore.setText("");
        } else {
            tvPhone.setText("点击登录");
            tvScore.setText("只差一步我们就能更亲近");
        }
    }

    @Override
    public void initListener() {
        mIvQiandao.setOnClickListener(this);
        mIvSetting.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mTvXiaoxi.setOnClickListener(this);
        mTvZhuti.setOnClickListener(this);
        mTvYijian.setOnClickListener(this);
        mLlNoticeMore.setOnClickListener(this);
        mIvGotoMaincenter.setOnClickListener(this);

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {
        mRecycleview.setLayoutManager(new GridLayoutManager(UserInofActivity.this, 3));
        SmallToolsAdapter myPindaoAdapter = new SmallToolsAdapter();
        myPindaoAdapter.setNewData(DataBean.getUserAdapter());
        mRecycleview.setAdapter(myPindaoAdapter);
        mRecycleview.setNestedScrollingEnabled(false);//禁止滑动

        mRecycleview1.setLayoutManager(new GridLayoutManager(UserInofActivity.this, 4));
        SmallToolsAdapter myPindaoAdapter1 = new SmallToolsAdapter();
        myPindaoAdapter1.setNewData(DataBean.getUserAdapter1());
        mRecycleview1.setAdapter(myPindaoAdapter);
        mRecycleview1.setNestedScrollingEnabled(false);//禁止滑动

        myPindaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent;
                switch (position){

                    case 0:
                        XingzuoActivity.access(UserInofActivity.this);
                        break;
                    case 1:
                        intent = new Intent(UserInofActivity.this, ZhutiImgeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(UserInofActivity.this, SuggestionActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Toast.makeText(UserInofActivity.this,"暂未开通",Toast.LENGTH_SHORT).show();

                        break;
                    case 4:
                        intent = new Intent(UserInofActivity.this, HistoryTodayActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(UserInofActivity.this, XiaohuaActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(UserInofActivity.this, ZhougongActivity.class);
                        startActivity(intent);
                        break;

                    case 7:
                        intent = new Intent(UserInofActivity.this, NoticeCenterActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        EventGotoNewsMessage msg = new EventGotoNewsMessage("头条");
                        EventBus.getDefault().post(msg);
                        finish();
                        break;
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_qiandao:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_setting:
                intent = new Intent(UserInofActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_xiaoxi:
                intent = new Intent(UserInofActivity.this, NoticeCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_zhuti:
                intent = new Intent(UserInofActivity.this, ZhutiImgeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_yijian:
                Toast.makeText(UserInofActivity.this,"暂未开通",Toast.LENGTH_SHORT).show();
//                intent = new Intent(UserInofActivity.this, NoticeCenterActivity.class);
//                startActivity(intent);
                break;
            case R.id.ll_notice_more:
                intent = new Intent(UserInofActivity.this, NoticeCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_goto_maincenter:
                if (spObj.getObject("isLogin", Boolean.class) != null && spObj.getObject("isLogin", Boolean.class)) {
                    MineCenterActivity.access(UserInofActivity.this);
                } else {
                    intent = new Intent(UserInofActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }

}
