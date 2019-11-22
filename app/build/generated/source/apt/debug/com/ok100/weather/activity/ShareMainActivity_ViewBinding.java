// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareMainActivity_ViewBinding implements Unbinder {
  private ShareMainActivity target;

  @UiThread
  public ShareMainActivity_ViewBinding(ShareMainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShareMainActivity_ViewBinding(ShareMainActivity target, View source) {
    this.target = target;

    target.mRecycle = Utils.findRequiredViewAsType(source, R.id.recycle, "field 'mRecycle'", RecyclerView.class);
    target.mScrollview = Utils.findRequiredViewAsType(source, R.id.scrollview, "field 'mScrollview'", ScrollView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShareMainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecycle = null;
    target.mScrollview = null;
  }
}
