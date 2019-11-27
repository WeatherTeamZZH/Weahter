package com.ok100.weather.contract;

import android.content.Context;


import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.http.ServiceResult;

import java.util.Map;

/**
 * Created by qiandd on 2017-7-27.
 */

public class NoticeMainListContract {


    public interface View {
    }

    public interface Presenter {
        void getNoticeList(Context context, Map<String, String> map);
        void getTotalWeatherGPS(Context context, Map<String, String> map);
        void getTotalWeather(Context context, Map<String, String> map);
        void getTotalWeather7(Context context, Map<String, String> map);
        void getTotalWeather15(Context context, Map<String, String> map);
        void getTotalWeather24(Context context, Map<String, String> map);
    }

    public interface Model {
        void getNoticeList(Context context, Map<String, String> map, ServiceResult<String> serviceResult);
        void getTotalWeather(Context context, Map<String, String> map, ServiceResult<WeatherTotalBean> serviceResult);
        void getTotalWeatherGPS(Context context, Map<String, String> map, ServiceResult<WeatherTotalBean> serviceResult);
        void getTotalWeather7(Context context, Map<String, String> map, ServiceResult<WeatherTotal7Bean> serviceResult);
        void getTotalWeather15(Context context, Map<String, String> map, ServiceResult<WeatherTotal15Bean> serviceResult);
        void getTotalWeather24(Context context, Map<String, String> map, ServiceResult<WeatherTotal24Bean> serviceResult);

    }


}