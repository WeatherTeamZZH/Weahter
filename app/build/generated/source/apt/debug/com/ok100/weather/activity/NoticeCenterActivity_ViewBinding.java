// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import com.ok100.weather.base.BaseViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoticeCenterActivity_ViewBinding implements Unbinder {
  private NoticeCenterActivity target;

  @UiThread
  public NoticeCenterActivity_ViewBinding(NoticeCenterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NoticeCenterActivity_ViewBinding(NoticeCenterActivity target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout_dataStatistics, "field 'tabLayout'", TabLayout.class);
    target.mTablayoutBottmoLine = Utils.findRequiredView(source, R.id.tablayout_bottmo_line, "field 'mTablayoutBottmoLine'");
    target.mBaseViewPager = Utils.findRequiredViewAsType(source, R.id.baseViewPager_dataStatistics, "field 'mBaseViewPager'", BaseViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NoticeCenterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.mTablayoutBottmoLine = null;
    target.mBaseViewPager = null;
  }
}
