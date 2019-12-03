// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class XiaohuaActivity_ViewBinding implements Unbinder {
  private XiaohuaActivity target;

  @UiThread
  public XiaohuaActivity_ViewBinding(XiaohuaActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public XiaohuaActivity_ViewBinding(XiaohuaActivity target, View source) {
    this.target = target;

    target.mRecycleview = Utils.findRequiredViewAsType(source, R.id.recycleview, "field 'mRecycleview'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    XiaohuaActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecycleview = null;
  }
}
