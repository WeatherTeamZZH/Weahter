package com.ok100.weather.model;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.ok100.weather.bean.ChannelBean;
import com.ok100.weather.bean.ChannelsBean;
import com.ok100.weather.bean.GetMyTokenBean;
import com.ok100.weather.bean.NowCityBean;
import com.ok100.weather.bean.TokenRuturnBean;
import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.contract.NoticeMainListContract;
import com.ok100.weather.contract.UcDataContract;
import com.ok100.weather.http.DialogCallback;
import com.ok100.weather.http.ServiceResult;
import com.ok100.weather.http.Urls;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by qiandd on 2017/09/07
 */

public class UcDataModelImpl implements UcDataContract.Model {

    @Override
    public void viptoken(Context context, Map<String, String> map, ServiceResult<TokenRuturnBean> serviceResult) {
        OkHttpUtils.post(Urls.getUcToken).params(map).execute(new DialogCallback<TokenRuturnBean>(context, new TypeToken<TokenRuturnBean>() {
        }.getType()) {
            @Override
            public void onSuccess(TokenRuturnBean noticeMainListBean, Call call, Response response) {
                serviceResult.onSuccess(noticeMainListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void channels(Context context, Map<String, String> map, ServiceResult<ChannelsBean> serviceResult) {
        OkHttpUtils.post(Urls.getChannels).params(map).execute(new DialogCallback<ChannelsBean>(context, new TypeToken<ChannelsBean>() {
        }.getType()) {
            @Override
            public void onSuccess(ChannelsBean noticeMainListBean, Call call, Response response) {
                serviceResult.onSuccess(noticeMainListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void homch(Context context, Map<String, String> map, ServiceResult<ChannelBean> serviceResult) {
        OkHttpUtils.post(Urls.geThomch).params(map).execute(new DialogCallback<ChannelBean>(context, new TypeToken<ChannelBean>() {
        }.getType()) {
            @Override
            public void onSuccess(ChannelBean noticeMainListBean, Call call, Response response) {
                serviceResult.onSuccess(noticeMainListBean);
            }
        }.showErrorMsg());
    }

    @Override
    public void access_token(Context context, Map<String, String> map, ServiceResult<ChannelBean> serviceResult) {

    }

    @Override
    public void cities(Context context, Map<String, String> map, ServiceResult<NowCityBean> serviceResult) {

    }

    @Override
    public void getMyToken(Context context, Map<String, String> map, ServiceResult<GetMyTokenBean> serviceResult) {

    }
}