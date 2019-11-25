// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
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
  }
}
