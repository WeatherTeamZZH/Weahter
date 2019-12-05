package com.ok100.weather.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.ok100.weather.adapter.MainTodayFuwuAdapter;
import com.ok100.weather.adapter.MainTodaySuggestAdapter;
import com.ok100.weather.adapter.NoticeMainFragmentItemAdapter;
import com.ok100.weather.adapter.Weather15MianAdapter;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.bean.DepartmentListBean;
import com.ok100.weather.bean.EventTitleMessage;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.NoticeMainChooseBean;
import com.ok100.weather.bean.SuggestGridViewBean;
import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.event.EventGotoNewsMessage;
import com.ok100.weather.gh.AirDialogFragment;
import com.ok100.weather.gh.GH_DefaultDialogFragment;
import com.ok100.weather.gh.GH_MapActivity;
import com.ok100.weather.hours24.IndexHorizontalScrollView;
import com.ok100.weather.hours24.Today24HourView;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.presenter.NewsListPresenterImpl;
import com.ok100.weather.presenter.NoticeMainListPresenterImpl;
import com.ok100.weather.utils.ChooseTypeUtils;
import com.ok100.weather.utils.DPUtils;
import com.ok100.weather.view.MySwipeRefreshLayout;
import com.ok100.weather.view.MyViewPager;

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


public class MainFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener, ReturnDataView<Object>, View.OnClickListener, BaseQuickAdapter.OnItemChildClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


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
    @BindView(R.id.ll_guanggao1)
    LinearLayout mLlGuanggao1;
    @BindView(R.id.tv_liebiao)
    TextView mTvLiebiao;
    @BindView(R.id.tv_qushi)
    TextView mTvQushi;
    @BindView(R.id.weather_view)
    ZzWeatherView weatherView;
    @BindView(R.id.recyclerview_15weather)
    RecyclerView mRecyclerview15weather;
    @BindView(R.id.iv_guanggao_donghua)
    ImageView mIvGuanggaoDonghua;
    @BindView(R.id.recyclerview_today_suggest)
    RecyclerView mRecyclerviewTodaySuggest;

    @BindView(R.id.ll_notice_main_more_item)
    LinearLayout mLlNoticeMainMoreItem;
    @BindView(R.id.rl_title_bar)
    RelativeLayout mRlTitleBar;
    @BindView(R.id.viewPager)
    MyViewPager viewPager;

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
                }
                break;
            case R.id.tv_shidu_xiao:
                if(weatherTotalBean!=null){
                GH_MapActivity.access(getActivity(),departmentListBeansString,weatherTotalBean);
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
            }
        });

        mRecyclerviewTodayFuwu.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        MainTodayFuwuAdapter mainTodayFuwuAdapter = new MainTodayFuwuAdapter();
        mainTodayFuwuAdapter.setNewData(DataBean.generateData());
        mRecyclerviewTodayFuwu.setAdapter(mainTodayFuwuAdapter);
        mRecyclerviewTodayFuwu.setNestedScrollingEnabled(false);//禁止滑动
        Glide.with(this).load(R.drawable.guanggaodemo).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mIvGuanggaoDonghua);

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


        setNewArraylist();
        mTabLayout.setSelectedTabIndicatorColor(Color.BLUE);
        http();
            }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }


    @Override
    public void onRefresh() {
        mSwipeRefreshLayoutVanlianNew.setRefreshing(false);
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

    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setNewArraylist() {
        departmentListBeans.clear();
        for (int i = 0; i < DataBean.getNewTitleList().size(); i++) {
            DepartmentListBean departmentListBean = new DepartmentListBean(DataBean.getNewTitleList().get(i));
            departmentListBeans.add(departmentListBean);
        }
        getTitleListData(departmentListBeans);
        initFragment();
        initViewPager();
        initTablayout();

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int jvli = (int) -verticalOffset/2;
                if(jvli<180){
                    mCoordinatorLayout.getBackground().setAlpha(jvli);
                }
            }
        });
    }


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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("position", position + "");
                NoticeMainFragment1 getFragment = (NoticeMainFragment1) viewPagerDataSourceList.get(position).getFragment();

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
            args.putString("departmentId", area);
            args.putString("type", DataBean.getNewTitleList().get(i));
            fragment.setArguments(args);
            titleListData = new TitleListData(DataBean.getNewTitleList().get(i));
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
            Log.e("visible", "mAppBarLayout显示");
            mAppBarLayout.setVisibility(View.VISIBLE);
            CoordinatorLayout.Behavior behavior =
                    ((CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams()).getBehavior();
            if (behavior instanceof AppBarLayout.Behavior) {
                AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
                int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
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
                Log.e("getTopAndBottomOffset", "getTopAndBottomOffset:"+appBarLayoutBehavior.getTopAndBottomOffset());
                int hight = mAppBarLayout.getHeight();
                appBarLayoutBehavior.setTopAndBottomOffset(-mAppBarLayout.getHeight());//快速滑动实现吸顶效果
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

}
