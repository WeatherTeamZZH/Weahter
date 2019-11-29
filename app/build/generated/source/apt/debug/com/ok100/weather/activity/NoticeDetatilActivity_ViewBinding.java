// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoticeDetatilActivity_ViewBinding implements Unbinder {
  private NoticeDetatilActivity target;

  @UiThread
  public NoticeDetatilActivity_ViewBinding(NoticeDetatilActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NoticeDetatilActivity_ViewBinding(NoticeDetatilActivity target, View source) {
    this.target = target;

    target.mWebview = Utils.findRequiredViewAsType(source, R.id.webview, "field 'mWebview'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NoticeDetatilActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWebview = null;
  }
}
