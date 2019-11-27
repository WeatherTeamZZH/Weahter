// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gb.stickercamera.app.camera.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import com.ok100.weather.gb.customview.PagerSlidingTabStrip;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AlbumActivity_ViewBinding implements Unbinder {
  private AlbumActivity target;

  @UiThread
  public AlbumActivity_ViewBinding(AlbumActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AlbumActivity_ViewBinding(AlbumActivity target, View source) {
    this.target = target;

    target.tab = Utils.findRequiredViewAsType(source, R.id.indicator, "field 'tab'", PagerSlidingTabStrip.class);
    target.pager = Utils.findRequiredViewAsType(source, R.id.pager, "field 'pager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AlbumActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tab = null;
    target.pager = null;
  }
}
