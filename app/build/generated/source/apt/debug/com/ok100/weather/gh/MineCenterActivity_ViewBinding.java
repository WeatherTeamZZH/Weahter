// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gh;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineCenterActivity_ViewBinding implements Unbinder {
  private MineCenterActivity target;

  private View view7f0800e8;

  private View view7f0800ea;

  private View view7f0800e5;

  private View view7f0800f1;

  private View view7f0800ed;

  private View view7f0800f6;

  private View view7f0801d8;

  @UiThread
  public MineCenterActivity_ViewBinding(MineCenterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MineCenterActivity_ViewBinding(final MineCenterActivity target, View source) {
    this.target = target;

    View view;
    target.ivImg = Utils.findRequiredViewAsType(source, R.id.iv_img, "field 'ivImg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_img, "field 'llImg' and method 'onViewClicked'");
    target.llImg = Utils.castView(view, R.id.ll_img, "field 'llImg'", LinearLayout.class);
    view7f0800e8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvNick = Utils.findRequiredViewAsType(source, R.id.tv_nick, "field 'tvNick'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_nick, "field 'llNick' and method 'onViewClicked'");
    target.llNick = Utils.castView(view, R.id.ll_nick, "field 'llNick'", LinearLayout.class);
    view7f0800ea = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBirth = Utils.findRequiredViewAsType(source, R.id.tv_birth, "field 'tvBirth'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_birth, "field 'llBirth' and method 'onViewClicked'");
    target.llBirth = Utils.castView(view, R.id.ll_birth, "field 'llBirth'", LinearLayout.class);
    view7f0800e5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvSex = Utils.findRequiredViewAsType(source, R.id.tv_sex, "field 'tvSex'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_sex, "field 'llSex' and method 'onViewClicked'");
    target.llSex = Utils.castView(view, R.id.ll_sex, "field 'llSex'", LinearLayout.class);
    view7f0800f1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_phone, "field 'llPhone' and method 'onViewClicked'");
    target.llPhone = Utils.castView(view, R.id.ll_phone, "field 'llPhone'", LinearLayout.class);
    view7f0800ed = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvWechat = Utils.findRequiredViewAsType(source, R.id.tv_wechat, "field 'tvWechat'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_wechat, "field 'llWechat' and method 'onViewClicked'");
    target.llWechat = Utils.castView(view, R.id.ll_wechat, "field 'llWechat'", LinearLayout.class);
    view7f0800f6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_logout, "field 'tvLogout' and method 'onViewClicked'");
    target.tvLogout = Utils.castView(view, R.id.tv_logout, "field 'tvLogout'", TextView.class);
    view7f0801d8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MineCenterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivImg = null;
    target.llImg = null;
    target.tvNick = null;
    target.llNick = null;
    target.tvBirth = null;
    target.llBirth = null;
    target.tvSex = null;
    target.llSex = null;
    target.tvPhone = null;
    target.llPhone = null;
    target.tvWechat = null;
    target.llWechat = null;
    target.tvLogout = null;

    view7f0800e8.setOnClickListener(null);
    view7f0800e8 = null;
    view7f0800ea.setOnClickListener(null);
    view7f0800ea = null;
    view7f0800e5.setOnClickListener(null);
    view7f0800e5 = null;
    view7f0800f1.setOnClickListener(null);
    view7f0800f1 = null;
    view7f0800ed.setOnClickListener(null);
    view7f0800ed = null;
    view7f0800f6.setOnClickListener(null);
    view7f0800f6 = null;
    view7f0801d8.setOnClickListener(null);
    view7f0801d8 = null;
  }
}
