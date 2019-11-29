// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import com.ok100.weather.view.MyRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoticeMainFragment1_ViewBinding implements Unbinder {
  private NoticeMainFragment1 target;

  @UiThread
  public NoticeMainFragment1_ViewBinding(NoticeMainFragment1 target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycle, "field 'mRecyclerView'", MyRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NoticeMainFragment1 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
  }
}
