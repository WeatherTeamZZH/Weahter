// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gh;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AirFragment_ViewBinding implements Unbinder {
  private AirFragment target;

  private View view7f0801fe;

  private View view7f0801ff;

  @UiThread
  public AirFragment_ViewBinding(final AirFragment target, View source) {
    this.target = target;

    View view;
    target.tablayout = Utils.findRequiredViewAsType(source, R.id.tablayout, "field 'tablayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    target.llNational = Utils.findRequiredViewAsType(source, R.id.ll_national, "field 'llNational'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_switch_1, "field 'tvSwitch1' and method 'onViewClicked'");
    target.tvSwitch1 = Utils.castView(view, R.id.tv_switch_1, "field 'tvSwitch1'", TextView.class);
    view7f0801fe = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_switch_2, "field 'tvSwitch2' and method 'onViewClicked'");
    target.tvSwitch2 = Utils.castView(view, R.id.tv_switch_2, "field 'tvSwitch2'", TextView.class);
    view7f0801ff = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rvList = Utils.findRequiredViewAsType(source, R.id.rv_list, "field 'rvList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AirFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tablayout = null;
    target.viewPager = null;
    target.llNational = null;
    target.tvSwitch1 = null;
    target.tvSwitch2 = null;
    target.rvList = null;

    view7f0801fe.setOnClickListener(null);
    view7f0801fe = null;
    view7f0801ff.setOnClickListener(null);
    view7f0801ff = null;
  }
}
