package com.ok100.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.ok100.weather.bean.ChannelBean;
import com.ok100.weather.bean.ChannelsBean;
import com.ok100.weather.bean.GetMyTokenBean;
import com.ok100.weather.bean.TianqiyubaoBean;
import com.ok100.weather.bean.TokenRuturnBean;
import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.contract.NoticeMainListContract;
import com.ok100.weather.contract.UcDataContract;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.ServiceResult;
import com.ok100.weather.model.NoticeMainListModelImpl;
import com.ok100.weather.model.UcDataModelImpl;

import java.util.Map;

/**
 * Created by MVPHelper on 2017/02/20
 */

public class UcDataPresenterImpl implements UcDataContract.Presenter {

    private ReturnDataView returnDataView;
    private UcDataModelImpl model;

    public UcDataPresenterImpl(ReturnDataView returnDataView) {
        this.returnDataView = returnDataView;
        model = new UcDataModelImpl();
    }

    @Override
    public void viptoken(Context context, Map<String, String> map) {
        model.viptoken(context, map, new ServiceResult<TokenRuturnBean>() {
            @Override
            public void onSuccess(TokenRuturnBean tokenRuturnBean) {
                returnDataView.returnData("viptoken", tokenRuturnBean);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    public void channels(Context context, Map<String, String> map) {
        model.channels(context, map, new ServiceResult<ChannelsBean>() {
            @Override
            public void onSuccess(ChannelsBean channelsBean) {
                returnDataView.returnData("channels", channelsBean);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    public void homch(Context context, Map<String, String> map) {
        model.homch(context, map, new ServiceResult<ChannelBean>() {
            @Override
            public void onSuccess(ChannelBean channelsBean) {
                returnDataView.returnData("homch", channelsBean);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    public void access_token(Context context, Map<String, String> map) {

    }

    @Override
    public void cities(Context context, Map<String, String> map) {

    }

    @Override
    public void getMyToken(Context context, Map<String, String> map) {
        model.getMyToken(context, map, new ServiceResult<GetMyTokenBean>() {
            @Override
            public void onSuccess(GetMyTokenBean getMyTokenBean) {
                returnDataView.returnData("getMyToken", getMyTokenBean);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

    @Override
    public void getTianqiyubao(Context context, Map<String, String> map) {
        model.getTianqiyubao(context, map, new ServiceResult<TianqiyubaoBean>() {
            @Override
            public void onSuccess(TianqiyubaoBean tianqiyubaoBean) {
                returnDataView.returnData("getTianqiyubao", tianqiyubaoBean);
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
}