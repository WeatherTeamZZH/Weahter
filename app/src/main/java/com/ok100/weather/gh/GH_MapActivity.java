package com.ok100.weather.gh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.TranslateAnimation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.activity.SelectPicPopupWindowActivity;
import com.ok100.weather.adapter.NoticeMainFragmentItemAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.ChannelsBean;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.DepartmentListBean;
import com.ok100.weather.bean.EventTitleMessage;
import com.ok100.weather.bean.NoticeMainChooseBean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.constant.ConstantCode;
import com.ok100.weather.constant.GGPositionId;
import com.ok100.weather.fragment.MainFragment;
import com.ok100.weather.fragment.NoticeMainFragment1;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.presenter.UcDataPresenterImpl;
import com.ok100.weather.utils.ChooseTypeUtils;
import com.ok100.weather.utils.DPUtils;
import com.ok100.weather.utils.GGDemoUtil;
import com.ok100.weather.view.MySwipeRefreshLayout;
import com.ok100.weather.view.MyViewPager;
import com.qq.e.ads.banner2.UnifiedBannerADListener;
import com.qq.e.ads.banner2.UnifiedBannerView;
import com.qq.e.comm.util.AdError;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GH_MapActivity extends BaseActivity implements LocationSource, AMapLocationListener, SwipeRefreshLayout.OnRefreshListener , ReturnDataView , UnifiedBannerADListener {


    @BindView(R.id.ll_all_gone_view)
    LinearLayout llAllGoneView;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.ll_notice_main_more_item)
    LinearLayout llNoticeMainMoreItem;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.viewPager)
    MyViewPager viewPager;
    @BindView(R.id.swipeRefreshLayout_vanlian_new)
    MySwipeRefreshLayout swipeRefreshLayoutVanlianNew;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.iv_updata_list)
    ImageView ivUpdataList;
    @BindView(R.id.iv_shipin_list)
    ImageView ivShipinList;
    @BindView(R.id.rl_main_bottom)
    RelativeLayout rlMainBottom;

    GH_MapView mapView;
    @BindView(R.id.tv_temp)
    TextView tvTemp;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_tigan)
    TextView tvTigan;
    @BindView(R.id.tv_nengjiandu)
    TextView tvNengjiandu;
    @BindView(R.id.tv_shidu)
    TextView tvShidu;
    @BindView(R.id.tv_qiya)
    TextView tvQiya;
    @BindView(R.id.tv_feng)
    TextView tvFeng;
    @BindView(R.id.tv_aqi)
    TextView tvAqi;
    @BindView(R.id.map)
    GH_MapView map;
    @BindView(R.id.tv_fenglevel)
    TextView tvFenglevel;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_big_image)
    ImageView mIvTitleBigImage;
    @BindView(R.id.tv_title_position)
    TextView mTvTitlePosition;
    @BindView(R.id.tv_title_city)
    TextView mTvTitleCity;
    @BindView(R.id.rl_title_city)
    RelativeLayout mRlTitleCity;
    @BindView(R.id.iv_title_shouzhang)
    ImageView mIvTitleShouzhang;
    @BindView(R.id.iv_title_back_weather)
    TextView mIvTitleBackWeather;
    @BindView(R.id.rl_title_all)
    RelativeLayout mRlTitleAll;
    @BindView(R.id.iv_weather)
    ImageView mIvWeather;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.bannerContainer)
    ViewGroup bannerContainer;
    private AMap aMap;
    private boolean isItemClickAction;
    private boolean isInputKeySearch;
    private String inputSearchKey;
    private LatLonPoint searchLatlonPoint;
    private AutoCompleteTextView searchText;
    private GeocodeSearch geocoderSearch;
    private Marker locationMarker;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;



    ArrayList<String> departmentListBeansString = new ArrayList<>();
    private FragmentPagerAdapter fragmentPagerAdapter;
    public int selectposition = 0;
    private List<ViewPagerDataSource> viewPagerDataSourceList = new ArrayList<>();
    private NoticeMainFragmentItemAdapter noticeMainFragmentItemAdapter;
    private ArrayList<NoticeMainChooseBean> noticeMainChooseBeanList = new ArrayList<NoticeMainChooseBean>();

    private WeatherTotalBean data;


    public static void access(Context context, ArrayList<String> departmentListBeansString, WeatherTotalBean weatherTotalBean) {
        Intent intent = new Intent(context, GH_MapActivity.class);
        intent.putExtra("data", weatherTotalBean);
        intent.putStringArrayListExtra("listdata", departmentListBeansString);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutID() {
        return R.layout.gh_activity_map;
    }

    @Override
    public void InitView() {
        data = (WeatherTotalBean) getIntent().getSerializableExtra("data");
        departmentListBeansString = (ArrayList<String>) getIntent().getSerializableExtra("listdata");

        llNoticeMainMoreItem.setOnClickListener(this);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年 MM月 dd日 HH时 mm分 ss秒");
        //上方天气数据接口对接
        tvTitle.setText(data.getData().getCityinfo().getCity());
        tvTemp.setText(data.getData().getNow().getCity().getDay_air_temperature());
        tvWeather.setText(data.getData().getNow().getCity().getWeather());
        tvTime.setText(dateFormat.format(data.getData().getNow().getUpdate_time()));

        tvTigan.setText(data.getData().getNow().getCity().getDay_air_temperature());
        tvNengjiandu.setText(data.getData().getNow().getDetail().getNjd());
        tvShidu.setText(data.getData().getNow().getDetail().getHumidity());
        tvQiya.setText(data.getData().getNow().getDetail().getQy());
        tvFeng.setText(data.getData().getNow().getDetail().getWind_direction());
        tvFenglevel.setText(data.getData().getNow().getDetail().getWind_power());
        tvAqi.setText(data.getData().getNow().getDetail().getAqi());

        mIvWeather.setImageResource(ChooseTypeUtils.getWeatherImgge(data.getData().getNow().getDetail().getWeather()));
        setTitleWeather(data.getData().getNow().getDetail().getTemperature() + "°");
        setTitleWeatherImage(ChooseTypeUtils.getWeatherImgge(data.getData().getNow().getDetail().getWeather()));
        mTvTitlePosition.setText(data.getData().getCityinfo().getArea());
        swipeRefreshLayoutVanlianNew.setOnRefreshListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {
        mapView = findViewById(R.id.map);
        mapView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    coordinatorLayout.requestDisallowInterceptTouchEvent(false);
                    appBarLayout.requestDisallowInterceptTouchEvent(false);
                    llAllGoneView.requestDisallowInterceptTouchEvent(false);
                } else {
                    coordinatorLayout.requestDisallowInterceptTouchEvent(true);
                    appBarLayout.requestDisallowInterceptTouchEvent(true);
                    llAllGoneView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        mapView.onCreate(savedInstanceState);
        init();
        http();
        doRefreshBanner();
    }

    private void doRefreshBanner() {
        GGDemoUtil.hideSoftInput(this);
        getBanner().loadAD();
    }

    /**
     * 初始化
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }

        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                if (!isItemClickAction && !isInputKeySearch) {
                    geoAddress();
                    startJumpAnimation();
                }
                searchLatlonPoint = new LatLonPoint(cameraPosition.target.latitude, cameraPosition.target.longitude);
                isInputKeySearch = false;
                isItemClickAction = false;
            }
        });

        aMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                addMarkerInScreenCenter(null);
            }
        });
    }

    private void setUpMap() {
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    public void geoAddress() {
//        Log.i("MY", "geoAddress"+ searchLatlonPoint.toString());
//        showDialog();
        searchText.setText("");
        if (searchLatlonPoint != null) {
            RegeocodeQuery query = new RegeocodeQuery(searchLatlonPoint, 200, GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
            geocoderSearch.getFromLocationAsyn(query);
        }
    }

    public void startJumpAnimation() {

        if (locationMarker != null) {
            //根据屏幕距离计算需要移动的目标点
            final LatLng latLng = locationMarker.getPosition();
            Point point = aMap.getProjection().toScreenLocation(latLng);
            point.y -= DPUtils.dip2px(this, 125);
            LatLng target = aMap.getProjection()
                    .fromScreenLocation(point);
            //使用TranslateAnimation,填写一个需要移动的目标点
            Animation animation = new TranslateAnimation(target);
            animation.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float input) {
                    // 模拟重加速度的interpolator
                    if (input <= 0.5) {
                        return (float) (0.5f - 2 * (0.5 - input) * (0.5 - input));
                    } else {
                        return (float) (0.5f - Math.sqrt((input - 0.5f) * (1.5f - input)));
                    }
                }
            });
            //整个移动所需要的时间
            animation.setDuration(600);
            //设置动画
            locationMarker.setAnimation(animation);
            //开始动画
            locationMarker.startAnimation();

        } else {
            Log.e("ama", "screenMarker is null");
        }
    }

    private void addMarkerInScreenCenter(LatLng locationLatLng) {
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        locationMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin)));
        //设置Marker在屏幕上,不跟随地图移动
        locationMarker.setPositionByPixels(screenPosition.x, screenPosition.y);
        locationMarker.setZIndex(1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_notice_main_more_item:
                departmentListBeansString.clear();
                if(channelsBean!=null){
                    for(int i=0;i<channelsBean.getData().size();i++){
                        departmentListBeansString.add(channelsBean.getData().get(i).getCatname());
                    }
                }
                Intent intent = new Intent(getContext(), SelectPicPopupWindowActivity.class);
                intent.putStringArrayListExtra("listdata", departmentListBeansString);
                startActivity(intent);

                SelectPicPopupWindowActivity.itemTabListener = new MainFragment.setItemTabListener() {
                    @Override
                    public void setItemPosition(int position) {
                        tabLayout.getTabAt(position).select();
                    }
                };
                break;
            case R.id.iv_shipin_list:
                tabLayout.getTabAt(1).select();
                break;
            case R.id.iv_updata_list:

                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_main_bottom:

                break;
            case R.id.iv_title_back_weather:
                setAllGoneViewVisible(true);
                setBottomVisible(false);

                break;
        }
    }

    private void initTablayout() {
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
                View view = tab.getCustomView();
                if (null != view && view instanceof TextView) {
                    ((TextView) view).setTextSize(19);
                    ((TextView) view).setTextColor(ContextCompat.getColor(getContext(), R.color.green_defult));
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

    private void initViewPager() {
        viewPager.setOffscreenPageLimit(0);
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
                selectposition = position;
                selectFragment = (NoticeMainFragment1) viewPagerDataSourceList.get(position).getFragment();
                ;
//                mSwipeRefreshLayoutVanlianNew.setRecycleview(getFragment.getRecyclerView());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getTitleListData() {

        Fragment fragment;
        TitleListData titleListData;
        viewPager.setOffscreenPageLimit(0);
        for (int i = 0; i < channelsBean.getData().size(); i++) {
            fragment = new NoticeMainFragment1();
            Bundle args = new Bundle();
            args.putString("departmentId", channelsBean.getData().get(i).getCatid());
            args.putString("type", channelsBean.getData().get(i).getCatname());
            fragment.setArguments(args);
            titleListData = new TitleListData(channelsBean.getData().get(i).getCatname());
            viewPagerDataSourceList.add(new ViewPagerDataSource(fragment, titleListData));
            ((NoticeMainFragment1) fragment).setBootomVisibleListener(new MainFragment.BootomVisibleListener() {
                @Override
                public void setBootomVisible(boolean visible) {
                    setBottomVisible(visible);
                }
            });
            ((NoticeMainFragment1) fragment).setTaybarVisibleListener(new MainFragment.TaybarVisibleListener() {

                @Override
                public void setTaybarVisible(boolean visible) {
                    if (visible) {

                    } else {
                        appBarLayout.setVisibility(View.GONE);
                        mRlTitle.setVisibility(View.GONE);
                        mRlTitleAll.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }


    public void setAllGoneViewVisible(boolean visible) {
        if (visible) {
            appBarLayout.setVisibility(View.VISIBLE);
            CoordinatorLayout.Behavior behavior =
                    ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
            if (behavior instanceof AppBarLayout.Behavior) {
                AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
                int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
                if (topAndBottomOffset != 0) {
                    appBarLayoutBehavior.setTopAndBottomOffset(0);
                }
            }
            for (int i = 0; i < viewPagerDataSourceList.size(); i++) {
                NoticeMainFragment1 getFragment = (NoticeMainFragment1) viewPagerDataSourceList.get(i).getFragment();
                ;
                getFragment.resateRecycle();
            }

        } else {
            appBarLayout.setVisibility(View.GONE);
        }

    }

    //    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setNewArraylist() {
//        initItemAdapter();
        getTitleListData();
        initFragment();
        initViewPager();
        initTablayout();
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("verticalOffset", verticalOffset + "");
                float mTabLayoutX = tabLayout.getX();
                Log.e("mTabLayoutX", mTabLayoutX + "");

                int totalScrollRange = appBarLayout.getTotalScrollRange();
                if (Math.abs(verticalOffset) >= totalScrollRange) {
                    Log.e("totalScrollRange", "totalScrollRange---true");
                } else {
                }
                if (Math.abs(verticalOffset) >= totalScrollRange - 100) {
                }
            }
        });
    }


    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setOnceLocation(true);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);

                LatLng curLatlng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());

                searchLatlonPoint = new LatLonPoint(curLatlng.latitude, curLatlng.longitude);

                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLatlng, 16f));

                isInputKeySearch = false;

                searchText.setText("");

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayoutVanlianNew.setRefreshing(false);
        selectFragment.onRefresh();
    }

    ChannelsBean channelsBean ;
    NoticeMainFragment1 selectFragment ;
    @Override
    public void returnData(String responseCode, Object o) {
        switch (responseCode){
            case "channels":
                channelsBean = (ChannelsBean) o;
                if(channelsBean.getCode().equals("200")){
                    setNewArraylist();
                    tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
                }

                break;
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onNoAD(AdError adError) {

    }


    public interface setItemTabListener {
        void setItemPosition(int position);
    }

    private void initFragment() {

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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

//    public interface BootomVisibleListener{
//        void setBootomVisible(boolean visible);
//    }

    public void setBottomVisible(boolean visible) {
        if (visible) {
            rlMainBottom.setVisibility(View.VISIBLE);
            appBarLayout.setVisibility(View.GONE);
            mRlTitle.setVisibility(View.GONE);
            mRlTitleAll.setVisibility(View.VISIBLE);
        } else {
            rlMainBottom.setVisibility(View.GONE);
            appBarLayout.setVisibility(View.VISIBLE);
            mRlTitle.setVisibility(View.VISIBLE);
            mRlTitleAll.setVisibility(View.GONE);
        }

    }

    public interface TaybarVisibleListener {
        void setTaybarVisible(boolean visible);
    }

    private void changeItemData(int position) {
        for (int i = 0; i < noticeMainChooseBeanList.size(); i++) {
            if (i == position) {
                noticeMainChooseBeanList.get(i).setState(true);
            } else {
                noticeMainChooseBeanList.get(i).setState(false);
            }
        }
    }


    @Override
    public void initListener() {
        ivShipinList.setOnClickListener(this);
        rlMainBottom.setOnClickListener(this);
        mIvTitleBackWeather.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
    }

    public void setTitleWeather(String string) {
        mTvTitleCity.setText(string);
    }

    public void setTitleWeatherImage(int string) {
        mIvTitleBigImage.setBackgroundResource(string);
    }

    private void http(){
        if(!TextUtils.isEmpty(MainActivity.MYTOKEN)){
            UcDataPresenterImpl ucDataPresenter = new UcDataPresenterImpl(this);
//            UCParamUtils ucParamUtils = new UCParamUtils(getContext());
////            HashMap<String, String> ucParamHashmap = ucParamUtils.getUcParamHashmap();
            HashMap<String, String> ucParamHashmap = new HashMap<>();
            ucParamHashmap.put("token",MainActivity.MYTOKEN);
            ucParamHashmap.put("apdid",MainActivity.APDIDP);
            ucDataPresenter.channels(GH_MapActivity.this,ucParamHashmap);
        }
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

    private String getPosID() {
        return GGPositionId.BANNER_POS_ID;
    }

    private UnifiedBannerView getBanner() {
        if(this.bv != null){
            bannerContainer.removeView(bv);
            bv.destroy();
        }
        String posId = getPosID();
        this.posId = posId;
        this.bv = new UnifiedBannerView(this, ConstantCode.GGAPPID, posId, this);
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
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        return new FrameLayout.LayoutParams(screenSize.x,  Math.round(screenSize.x / 6.4F));
    }

}
