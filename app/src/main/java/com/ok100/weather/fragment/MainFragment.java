package com.ok100.weather.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.activity.SelectPicPopupWindowActivity;
import com.ok100.weather.activity.WelcomeActivity;
import com.ok100.weather.adapter.MainTodayFuwuAdapter;
import com.ok100.weather.adapter.MainTodaySuggestAdapter;
import com.ok100.weather.adapter.NoticeMainFragmentItemAdapter;
import com.ok100.weather.adapter.Weather15MianAdapter;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.ChannelsBean;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.bean.DepartmentListBean;
import com.ok100.weather.bean.EventTitleMessage;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.NoticeMainChooseBean;
import com.ok100.weather.bean.SuggestGridViewBean;
import com.ok100.weather.bean.TianqiyubaoBean;
import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.constant.ConstantCode;
import com.ok100.weather.constant.GGPositionId;
import com.ok100.weather.event.EventGotoNewsMessage;
import com.ok100.weather.gh.AirDialogFragment;
import com.ok100.weather.gh.GH_DefaultDialogFragment;
import com.ok100.weather.gh.GH_MapActivity;
import com.ok100.weather.hours24.IndexHorizontalScrollView;
import com.ok100.weather.hours24.Today24HourView;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.Urls;
import com.ok100.weather.presenter.NewsListPresenterImpl;
import com.ok100.weather.presenter.NoticeMainListPresenterImpl;
import com.ok100.weather.presenter.UcDataPresenterImpl;
import com.ok100.weather.utils.ChooseTypeUtils;
import com.ok100.weather.utils.DPUtils;
import com.ok100.weather.utils.GGDemoUtil;
import com.ok100.weather.utils.UCParamUtils;
import com.ok100.weather.view.MySwipeRefreshLayout;
import com.ok100.weather.view.MyViewPager;
import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;
import com.qq.e.ads.nativ.ADSize;
import com.qq.e.ads.nativ.NativeAD;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.ads.nativ.NativeExpressMediaListener;
import com.qq.e.comm.constants.AdPatternType;
import com.qq.e.comm.pi.AdData;
import com.qq.e.comm.util.AdError;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.zhouzhuo.zzweatherview.AirLevel;
import me.zhouzhuo.zzweatherview.WeatherItemView;
import me.zhouzhuo.zzweatherview.WeatherModel;
import me.zhouzhuo.zzweatherview.ZzWeatherView;


public class MainFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener, ReturnDataView<Object>, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener , NativeExpressAD.NativeExpressADListener ,UnifiedBannerADListener{


    @BindView(R.id.tv_weather_temp)
    TextView mTvWeatherTemp;
    @BindView(R.id.tv_weater_xiao)
    TextView mTvWeaterXiao;
    @BindView(R.id.tv_shidu_xiao)
    TextView mTvShiduXiao;
    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.linearlayout1)
    LinearLayout mLinearlayout1;
    //    @BindView(R.id.rl_second_view)
//    RelativeLayout mRlSecondView;
    @BindView(R.id.tv_liebiao)
    TextView mTvLiebiao;
    @BindView(R.id.tv_qushi)
    TextView mTvQushi;
    @BindView(R.id.weather_view)
    ZzWeatherView weatherView;
    @BindView(R.id.recyclerview_15weather)
    RecyclerView mRecyclerview15weather;

    @BindView(R.id.recyclerview_today_suggest)
    RecyclerView mRecyclerviewTodaySuggest;

    @BindView(R.id.ll_notice_main_more_item)
    LinearLayout mLlNoticeMainMoreItem;
    @BindView(R.id.rl_title_bar)
    RelativeLayout mRlTitleBar;
    @BindView(R.id.viewPager)
    MyViewPager viewPager;
    @BindView(R.id.container)
    ViewGroup container;
    @BindView(R.id.container2)
    ViewGroup container2;
    @BindView(R.id.today24HourView)
    Today24HourView mToday24HourView;
    @BindView(R.id.indexHorizontalScrollView)
    IndexHorizontalScrollView mIndexHorizontalScrollView;
    //    @BindView(R.id.ll_scroll_all)
