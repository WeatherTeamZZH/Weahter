// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gh;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
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
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    target.swipeRefreshLayoutVanlianNew = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout_vanlian_new, "field 'swipeRefreshLayoutVanlianNew'", SwipeRefreshLayout.class);
    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinatorLayout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    target.ivUpdataList = Utils.findRequiredViewAsType(source, R.id.iv_updata_list, "field 'ivUpdataList'", ImageView.class);
    target.ivShipinList = Utils.findRequiredViewAsType(source, R.id.iv_shipin_list, "field 'ivShipinList'", ImageView.class);
    target.rlMainBottom = Utils.findRequiredViewAsType(source, R.id.rl_main_bottom, "field 'rlMainBottom'", RelativeLayout.class);
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
  }
}
