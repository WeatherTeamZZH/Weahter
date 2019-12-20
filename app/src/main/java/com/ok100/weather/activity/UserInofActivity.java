package com.ok100.weather.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.ok100.weather.constant.ConstantCode;
import com.ok100.weather.constant.GGPositionId;
import com.ok100.weather.event.EventGotoNewsMessage;
import com.ok100.weather.gh.GlideCircleTransform;
import com.ok100.weather.gh.MineCenterActivity;
import com.ok100.weather.gh.XingzuoActivity;
import com.ok100.weather.utils.EmptyUtils;
import com.ok100.weather.utils.SPObj;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.nativ.ADSize;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.ads.nativ.NativeExpressMediaListener;
import com.qq.e.comm.constants.AdPatternType;
import com.qq.e.comm.pi.AdData;
import com.qq.e.comm.util.AdError;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInofActivity extends BaseActivity implements NativeExpressAD.NativeExpressADListener{

    private static final String TAG = "UserInofActivity";

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
    @BindView(R.id.container)
    ViewGroup container;

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

                    case 1:
                        XingzuoActivity.access(UserInofActivity.this);
                        break;
                    case 2:
                        intent = new Intent(UserInofActivity.this, ZhutiImgeActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(UserInofActivity.this, SuggestionActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        Toast.makeText(UserInofActivity.this,"暂未开通",Toast.LENGTH_SHORT).show();

                        break;
                    case 5:
                        intent = new Intent(UserInofActivity.this, HistoryTodayActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(UserInofActivity.this, XiaohuaActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(UserInofActivity.this, ZhougongActivity.class);
                        startActivity(intent);
                        break;

                    case 8:
                        intent = new Intent(UserInofActivity.this, NoticeCenterActivity.class);
                        startActivity(intent);
                        break;
                    case 0:
                        EventGotoNewsMessage msg = new EventGotoNewsMessage("头条");
                        EventBus.getDefault().post(msg);
                        finish();
                        break;
                }
            }
        });

        refreshAd();
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
                MobclickAgent.onEvent(UserInofActivity.this, "click_HeaderIcon");
                break;
        }
    }


    private int adWidth =500, adHeight=300; // 广告宽高
    private NativeExpressADView nativeExpressADView;
    private boolean isPreloadVideo;
    private NativeExpressAD nativeExpressAD;
    private boolean isAdFullWidth = true, isAdAutoHeight = false; // 是否采用了ADSize.FULL_WIDTH，ADSize.AUTO_HEIGHT

    @Override
    public void onADLoaded(List<NativeExpressADView> adList) {
        Log.i(TAG, "onADLoaded: " + adList.size());
        // 释放前一个展示的NativeExpressADView的资源
        if (nativeExpressADView != null) {
            nativeExpressADView.destroy();
        }

        if (container.getVisibility() != View.VISIBLE) {
            container.setVisibility(View.VISIBLE);
        }

        if (container.getChildCount() > 0) {
            container.removeAllViews();
        }

        nativeExpressADView = adList.get(0);
        Log.i(TAG, "onADLoaded, video info: " + getAdInfo(nativeExpressADView));
        if (nativeExpressADView.getBoundData().getAdPatternType() == AdPatternType.NATIVE_VIDEO) {
            nativeExpressADView.setMediaListener(mediaListener);
            if(isPreloadVideo) {
                // 预加载视频素材，加载成功会回调mediaListener的onVideoCached方法，失败的话回调onVideoError方法errorCode为702。
                nativeExpressADView.preloadVideo();
            }
        } else {
            isPreloadVideo = false;
        }
        if(!isPreloadVideo) {
            // 广告可见才会产生曝光，否则将无法产生收益。
            container.addView(nativeExpressADView);
            nativeExpressADView.render();
        }
    }

    @Override
    public void onRenderFail(NativeExpressADView nativeExpressADView) {

    }

    @Override
    public void onRenderSuccess(NativeExpressADView nativeExpressADView) {

    }

    @Override
    public void onADExposure(NativeExpressADView nativeExpressADView) {

    }

    @Override
    public void onADClicked(NativeExpressADView nativeExpressADView) {

    }

    @Override
    public void onADClosed(NativeExpressADView nativeExpressADView) {
        Log.i(TAG, "onADClosed");
        // 当广告模板中的关闭按钮被点击时，广告将不再展示。NativeExpressADView也会被Destroy，释放资源，不可以再用来展示。
        if (container != null && container.getChildCount() > 0) {
            container.removeAllViews();
            container.setVisibility(View.GONE);
        }
    }

    @Override
    public void onADLeftApplication(NativeExpressADView nativeExpressADView) {

    }

    @Override
    public void onADOpenOverlay(NativeExpressADView nativeExpressADView) {

    }

    @Override
    public void onADCloseOverlay(NativeExpressADView nativeExpressADView) {

    }

    @Override
    public void onNoAD(AdError adError) {

    }

    /**
     * 获取广告数据
     *
     * @param nativeExpressADView
     * @return
     */
    private String getAdInfo(NativeExpressADView nativeExpressADView) {
        AdData adData = nativeExpressADView.getBoundData();
        if (adData != null) {
            StringBuilder infoBuilder = new StringBuilder();
            infoBuilder.append("title:").append(adData.getTitle()).append(",")
                    .append("desc:").append(adData.getDesc()).append(",")
                    .append("patternType:").append(adData.getAdPatternType());
            if (adData.getAdPatternType() == AdPatternType.NATIVE_VIDEO) {
                infoBuilder.append(", video info: ").append(getVideoInfo(adData.getProperty(AdData.VideoPlayer.class)));
            }
            Log.d(TAG, "eCPM = " + adData.getECPM() + " , eCPMLevel = " + adData.getECPMLevel() + " , " +
                    "videoDuration = " + adData.getVideoDuration());
            return infoBuilder.toString();
        }
        return null;
    }

    private NativeExpressMediaListener mediaListener = new NativeExpressMediaListener() {
        @Override
        public void onVideoInit(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoInit: "
                    + getVideoInfo(nativeExpressADView.getBoundData().getProperty(AdData.VideoPlayer.class)));
        }

        @Override
        public void onVideoLoading(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoLoading");
        }

        @Override
        public void onVideoCached(NativeExpressADView adView) {
            Log.i(TAG, "onVideoCached");
            // 视频素材加载完成，此时展示视频广告不会有进度条。
            if(isPreloadVideo) {
                // 广告可见才会产生曝光，否则将无法产生收益。
                container.addView(nativeExpressADView);
                nativeExpressADView.render();
            }
        }

        @Override
        public void onVideoReady(NativeExpressADView nativeExpressADView, long l) {
            Log.i(TAG, "onVideoReady");
        }

        @Override
        public void onVideoStart(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoStart: "
                    + getVideoInfo(nativeExpressADView.getBoundData().getProperty(AdData.VideoPlayer.class)));
        }

        @Override
        public void onVideoPause(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoPause: "
                    + getVideoInfo(nativeExpressADView.getBoundData().getProperty(AdData.VideoPlayer.class)));
        }

        @Override
        public void onVideoComplete(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoComplete: "
                    + getVideoInfo(nativeExpressADView.getBoundData().getProperty(AdData.VideoPlayer.class)));
        }

        @Override
        public void onVideoError(NativeExpressADView nativeExpressADView, AdError adError) {
            Log.i(TAG, "onVideoError");
        }

        @Override
        public void onVideoPageOpen(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoPageOpen");
        }

        @Override
        public void onVideoPageClose(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoPageClose");
        }
    };

    /**
     * 获取播放器实例
     *
     * 仅当视频回调{@link NativeExpressMediaListener#onVideoInit(NativeExpressADView)}调用后才会有返回值
     *
     * @param videoPlayer
     * @return
     */
    private String getVideoInfo(AdData.VideoPlayer videoPlayer) {
        if (videoPlayer != null) {
            StringBuilder videoBuilder = new StringBuilder();
            videoBuilder.append("{state:").append(videoPlayer.getVideoState()).append(",")
                    .append("duration:").append(videoPlayer.getDuration()).append(",")
                    .append("position:").append(videoPlayer.getCurrentPosition()).append("}");
            return videoBuilder.toString();
        }
        return null;
    }

    private void refreshAd() {
        try {

//            hideSoftInput();
            /**
             *  如果选择支持视频的模版样式，请使用{@link PositionId#NATIVE_EXPRESS_SUPPORT_VIDEO_POS_ID}
             */
            nativeExpressAD = new NativeExpressAD(this, getMyADSize(), ConstantCode.GGAPPID, getPosId(), this); // 这里的Context必须为Activity
            //这里可能有问题
            VideoOption option = getVideoOption(new Intent());
            if(option != null){
                // setVideoOption是可选的，开发者可根据需要选择是否配置
                nativeExpressAD.setVideoOption(option);
            }
            nativeExpressAD.setMinVideoDuration(getMinVideoDuration());
            nativeExpressAD.setMaxVideoDuration(getMaxVideoDuration());
            /**
             * 如果广告位支持视频广告，强烈建议在调用loadData请求广告前调用setVideoPlayPolicy，有助于提高视频广告的eCPM值 <br/>
             * 如果广告位仅支持图文广告，则无需调用
             */

            /**
             * 设置本次拉取的视频广告，从用户角度看到的视频播放策略<p/>
             *
             * "用户角度"特指用户看到的情况，并非SDK是否自动播放，与自动播放策略AutoPlayPolicy的取值并非一一对应 <br/>
             *
             * 如自动播放策略为AutoPlayPolicy.WIFI，但此时用户网络为4G环境，在用户看来就是手工播放的
             */
            nativeExpressAD.setVideoPlayPolicy(getVideoPlayPolicy(VideoOption.AutoPlayPolicy.ALWAYS, this));  // 本次拉回的视频广告，在用户看来是否为自动播放的
            nativeExpressAD.loadAD(1);
        } catch (NumberFormatException e) {
            Log.w(TAG, "ad size invalid.");
            Toast.makeText(this, "请输入合法的宽高数值", Toast.LENGTH_SHORT).show();
        }
    }

    public static int getVideoPlayPolicy(int autoPlayPolicy, Context context){
        if(autoPlayPolicy == VideoOption.AutoPlayPolicy.ALWAYS){
            return VideoOption.VideoPlayPolicy.AUTO;
        }else if(autoPlayPolicy == VideoOption.AutoPlayPolicy.WIFI){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return wifiNetworkInfo != null && wifiNetworkInfo.isConnected() ? VideoOption.VideoPlayPolicy.AUTO
                    : VideoOption.VideoPlayPolicy.MANUAL;
        }else if(autoPlayPolicy == VideoOption.AutoPlayPolicy.NEVER){
            return VideoOption.VideoPlayPolicy.MANUAL;
        }
        return VideoOption.VideoPlayPolicy.UNKNOWN;
    }

    private int getMinVideoDuration() {
//    return getIntent().getIntExtra(Constants.MIN_VIDEO_DURATION, 0);
        return 5;
    }

    private int getMaxVideoDuration() {
//    return getIntent().getIntExtra(Constants.MAX_VIDEO_DURATION, 0);
        return 60;
    }

    //这个方法别的activity弄过来的
    public static VideoOption getVideoOption(Intent intent) {
        if(intent == null){
            return null;
        }

        VideoOption videoOption = null;
        boolean noneOption = intent.getBooleanExtra(GGPositionId.NONE_OPTION, false);
        if (!noneOption) {
            VideoOption.Builder builder = new VideoOption.Builder();

            builder.setAutoPlayPolicy(intent.getIntExtra(GGPositionId.PLAY_NETWORK, VideoOption.AutoPlayPolicy.ALWAYS));
            builder.setAutoPlayMuted(intent.getBooleanExtra(GGPositionId.PLAY_MUTE, true));
            builder.setDetailPageMuted(intent.getBooleanExtra(GGPositionId.DETAIL_PAGE_MUTED,false));

            videoOption = builder.build();
        }
        return videoOption;
    }

    private ADSize getMyADSize() {
        int w = isAdFullWidth ? ADSize.FULL_WIDTH : adWidth;
        int h = isAdAutoHeight ? ADSize.AUTO_HEIGHT : adHeight;
        return new ADSize(w, h);
    }

    private String getPosId() {
        return GGPositionId.MAIN_POS_ID;
    }
}
