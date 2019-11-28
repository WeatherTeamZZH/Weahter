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

public class AboutOursActivity_ViewBinding implements Unbinder {
  private AboutOursActivity target;

  @UiThread
  public AboutOursActivity_ViewBinding(AboutOursActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AboutOursActivity_ViewBinding(AboutOursActivity target, View source) {
    this.target = target;

    target.mWebView = Utils.findRequiredViewAsType(source, R.id.webView, "field 'mWebView'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AboutOursActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mWebView = null;
  }
}
