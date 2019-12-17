package com.ok100.weather.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.HashMap;

/**
 * @Description: This is UCParamUtils
 * @Author: QianDongDong
 * @Time: 2019/12/16 11:30
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class UCParamUtils {

    private Context mContext;
    public UCParamUtils(Context mContext){
        this.mContext = mContext;
    }

    public HashMap<String ,String > getUcParamHashmap(){
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("app","tiantianqixiang-iflow");
        stringStringHashMap.put("dn",getIMEI());
        stringStringHashMap.put("fr","android");
        try {
            stringStringHashMap.put("ve",getVersionName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stringStringHashMap.put("imei",getIMEI());
        stringStringHashMap.put("oaid",getAndroidId());
        int networkState = IntenetUtil.getNetworkState(mContext);
        if(networkState==1||networkState==7){
            stringStringHashMap.put("nt","2");
        }else if(networkState==2||networkState==3||networkState==4||networkState==5||networkState==6){
            stringStringHashMap.put("nt","1");
        }else {
            stringStringHashMap.put("nt","99");
        }

//        stringStringHashMap.put("client_ip","");

        return stringStringHashMap;
    }

    public String getIMEI(){
        TelephonyManager telephonyManager = (TelephonyManager)mContext.getSystemService(mContext.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String imei = telephonyManager.getDeviceId();
        return imei;
    }

    private String getVersionName() throws Exception
    {
        // 获取packagemanager的实例
        PackageManager packageManager = mContext.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(),0);
        String version = packInfo.versionName;
        return version;
    }

    public String getAndroidId(){
        String ANDROID_ID = Settings.System.getString(mContext.getContentResolver(), Settings.System.ANDROID_ID);
        return ANDROID_ID;
    }


    
}
