package com.ok100.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.ok100.weather.bean.HistoryTodayBean;
import com.ok100.weather.bean.LoginBean;
import com.ok100.weather.bean.SMSBean;
import com.ok100.weather.bean.XiaohuaBean;
import com.ok100.weather.bean.ZhougongBean;
import com.ok100.weather.bean.ZhougongStalyBean;
import com.ok100.weather.contract.LoginContract;
import com.ok100.weather.contract.UserInfoIconContract;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.ServiceResult;
import com.ok100.weather.model.LoginModelImpl;
import com.ok100.weather.model.UserInfoIconModelImpl;

import java.util.Map;

/**
 * Created by MVPHelper on 2017/02/20
 */

public class UserInfoIconPresenterImpl implements UserInfoIconContract.Presenter {

    private ReturnDataView returnDataView;
    private UserInfoIconModelImpl model;

    public UserInfoIconPresenterImpl(ReturnDataView returnDataView) {
        this.returnDataView = returnDataView;
        model = new UserInfoIconModelImpl();
    }




    @Override
    public void getZhougongData(Context context, Map<String, String> map) {
        model.getZhougongData(context, map, new ServiceResult<ZhougongBean>() {
            @Override
            public void onSuccess(ZhougongBean zhougongBean) {
                returnDataView.returnData("getZhougongData", zhougongBean);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    public void getZhougongStateData(Context context, Map<String, String> map) {
        model.getZhougongStateData(context, map, new ServiceResult<ZhougongStalyBean>() {
            @Override
            public void onSuccess(ZhougongStalyBean zhougongStalyBean) {
                returnDataView.returnData("getZhougongStateData", zhougongStalyBean);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    public void getXiaohua(Context context, Map<String, String> map) {
        model.getXiaohua(context, map, new ServiceResult<XiaohuaBean>() {
            @Override
            public void onSuccess(XiaohuaBean xiaohuaBean) {
                returnDataView.returnData("getXiaohua", xiaohuaBean);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    public void getHisttoryTodatData(Context context, Map<String, String> map) {
        model.getHisttoryTodatData(context, map, new ServiceResult<HistoryTodayBean>() {
            @Override
            public void onSuccess(HistoryTodayBean historyTodayBean) {
                returnDataView.returnData("getHisttoryTodatData", historyTodayBean);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
}