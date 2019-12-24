package com.ok100.weather.contract;

import android.content.Context;

import com.ok100.weather.bean.ChannelBean;
import com.ok100.weather.bean.ChannelsBean;
import com.ok100.weather.bean.GetMyTokenBean;
import com.ok100.weather.bean.NowCityBean;
import com.ok100.weather.bean.TianqiyubaoBean;
import com.ok100.weather.bean.TokenRuturnBean;
import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.http.ServiceResult;

import java.util.Map;

/**
 * Created by qiandd on 2017-7-27.
 */

public class UcDataContract {


    public interface View {
    }

    public interface Presenter {
        void viptoken(Context context, Map<String, String> map);
        void channels(Context context, Map<String, String> map);
        void homch(Context context, Map<String, String> map);
        void access_token(Context context, Map<String, String> map);
        void cities(Context context, Map<String, String> map);
        void getMyToken(Context context, Map<String, String> map);
        void getTianqiyubao(Context context, Map<String, String> map);
    }

    public interface Model {
        void viptoken(Context context, Map<String, String> map, ServiceResult<TokenRuturnBean> serviceResult);
        void channels(Context context, Map<String, String> map, ServiceResult<ChannelsBean> serviceResult);
        void homch(Context context, Map<String, String> map, ServiceResult<ChannelBean> serviceResult);
        void access_token(Context context, Map<String, String> map, ServiceResult<ChannelBean> serviceResult);
        void cities(Context context, Map<String, String> map, ServiceResult<NowCityBean> serviceResult);
        void getMyToken(Context context, Map<String, String> map, ServiceResult<GetMyTokenBean> serviceResult);
        void getTianqiyubao(Context context, Map<String, String> map, ServiceResult<TianqiyubaoBean> serviceResult);

    }


}