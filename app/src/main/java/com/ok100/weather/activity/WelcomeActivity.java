package com.ok100.weather.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ok100.weather.MainActivity;
import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.constant.ConstantCode;
import com.ok100.weather.constant.GGPositionId;
import com.ok100.weather.dialog.FastInputCostomerFailDialog;
import com.ok100.weather.location.LocationUtils;
import com.ok100.weather.utils.AppUtils;
import com.ok100.weather.utils.SharePreferencesUtil;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class WelcomeActivity extends BaseActivity implements EasyPermissions.RationaleCallbacks, EasyPermissions.PermissionCallbacks, AMapLocationListener, SplashADListener {
    private static final String TAG = "WelcomeActivity";
    @BindView(R.id.splash_container)
    ViewGroup container;
    //    private TextView welcome_tv_version, welcome_tv_load;
//    private List<String> strs = new ArrayList<>();
    private int time = 1;
    private SharePreferencesUtil sharePreferenceUtil;
    private ImageView iv_welcome;
    private TextView tv_app_version;
    private TextView tv_app_name;
    public static final String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    public String locationX = "39.90";
    public String locationY = "116.38";
    public String locationCiyt = "";

    public static final int PERMISSIONS_ACCESS_LOCATION = 1001;

    @Override
    public int getLayoutID() {
        return R.layout.activity_welcome;
    }

    /**
     * 为防止无广告时造成视觉上类似于"闪退"的情况，设定无广告时页面跳转根据需要延迟一定时间，demo
     * 给出的延时逻辑是从拉取广告开始算开屏最少持续多久，仅供参考，开发者可自定义延时逻辑，如果开发者采用demo
     * 中给出的延时逻辑，也建议开发者考虑自定义minSplashTimeWhenNoAD的值（单位ms）
     **/
    private int minSplashTimeWhenNoAD = 2000;
    /**
     * 记录拉取广告的时间
     */
    private long fetchSplashADTime = 0;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void InitView() {

        tv_app_version = (TextView) findViewById(R.id.tv_app_version);
        tv_app_name = (TextView) findViewById(R.id.tv_app_name);
        String versionCode = AppUtils.getVersionNameCode(WelcomeActivity.this);
        String appName = AppUtils.getAppName(WelcomeActivity.this);
        tv_app_name.setText(appName);
        tv_app_version.setText("V " + versionCode);
        sharePreferenceUtil = new SharePreferencesUtil();
        MobclickAgent.onEvent(WelcomeActivity.this, "open_weather");


        if (Build.VERSION.SDK_INT >= 23) {
            checkAndRequestPermission();
        }
//        else {
//            // 如果是Android6.0以下的机器，建议在manifest中配置相关权限，这里可以直接调用SDK
//            fetchSplashAD(this, container, skipView, ConstantCode.GGAPPID, getPosId(), this, 0);
//        }
        getTestDeviceInfo(WelcomeActivity.this);

    }

    public static String[] getTestDeviceInfo(Context context){
        String[] deviceInfo = new String[2];
        try {
            if(context != null){
                deviceInfo[0] = DeviceConfig.getDeviceIdForGeneral(context);
                deviceInfo[1] = DeviceConfig.getMac(context);
                Log.e("deviceInfo",deviceInfo[0]);
                Log.e("deviceInfo",deviceInfo[1]);
            }
        } catch (Exception e){
        }
        return deviceInfo;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {


        String isFirstAppLogin = (String) SharePreferencesUtil.get(WelcomeActivity.this, "isFirstAppLogin", "0");
        if (isFirstAppLogin.equals("0")) {

            FastInputCostomerFailDialog fastInputCostomerFailDialog = new FastInputCostomerFailDialog(WelcomeActivity.this);
            fastInputCostomerFailDialog.setOnOffDialogListener(new FastInputCostomerFailDialog.OffDialogListener() {

                @Override
                public void offDialog(boolean isagree) {
                    if (isagree) {
                        initDataAgree();
                        SharePreferencesUtil.put(WelcomeActivity.this, "isFirstAppLogin", "1");
                        fastInputCostomerFailDialog.dismiss();
                        MobclickAgent.onEvent(WelcomeActivity.this, "download_app");
                    } else {
                        finish();
                    }
                }
            });

            showDialog(fastInputCostomerFailDialog, "fastInputCostomerFailDialog");
        } else {
            initDataAgree();
        }


    }

    private void initDataAgree() {
        if (EasyPermissions.hasPermissions(WelcomeActivity.this, perms)) {
            // Already have permission, do the thing
//            getXY();
            locationGD();
            Log.e(TAG, "Already have permission, do the thing");
        } else {
            // Do not have permissions, request them now
            Log.e(TAG, "需要定位权限");
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
                locationX = location.getLatitude() + "";
                locationY = location.getLongitude() + "";
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
        new Thread(new Runnable() {
            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fetchSplashAD(WelcomeActivity.this, container, skipView, ConstantCode.GGAPPID, getPosId(), WelcomeActivity.this, 0);
                    }
                });

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

        if (requestCode == 1024 && hasAllPermissionsGranted(grantResults)) {
//            fetchSplashAD(this, container, skipView, ConstantCode.GGAPPID, getPosId(), this, 0);
        } else {
            Toast.makeText(this, "应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        // 此处表示权限申请已经成功，可以使用该权限完成app的相应的操作了
        Log.e(TAG, "同意了 权限申请");
//        getXY();
        locationGD();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // 此处表示权限申请被用户拒绝了，此处可以通过弹框等方式展示申请该权限的原因，以使用户允许使用该权限

        //(可选的)检查用户是否拒绝授权权限，并且点击了“不再询问”（测试如果不点击 不再询问也会调用这个方法，所以只要拒绝就会调用这个方法）
        //下面的语句，展示一个对话框指导用户在应用设置里授权权限
        Log.e(TAG, "拒绝了 权限申请");
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("申请权限")
                    .setRationale("应用需要这个权限")
                    .build()
                    .show();
            Log.e(TAG, "引导设置 申请权限");
        } else {
            request();
        }
    }

    public void request() {
        EasyPermissions.requestPermissions(WelcomeActivity.this, "需要定位权限", 1001, perms);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            // 当用户从应用设置界面返回的时候，可以做一些事情，比如弹出一个土司。
            Toast.makeText(this, "权限设置界面返回", Toast.LENGTH_SHORT).show();
            startHome();
        }

    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        Log.e(TAG, "引导设置" + requestCode);
    }

    @Override
    public void onRationaleDenied(int requestCode) {
        startHome();
    }


    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    public void locationGD() {

        mlocationClient = new AMapLocationClient(this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //单次定位
        mLocationOption.setOnceLocation(true);
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(2000);
        mLocationOption.setNeedAddress(true);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", amapLocation.getLatitude()
                        + amapLocation.getLatitude() + ", errInfo:"
                        + amapLocation.getDistrict() + amapLocation.getCity());
                locationX = amapLocation.getLatitude() + "";
                locationY = amapLocation.getLongitude() + "";

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
//            SystemClock.sleep(2000);
            startHome();
        }
    }


    public boolean canJump = false;
    private boolean needStartDemoList = true;
    private SplashAD splashAD;

    private TextView skipView;
    private ImageView splashHolder;
    private static final String SKIP_TEXT = "点击跳过 %d";

    private void fetchSplashAD(Activity activity, ViewGroup adContainer, View skipContainer,
                               String appId, String posId, SplashADListener adListener, int fetchDelay) {
        fetchSplashADTime = System.currentTimeMillis();
        splashAD = new SplashAD(activity, skipContainer, appId, posId, adListener, fetchDelay);
        splashAD.fetchAndShowIn(adContainer);
    }

    @Override
    public void onADPresent() {
        Log.i("AD_DEMO", "SplashADPresent");
    }

    @Override
    public void onADClicked() {
        Log.i("AD_DEMO", "SplashADClicked clickUrl: "
                + (splashAD.getExt() != null ? splashAD.getExt().get("clickUrl") : ""));
    }

    /**
     * 倒计时回调，返回广告还将被展示的剩余时间。
     * 通过这个接口，开发者可以自行决定是否显示倒计时提示，或者还剩几秒的时候显示倒计时
     *
     * @param millisUntilFinished 剩余毫秒数
     */
    @Override
    public void onADTick(long millisUntilFinished) {
        Log.i("AD_DEMO", "SplashADTick " + millisUntilFinished + "ms");
        if (skipView != null) {
            skipView.setText(String.format(SKIP_TEXT, Math.round(millisUntilFinished / 1000f)));
        }
    }

    @Override
    public void onADExposure() {
        Log.i("AD_DEMO", "SplashADExposure");
    }

    @Override
    public void onADDismissed() {
        Log.i("AD_DEMO", "SplashADDismissed");
        next();
    }

    @Override
    public void onNoAD(AdError error) {
        Log.i(
                "AD_DEMO",
                String.format("LoadSplashADFail, eCode=%d, errorMsg=%s", error.getErrorCode(),
                        error.getErrorMsg()));
        /**
         * 为防止无广告时造成视觉上类似于"闪退"的情况，设定无广告时页面跳转根据需要延迟一定时间，demo
         * 给出的延时逻辑是从拉取广告开始算开屏最少持续多久，仅供参考，开发者可自定义延时逻辑，如果开发者采用demo
         * 中给出的延时逻辑，也建议开发者考虑自定义minSplashTimeWhenNoAD的值
         **/
        long alreadyDelayMills = System.currentTimeMillis() - fetchSplashADTime;//从拉广告开始到onNoAD已经消耗了多少时间
        long shouldDelayMills = alreadyDelayMills > minSplashTimeWhenNoAD ? 0 : minSplashTimeWhenNoAD
                - alreadyDelayMills;//为防止加载广告失败后立刻跳离开屏可能造成的视觉上类似于"闪退"的情况，根据设置的minSplashTimeWhenNoAD
        // 计算出还需要延时多久
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (needStartDemoList) {
//                    WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.putExtra("locationX",locationX);
                    intent.putExtra("locationY",locationY);
                    intent.putExtra("locationCiyt",locationCiyt);
                    startActivity(intent);
                    finish();
                }
                WelcomeActivity.this.finish();
            }
        }, shouldDelayMills);
    }

    /**
     * 设置一个变量来控制当前开屏页面是否可以跳转，当开屏广告为普链类广告时，点击会打开一个广告落地页，此时开发者还不能打开自己的App主页。当从广告落地页返回以后，
     * 才可以跳转到开发者自己的App主页；当开屏广告是App类广告时只会下载App。
     */
    private void next() {
        if (canJump) {
            if (needStartDemoList) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("locationX",locationX);
                intent.putExtra("locationY",locationY);
                intent.putExtra("locationCiyt",locationCiyt);
                startActivity(intent);
                finish();
//                this.startActivity(new Intent(this, MainActivity.class));
            }
            this.finish();
        } else {
            canJump = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (canJump) {
            next();
        }
        canJump = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        LocationUtils.unregister();
    }

    /**
     * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * ----------非常重要----------
     * <p>
     * Android6.0以上的权限适配简单示例：
     * <p>
     * 如果targetSDKVersion >= 23，那么建议动态申请相关权限，再调用广点通SDK
     * <p>
     * SDK不强制校验下列权限（即:无下面权限sdk也可正常工作），但建议开发者申请下面权限，尤其是READ_PHONE_STATE权限
     * <p>
     * READ_PHONE_STATE权限用于允许SDK获取用户标识,
     * 针对单媒体的用户，允许获取权限的，投放定向广告；不允许获取权限的用户，投放通投广告，媒体可以选择是否把用户标识数据提供给优量汇，并承担相应广告填充和eCPM单价下降损失的结果。
     * <p>
     * Demo代码里是一个基本的权限申请示例，请开发者根据自己的场景合理地编写这部分代码来实现权限申请。
     * 注意：下面的`checkSelfPermission`和`requestPermissions`方法都是在Android6.0的SDK中增加的API，如果您的App还没有适配到Android6.0以上，则不需要调用这些方法，直接调用广点通SDK即可。
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        // 如果需要的权限都已经有了，那么直接调用SDK
        if (lackedPermission.size() == 0) {
//            fetchSplashAD(this, container, skipView, ConstantCode.GGAPPID, getPosId(), this, 0);
        } else {
            // 否则，建议请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


    private String getPosId() {
        return GGPositionId.SPLASH_POS_ID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
