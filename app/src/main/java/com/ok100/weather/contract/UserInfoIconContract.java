package com.ok100.weather.contract;

import android.content.Context;

import com.ok100.weather.bean.HistoryTodayBean;
import com.ok100.weather.bean.LoginBean;
import com.ok100.weather.bean.SMSBean;
import com.ok100.weather.bean.XiaohuaBean;
import com.ok100.weather.bean.ZhougongBean;
import com.ok100.weather.bean.ZhougongStalyBean;
import com.ok100.weather.http.ServiceResult;

import java.util.Map;

/**
 * Created by qiandd on 2017-7-27.
 */

public class UserInfoIconContract {


    public interface View {
    }

    public interface Presenter {
        void getZhougongData(Context context, Map<String, String> map);
        void getZhougongStateData(Context context, Map<String, String> map);
        void getXiaohua(Context context, Map<String, String> map);
        void getHisttoryTodatData(Context context, Map<String, String> map);

    }

    public interface Model {
        void getZhougongData(Context context, Map<String, String> map, ServiceResult<ZhougongBean> serviceResult);
        void getZhougongStateData(Context context, Map<String, String> map, ServiceResult<ZhougongStalyBean> serviceResult);
        void getXiaohua(Context context, Map<String, String> map, ServiceResult<XiaohuaBean> serviceResult);
        void getHisttoryTodatData(Context context, Map<String, String> map, ServiceResult<HistoryTodayBean> serviceResult);

    }


}