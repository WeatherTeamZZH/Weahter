// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gh;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AirItemFragment_ViewBinding implements Unbinder {
  private AirItemFragment target;

  @UiThread
  public AirItemFragment_ViewBinding(AirItemFragment target, View source) {
    this.target = target;

    target.sesameView = Utils.findRequiredViewAsType(source, R.id.sesame_view, "field 'sesameView'", NewCreditSesameView.class);
    target.tvCo2 = Utils.findRequiredViewAsType(source, R.id.tv_co2, "field 'tvCo2'", TextView.class);
    target.tvO3 = Utils.findRequiredViewAsType(source, R.id.tv_o3, "field 'tvO3'", TextView.class);
    target.tvSo2 = Utils.findRequiredViewAsType(source, R.id.tv_so2, "field 'tvSo2'", TextView.class);
    target.tvCo = Utils.findRequiredViewAsType(source, R.id.tv_co, "field 'tvCo'", TextView.class);
    target.tvPm25 = Utils.findRequiredViewAsType(source, R.id.tv_pm25, "field 'tvPm25'", TextView.class);
    target.tvPm10 = Utils.findRequiredViewAsType(source, R.id.tv_pm10, "field 'tvPm10'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AirItemFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sesameView = null;
    target.tvCo2 = null;
    target.tvO3 = null;
    target.tvSo2 = null;
    target.tvCo = null;
    target.tvPm25 = null;
    target.tvPm10 = null;
  }
}
