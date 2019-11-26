package com.ok100.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.ok100.weather.bean.LoginBean;
import com.ok100.weather.bean.SMSBean;
import com.ok100.weather.contract.LoginContract;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.ServiceResult;
import com.ok100.weather.model.LoginModelImpl;

import java.util.Map;

/**
 * Created by MVPHelper on 2017/02/20
 */

public class LoginPresenterImpl implements LoginContract.Presenter {

    private ReturnDataView returnDataView;
    private LoginModelImpl model;

    public LoginPresenterImpl(ReturnDataView returnDataView) {
        this.returnDataView = returnDataView;
        model = new LoginModelImpl();
    }


    @Override
    public void sendSms(Context context, Map<String, String> map) {
        model.sendSms(context, map, new ServiceResult<SMSBean>() {
            @Override
            public void onSuccess(SMSBean loginBean) {
                returnDataView.returnData("sendSms", loginBean);
            }

            @Override
            public void onFailed(String msg) {
                Log.e("sendSms",msg);
            }
        });
    }

    @Override
    public void smslogin(Context context, Map<String, String> map) {
        model.smslogin(context, map, new ServiceResult<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                returnDataView.returnData("smslogin", loginBean);
            }

            @Override
            public void onFailed(String msg) {
                Log.e("smslogin",msg);
            }
        });
    }
}