package com.ok100.weather.contract;

import android.content.Context;

import com.ok100.weather.bean.LoginBean;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.http.ServiceResult;

import java.util.Map;

/**
 * Created by qiandd on 2017-7-27.
 */

public class LoginContract {


    public interface View {
    }

    public interface Presenter {
        void sendSms(Context context, Map<String, String> map);
        void smslogin(Context context, Map<String, String> map);

    }

    public interface Model {
        void sendSms(Context context, Map<String, String> map, ServiceResult<LoginBean> serviceResult);
        void smslogin(Context context, Map<String, String> map, ServiceResult<LoginBean> serviceResult);

    }


}