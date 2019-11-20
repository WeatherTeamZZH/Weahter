package com.ok100.weather.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.contract.NoticeMainListContract;
import com.ok100.weather.http.DialogCallback;
import com.ok100.weather.http.ServiceResult;
import com.ok100.weather.http.Urls;


import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by qiandd on 2017/09/07
 */

public class NoticeMainListModelImpl implements NoticeMainListContract.Model {

    private String UserId = "Authorization";
    private String UserKey = "APPCODE 4a03888e106b4dc389c31b6469f0da0b";
    @Override
    public void getNoticeList(Context context, Map<String, String> map, final ServiceResult<String> serviceResult) {
        OkHttpUtils.post(Urls.noticeMainList).params(map).execute(new DialogCallback<String>(context, new TypeToken<String>() {
        }.getType()) {
            @Override
            public void onSuccess(String noticeMainListBean, Call call, Response response) {
                serviceResult.onSuccess(noticeMainListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void getTotalWeather(Context context, Map<String, String> map, ServiceResult<WeatherTotalBean> serviceResult) {
        OkHttpUtils.get(Urls.httpWeather).params(map).headers(UserId,UserKey).execute(new DialogCallback<WeatherTotalBean>(context, new TypeToken<WeatherTotalBean>() {
        }.getType()) {
            @Override
            public void onSuccess(WeatherTotalBean noticeMainListBean, Call call, Response response) {
                serviceResult.onSuccess(noticeMainListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void getTotalWeather7(Context context, Map<String, String> map, ServiceResult<WeatherTotal7Bean> serviceResult) {
        OkHttpUtils.get(Urls.httpWeather).params(map).headers(UserId,UserKey).execute(new DialogCallback<WeatherTotal7Bean>(context, new TypeToken<WeatherTotal7Bean>() {
        }.getType()) {
            @Override
            public void onSuccess(WeatherTotal7Bean noticeMainListBean, Call call, Response response) {
                serviceResult.onSuccess(noticeMainListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void getTotalWeather15(Context context, Map<String, String> map, ServiceResult<WeatherTotal15Bean> serviceResult) {
        OkHttpUtils.get(Urls.httpWeather).params(map).headers(UserId,UserKey).execute(new DialogCallback<WeatherTotal15Bean>(context, new TypeToken<WeatherTotal15Bean>() {
        }.getType()) {
            @Override
            public void onSuccess(WeatherTotal15Bean noticeMainListBean, Call call, Response response) {
                serviceResult.onSuccess(noticeMainListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void getTotalWeather24(Context context, Map<String, String> map, ServiceResult<WeatherTotal24Bean> serviceResult) {
        OkHttpUtils.get(Urls.httpWeather).params(map).headers(UserId,UserKey).execute(new DialogCallback<WeatherTotal24Bean>(context, new TypeToken<WeatherTotal24Bean>() {
        }.getType()) {
            @Override
            public void onSuccess(WeatherTotal24Bean noticeMainListBean, Call call, Response response) {
                serviceResult.onSuccess(noticeMainListBean);
            }
        }.showErrorMsg());
    }
}