// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.mEdittext1 = Utils.findRequiredViewAsType(source, R.id.edittext1, "field 'mEdittext1'", EditText.class);
    target.mTextview1 = Utils.findRequiredViewAsType(source, R.id.textview1, "field 'mTextview1'", TextView.class);
    target.mEdittext2 = Utils.findRequiredViewAsType(source, R.id.edittext2, "field 'mEdittext2'", EditText.class);
    target.mTextview2 = Utils.findRequiredViewAsType(source, R.id.textview2, "field 'mTextview2'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.ivTitleLeft = Utils.findRequiredViewAsType(source, R.id.iv_title_left, "field 'ivTitleLeft'", ImageView.class);
    target.tvTitleLeft = Utils.findRequiredViewAsType(source, R.id.tv_title_left, "field 'tvTitleLeft'", TextView.class);
    target.llTitleLeft = Utils.findRequiredViewAsType(source, R.id.ll_title_left, "field 'llTitleLeft'", LinearLayout.class);
    target.ivTitleRight = Utils.findRequiredViewAsType(source, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    target.tvTitleRight = Utils.findRequiredViewAsType(source, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    target.llTitleRight = Utils.findRequiredViewAsType(source, R.id.ll_title_right, "field 'llTitleRight'", LinearLayout.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    target.baseRlTitle = Utils.findRequiredViewAsType(source, R.id.base_rl_title, "field 'baseRlTitle'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEdittext1 = null;
    target.mTextview1 = null;
    target.mEdittext2 = null;
    target.mTextview2 = null;
    target.tvTitle = null;
    target.ivTitleLeft = null;
    target.tvTitleLeft = null;
    target.llTitleLeft = null;
    target.ivTitleRight = null;
    target.tvTitleRight = null;
    target.llTitleRight = null;
    target.viewLine = null;
    target.baseRlTitle = null;
  }
}
