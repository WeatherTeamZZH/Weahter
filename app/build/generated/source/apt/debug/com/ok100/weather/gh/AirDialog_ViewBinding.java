// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gh;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.flyco.tablayout.CommonTabLayout;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AirDialog_ViewBinding implements Unbinder {
  private AirDialog target;

  @UiThread
  public AirDialog_ViewBinding(AirDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AirDialog_ViewBinding(AirDialog target, View source) {
    this.target = target;

    target.tablayout = Utils.findRequiredViewAsType(source, R.id.tablayout, "field 'tablayout'", CommonTabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AirDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tablayout = null;
    target.viewPager = null;
  }
}
