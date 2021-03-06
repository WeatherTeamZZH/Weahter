// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gh;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import com.ok100.weather.view.MySwipeRefreshLayout;
import com.ok100.weather.view.MyViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GH_MapActivity_ViewBinding implements Unbinder {
  private GH_MapActivity target;

  @UiThread
  public GH_MapActivity_ViewBinding(GH_MapActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GH_MapActivity_ViewBinding(GH_MapActivity target, View source) {
    this.target = target;

    target.llAllGoneView = Utils.findRequiredViewAsType(source, R.id.ll_all_gone_view, "field 'llAllGoneView'", LinearLayout.class);
    target.appBarLayout = Utils.findRequiredViewAsType(source, R.id.appBarLayout, "field 'appBarLayout'", AppBarLayout.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
    target.llNoticeMainMoreItem = Utils.findRequiredViewAsType(source, R.id.ll_notice_main_more_item, "field 'llNoticeMainMoreItem'", LinearLayout.class);
    target.rlTitleBar = Utils.findRequiredViewAsType(source, R.id.rl_title_bar, "field 'rlTitleBar'", RelativeLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", MyViewPager.class);
    target.swipeRefreshLayoutVanlianNew = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout_vanlian_new, "field 'swipeRefreshLayoutVanlianNew'", MySwipeRefreshLayout.class);
    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinatorLayout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.ivUpdataList = Utils.findRequiredViewAsType(source, R.id.iv_updata_list, "field 'ivUpdataList'", ImageView.class);
    target.ivShipinList = Utils.findRequiredViewAsType(source, R.id.iv_shipin_list, "field 'ivShipinList'", ImageView.class);
    target.rlMainBottom = Utils.findRequiredViewAsType(source, R.id.rl_main_bottom, "field 'rlMainBottom'", RelativeLayout.class);
    target.tvTemp = Utils.findRequiredViewAsType(source, R.id.tv_temp, "field 'tvTemp'", TextView.class);
    target.tvWeather = Utils.findRequiredViewAsType(source, R.id.tv_weather, "field 'tvWeather'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvTigan = Utils.findRequiredViewAsType(source, R.id.tv_tigan, "field 'tvTigan'", TextView.class);
    target.tvNengjiandu = Utils.findRequiredViewAsType(source, R.id.tv_nengjiandu, "field 'tvNengjiandu'", TextView.class);
    target.tvShidu = Utils.findRequiredViewAsType(source, R.id.tv_shidu, "field 'tvShidu'", TextView.class);
    target.tvQiya = Utils.findRequiredViewAsType(source, R.id.tv_qiya, "field 'tvQiya'", TextView.class);
    target.tvFeng = Utils.findRequiredViewAsType(source, R.id.tv_feng, "field 'tvFeng'", TextView.class);
    target.tvAqi = Utils.findRequiredViewAsType(source, R.id.tv_aqi, "field 'tvAqi'", TextView.class);
    target.map = Utils.findRequiredViewAsType(source, R.id.map, "field 'map'", GH_MapView.class);
    target.tvFenglevel = Utils.findRequiredViewAsType(source, R.id.tv_fenglevel, "field 'tvFenglevel'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.mIvTitleBigImage = Utils.findRequiredViewAsType(source, R.id.iv_title_big_image, "field 'mIvTitleBigImage'", ImageView.class);
    target.mTvTitlePosition = Utils.findRequiredViewAsType(source, R.id.tv_title_position, "field 'mTvTitlePosition'", TextView.class);
    target.mTvTitleCity = Utils.findRequiredViewAsType(source, R.id.tv_title_city, "field 'mTvTitleCity'", TextView.class);
    target.mRlTitleCity = Utils.findRequiredViewAsType(source, R.id.rl_title_city, "field 'mRlTitleCity'", RelativeLayout.class);
    target.mIvTitleShouzhang = Utils.findRequiredViewAsType(source, R.id.iv_title_shouzhang, "field 'mIvTitleShouzhang'", ImageView.class);
    target.mIvTitleBackWeather = Utils.findRequiredViewAsType(source, R.id.iv_title_back_weather, "field 'mIvTitleBackWeather'", TextView.class);
    target.mRlTitleAll = Utils.findRequiredViewAsType(source, R.id.rl_title_all, "field 'mRlTitleAll'", RelativeLayout.class);
    target.mIvWeather = Utils.findRequiredViewAsType(source, R.id.iv_weather, "field 'mIvWeather'", ImageView.class);
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GH_MapActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llAllGoneView = null;
    target.appBarLayout = null;
    target.tabLayout = null;
    target.llNoticeMainMoreItem = null;
    target.rlTitleBar = null;
    target.viewPager = null;
    target.swipeRefreshLayoutVanlianNew = null;
    target.coordinatorLayout = null;
    target.ivUpdataList = null;
    target.ivShipinList = null;
    target.rlMainBottom = null;
    target.tvTemp = null;
    target.tvWeather = null;
    target.tvTime = null;
    target.tvTigan = null;
    target.tvNengjiandu = null;
    target.tvShidu = null;
    target.tvQiya = null;
    target.tvFeng = null;
    target.tvAqi = null;
    target.map = null;
    target.tvFenglevel = null;
    target.tvTitle = null;
    target.mIvTitleBigImage = null;
    target.mTvTitlePosition = null;
    target.mTvTitleCity = null;
    target.mRlTitleCity = null;
    target.mIvTitleShouzhang = null;
    target.mIvTitleBackWeather = null;
    target.mRlTitleAll = null;
    target.mIvWeather = null;
    target.mIvBack = null;
    target.mRlTitle = null;
  }
}
