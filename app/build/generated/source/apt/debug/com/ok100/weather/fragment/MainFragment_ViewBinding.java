// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import com.ok100.weather.hours24.IndexHorizontalScrollView;
import com.ok100.weather.hours24.Today24HourView;
import com.ok100.weather.view.MySwipeRefreshLayout;
import com.ok100.weather.view.MyViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;
import me.zhouzhuo.zzweatherview.ZzWeatherView;

public class MainFragment_ViewBinding implements Unbinder {
  private MainFragment target;

  @UiThread
  public MainFragment_ViewBinding(MainFragment target, View source) {
    this.target = target;

    target.mTvWeatherTemp = Utils.findRequiredViewAsType(source, R.id.tv_weather_temp, "field 'mTvWeatherTemp'", TextView.class);
    target.mTvWeaterXiao = Utils.findRequiredViewAsType(source, R.id.tv_weater_xiao, "field 'mTvWeaterXiao'", TextView.class);
    target.mTvShiduXiao = Utils.findRequiredViewAsType(source, R.id.tv_shidu_xiao, "field 'mTvShiduXiao'", TextView.class);
    target.relativeLayout1 = Utils.findRequiredViewAsType(source, R.id.relativeLayout1, "field 'relativeLayout1'", RelativeLayout.class);
    target.mLinearlayout1 = Utils.findRequiredViewAsType(source, R.id.linearlayout1, "field 'mLinearlayout1'", LinearLayout.class);
    target.mLlGuanggao1 = Utils.findRequiredViewAsType(source, R.id.ll_guanggao1, "field 'mLlGuanggao1'", LinearLayout.class);
    target.mTvLiebiao = Utils.findRequiredViewAsType(source, R.id.tv_liebiao, "field 'mTvLiebiao'", TextView.class);
    target.mTvQushi = Utils.findRequiredViewAsType(source, R.id.tv_qushi, "field 'mTvQushi'", TextView.class);
    target.weatherView = Utils.findRequiredViewAsType(source, R.id.weather_view, "field 'weatherView'", ZzWeatherView.class);
    target.mRecyclerview15weather = Utils.findRequiredViewAsType(source, R.id.recyclerview_15weather, "field 'mRecyclerview15weather'", RecyclerView.class);
    target.mIvGuanggaoDonghua = Utils.findRequiredViewAsType(source, R.id.iv_guanggao_donghua, "field 'mIvGuanggaoDonghua'", ImageView.class);
    target.mRecyclerviewTodaySuggest = Utils.findRequiredViewAsType(source, R.id.recyclerview_today_suggest, "field 'mRecyclerviewTodaySuggest'", RecyclerView.class);
    target.mLlNoticeMainMoreItem = Utils.findRequiredViewAsType(source, R.id.ll_notice_main_more_item, "field 'mLlNoticeMainMoreItem'", LinearLayout.class);
    target.mRlTitleBar = Utils.findRequiredViewAsType(source, R.id.rl_title_bar, "field 'mRlTitleBar'", RelativeLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", MyViewPager.class);
    target.mToday24HourView = Utils.findRequiredViewAsType(source, R.id.today24HourView, "field 'mToday24HourView'", Today24HourView.class);
    target.mIndexHorizontalScrollView = Utils.findRequiredViewAsType(source, R.id.indexHorizontalScrollView, "field 'mIndexHorizontalScrollView'", IndexHorizontalScrollView.class);
    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'mTabLayout'", TabLayout.class);
    target.mCoordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinatorLayout, "field 'mCoordinatorLayout'", CoordinatorLayout.class);
    target.mAppBarLayout = Utils.findRequiredViewAsType(source, R.id.appBarLayout, "field 'mAppBarLayout'", AppBarLayout.class);
    target.mSwipeRefreshLayoutVanlianNew = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout_vanlian_new, "field 'mSwipeRefreshLayoutVanlianNew'", MySwipeRefreshLayout.class);
    target.mRlMainBottom = Utils.findRequiredViewAsType(source, R.id.rl_main_bottom, "field 'mRlMainBottom'", RelativeLayout.class);
    target.mIvUpdataList = Utils.findRequiredViewAsType(source, R.id.iv_updata_list, "field 'mIvUpdataList'", ImageView.class);
    target.mIvShipinList = Utils.findRequiredViewAsType(source, R.id.iv_shipin_list, "field 'mIvShipinList'", ImageView.class);
    target.mRecyclerviewTodayFuwu = Utils.findRequiredViewAsType(source, R.id.recyclerview_today_fuwu, "field 'mRecyclerviewTodayFuwu'", RecyclerView.class);
    target.mLlWeatherView = Utils.findRequiredViewAsType(source, R.id.ll_weather_view, "field 'mLlWeatherView'", LinearLayout.class);
    target.mTvKongqizhiliang = Utils.findRequiredViewAsType(source, R.id.tv_kongqizhiliang, "field 'mTvKongqizhiliang'", TextView.class);
    target.mTvBottomTodayWendu = Utils.findRequiredViewAsType(source, R.id.tv_bottom_today_wendu, "field 'mTvBottomTodayWendu'", TextView.class);
    target.mTvBottomTodayWeather = Utils.findRequiredViewAsType(source, R.id.tv_bottom_today_weather, "field 'mTvBottomTodayWeather'", TextView.class);
    target.mTvBottomTodayFeng = Utils.findRequiredViewAsType(source, R.id.tv_bottom_today_feng, "field 'mTvBottomTodayFeng'", TextView.class);
    target.mTvBottomTomorrowWendu = Utils.findRequiredViewAsType(source, R.id.tv_bottom_tomorrow_wendu, "field 'mTvBottomTomorrowWendu'", TextView.class);
    target.mTvBottomTomorrowWeather = Utils.findRequiredViewAsType(source, R.id.tv_bottom_tomorrow_weather, "field 'mTvBottomTomorrowWeather'", TextView.class);
    target.mTvBottomTomorrowFeng = Utils.findRequiredViewAsType(source, R.id.tv_bottom_tomorrow_feng, "field 'mTvBottomTomorrowFeng'", TextView.class);
    target.mTvRichuTime = Utils.findRequiredViewAsType(source, R.id.tv_richu_time, "field 'mTvRichuTime'", TextView.class);
    target.mTvRiluoTime = Utils.findRequiredViewAsType(source, R.id.tv_riluo_time, "field 'mTvRiluoTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvWeatherTemp = null;
    target.mTvWeaterXiao = null;
    target.mTvShiduXiao = null;
    target.relativeLayout1 = null;
    target.mLinearlayout1 = null;
    target.mLlGuanggao1 = null;
    target.mTvLiebiao = null;
    target.mTvQushi = null;
    target.weatherView = null;
    target.mRecyclerview15weather = null;
    target.mIvGuanggaoDonghua = null;
    target.mRecyclerviewTodaySuggest = null;
    target.mLlNoticeMainMoreItem = null;
    target.mRlTitleBar = null;
    target.viewPager = null;
    target.mToday24HourView = null;
    target.mIndexHorizontalScrollView = null;
    target.mTabLayout = null;
    target.mCoordinatorLayout = null;
    target.mAppBarLayout = null;
    target.mSwipeRefreshLayoutVanlianNew = null;
    target.mRlMainBottom = null;
    target.mIvUpdataList = null;
    target.mIvShipinList = null;
    target.mRecyclerviewTodayFuwu = null;
    target.mLlWeatherView = null;
    target.mTvKongqizhiliang = null;
    target.mTvBottomTodayWendu = null;
    target.mTvBottomTodayWeather = null;
    target.mTvBottomTodayFeng = null;
    target.mTvBottomTomorrowWendu = null;
    target.mTvBottomTomorrowWeather = null;
    target.mTvBottomTomorrowFeng = null;
    target.mTvRichuTime = null;
    target.mTvRiluoTime = null;
  }
}
