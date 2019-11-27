package com.ok100.weather.activity;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.location.LocationUtils;
import com.ok100.weather.utils.AppUtils;
import com.ok100.weather.utils.SharePreferencesUtil;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class WelcomeActivity extends BaseActivity  implements EasyPermissions.RationaleCallbacks ,EasyPermissions.PermissionCallbacks{
    private static final String TAG = "WelcomeActivity";
    //    private TextView welcome_tv_version, welcome_tv_load;
//    private List<String> strs = new ArrayList<>();
    private int time = 1;
    private SharePreferencesUtil sharePreferenceUtil;
    private ImageView iv_welcome;
    private TextView tv_app_version;
    private TextView tv_app_name;
   public static final String[] perms = { Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.ACCESS_COARSE_LOCATION };
   public String locationX = "";
   public String locationY = "";
   public String locationCiyt = "";

    public static final int PERMISSIONS_ACCESS_LOCATION = 1001;
    @Override
    public int getLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    public void InitView() {

        tv_app_version = (TextView) findViewById(R.id.tv_app_version);
        tv_app_name = (TextView) findViewById(R.id.tv_app_name);
        String versionCode = AppUtils.getVersionNameCode(WelcomeActivity.this);
        String appName = AppUtils.getAppName(WelcomeActivity.this);
        tv_app_name.setText(appName);
        tv_app_version.setText("V " + versionCode);
        sharePreferenceUtil = new SharePreferencesUtil();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {
        if (EasyPermissions.hasPermissions( WelcomeActivity.this , perms)) {
            // Already have permission, do the thing
            getXY();
            Log.e(TAG , "Already have permission, do the thing" );
        } else {
            // Do not have permissions, request them now
            Log.e(TAG , "需要定位权限" );
            EasyPermissions.requestPermissions(WelcomeActivity.this, "需要定位权限", PERMISSIONS_ACCESS_LOCATION, perms);
        }
//        EasyPermissions.requestPermissions(
//                new PermissionRequest.Builder(this, Manifest.permission.ACCESS_COARSE_LOCATION, perms)
//                        .setRationale(R.string.camera_and_location_rationale)
//                        .setPositiveButtonText(R.string.rationale_ask_ok)
//                        .setNegativeButtonText(R.string.rationale_ask_cancel)
//                        .setTheme(R.style.my_fancy_style)
//                        .build());
    }


    private void getXY() {
        LocationUtils.register(this, 0, 0, new LocationUtils.OnLocationChangeListener() {
            @Override
            public void getLastKnownLocation(Location location) {
                Log.e(TAG, "onLocationChanged: " + location.getLatitude());
            }

            @Override
            public void onLocationChanged(Location location) {
                //位置信息变化时触发
                Log.e(TAG, "定位方式：" + location.getProvider());
                Log.e(TAG, "纬度：" + location.getLatitude());
                Log.e(TAG, "经度：" + location.getLongitude());
                Log.e(TAG, "海拔：" + location.getAltitude());
                Log.e(TAG, "时间：" + location.getTime());
                Log.e(TAG, "国家：" + LocationUtils.getCountryName(WelcomeActivity.this, location.getLatitude(), location.getLongitude()));
                Log.e(TAG, "获取地理位置：" + LocationUtils.getAddress(WelcomeActivity.this, location.getLatitude(), location.getLongitude()));
                Log.e(TAG, "所在地：" + LocationUtils.getLocality(WelcomeActivity.this, location.getLatitude(), location.getLongitude()));
                Log.e(TAG, "所在街道：" + LocationUtils.getStreet(WelcomeActivity.this, location.getLatitude(), location.getLongitude()));
                locationX = location.getLatitude()+"";
                locationY = location.getLongitude()+"";
                locationCiyt = LocationUtils.getLocality(WelcomeActivity.this, location.getLatitude(), location.getLongitude());
//                if(!(TextUtils.isEmpty(locationX)||TextUtils.isEmpty(locationX))){
////                    startHome();
////                }
                startHome();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        });
    }

    private void startHome() {
        new Thread (new Runnable(){
            public void run(){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("locationX",locationX);
                intent.putExtra("locationY",locationY);
                intent.putExtra("locationCiyt",locationCiyt);
                startActivity(intent);
                finish();
            }
        }).start();

    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        // 此处表示权限申请已经成功，可以使用该权限完成app的相应的操作了
        Log.e(TAG , "同意了 权限申请" );
        getXY();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // 此处表示权限申请被用户拒绝了，此处可以通过弹框等方式展示申请该权限的原因，以使用户允许使用该权限

        //(可选的)检查用户是否拒绝授权权限，并且点击了“不再询问”（测试如果不点击 不再询问也会调用这个方法，所以只要拒绝就会调用这个方法）
        //下面的语句，展示一个对话框指导用户在应用设置里授权权限
        Log.e(TAG , "拒绝了 权限申请" );
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("申请权限")
                    .setRationale("应用需要这个权限")
                    .build()
                    .show();
            Log.e(TAG , "引导设置 申请权限" );
        }else {
            request();
        }
    }
    public void request(){
        EasyPermissions.requestPermissions(WelcomeActivity.this, "需要定位权限", 1001, perms);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            // 当用户从应用设置界面返回的时候，可以做一些事情，比如弹出一个土司。
            Toast.makeText(this, "权限设置界面返回" , Toast.LENGTH_SHORT).show();
            startHome();
        }

    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        Log.e(TAG , "引导设置"+requestCode );
    }

    @Override
    public void onRationaleDenied(int requestCode) {
        startHome();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtils.unregister();
    }
}
