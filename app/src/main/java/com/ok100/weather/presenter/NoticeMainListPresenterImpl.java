package com.ok100.weather.presenter;

import android.content.Context;
import android.util.Log;


import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.contract.NoticeMainListContract;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.ServiceResult;
import com.ok100.weather.model.NoticeMainListModelImpl;

import java.util.Map;

/**
 * Created by MVPHelper on 2017/02/20
 */

public class NoticeMainListPresenterImpl implements NoticeMainListContract.Presenter {

    private ReturnDataView returnDataView;
    private NoticeMainListModelImpl model;

    public NoticeMainListPresenterImpl(ReturnDataView returnDataView) {
        this.returnDataView = returnDataView;
        model = new NoticeMainListModelImpl();
    }


    @Override
    public void getNoticeList(Context context, Map<String, String> map) {
        model.getNoticeList(context, map ,new ServiceResult<String>() {
            @Override
            public void onSuccess(String noticeMainListBean) {
                returnDataView.returnData("1", noticeMainListBean);
            }

            @Override
            public void onFailed(String msg) {
                returnDataView.showError(msg);
            }
        });
    }

    @Override
    public void getTotalWeather(Context context, Map<String, String> map) {
        model.getTotalWeather(context, map, new ServiceResult<WeatherTotalBean>() {
            @Override
            public void onSuccess(WeatherTotalBean weatherTotalBean) {
                returnDataView.returnData("getTotalWeather", weatherTotalBean);
            }

            @Override
            public void onFailed(String msg) {
                Log.e("onFailed",msg);
            }
        });
    }

    @Override
    public void getTotalWeather7(Context context, Map<String, String> map) {
        model.getTotalWeather7(context, map, new ServiceResult<WeatherTotal7Bean>() {
            @Override
            public void onSuccess(WeatherTotal7Bean weatherTotalBean) {
                returnDataView.returnData("getTotalWeather7", weatherTotalBean);
            }

            @Override
            public void onFailed(String msg) {
                Log.e("onFailed",msg);
            }
        });
    }

    @Override
    public void getTotalWeather15(Context context, Map<String, String> map) {
        model.getTotalWeather15(context, map, new ServiceResult<WeatherTotal15Bean>() {
            @Override
            public void onSuccess(WeatherTotal15Bean weatherTotalBean) {
                returnDataView.returnData("getTotalWeather15", weatherTotalBean);
            }

            @Override
            public void onFailed(String msg) {
                Log.e("onFailed",msg);
            }
        });
    }

    @Override
    public void getTotalWeather24(Context context, Map<String, String> map) {
        model.getTotalWeather24(context, map, new ServiceResult<WeatherTotal24Bean>() {
            @Override
            public void onSuccess(WeatherTotal24Bean weatherTotalBean) {
                returnDataView.returnData("getTotalWeather24", weatherTotalBean);
            }

            @Override
            public void onFailed(String msg) {
                Log.e("onFailed",msg);
            }
        });
    }
}