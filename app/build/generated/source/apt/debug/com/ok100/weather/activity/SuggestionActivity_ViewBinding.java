// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SuggestionActivity_ViewBinding implements Unbinder {
  private SuggestionActivity target;

  @UiThread
  public SuggestionActivity_ViewBinding(SuggestionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SuggestionActivity_ViewBinding(SuggestionActivity target, View source) {
    this.target = target;

    target.mSuggestEtInput = Utils.findRequiredViewAsType(source, R.id.suggest_et_input, "field 'mSuggestEtInput'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SuggestionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mSuggestEtInput = null;
  }
}
