package com.ok100.weather.model;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.ok100.weather.bean.LoginBean;
import com.ok100.weather.bean.SMSBean;
import com.ok100.weather.contract.LoginContract;
import com.ok100.weather.http.DialogCallback;
import com.ok100.weather.http.ServiceResult;
import com.ok100.weather.http.Urls;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by qiandd on 2017/09/07
 */

public class LoginModelImpl implements LoginContract.Model {

    @Override
    public void sendSms(Context context, Map<String, String> map, ServiceResult<SMSBean> serviceResult) {
        OkHttpUtils.post(Urls.sendSms).params(map).execute(new DialogCallback<SMSBean>(context, new TypeToken<SMSBean>() {
        }.getType()) {
            @Override
            public void onSuccess(SMSBean newsListBean, Call call, Response response) {
                serviceResult.onSuccess(newsListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void smslogin(Context context, Map<String, String> map, ServiceResult<LoginBean> serviceResult) {
        OkHttpUtils.post(Urls.smslogin).params(map).execute(new DialogCallback<LoginBean>(context, new TypeToken<LoginBean>() {
        }.getType()) {
            @Override
            public void onSuccess(LoginBean newsListBean, Call call, Response response) {
                serviceResult.onSuccess(newsListBean);
            }
        }.showErrorMsg());
    }
}