//    LinearLayout mLlScrollAll;
//    @BindView(R.id.ll_all_gone_view)
//    LinearLayout mLlAllGoneView;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.swipeRefreshLayout_vanlian_new)
    MySwipeRefreshLayout mSwipeRefreshLayoutVanlianNew;

    @BindView(R.id.rl_main_bottom)
    RelativeLayout mRlMainBottom;
    @BindView(R.id.iv_updata_list)
    ImageView mIvUpdataList;
    @BindView(R.id.iv_shipin_list)
    ImageView mIvShipinList;


    @BindView(R.id.recyclerview_today_fuwu)
    RecyclerView mRecyclerviewTodayFuwu;
    Unbinder unbinder1;
    @BindView(R.id.ll_weather_view)
    LinearLayout mLlWeatherView;
    @BindView(R.id.tv_kongqizhiliang)
    TextView mTvKongqizhiliang;
    @BindView(R.id.tv_bottom_today_wendu)
    TextView mTvBottomTodayWendu;
    @BindView(R.id.tv_bottom_today_weather)
    TextView mTvBottomTodayWeather;
    @BindView(R.id.tv_bottom_today_feng)
    TextView mTvBottomTodayFeng;
    @BindView(R.id.tv_bottom_tomorrow_wendu)
    TextView mTvBottomTomorrowWendu;
    @BindView(R.id.tv_bottom_tomorrow_weather)
    TextView mTvBottomTomorrowWeather;
    @BindView(R.id.tv_bottom_tomorrow_feng)
    TextView mTvBottomTomorrowFeng;
    @BindView(R.id.tv_richu_time)
    TextView mTvRichuTime;
    @BindView(R.id.tv_riluo_time)
    TextView mTvRiluoTime;
    @BindView(R.id.bannerContainer)
    ViewGroup bannerContainer;

    @BindView(R.id.ll_guanggao1)
    LinearLayout ll_guanggao1;

    @BindView(R.id.tv_guanggaol)
    TextView tv_guanggaol;
    @BindView(R.id.tv_guanggao2)
    TextView tv_guanggao2;
    @BindView(R.id.iv_guanggao2)
    ImageView iv_guanggao2;
    @BindView(R.id.iv_guanggao1)
    ImageView iv_guanggao1;
    @BindView(R.id.ll_guanggao_left)
    LinearLayout ll_guanggao_left;
    @BindView(R.id.ll_guanggao_right)
    LinearLayout ll_guanggao_right;
    @BindView(R.id.ll_recycle_bottom)
    LinearLayout ll_recycle_bottom;


    private static final String TAG = "MainFragment";
    private LinearLayout ll_notice_main_more_item;
    private List<ViewPagerDataSource> viewPagerDataSourceList = new ArrayList<>();

    private ArrayList<NoticeMainChooseBean> noticeMainChooseBeanList = new ArrayList<NoticeMainChooseBean>();
    private FragmentStatePagerAdapter fragmentPagerAdapter;
    private NoticeMainFragmentItemAdapter noticeMainFragmentItemAdapter;

    List<DepartmentListBean> departmentListBeans = new ArrayList<>();
    ArrayList<String> departmentListBeansString = new ArrayList<>();
    Weather15MianAdapter weather15MianAdapter;


    public String city;
    public String prov;
    public String area;

    public static final String CITY = "city";
    public static final String PROV = "prov";
    public static final String AREA = "area";

    NewsListPresenterImpl newsListPresenterImpl ;
    WeatherTotalBean weatherTotalBean ;

    public boolean weather7 = false;
    public boolean weather15 = false;
    WeatherTotal15Bean weatherTotal15Bean ;
    WeatherTotal7Bean weatherTotal7Bean ;
    MainTodaySuggestAdapter mainTodaySuggestAdapter;

    ChannelsBean channelsBean ;

    @Override
    protected int getLayoutID() {
        city = getArguments().getString(CITY);
        prov = getArguments().getString(PROV);
        area = getArguments().getString(AREA);
        return R.layout.fragment_main;
    }


    public static MainFragment newInstance(String prov, String city, String area) {

        Bundle args = new Bundle();
        args.putString(PROV, prov);
        args.putString(CITY, city);
        args.putString(AREA, area);
        Log.e("xxxx",city+prov+area);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_liebiao:
                mRecyclerview15weather.setVisibility(View.VISIBLE);
                mLlWeatherView.setVisibility(View.GONE);
                mTvLiebiao.setTextColor(Color.parseColor("#FFFFFF"));
                mTvLiebiao.setTextSize(DPUtils.dip2px(getActivity(), 8));
                mTvQushi.setTextColor(Color.parseColor("#aaFFFFFF"));
                mTvQushi.setTextSize(DPUtils.dip2px(getActivity(), 7));
                break;
            case R.id.tv_qushi:
                mRecyclerview15weather.setVisibility(View.GONE);
                mLlWeatherView.setVisibility(View.VISIBLE);
                mTvLiebiao.setTextColor(Color.parseColor("#aaFFFFFF"));
                mTvLiebiao.setTextSize(DPUtils.dip2px(getActivity(), 7));
                mTvQushi.setTextColor(Color.parseColor("#FFFFFF"));
                mTvQushi.setTextSize(DPUtils.dip2px(getActivity(), 8));
                break;
            case R.id.ll_notice_main_more_item:
                departmentListBeansString.clear();
                for (int i = 0; i < departmentListBeans.size(); i++) {
                    departmentListBeansString.add(departmentListBeans.get(i).getDepartmentName());
                }
                intent = new Intent(getActivity(), SelectPicPopupWindowActivity.class);
                intent.putStringArrayListExtra("listdata", departmentListBeansString);
                startActivity(intent);

                SelectPicPopupWindowActivity.itemTabListener = new setItemTabListener() {
                    @Override
                    public void setItemPosition(int position) {
                        mTabLayout.getTabAt(position).select();
                    }
                };
                break;
            case R.id.iv_updata_list:
                mSwipeRefreshLayoutVanlianNew.setRefreshing(true);
                mSwipeRefreshLayoutVanlianNew.setRefreshing(false);
                selectFragment.onRefresh();
                break;
            case R.id.iv_shipin_list:
                mTabLayout.getTabAt(1).select();

                break;
            case R.id.rl_main_bottom:
//                mTabLayout.getTabAt(1).select();
                break;
            case R.id.tv_weather_temp:
//                mTabLayout.getTabAt(1).select();
//                AirDialogFragment.access(getFragmentManager());
                if(weatherTotal7Bean!=null){
                    AirDialogFragment.access(getChildFragmentManager(),weatherTotal7Bean);
                    MobclickAgent.onEvent(getActivity(), "clickTemDetailController");
                }
                break;
            case R.id.tv_shidu_xiao:
                if(weatherTotalBean!=null){
                GH_MapActivity.access(getActivity(),departmentListBeansString,weatherTotalBean);
                    MobclickAgent.onEvent(getActivity(), "clickTodayDetailController");
                }

                break;

        }
    }



    public interface setItemTabListener {
        void setItemPosition(int position);
    }


    @Override
    public void showError(String msg) {

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void init(Bundle savedInstanceState, View contentView) {
        Log.e("onDestroy", "mainfragmetn++init++"+city+"+++"+area);
        findView();
        setMianRelativeHeight();
        getPhoneHeight();
        mCoordinatorLayout.getBackground().setAlpha(0);
        mSwipeRefreshLayoutVanlianNew.setOnRefreshListener(this);
        weather15MianAdapter = new Weather15MianAdapter();
        mRecyclerview15weather.setAdapter(weather15MianAdapter);
        mRecyclerview15weather.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerview15weather.setNestedScrollingEnabled(false);//禁止滑动

        mRecyclerviewTodaySuggest.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mainTodaySuggestAdapter = new MainTodaySuggestAdapter();

        mRecyclerviewTodaySuggest.setAdapter(mainTodaySuggestAdapter);
        mRecyclerviewTodaySuggest.setNestedScrollingEnabled(false);//禁止滑动
        mainTodaySuggestAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SuggestGridViewBean bean  = (SuggestGridViewBean) adapter.getData().get(position);
                GH_DefaultDialogFragment access = GH_DefaultDialogFragment.access(getChildFragmentManager());
                access.setTitle(bean.getName1(),bean.getName2(),bean.getName3());
                MobclickAgent.onEvent(getActivity(), "clickShowTheSuggestView");
            }
        });

        mRecyclerviewTodayFuwu.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        MainTodayFuwuAdapter mainTodayFuwuAdapter = new MainTodayFuwuAdapter();
        mainTodayFuwuAdapter.setNewData(DataBean.generateData());
        mRecyclerviewTodayFuwu.setAdapter(mainTodayFuwuAdapter);
        mRecyclerviewTodayFuwu.setNestedScrollingEnabled(false);//禁止滑动
        //gif圖片
