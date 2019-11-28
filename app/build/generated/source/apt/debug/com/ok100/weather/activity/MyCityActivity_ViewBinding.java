// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
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

public class MyCityActivity_ViewBinding implements Unbinder {
  private MyCityActivity target;

  @UiThread
  public MyCityActivity_ViewBinding(MyCityActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyCityActivity_ViewBinding(MyCityActivity target, View source) {
    this.target = target;

    target.mTvMyCity = Utils.findRequiredViewAsType(source, R.id.tv_my_city, "field 'mTvMyCity'", TextView.class);
    target.editText = Utils.findRequiredViewAsType(source, R.id.edittext_search, "field 'editText'", EditText.class);
    target.mTextviewQuxiao = Utils.findRequiredViewAsType(source, R.id.textview_quxiao, "field 'mTextviewQuxiao'", TextView.class);
    target.mRecycleview1 = Utils.findRequiredViewAsType(source, R.id.recycleview1, "field 'mRecycleview1'", RecyclerView.class);
    target.mRecycleview2 = Utils.findRequiredViewAsType(source, R.id.recycleview2, "field 'mRecycleview2'", RecyclerView.class);
    target.mRecycleview3 = Utils.findRequiredViewAsType(source, R.id.recycleview3, "field 'mRecycleview3'", RecyclerView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mLlRecycle1 = Utils.findRequiredViewAsType(source, R.id.ll_recycle1, "field 'mLlRecycle1'", LinearLayout.class);
    target.mLlRecycle2 = Utils.findRequiredViewAsType(source, R.id.ll_recycle2, "field 'mLlRecycle2'", LinearLayout.class);
    target.mIvAddCity = Utils.findRequiredViewAsType(source, R.id.iv_add_city, "field 'mIvAddCity'", ImageView.class);
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    target.mIvChooseCity = Utils.findRequiredViewAsType(source, R.id.iv_choose_city, "field 'mIvChooseCity'", ImageView.class);
    target.mRecycleview4 = Utils.findRequiredViewAsType(source, R.id.recycleview4, "field 'mRecycleview4'", RecyclerView.class);
    target.mNestedscrollview = Utils.findRequiredViewAsType(source, R.id.nestedscrollview, "field 'mNestedscrollview'", NestedScrollView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyCityActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvMyCity = null;
    target.editText = null;
    target.mTextviewQuxiao = null;
    target.mRecycleview1 = null;
    target.mRecycleview2 = null;
    target.mRecycleview3 = null;
    target.mRlTitle = null;
    target.mLlRecycle1 = null;
    target.mLlRecycle2 = null;
    target.mIvAddCity = null;
    target.mIvBack = null;
    target.mIvChooseCity = null;
    target.mRecycleview4 = null;
    target.mNestedscrollview = null;
  }
}
