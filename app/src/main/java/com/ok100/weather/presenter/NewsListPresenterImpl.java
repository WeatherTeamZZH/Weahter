package com.ok100.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.WeatherTotal15Bean;
import com.ok100.weather.bean.WeatherTotal24Bean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.contract.NewsListContract;
import com.ok100.weather.contract.NoticeMainListContract;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.ServiceResult;
import com.ok100.weather.model.NewsListModelImpl;
import com.ok100.weather.model.NoticeMainListModelImpl;

import java.util.Map;

/**
 * Created by MVPHelper on 2017/02/20
 */

public class NewsListPresenterImpl implements NewsListContract.Presenter {

    private ReturnDataView returnDataView;
    private NewsListModelImpl model;

    public NewsListPresenterImpl(ReturnDataView returnDataView) {
        this.returnDataView = returnDataView;
        model = new NewsListModelImpl();
    }


    @Override
    public void getNewsList(Context context, Map<String, String> map) {
        model.getNewsList(context, map, new ServiceResult<NewsListBean>() {
            @Override
            public void onSuccess(NewsListBean newsListBean) {
                returnDataView.returnData("getNewsList", newsListBean);
            }

            @Override
            public void onFailed(String msg) {
                Log.e("onFailed",msg);
            }
        });
    }
}