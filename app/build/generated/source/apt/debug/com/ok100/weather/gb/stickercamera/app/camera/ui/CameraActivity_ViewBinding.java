// Generated code from Butter Knife. Do not modify!
package com.ok100.weather.gb.stickercamera.app.camera.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ok100.weather.R;
import com.ok100.weather.gb.customview.CameraGrid;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CameraActivity_ViewBinding implements Unbinder {
  private CameraActivity target;

  @UiThread
  public CameraActivity_ViewBinding(CameraActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CameraActivity_ViewBinding(CameraActivity target, View source) {
    this.target = target;

    target.cameraGrid = Utils.findRequiredViewAsType(source, R.id.masking, "field 'cameraGrid'", CameraGrid.class);
    target.photoArea = Utils.findRequiredViewAsType(source, R.id.photo_area, "field 'photoArea'", LinearLayout.class);
    target.takePhotoPanel = Utils.findRequiredView(source, R.id.panel_take_photo, "field 'takePhotoPanel'");
    target.takePicture = Utils.findRequiredViewAsType(source, R.id.takepicture, "field 'takePicture'", Button.class);
    target.flashBtn = Utils.findRequiredViewAsType(source, R.id.flashBtn, "field 'flashBtn'", ImageView.class);
    target.changeBtn = Utils.findRequiredViewAsType(source, R.id.change, "field 'changeBtn'", ImageView.class);
    target.backBtn = Utils.findRequiredViewAsType(source, R.id.back, "field 'backBtn'", ImageView.class);
    target.galleryBtn = Utils.findRequiredViewAsType(source, R.id.next, "field 'galleryBtn'", ImageView.class);
    target.focusIndex = Utils.findRequiredView(source, R.id.focus_index, "field 'focusIndex'");
    target.surfaceView = Utils.findRequiredViewAsType(source, R.id.surfaceView, "field 'surfaceView'", SurfaceView.class);
    target.root_view_layout = Utils.findRequiredViewAsType(source, R.id.root_view_layout, "field 'root_view_layout'", LinearLayout.class);
    target.sub_img = Utils.findRequiredViewAsType(source, R.id.sub_img, "field 'sub_img'", ImageView.class);
    target.animal_img = Utils.findRequiredViewAsType(source, R.id.animal_img, "field 'animal_img'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CameraActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cameraGrid = null;
    target.photoArea = null;
    target.takePhotoPanel = null;
    target.takePicture = null;
    target.flashBtn = null;
    target.changeBtn = null;
    target.backBtn = null;
    target.galleryBtn = null;
    target.focusIndex = null;
    target.surfaceView = null;
    target.root_view_layout = null;
    target.sub_img = null;
    target.animal_img = null;
  }
}