//        Glide.with(this).load(R.drawable.guanggaodemo).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvGuanggaoDonghua);
        refreshAd("1");

    }

    public int phoneHeight = 1280 ;
    public void getPhoneHeight(){
        WindowManager wm = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        phoneHeight = wm.getDefaultDisplay().getHeight();
    }




    private void initTablayout() {
        mTabLayout.setupWithViewPager(viewPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
                View view = tab.getCustomView();
                if (null != view && view instanceof TextView) {
                    ((TextView) view).setTextSize(19);
                    ((TextView) view).setTextColor(ContextCompat.getColor(getActivity(), R.color.green_defult));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void findView() {

        mIndexHorizontalScrollView.setToday24HourView(mToday24HourView);


        ll_notice_main_more_item = (LinearLayout) findViewById(R.id.ll_notice_main_more_item);
        ll_notice_main_more_item.setOnClickListener(this);
        mTvQushi.setOnClickListener(this);
        mTvLiebiao.setOnClickListener(this);
        mIvShipinList.setOnClickListener(this);
        mTvShiduXiao.setOnClickListener(this);
        mRlMainBottom.setOnClickListener(this);
        mTvWeatherTemp.setOnClickListener(this);
        mIvUpdataList.setOnClickListener(this);
    }


    public void setOnClickItem(int pisition) {
        mTabLayout.getTabAt(pisition).select();
    }

//    public interface setItemTab;


    private void setMianRelativeHeight() {

        ViewGroup.LayoutParams pp = relativeLayout1.getLayoutParams();
        //mrlayt.getLayoutParams();
        WindowManager wm = getActivity().getWindowManager();
        Display d = wm.getDefaultDisplay();
        pp.height = d.getHeight() - getStateBar3() - DPUtils.dip2px(getActivity(), 60);
//        pp.height = d.getHeight() - getStateBar3();
        relativeLayout1.setLayoutParams(pp);
    }

    private int mianTabHeight = 0;


    //状态栏高度
    private int getStateBar3() {
        int result = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = this.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void lazyLoad() {
        super.lazyLoad();
//        onRefresh();

        initData();

        http();
        getHideType();
            }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }


    @Override
    public void onRefresh() {
        mSwipeRefreshLayoutVanlianNew.setRefreshing(false);
        selectFragment.onRefresh();
    }

    private void initData() {
        //填充天气数据
        //画折线
//        weatherView.setLineType(ZzWeatherView.LINE_TYPE_DISCOUNT);
        //画曲线
        weatherView.setLineType(ZzWeatherView.LINE_TYPE_CURVE);
        //设置线宽
        weatherView.setLineWidth(4);
        //设置一屏幕显示几列(最少3列)
        try {
            weatherView.setColumnNumber(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置白天和晚上线条的颜色
        weatherView.setDayAndNightLineColor(Color.WHITE, Color.WHITE);

        //点击某一列
        weatherView.setOnWeatherItemClickListener(new ZzWeatherView.OnWeatherItemClickListener() {
            @Override
            public void onItemClick(WeatherItemView itemView, int position, WeatherModel weatherModel) {
//                Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
            }
        });

        doRefreshBanner();
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setNewArraylist() {
        getTitleData();
        initFragment();
        initViewPager();
        initTablayout();
//        CoordinatorLayout.LayoutParams linearParams =(CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
//        linearParams.height=5000;
//        mAppBarLayout.setLayoutParams(linearParams);

        View mAppBarChildAt = mAppBarLayout.getChildAt(0);
        AppBarLayout.LayoutParams  mAppBarParams = (AppBarLayout.LayoutParams)
                mAppBarChildAt.getLayoutParams();
//        mAppBarParams.setScrollFlags(0);

        mAppBarParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        mAppBarChildAt.setLayoutParams(mAppBarParams);



        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                scrollToBottom(verticalOffset);
                int jvli = (int) -verticalOffset/2;
                if(jvli<180){
                    mCoordinatorLayout.getBackground().setAlpha(jvli);
                }
            }
        });
    }

    private void getTitleData() {
        departmentListBeans.clear();
        for (int i = 0; i < channelsBean.getData().size(); i++) {
            DepartmentListBean departmentListBean = new DepartmentListBean(channelsBean.getData().get(i).getCatname());
            departmentListBeans.add(departmentListBean);
        }
        getTitleListData(departmentListBeans);
    }


    NoticeMainFragment1 selectFragment ;
    private void initViewPager() {
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(fragmentPagerAdapter);

//        pagerTabStrip.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
//            @Override
//            public void onTabReselected(int position) {
//                viewPagerDataSourceList.get(position).getTitleListData().getTitleName();
//            }
//        });
//        viewPager.setOffscreenPageLimit(fragmentPagerAdapter.getCount());
        selectFragment = (NoticeMainFragment1) viewPagerDataSourceList.get(0).getFragment();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("position", position + "");
                selectFragment = (NoticeMainFragment1) viewPagerDataSourceList.get(position).getFragment();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initFragment() {

        fragmentPagerAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return viewPagerDataSourceList.get(position).getFragment();
            }

            @Override
            public int getCount() {
                return viewPagerDataSourceList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return viewPagerDataSourceList.get(position).getTitleListData().getTitleName();
            }
        };
    }
    NoticeMainFragment1 fragment;
    private void getTitleListData(List<DepartmentListBean> departmentListBean) {
        TitleListData titleListData;
        viewPagerDataSourceList.clear();
        for (int i = 0; i < departmentListBean.size(); i++) {
            fragment = new NoticeMainFragment1();
            Bundle args = new Bundle();
            args.putString("departmentId", channelsBean.getData().get(i).getCatid());
            args.putString("type", channelsBean.getData().get(i).getCatname());
            fragment.setArguments(args);
            titleListData = new TitleListData(channelsBean.getData().get(i).getCatname());
            viewPagerDataSourceList.add(new ViewPagerDataSource(fragment, titleListData));
            ((NoticeMainFragment1) fragment).setBootomVisibleListener(new BootomVisibleListener() {
                @Override
                public void setBootomVisible(boolean visible) {
                    setBottomVisible(visible);

//                    mAppBarLayout.setVisibility(View.GONE);

                }
            });
            ((NoticeMainFragment1) fragment).setTaybarVisibleListener(new TaybarVisibleListener() {

                @Override
                public void setTaybarVisible(boolean visible) {
                    setAllGoneViewVisible(visible);

                }
            });
        }
    }


    private class TitleListData {
        private String titleName;

        public TitleListData(String titleName) {
            this.titleName = titleName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }

//    private View getEmptyView() {
//        View empty = LayoutInflater.from(getActivity()).inflate(R.layout.list_empty_view, (ViewGroup) mRecyclerView.getParent(), false);
//        return empty;
//    }

    @Override
    public void onLoadMoreRequested() {


    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }


    private class ViewPagerDataSource {
        private Fragment fragment;
        private TitleListData titleListData;

        public ViewPagerDataSource(Fragment fragment, TitleListData titleListData) {
            this.fragment = fragment;
            this.titleListData = titleListData;
        }


        public Fragment getFragment() {
            return fragment;
        }

        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }

        public TitleListData getTitleListData() {
            return titleListData;
        }

        public void setTitleListData(TitleListData titleListData) {
            this.titleListData = titleListData;
        }
    }

    public void setBottomVisible(boolean visible) {
        if (visible) {
            mRlMainBottom.setVisibility(View.VISIBLE);
        } else {
            mRlMainBottom.setVisibility(View.GONE);
        }

    }


    public interface BootomVisibleListener {
        void setBootomVisible(boolean visible);
    }

    public interface TaybarVisibleListener {
        void setTaybarVisible(boolean visible);
    }


    public void setAllGoneViewVisible(boolean visible) {
        if (visible) {

            mAppBarLayout.setVisibility(View.VISIBLE);
            CoordinatorLayout.Behavior behavior =
                    ((CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams()).getBehavior();
            if (behavior instanceof AppBarLayout.Behavior) {
                AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
                int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
                Log.e("visible", "mAppBarLayout显示"+topAndBottomOffset);
                if (topAndBottomOffset != 0) {
                    appBarLayoutBehavior.setTopAndBottomOffset(0);
                }
            }
            Log.e("viewPagerDataSourceList",viewPagerDataSourceList.size()+area);
            for (int i = 0; i < viewPagerDataSourceList.size(); i++) {
                NoticeMainFragment1 getFragment = (NoticeMainFragment1) viewPagerDataSourceList.get(i).getFragment();
                Log.e("NoticeMainFragment1",getFragment.type+"----");
                getFragment.resateRecycle();
                getFragment.xiaoyulin = true;
            }

        } else {
            mAppBarLayout.setVisibility(View.GONE);
            Log.e("visible", "mAppBarLayout隐藏");
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void returnData(String responseCode, Object o) {
        switch (responseCode) {
            case "getTotalWeather":
                weatherTotalBean = (WeatherTotalBean) o;
                if(weatherTotalBean==null){
                    return;
                }
                Log.e("weatherTotalBean", weatherTotalBean.toString());
                setWeatherData(weatherTotalBean);
                mToday24HourView.invalidate();
                mToday24HourView.postInvalidate();
                break;
            case "getTotalWeather15":
                weather15 = true;
                weatherTotal15Bean = (WeatherTotal15Bean) o;
                if(weatherTotal15Bean==null){
                    return;
                }
                if(weather7){
                    List<WeatherModel> arraylist = new ArrayList<WeatherModel>();
                    arraylist.addAll(generateData7(weatherTotal7Bean));
                    arraylist.addAll(generateData15(weatherTotal15Bean));
                    weather15MianAdapter.setNewData(arraylist);
                    weatherView.setList(arraylist);
                }
                break;
            case "getTotalWeather7":
                weather7 = true;
                weatherTotal7Bean = (WeatherTotal7Bean) o;
                if(weatherTotal7Bean==null){
                    return;
                }
                if(weather15){
                    List<WeatherModel> arraylist = new ArrayList<WeatherModel>();
                    arraylist.addAll(generateData7(weatherTotal7Bean));
                    arraylist.addAll(generateData15(weatherTotal15Bean));
                    weather15MianAdapter.setNewData(arraylist);
                    weatherView.setList(arraylist);
                }
                setTomorrowData(weatherTotal7Bean);
                setAirDialogFragment(weatherTotal7Bean);
                setSuggettAdapter(weatherTotal7Bean);

                break;

            case "getTotalWeather24":
                WeatherTotal24Bean getTotalWeather24 = (WeatherTotal24Bean) o;
                if(getTotalWeather24==null){
                    return;
                }
                ArrayList<Integer> tempList = new ArrayList<Integer>();
                ArrayList<Integer> windyList = new ArrayList<Integer>();
                ArrayList<Integer> resList = new ArrayList<Integer>();
                mToday24HourView.tempList.clear();
                mToday24HourView.windyList.clear();
                mToday24HourView.resList.clear();
                for(int i = 0;i<getTotalWeather24.getData().getHour().size();i++){
                    mToday24HourView.tempList.add(Integer.parseInt(getTotalWeather24.getData().getHour().get(i).getTemperature()));
                    List<String> stringList = stringToInt(getTotalWeather24.getData().getHour().get(i).getWind_power());
                    if(stringList!=null&&stringList.size()>0){
                        mToday24HourView.windyList.add(Integer.parseInt(stringList.get(0)));
                    }
                    String weather = getTotalWeather24.getData().getHour().get(i).getWeather();
                    if(weather.contains("晴")){
                        mToday24HourView.resList.add(R.mipmap.icon_qing);
                    }else if(weather.contains("云")){
                        mToday24HourView.resList.add(R.mipmap.icon_duoyun);
                    }else if(weather.contains("雾")){
                        mToday24HourView.resList.add(R.mipmap.icon_wu);
                    }else if(weather.contains("雨")){
                        mToday24HourView.resList.add(R.mipmap.icon_xiaoyu);
                    }else if(weather.contains("雪")){
                        mToday24HourView.resList.add(R.mipmap.icon_xiaoxue);
                    }else {
                        mToday24HourView.resList.add(R.mipmap.icon_qing);
                    }
                    String time = getTotalWeather24.getData().getHour().get(i).getTime();
                    time = time.substring(time.length()-4,time.length()-2)+":"+time.substring(time.length()-2,time.length());
                    mToday24HourView.timeList.add(time);
                }
                mToday24HourView.setData();
                mToday24HourView.invalidate();
                break;
            case "channels":
                channelsBean = (ChannelsBean) o;
                if(channelsBean.getCode().equals("200")){
                    setNewArraylist();
                    mTabLayout.setSelectedTabIndicatorColor(Color.BLUE);
                }

                break;
            case "getTianqiyubao":
                TianqiyubaoBean tianqiyubaoBean = (TianqiyubaoBean)o;
                if(tianqiyubaoBean.getCode()==1){
                    int status = tianqiyubaoBean.getData().getStatus();
                    //关闭
                    if(status==0){
                        ll_recycle_bottom.setVisibility(View.GONE);
                        isShowNews = false;
                    }else {
                        ll_recycle_bottom.setVisibility(View.VISIBLE);
                        isShowNews = true;
                    }
                }

                break;
        }
    }

    private void setSuggettAdapter(WeatherTotal7Bean weatherTotal7Bean) {
        ArrayList<SuggestGridViewBean> suggestGridViewBeanList = new ArrayList<>();
        SuggestGridViewBean suggestGridViewBean ;
        WeatherTotal7Bean.DataBean.Day7Bean.LiveBean live = weatherTotal7Bean.getData().getDay7().get(0).getLive();
        for (int i= 0 ;i<13;i++){
        switch (i){
            case 1:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getAc().getName());
                suggestGridViewBean.setName2(live.getAc().getTitle());
                suggestGridViewBean.setName3(live.getAc().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_kongtiao);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 2:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getAg().getName());
                suggestGridViewBean.setName2(live.getAg().getTitle());
                suggestGridViewBean.setName3(live.getAg().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_guomin);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 3:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getBl().getName());
                suggestGridViewBean.setName2(live.getBl().getTitle());
                suggestGridViewBean.setName3(live.getBl().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_xuetang);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 4:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getCl().getName());
                suggestGridViewBean.setName2(live.getCl().getTitle());
                suggestGridViewBean.setName3(live.getCl().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_chenlian);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 5:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getCo().getName());
                suggestGridViewBean.setName2(live.getCo().getTitle());
                suggestGridViewBean.setName3(live.getCo().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_shushidu);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 6:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getDy().getName());
                suggestGridViewBean.setName2(live.getDy().getTitle());
                suggestGridViewBean.setName3(live.getDy().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_diaoyu);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 7:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getFs().getName());
                suggestGridViewBean.setName2(live.getFs().getTitle());
                suggestGridViewBean.setName3(live.getFs().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_fangshai);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 8:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getGj().getName());
                suggestGridViewBean.setName2(live.getGj().getTitle());
                suggestGridViewBean.setName3(live.getGj().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_guangjie);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 9:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getGl().getName());
                suggestGridViewBean.setName2(live.getGl().getTitle());
                suggestGridViewBean.setName3(live.getGl().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_taiyangjing);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 10:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getGm().getName());
                suggestGridViewBean.setName2(live.getGm().getTitle());
                suggestGridViewBean.setName3(live.getGm().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_ganmao);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 11:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getHc().getName());
                suggestGridViewBean.setName2(live.getHc().getTitle());
                suggestGridViewBean.setName3(live.getHc().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_huachuan);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 12:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getJt().getName());
                suggestGridViewBean.setName2(live.getJt().getTitle());
                suggestGridViewBean.setName3(live.getJt().getDesc());
                suggestGridViewBean.setImageUlrRes(R.mipmap.main_icon_huachuan);
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
            case 13:
                suggestGridViewBean = new SuggestGridViewBean();
                suggestGridViewBean.setName1(live.getLs().getName());
                suggestGridViewBean.setName2(live.getLs().getTitle());
                suggestGridViewBean.setName3(live.getLs().getDesc());
                suggestGridViewBeanList.add(suggestGridViewBean);
                break;
        }
        }
        mainTodaySuggestAdapter.setNewData(suggestGridViewBeanList);
    }

    public List<String> stringToInt(String args) {
        List<String> digitList = new ArrayList<String>();
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(args);
        String result = m.replaceAll("");
        for (int i = 0; i < result.length(); i++) {
            digitList.add(result.substring(i, i + 1));
        }
        return digitList;
    }

    private void setAirDialogFragment(WeatherTotal7Bean weatherTotal7Bean) {

    }

    private void http() {

        city = getArguments().getString(CITY);
        prov = getArguments().getString(PROV);
        area = getArguments().getString(AREA);

        NoticeMainListPresenterImpl noticeMainListPresenter = new NoticeMainListPresenterImpl(this);
        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("area", "西湖");
        if (!TextUtils.isEmpty(prov)) {
            hashMap.put("prov", prov);
        }
        if (!TextUtils.isEmpty(city)) {
            hashMap.put("city", city);
        }
        if (!TextUtils.isEmpty(area)) {
            hashMap.put("area", area);
        }
        hashMap.put("needday", "1");
        noticeMainListPresenter.getTotalWeather(getActivity(), hashMap);
        hashMap.put("needday", "7");
        noticeMainListPresenter.getTotalWeather7(getActivity(), hashMap);
        hashMap.put("needday", "15");
        noticeMainListPresenter.getTotalWeather15(getActivity(), hashMap);
        hashMap.put("needday", "24");
        noticeMainListPresenter.getTotalWeather24(getActivity(), hashMap);

        newsListPresenterImpl = new NewsListPresenterImpl(this);
        HashMap<String, String> hashMapNew = new HashMap<>();
        hashMapNew.put("type","top");

        if(!TextUtils.isEmpty(MainActivity.MYTOKEN)){
            UcDataPresenterImpl ucDataPresenter = new UcDataPresenterImpl(this);
//            UCParamUtils ucParamUtils = new UCParamUtils(getContext());
////            HashMap<String, String> ucParamHashmap = ucParamUtils.getUcParamHashmap();
            HashMap<String, String> ucParamHashmap = new HashMap<>();
            ucParamHashmap.put("token",MainActivity.MYTOKEN);
            ucParamHashmap.put("apdid",MainActivity.APDIDP);
            ucDataPresenter.channels(getActivity(),ucParamHashmap);
        }
    }


    public void setWeatherData(WeatherTotalBean weatherTotalBean) {
        mTvWeatherTemp.setText(weatherTotalBean.getData().getNow().getDetail().getTemperature() + "°");
        mTvKongqizhiliang.setText(weatherTotalBean.getData().getNow().getDetail().getAqi() + " " + weatherTotalBean.getData().getNow().getDetail().getQuality());
        mTvWeaterXiao.setText(weatherTotalBean.getData().getNow().getDetail().getWeather());
        mTvShiduXiao.setText("湿度" + weatherTotalBean.getData().getNow().getDetail().getHumidity() + ">");

        mTvRichuTime.setText("日出 "+weatherTotalBean.getData().getNow().getDetail().getSun_begin());
        mTvRiluoTime.setText("日落 "+weatherTotalBean.getData().getNow().getDetail().getSun_end());

        mTvBottomTodayWendu.setText(weatherTotalBean.getData().getNow().getCity().getNight_air_temperature() + "°/" + weatherTotalBean.getData().getNow().getCity().getDay_air_temperature() + "°");
        mTvBottomTodayWeather.setText(weatherTotalBean.getData().getNow().getDetail().getWeather());
        mTvBottomTodayFeng.setText(weatherTotalBean.getData().getNow().getDetail().getWind_power());

        if(weatherTotalBean!=null&&this.isVisible){
            MainActivity activity = (MainActivity) getActivity();
            activity.setTitleDate(weatherTotalBean.getData().getNow().getDetail().getDate(),weatherTotalBean.getData().getNow().getDetail().getWeek(),weatherTotalBean.getData().getNow().getDetail().getNongli());
            activity.setTitleWeather(weatherTotalBean.getData().getNow().getDetail().getTemperature()+"°");
            activity.setTitleWeatherImage(weatherTotalBean.getData().getNow().getDetail().getWeather());
        }
    }

    public void setTomorrowData(WeatherTotal7Bean weatherTotal7Bean) {
        mTvBottomTomorrowFeng.setText(weatherTotal7Bean.getData().getDay7().get(1).getDay_wind_power());
        mTvBottomTomorrowWendu.setText(weatherTotal7Bean.getData().getDay7().get(1).getNight_air_temperature() + "°/" + weatherTotal7Bean.getData().getDay7().get(1).getDay_air_temperature() + "°");
        mTvBottomTomorrowWeather.setText(weatherTotal7Bean.getData().getDay7().get(1).getDay_air_weather());
    }

    public MainActivity.TitleDateListener setTitleDateListener;
    public void setTitleDateListener(MainActivity.TitleDateListener setTitleDateListener){
        this.setTitleDateListener= setTitleDateListener;
    };

    public void setTitleData(){
        if(weatherTotalBean!=null){
            setTitleDateListener.setTitleDate(weatherTotalBean.getData().getNow().getDetail().getDate()+weatherTotalBean.getData().getNow().getDetail().getWeek()+" 农历"+weatherTotalBean.getData().getNow().getDetail().getNongli());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            Log.e("isVisibleToUser","true"+area);
//            if(weatherTotalBean!=null){
//                MainActivity activity = (MainActivity) getActivity();
//                activity.setTitleDate(weatherTotalBean.getData().getNow().getDetail().getDate()+" 周"+weatherTotalBean.getData().getNow().getDetail().getWeek()+" 农历"+weatherTotalBean.getData().getNow().getDetail().getNongli());
//                activity.setTitleWeather(weatherTotalBean.getData().getNow().getDetail().getTemperature()+"°");
//                activity.setTitleWeatherImage(ChooseTypeUtils.getWeatherImgge(weatherTotalBean.getData().getNow().getDetail().getWeather()));
//            }
//
//        } else {
//            Log.e("isVisibleToUser","false"+area);
//        }
    }


    private List<WeatherModel> generateData15(WeatherTotal15Bean getTotalWeather15) {
        ArrayList<WeatherModel> weatherModels = new ArrayList<>();
        WeatherModel model;
        List<WeatherTotal15Bean.DataBean.Day15Bean> day15list = getTotalWeather15.getData().getDay15();
        for (int i = 0; i < day15list.size(); i++) {
            //数据源
            model = new WeatherModel();
            model.setWeek("周"+day15list.get(i).getWeek());
            model.setDate(day15list.get(i).getDate().substring(4,6)+"/"+day15list.get(i).getDate().substring(6,day15list.get(i).getDate().length()));
            model.setDayWeather(day15list.get(i).getDay_air_weather());
            model.setDayTemp(Integer.parseInt(day15list.get(i).getDay_air_temperature()));
            model.setNightTemp(Integer.parseInt(day15list.get(i).getNight_air_temperature()));
            model.setNightWeather(day15list.get(i).getNight_air_weather());
            model.setWindOrientation(day15list.get(i).getDay_wind_direction());
            model.setWindLevel(day15list.get(i).getDay_wind_power()); //风级
            model.setAirLevel(AirLevel.EXCELLENT); //空气质量
            model.setDayPic(ChooseTypeUtils.getWeatherImgge(day15list.get(i).getDay_air_weather()));
            model.setNightPic(ChooseTypeUtils.getWeatherImgge(day15list.get(i).getNight_air_weather()));
            weatherModels.add(model);
        }
        return weatherModels;
    }

    private List<WeatherModel> generateData7(WeatherTotal7Bean getTotalWeather7) {
        ArrayList<WeatherModel> weatherModels = new ArrayList<>();
        WeatherModel model;
        List<WeatherTotal7Bean.DataBean.Day7Bean> day7list = getTotalWeather7.getData().getDay7();
        for (int i = 0; i < day7list.size(); i++) {
            //数据源
            model = new WeatherModel();
            model.setWeek("周"+day7list.get(i).getWeek());
            model.setDate(day7list.get(i).getDate().substring(4,6)+"/"+day7list.get(i).getDate().substring(6,day7list.get(i).getDate().length()));
            model.setDayWeather(day7list.get(i).getDay_air_weather());
            model.setDayTemp(Integer.parseInt(day7list.get(i).getDay_air_temperature()));
            model.setNightTemp(Integer.parseInt(day7list.get(i).getNight_air_temperature()));
            model.setNightWeather(day7list.get(i).getNight_air_weather());
            model.setWindOrientation(day7list.get(i).getDay_wind_direction());
            model.setWindLevel(day7list.get(i).getDay_wind_power()); //风级
            model.setAirLevel(ChooseTypeUtils.getWeatherQuality(day7list.get(i).getQuality())); //空气质量
            model.setDayPic(ChooseTypeUtils.getWeatherImgge(day7list.get(i).getDay_air_weather()));
            model.setNightPic(ChooseTypeUtils.getWeatherImgge(day7list.get(i).getNight_air_weather()));
            weatherModels.add(model);
        }
        return weatherModels;
    }

    public void setScollToList(){
        if(!(mCoordinatorLayout==null||mTabLayout==null)){
            mCoordinatorLayout.scrollTo(0,mTabLayout.getHeight());
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(EventGotoNewsMessage message) {
        if(isVisible){
            mTabLayout.getTabAt(0).select();
//            String title = message.getTitle();
            if(!(mCoordinatorLayout==null||mTabLayout==null)){
                scrollToTop(false);
            }
        }
    }

    public void scrollToTop(boolean flag) {
        CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            if (flag) {
                appBarLayoutBehavior.setTopAndBottomOffset(0); //快熟滑动到顶部
            } else {
                int hight = mAppBarLayout.getHeight();
                appBarLayoutBehavior.setTopAndBottomOffset(-mAppBarLayout.getHeight());//快速滑动实现吸顶效果
            }
        }
    }
    public boolean isShowNews = false;
    //
    public void scrollToBottom(int verticalOffset) {
        if(!isShowNews){
            if(-verticalOffset>mAppBarLayout.getHeight()-phoneHeight){
                CoordinatorLayout.Behavior behavior =
                        ((CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams()).getBehavior();
                if (behavior instanceof AppBarLayout.Behavior) {
                    AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
                    int hight = mAppBarLayout.getHeight();

                    appBarLayoutBehavior.setTopAndBottomOffset(-mAppBarLayout.getHeight()+phoneHeight);//快速滑动实现吸顶效果
                }
            }

        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (bv != null) {
            bv.destroy();
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    private int adWidth =500, adHeight=500; // 广告宽高
    private NativeExpressADView nativeExpressADView,nativeExpressADView2;
    private boolean isPreloadVideo;
    private NativeExpressAD nativeExpressAD,nativeExpressAD2;
    private boolean isAdFullWidth = true, isAdAutoHeight = true; // 是否采用了ADSize.FULL_WIDTH，ADSize.AUTO_HEIGHT

    private void refreshAd(String string) {
        try {

//            hideSoftInput();
            /**
             *  如果选择支持视频的模版样式，请使用{@link PositionId#NATIVE_EXPRESS_SUPPORT_VIDEO_POS_ID}
             */
            if(string.equals("1")){
                nativeExpressAD = new NativeExpressAD(getActivity(), getMyADSize(), ConstantCode.GGAPPID, GGPositionId.MAIN_POS_ID, this); // 这里的Context必须为Activity
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
                nativeExpressAD.setVideoPlayPolicy(getVideoPlayPolicy(VideoOption.AutoPlayPolicy.ALWAYS, getActivity()));  // 本次拉回的视频广告，在用户看来是否为自动播放的
                nativeExpressAD.loadAD(1);
            }else {
                nativeExpressAD2 = new NativeExpressAD(getActivity(), getMyADSize(), ConstantCode.GGAPPID, GGPositionId.MAIN_RIGHTIMAGE_POS_ID, this); // 这里的Context必须为Activity
                nativeExpressAD2.loadAD(1);
            }


        } catch (NumberFormatException e) {
            Log.w(TAG, "ad size invalid.");
            Toast.makeText(getActivity(), "请输入合法的宽高数值", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onNoAD(AdError adError) {
        Log.i(
                TAG,
                String.format("onNoAD, error code: %d, error msg: %s", adError.getErrorCode(),
                        adError.getErrorMsg()));
    }

    public int guanggaoPosition = 0 ;

    @Override
    public void onADLoaded(List<NativeExpressADView> adList) {
        Log.e("onADLoaded1111", "onADLoaded: " + adList.size());
        // 释放前一个展示的NativeExpressADView的资源
        if(guanggaoPosition==0){
            if (this.nativeExpressADView != null) {
                this.nativeExpressADView.destroy();
            }
            if (container.getVisibility() != View.VISIBLE) {
                container.setVisibility(View.VISIBLE);
            }
            if (container.getChildCount() > 0) {
                container.removeAllViews();
            }
            this.nativeExpressADView = adList.get(0);
            Log.i(TAG, "onADLoaded, video info: " + getAdInfo(this.nativeExpressADView));
            if (this.nativeExpressADView.getBoundData().getAdPatternType() == AdPatternType.NATIVE_VIDEO) {
                this.nativeExpressADView.setMediaListener(mediaListener);
                if(isPreloadVideo) {
                    // 预加载视频素材，加载成功会回调mediaListener的onVideoCached方法，失败的话回调onVideoError方法errorCode为702。
                    this.nativeExpressADView.preloadVideo();
                }
            } else {
                isPreloadVideo = false;
            }
            if(!isPreloadVideo) {
                // 广告可见才会产生曝光，否则将无法产生收益。
                container.addView(this.nativeExpressADView);
                this.nativeExpressADView.render();
            }
            refreshAd("2");
            guanggaoPosition = 1;
        }else {
            if (nativeExpressADView2 != null) {
                nativeExpressADView2.destroy();
            }
            if (container2.getVisibility() != View.VISIBLE) {
                container2.setVisibility(View.VISIBLE);
            }
            if (container2.getChildCount() > 0) {
                container2.removeAllViews();
            }
            this.nativeExpressADView2 = adList.get(0);

            if (this.nativeExpressADView2.getBoundData().getAdPatternType() == AdPatternType.NATIVE_VIDEO) {
                this.nativeExpressADView2.setMediaListener(mediaListener);
                if(isPreloadVideo) {
                    // 预加载视频素材，加载成功会回调mediaListener的onVideoCached方法，失败的话回调onVideoError方法errorCode为702。
                    nativeExpressADView2.preloadVideo();
                }
            } else {
                isPreloadVideo = false;
            }
            if(!isPreloadVideo) {
                // 广告可见才会产生曝光，否则将无法产生收益。
                this.nativeExpressADView2.render();
                container2.addView(nativeExpressADView2);
                nativeExpressADView2.render();
            }
        }
    }


    @Override
    public void onRenderFail(NativeExpressADView adView) {
        Log.i(TAG, "onRenderFail");
    }

    @Override
    public void onRenderSuccess(NativeExpressADView adView) {
        Log.i(TAG, "onRenderSuccess");
    }

    @Override
    public void onADExposure(NativeExpressADView adView) {
        Log.i(TAG, "onADExposure");
    }

    @Override
    public void onADClicked(NativeExpressADView adView) {
        Log.i(TAG, "onADClicked" + adView.ext.get("clickUrl"));
    }

    @Override
    public void onADClosed(NativeExpressADView adView) {
        Log.i(TAG, "onADClosed");

        // 当广告模板中的关闭按钮被点击时，广告将不再展示。NativeExpressADView也会被Destroy，释放资源，不可以再用来展示。
        if (container != null && container.getChildCount() > 0) {
            container.removeAllViews();
            container.setVisibility(View.GONE);
        }
    }

    @Override
    public void onADLeftApplication(NativeExpressADView adView) {
        Log.i(TAG, "onADLeftApplication");
    }

    @Override
    public void onADOpenOverlay(NativeExpressADView adView) {
        Log.i(TAG, "onADOpenOverlay");
    }

    @Override
    public void onADCloseOverlay(NativeExpressADView adView) {
        Log.i(TAG, "onADCloseOverlay");
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


    private String getPosId() {
        return GGPositionId.MAIN_POS_ID;
    }

    private int getMinVideoDuration() {
//    return getIntent().getIntExtra(Constants.MIN_VIDEO_DURATION, 0);
        return 5;
    }

    private int getMaxVideoDuration() {
//    return getIntent().getIntExtra(Constants.MAX_VIDEO_DURATION, 0);
        return 60;
    }

    private ADSize getMyADSize() {
        int w = isAdFullWidth ? ADSize.FULL_WIDTH : adWidth;
        int h = isAdAutoHeight ? ADSize.AUTO_HEIGHT : adHeight;
        return new ADSize(w, h);
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



    UnifiedBannerView bv;
    String posId;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (bv != null) {
            bv.setLayoutParams(getUnifiedBannerLayoutParams());
        }
    }

    private UnifiedBannerView getBanner() {
        if(this.bv != null){
            bannerContainer.removeView(bv);
            bv.destroy();
        }
        String posId = getPosID();
        this.posId = posId;
        this.bv = new UnifiedBannerView(getActivity(), ConstantCode.GGAPPID, posId, this);
        bv.setRefresh(5);

        // 不需要传递tags使用下面构造函数
        // this.bv = new UnifiedBannerView(this, Constants.APPID, posId, this);
        bannerContainer.addView(bv, getUnifiedBannerLayoutParams());
        return this.bv;
    }


    /**
     * banner2.0规定banner宽高比应该为6.4:1 , 开发者可自行设置符合规定宽高比的具体宽度和高度值
     *
     * @return
     */
    private FrameLayout.LayoutParams getUnifiedBannerLayoutParams() {
        Point screenSize = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(screenSize);
        return new FrameLayout.LayoutParams(screenSize.x,  Math.round(screenSize.x / 6.4F));
    }

    private String getPosID() {
        return GGPositionId.BANNER_POS_ID;
    }


    private void doRefreshBanner() {
        GGDemoUtil.hideSoftInput(getActivity());
        getBanner().loadAD();

        myInterClass myInterClass = new myInterClass();
        
    }



    private void doCloseBanner() {
        bannerContainer.removeAllViews();
        if (bv != null) {
            bv.destroy();
            bv = null;
        }
    }
    List<NativeADDataRef> dataList ;
    class myInterClass implements NativeAD.NativeAdListener{


        private NativeADDataRef adItem;
        private NativeAD nativeAD;

        UnifiedBannerView bv;

        public myInterClass(){
            loadAD();
        }

        @Override
        public void onADLoaded(List<NativeADDataRef> list) {
            Log.e("listlistlist",list.toString());
            dataList = list;
//            Toast.makeText(getActivity(), "原生广告加载成功"+list.size(), Toast.LENGTH_LONG).show();
            if(list.size()>0){
                NativeADDataRef nativeADDataRef = list.get(0);
            }
            if(list.size()>1){
                NativeADDataRef nativeADDataRef = list.get(1);
                tv_guanggaol.setText(nativeADDataRef.getTitle());
                Glide.with(getActivity())
                        .load(nativeADDataRef.getImgUrl())
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_guanggao1);
                nativeADDataRef.onExposured(ll_guanggao_left); // 需要先调用曝光接口
                ll_guanggao_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adItem = nativeADDataRef;
                        adItem.onClicked(ll_guanggao_left);
//                        getADButtonText();
                    }
                });
            }
            if(list.size()>2){
                NativeADDataRef nativeADDataRef = list.get(2);
                tv_guanggao2.setText(nativeADDataRef.getTitle());
                Glide.with(getActivity())
                        .load(nativeADDataRef.getImgUrl())
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(iv_guanggao2);
                nativeADDataRef.onExposured(ll_guanggao_right); // 需要先调用曝光接口
                ll_guanggao_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adItem = nativeADDataRef;
                        adItem.onClicked(ll_guanggao_right);
//                        getADButtonText();
                    }
                });
            }
            if(list.size()>3){
                NativeADDataRef nativeADDataRef = list.get(3);
            }


        }

        @Override
        public void onADStatusChanged(NativeADDataRef nativeADDataRef) {
            String adButtonText = getADButtonText();
            Log.e("adButtonText",adButtonText);
        }

        @Override
        public void onADError(NativeADDataRef nativeADDataRef, AdError adError) {

        }

        @Override
        public void onNoAD(AdError adError) {

        }


        public void loadAD() {
//        nativeAD = new NativeAD(this, ConstantCode.GGAPPID, posid, this);
            nativeAD = new NativeAD(getActivity(), "1101152570", "5010320697302671", this);
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
            GGDemoUtil.hideSoftInput(getActivity());
        }


        private String getPosID() {
            return GGPositionId.BANNER_POS_ID;
        }

        /**
         * App类广告安装、下载状态的更新（普链广告没有此状态，其值为-1） 返回的AppStatus含义如下： 0：未下载 1：已安装 2：已安装旧版本 4：下载中（可获取下载进度“0-100”）
         * 8：下载完成 16：下载失败
         */
        private String getADButtonText() {
            if (adItem == null) {
                return "……";
            }
            if (!adItem.isAPP()) {
                return "查看详情";
            }
            switch (adItem.getAPPStatus()) {
                case 0:
                    return "点击下载";
                case 1:
                    return "点击启动";
                case 2:
                    return "点击更新";
                case 4:
                    return adItem.getProgress() > 0 ? "下载中" + adItem.getProgress()+ "%" : "下载中"; // 特别注意：当进度小于0时，不要使用进度来渲染界面
                case 8:
                    return "下载完成";
                case 16:
                    return "下载失败,点击重试";
                default:
                    return "查看详情";
            }
        }
    }

    public void getHideType(){
        UcDataPresenterImpl ucDataPresenter = new UcDataPresenterImpl(this);
        ucDataPresenter.getTianqiyubao(getActivity() ,new HashMap<>());
    }

}
