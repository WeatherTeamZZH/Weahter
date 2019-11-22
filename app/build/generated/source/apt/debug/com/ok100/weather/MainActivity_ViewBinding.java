// Generated code from Butter Knife. Do not modify!
package com.ok100.weather;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.mIvAddWeather = Utils.findRequiredViewAsType(source, R.id.iv_add_weather, "field 'mIvAddWeather'", ImageView.class);
    target.mTvCity = Utils.findRequiredViewAsType(source, R.id.tv_city, "field 'mTvCity'", TextView.class);
    target.mTvDate = Utils.findRequiredViewAsType(source, R.id.tv_date, "field 'mTvDate'", TextView.class);
    target.mIvWeatherShare = Utils.findRequiredViewAsType(source, R.id.iv_weather_share, "field 'mIvWeatherShare'", ImageView.class);
    target.mIvWeatherUser = Utils.findRequiredViewAsType(source, R.id.iv_weather_user, "field 'mIvWeatherUser'", ImageView.class);
    target.mviewpager = Utils.findRequiredViewAsType(source, R.id.id_viewpager, "field 'mviewpager'", ViewPager.class);
    target.mRceycleviewSpot = Utils.findRequiredViewAsType(source, R.id.rceycleview_spot, "field 'mRceycleviewSpot'", RecyclerView.class);
    target.mLlAllBg = Utils.findRequiredViewAsType(source, R.id.ll_all_bg, "field 'mLlAllBg'", LinearLayout.class);
    target.mIvTitleBigImage = Utils.findRequiredViewAsType(source, R.id.iv_title_big_image, "field 'mIvTitleBigImage'", ImageView.class);
    target.mTvTitlePosition = Utils.findRequiredViewAsType(source, R.id.tv_title_position, "field 'mTvTitlePosition'", TextView.class);
    target.mTvTitleCity = Utils.findRequiredViewAsType(source, R.id.tv_title_city, "field 'mTvTitleCity'", TextView.class);
    target.mRlTitleCity = Utils.findRequiredViewAsType(source, R.id.rl_title_city, "field 'mRlTitleCity'", RelativeLayout.class);
    target.mIvTitleShouzhang = Utils.findRequiredViewAsType(source, R.id.iv_title_shouzhang, "field 'mIvTitleShouzhang'", ImageView.class);
    target.mIvTitleBackWeather = Utils.findRequiredViewAsType(source, R.id.iv_title_back_weather, "field 'mIvTitleBackWeather'", TextView.class);
    target.mRlTitleAll = Utils.findRequiredViewAsType(source, R.id.rl_title_all, "field 'mRlTitleAll'", RelativeLayout.class);
    target.mRlTitleDefult = Utils.findRequiredViewAsType(source, R.id.rl_title_defult, "field 'mRlTitleDefult'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvAddWeather = null;
    target.mTvCity = null;
    target.mTvDate = null;
    target.mIvWeatherShare = null;
    target.mIvWeatherUser = null;
    target.mviewpager = null;
    target.mRceycleviewSpot = null;
    target.mLlAllBg = null;
    target.mIvTitleBigImage = null;
    target.mTvTitlePosition = null;
    target.mTvTitleCity = null;
    target.mRlTitleCity = null;
    target.mIvTitleShouzhang = null;
    target.mIvTitleBackWeather = null;
    target.mRlTitleAll = null;
    target.mRlTitleDefult = null;
  }
}
