package com.ok100.weather.model;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.ok100.weather.bean.HistoryTodayBean;
import com.ok100.weather.bean.LoginBean;
import com.ok100.weather.bean.SMSBean;
import com.ok100.weather.bean.XiaohuaBean;
import com.ok100.weather.bean.ZhougongBean;
import com.ok100.weather.bean.ZhougongStalyBean;
import com.ok100.weather.contract.LoginContract;
import com.ok100.weather.contract.UserInfoIconContract;
import com.ok100.weather.http.DialogCallback;
import com.ok100.weather.http.ServiceResult;
import com.ok100.weather.http.Urls;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by qiandd on 2017/09/07
 */

public class UserInfoIconModelImpl implements UserInfoIconContract.Model {

    @Override
    public void getZhougongData(Context context, Map<String, String> map, ServiceResult<ZhougongBean> serviceResult) {
        OkHttpUtils.post(Urls.zhouGongUrl).params(map).execute(new DialogCallback<ZhougongBean>(context, new TypeToken<ZhougongBean>() {
        }.getType()) {
            @Override
            public void onSuccess(ZhougongBean newsListBean, Call call, Response response) {
                serviceResult.onSuccess(newsListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void getZhougongStateData(Context context, Map<String, String> map, ServiceResult<ZhougongStalyBean> serviceResult) {
        OkHttpUtils.post(Urls.zhouGongStyleUrl).params(map).execute(new DialogCallback<ZhougongStalyBean>(context, new TypeToken<ZhougongStalyBean>() {
        }.getType()) {
            @Override
            public void onSuccess(ZhougongStalyBean newsListBean, Call call, Response response) {
                serviceResult.onSuccess(newsListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void getXiaohua(Context context, Map<String, String> map, ServiceResult<XiaohuaBean> serviceResult) {
        OkHttpUtils.get(Urls.xiaoHuaUrl).params(map).execute(new DialogCallback<XiaohuaBean>(context, new TypeToken<XiaohuaBean>() {
        }.getType()) {
            @Override
            public void onSuccess(XiaohuaBean newsListBean, Call call, Response response) {
                serviceResult.onSuccess(newsListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void getHisttoryTodatData(Context context, Map<String, String> map, ServiceResult<HistoryTodayBean> serviceResult) {
        OkHttpUtils.post(Urls.historyTodayUrl).params(map).execute(new DialogCallback<HistoryTodayBean>(context, new TypeToken<HistoryTodayBean>() {
        }.getType()) {
            @Override
            public void onSuccess(HistoryTodayBean newsListBean, Call call, Response response) {
                serviceResult.onSuccess(newsListBean);
            }
        }.showErrorMsg());
    }
}