package com.ok100.weather.contract;

import android.content.Context;

import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.http.ServiceResult;

import java.util.Map;

/**
 * Created by qiandd on 2017-7-27.
 */

public class NewsListContract {


    public interface View {
    }

    public interface Presenter {
        void getNewsList(Context context, Map<String, String> map);

    }

    public interface Model {
        void getNewsList(Context context, Map<String, String> map, ServiceResult<NewsListBean> serviceResult);

    }


}