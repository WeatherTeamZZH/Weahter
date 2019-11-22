package com.ok100.weather.model;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.contract.NewsListContract;
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

public class NewsListModelImpl implements NewsListContract.Model {

    @Override
    public void getNewsList(Context context, Map<String, String> map, ServiceResult<NewsListBean> serviceResult) {
        OkHttpUtils.get(Urls.NewUrl).params(map).execute(new DialogCallback<NewsListBean>(context, new TypeToken<NewsListBean>() {
        }.getType()) {
            @Override
            public void onSuccess(NewsListBean newsListBean, Call call, Response response) {
                serviceResult.onSuccess(newsListBean);
            }
        }.showErrorMsg());
    }
}