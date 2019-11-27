// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gb.stickercamera.app.camera.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.imagezoom.ImageViewTouch;
import com.ok100.weather.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CropPhotoActivity_ViewBinding implements Unbinder {
  private CropPhotoActivity target;

  @UiThread
  public CropPhotoActivity_ViewBinding(CropPhotoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CropPhotoActivity_ViewBinding(CropPhotoActivity target, View source) {
    this.target = target;

    target.cropImage = Utils.findRequiredViewAsType(source, R.id.crop_image, "field 'cropImage'", ImageViewTouch.class);
    target.drawArea = Utils.findRequiredViewAsType(source, R.id.draw_area, "field 'drawArea'", ViewGroup.class);
    target.wrapImage = Utils.findRequiredView(source, R.id.wrap_image, "field 'wrapImage'");
    target.btnCropType = Utils.findRequiredView(source, R.id.btn_crop_type, "field 'btnCropType'");
    target.imageCenter = Utils.findRequiredViewAsType(source, R.id.image_center, "field 'imageCenter'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CropPhotoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cropImage = null;
    target.drawArea = null;
    target.wrapImage = null;
    target.btnCropType = null;
    target.imageCenter = null;
  }
}
