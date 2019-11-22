// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserInofActivity_ViewBinding implements Unbinder {
  private UserInofActivity target;

  @UiThread
  public UserInofActivity_ViewBinding(UserInofActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserInofActivity_ViewBinding(UserInofActivity target, View source) {
    this.target = target;

    target.mRecycleview = Utils.findRequiredViewAsType(source, R.id.recycleview, "field 'mRecycleview'", RecyclerView.class);
    target.mRecycleview1 = Utils.findRequiredViewAsType(source, R.id.recycleview1, "field 'mRecycleview1'", RecyclerView.class);
    target.mIvSetting = Utils.findRequiredViewAsType(source, R.id.iv_setting, "field 'mIvSetting'", ImageView.class);
    target.mIvQiandao = Utils.findRequiredViewAsType(source, R.id.iv_qiandao, "field 'mIvQiandao'", TextView.class);
    target.mIvBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    target.mTvXiaoxi = Utils.findRequiredViewAsType(source, R.id.tv_xiaoxi, "field 'mTvXiaoxi'", TextView.class);
    target.mTvZhuti = Utils.findRequiredViewAsType(source, R.id.tv_zhuti, "field 'mTvZhuti'", TextView.class);
    target.mTvYijian = Utils.findRequiredViewAsType(source, R.id.tv_yijian, "field 'mTvYijian'", TextView.class);
    target.mLlNoticeMore = Utils.findRequiredViewAsType(source, R.id.ll_notice_more, "field 'mLlNoticeMore'", LinearLayout.class);
    target.mIvGotoMaincenter = Utils.findRequiredViewAsType(source, R.id.iv_goto_maincenter, "field 'mIvGotoMaincenter'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserInofActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecycleview = null;
    target.mRecycleview1 = null;
    target.mIvSetting = null;
    target.mIvQiandao = null;
    target.mIvBack = null;
    target.mTvXiaoxi = null;
    target.mTvZhuti = null;
    target.mTvYijian = null;
    target.mLlNoticeMore = null;
    target.mIvGotoMaincenter = null;
  }
}
