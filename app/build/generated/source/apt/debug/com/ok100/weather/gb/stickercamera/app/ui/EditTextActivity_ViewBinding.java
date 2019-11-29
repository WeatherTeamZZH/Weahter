// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gb.stickercamera.app.ui;

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

public class EditTextActivity_ViewBinding implements Unbinder {
  private EditTextActivity target;

  @UiThread
  public EditTextActivity_ViewBinding(EditTextActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditTextActivity_ViewBinding(EditTextActivity target, View source) {
    this.target = target;

    target.contentView = Utils.findRequiredViewAsType(source, R.id.text_input, "field 'contentView'", EditText.class);
    target.numberTips = Utils.findRequiredViewAsType(source, R.id.tag_input_tips, "field 'numberTips'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditTextActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.contentView = null;
    target.numberTips = null;
  }
}
