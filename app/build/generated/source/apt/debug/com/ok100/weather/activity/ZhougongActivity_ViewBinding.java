// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ZhougongActivity_ViewBinding implements Unbinder {
  private ZhougongActivity target;

  @UiThread
  public ZhougongActivity_ViewBinding(ZhougongActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ZhougongActivity_ViewBinding(ZhougongActivity target, View source) {
    this.target = target;

    target.mRecycleview = Utils.findRequiredViewAsType(source, R.id.recycleview, "field 'mRecycleview'", RecyclerView.class);
    target.mEdittext = Utils.findRequiredViewAsType(source, R.id.edittext, "field 'mEdittext'", EditText.class);
    target.mTvSearch = Utils.findRequiredViewAsType(source, R.id.tv_search, "field 'mTvSearch'", TextView.class);
    target.mRecycleviewTitle = Utils.findRequiredViewAsType(source, R.id.recycleview_title, "field 'mRecycleviewTitle'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ZhougongActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecycleview = null;
    target.mEdittext = null;
    target.mTvSearch = null;
    target.mRecycleviewTitle = null;
  }
}
