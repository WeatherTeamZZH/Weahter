// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gb.stickercamera.app.camera.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import it.sephiroth.android.library.widget.HListView;
import java.lang.IllegalStateException;
import java.lang.Override;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

public class PhotoProcessActivity_ViewBinding implements Unbinder {
  private PhotoProcessActivity target;

  @UiThread
  public PhotoProcessActivity_ViewBinding(PhotoProcessActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhotoProcessActivity_ViewBinding(PhotoProcessActivity target, View source) {
    this.target = target;

    target.mGPUImageView = Utils.findRequiredViewAsType(source, R.id.gpuimage, "field 'mGPUImageView'", GPUImageView.class);
    target.drawArea = Utils.findRequiredViewAsType(source, R.id.drawing_view_container, "field 'drawArea'", ViewGroup.class);
    target.stickerBtn = Utils.findRequiredViewAsType(source, R.id.sticker_btn, "field 'stickerBtn'", TextView.class);
    target.filterBtn = Utils.findRequiredViewAsType(source, R.id.filter_btn, "field 'filterBtn'", TextView.class);
    target.labelBtn = Utils.findRequiredViewAsType(source, R.id.text_btn, "field 'labelBtn'", TextView.class);
    target.bottomToolBar = Utils.findRequiredViewAsType(source, R.id.list_tools, "field 'bottomToolBar'", HListView.class);
    target.toolArea = Utils.findRequiredViewAsType(source, R.id.toolbar_area, "field 'toolArea'", ViewGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PhotoProcessActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mGPUImageView = null;
    target.drawArea = null;
    target.stickerBtn = null;
    target.filterBtn = null;
    target.labelBtn = null;
    target.bottomToolBar = null;
    target.toolArea = null;
  }
}
