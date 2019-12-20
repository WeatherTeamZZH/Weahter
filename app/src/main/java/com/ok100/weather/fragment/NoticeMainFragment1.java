package com.ok100.weather.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.activity.NoticeDetatilActivity;
import com.ok100.weather.adapter.NoticeMainFragmentAdapter;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.ChannelBean;
import com.ok100.weather.bean.EventTitleMessage;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.NoticeMainListBean;
import com.ok100.weather.constant.ConstantCode;
import com.ok100.weather.constant.GGPositionId;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.Urls;
import com.ok100.weather.presenter.NewsListPresenterImpl;
import com.ok100.weather.presenter.NoticeMainListPresenterImpl;
import com.ok100.weather.presenter.UcDataPresenterImpl;
import com.ok100.weather.utils.ChooseTypeUtils;
import com.ok100.weather.view.CustomLoadMoreViewNews;
import com.ok100.weather.view.MyLinearLayoutManager1;
import com.ok100.weather.view.MyRecyclerView;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.nativ.ADSize;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.ads.nativ.NativeExpressMediaListener;
import com.qq.e.comm.pi.AdData;
import com.qq.e.comm.util.AdError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


public class NoticeMainFragment1 extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener, ReturnDataView<Object>, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, View.OnTouchListener , NativeExpressAD.NativeExpressADListener {
    private static final String TAG = NoticeMainFragment1.class.getSimpleName();
    @BindView(R.id.recycle)
    MyRecyclerView mRecyclerView;
    Unbinder unbinder;

    //    private SwipeRefreshLayout mSwipeRefreshLayout;

    private NoticeMainFragmentAdapter noticeMainFragmentAdapter;

    private int page = 1;
    private int pageNubmer = 10;
    private String departmentId = "100";
    public String type = "";
    private NoticeMainListPresenterImpl noticeMainListPresenterImpl;
    private ArrayList<NoticeMainListBean> listBeenData = new ArrayList<>();

    NewsListBean newsListBean;
    NewsListPresenterImpl newsListPresenterImpl;

    public boolean isXiaoyulin() {
        return xiaoyulin;
    }

    public void setXiaoyulin(boolean xiaoyulin) {
        this.xiaoyulin = xiaoyulin;
    }

    public boolean xiaoyulin = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news_vanlian;
    }


    @Override
    public void returnData(String responseCode, Object o) {
        switch (responseCode) {
            case "getNewsList":
//                newsListBean = (NewsListBean) o;
//                newsListBean.getResult().getData();
//                if (page == 1) {
//                    noticeMainFragmentAdapter.setNewData(newsListBean.getResult().getData());
////            listBeenData.addAll(noticeMainListBean.getList());
////            noticeMainFragmentAdapter.setNewData(listBeenData);
////            mSwipeRefreshLayout.setRefreshing(false);
//                } else {
////            listBeenData.addAll(noticeMainListBean.getList());
////            noticeMainFragmentAdapter.setNewData(listBeenData);
//                    noticeMainFragmentAdapter.addData(newsListBean.getResult().getData());
//                    noticeMainFragmentAdapter.loadMoreComplete();
//                }
                break;
            case "homch":
                ChannelBean channelBean = (ChannelBean) o;
                List<ChannelBean.DataBean> dataList = channelBean.getData();
                ChannelBean.DataBean dataBean = new ChannelBean.DataBean();
                dataBean.setType("1");
                dataList.add(dataBean);
                if (page == 1) {
                    noticeMainFragmentAdapter.setNewData(dataList);
//            listBeenData.addAll(noticeMainListBean.getList());
//            noticeMainFragmentAdapter.setNewData(listBeenData);
//            mSwipeRefreshLayout.setRefreshing(false);
                } else {
//            listBeenData.addAll(noticeMainListBean.getList());
//            noticeMainFragmentAdapter.setNewData(listBeenData);
                    noticeMainFragmentAdapter.addData(channelBean.getData());
                    noticeMainFragmentAdapter.loadMoreComplete();
                }

                break;
        }


//        if (noticeMainListBean.getList().size() < pageNubmer) {
//            noticeMainFragmentAdapter.loadMoreEnd();
//            noticeMainFragmentAdapter.removeAllFooterView();
//        }

    }

    @Override
    public void showError(String msg) {
//        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    protected void init(Bundle savedInstanceState, View contentView) {
        findView();
        initAdapter();
        recyvleViewScrollLister();
        noticeMainListPresenterImpl = new NoticeMainListPresenterImpl(this);

    }



    private void http() {
//        newsListPresenterImpl = new NewsListPresenterImpl(this);
//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("type", ChooseTypeUtils.getNewType(type));
//        newsListPresenterImpl.getNewsList(getActivity(), stringStringHashMap);

        UcDataPresenterImpl ucDataPresenter = new UcDataPresenterImpl(this);
//            UCParamUtils ucParamUtils = new UCParamUtils(getContext());
////            HashMap<String, String> ucParamHashmap = ucParamUtils.getUcParamHashmap();
        HashMap<String, String> ucParamHashmap = new HashMap<>();
        ucParamHashmap.put("token",MainActivity.MYTOKEN);
        ucParamHashmap.put("apdid", MainActivity.APDIDP);
        ucParamHashmap.put("catid", departmentId);
        ucParamHashmap.put("pageSize", "3");
        ucParamHashmap.put("p", page+"");
        ucDataPresenter.homch(getActivity(),ucParamHashmap);


    }


    public boolean isTabVisible = true;
    private int totalDy = 0;


    private void recyvleViewScrollLister() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!recyclerView.canScrollVertically(1)) {
//                        callback.onScrollToBottom();
                    }
                    if (!recyclerView.canScrollVertically(-1)) {
                        Log.e("stopScroll", "stopScroll");
                        mRecyclerView.stopScroll();
                    }
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalDy -= dy;
                if (totalDy < 0) {
                    if (xiaoyulin) {
                        mRecyclerView.stopScroll();
                        if(MainActivity.backTitle){
                            xiaoyulin = true;
                            resateRecycle();
                            return;
                        }

//
                        Activity activity = (Activity) getActivity();
                        if(activity instanceof MainActivity){
                            MainActivity mainActivity = (MainActivity) activity;
                            mainActivity.hitiTitle(false);
                            mainActivity.appBarView(false);
                            mainActivity.bottomView(true);
                        }else {
                            bootomVisibleListener.setBootomVisible(true);
//                            taybarVisibleListener.setTaybarVisible(false);
                        }

                        xiaoyulin = false;
                    }

                } else {
//                    mRecyclerView.stopScroll();
                    xiaoyulin = true;
//                    bootomVisibleListener.setBootomVisible(false);
//                    taybarVisibleListener.setTaybarVisible(true);
                    //滑到顶部
                }


            }
        });

    }

    MyLinearLayoutManager1 linearLayoutManager;

    private void initAdapter() {
        linearLayoutManager = new MyLinearLayoutManager1(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        noticeMainFragmentAdapter = new NoticeMainFragmentAdapter(getActivity());
//        mRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
        mRecyclerView.setAdapter(noticeMainFragmentAdapter);


        //设置加载
//        noticeMainFragmentAdapter.setEnableLoadMore(true);
//        noticeMainFragmentAdapter.seton(this);
//        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(87, 136, 255));
        noticeMainFragmentAdapter.setOnLoadMoreListener(this, mRecyclerView);
//        noticeMainFragmentAdapter.disableLoadMoreIfNotFullPage();
//        noticeMainFragmentAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        noticeMainFragmentAdapter.setOnItemClickListener(this);
        noticeMainFragmentAdapter.setOnItemChildClickListener(this);
        noticeMainFragmentAdapter.setOnItemLongClickListener(this);
        noticeMainFragmentAdapter.setLoadMoreView(new CustomLoadMoreViewNews());
//        noticeMainFragmentAdapter.setEmptyView(getEmptyView());
//        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_news_main_footview, null);
//        noticeMainFragmentAdapter.setFooterView(layout);

        noticeMainFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ChannelBean.DataBean newsListBean = (ChannelBean.DataBean) adapter.getData().get(position);
                String url = newsListBean.getUrl();
                Intent intent = new Intent(getActivity(), NoticeDetatilActivity.class);
                intent.putExtra("url", url);
                getActivity().startActivity(intent);

            }
        });

    }


    private void findView() {
//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout_vanlian_new);
        if (getArguments() != null) {
            departmentId = getArguments().getString("departmentId");
            type = getArguments().getString("type");
        }
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        onRefresh();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onRefresh() {
//        mSwipeRefreshLayout.setRefreshing(true);
        page = 1;
        http();
//        mSwipeRefreshLayout.setRefreshing(false);
        initNativeExpressAD("1");
    }

    @Override
    public void onResume() {
        super.onResume();
//        onRefresh();
    }

//    private View getEmptyView() {
//        View empty = LayoutInflater.from(getActivity()).inflate(R.layout.list_empty_view, (ViewGroup) mRecyclerView.getParent(), false);
//        return empty;
//    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        http();

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        NoticeMainListBean.ListBean listBean = listBeenData.get(position);
        Intent intent = new Intent(getActivity(), NoticeDetatilActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("onStart", "onStart");
    }

    public MainFragment.BootomVisibleListener bootomVisibleListener;

    public void setBootomVisibleListener(MainFragment.BootomVisibleListener bootomVisibleListener) {
        this.bootomVisibleListener = bootomVisibleListener;
    }

    public MainFragment.TaybarVisibleListener taybarVisibleListener;

    public void setTaybarVisibleListener(MainFragment.TaybarVisibleListener taybarVisibleListener) {
        this.taybarVisibleListener = taybarVisibleListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private GestureDetector mGestureDetector;



    @Override
    public void onDestroyView() {
        Log.e("onDestroy", "nocitefragmet++onDestroyView++"+departmentId+"+++"+type);
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        Log.e("onDestroy", "nocitefragmet++onDestroy++"+departmentId+"+++"+type);
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    public void resateRecycle() {
//        RecyclerView recyclerView  = (RecyclerView) findViewById(R.id.recycle);
        Log.e("onDestroy", "nocitefragmet++resateRecycle++"+departmentId+"+++"+type);
        if (mRecyclerView != null) {
            Log.e("mainFragment", "有");
            mRecyclerView.scrollBy(0, totalDy - 100);
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(EventTitleMessage titleMessage) {
        String message = titleMessage.getMessage();
        Log.e("message",message);
        if (mRecyclerView != null) {
            mRecyclerView.scrollBy(0, totalDy - 100);
        }

    }
    public int guanggaoPosition = 0 ;
    List<NativeExpressADView> listData = new ArrayList<NativeExpressADView>();
    @Override
    public void onADLoaded(List<NativeExpressADView> list) {
        Log.e("onADLoaded", "onADLoaded: " + list.size());
        if(listData.size()>=20){
            listData.clear();
        }
        if(guanggaoPosition==0){
            listData.addAll(list);
            initNativeExpressAD("2");
            guanggaoPosition = 1;
        }else {
            listData.addAll(list);
            noticeMainFragmentAdapter.setArray(listData);
            noticeMainFragmentAdapter.notifyDataSetChanged();
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
    private NativeExpressAD mADManager,mADManager2;
    private List<NativeExpressADView> mAdViewList;
    public static final int AD_COUNT = 10;
    /**
     * 如果选择支持视频的模版样式，请使用{@link PositionId#NATIVE_EXPRESS_SUPPORT_VIDEO_POS_ID}
     */
    private void initNativeExpressAD(String string) {
        ADSize adSize = new ADSize(ADSize.FULL_WIDTH, ADSize.AUTO_HEIGHT);
        if(string.equals("1")){
             // 消息流中用AUTO_HEIGHT
//        mADManager = new NativeExpressAD(getActivity(), adSize, "1101152570", "7030020348049331", this);
            mADManager = new NativeExpressAD(getActivity(), adSize, ConstantCode.GGAPPID, GGPositionId.MAIN_RIGHTIMAGE_POS_ID, this);


            VideoOption option = null;
            boolean noneOption = false;
            if (!noneOption) {
                VideoOption.Builder builder = new VideoOption.Builder();

                builder.setAutoPlayPolicy(VideoOption.AutoPlayPolicy.ALWAYS);
                builder.setAutoPlayMuted(true);
                builder.setDetailPageMuted(true);

                option = builder.build();
            }
            if(option != null){
                // setVideoOption是可选的，开发者可根据需要选择是否配置
                mADManager.setVideoOption(option);
            }

            mADManager.setMinVideoDuration(10);
            mADManager.setMaxVideoDuration(50);
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
            mADManager.setVideoPlayPolicy(1);  // 本次拉回的视频广告，在用户看来是否为自动播放的
            mADManager.loadAD(AD_COUNT);
        }else {
            mADManager2 = new NativeExpressAD(getActivity(), adSize, ConstantCode.GGAPPID, GGPositionId.ZIDINGYI, this); // 这里的Context必须为Activity
            mADManager2.loadAD(AD_COUNT);
        }

    }

    private NativeExpressMediaListener mediaListener = new NativeExpressMediaListener() {
        @Override
        public void onVideoInit(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoInit: "
                    + getVideoInfo(nativeExpressADView.getBoundData().getProperty(AdData.VideoPlayer.class)));
        }

        @Override
        public void onVideoLoading(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoLoading: "
                    + getVideoInfo(nativeExpressADView.getBoundData().getProperty(AdData.VideoPlayer.class)));
        }

        @Override
        public void onVideoCached(NativeExpressADView nativeExpressADView) {
            Log.i(TAG, "onVideoCached: "
                    + getVideoInfo(nativeExpressADView.getBoundData().getProperty(AdData.VideoPlayer.class)));
        }

        @Override
        public void onVideoReady(NativeExpressADView nativeExpressADView, long l) {
            Log.i(TAG, "onVideoReady: "
                    + getVideoInfo(nativeExpressADView.getBoundData().getProperty(AdData.VideoPlayer.class)));
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

    private String getVideoInfo(AdData.VideoPlayer videoPlayer) {
        if (videoPlayer != null) {
            StringBuilder videoBuilder = new StringBuilder();
            videoBuilder.append("state:").append(videoPlayer.getVideoState()).append(",")
                    .append("duration:").append(videoPlayer.getDuration()).append(",")
                    .append("position:").append(videoPlayer.getCurrentPosition());
            return videoBuilder.toString();
        }
        return null;
    }
}
