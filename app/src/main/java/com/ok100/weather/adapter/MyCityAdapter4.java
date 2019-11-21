package com.ok100.weather.adapter;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.AllCityGreenBean;
import com.ok100.weather.bean.CityGreenDaoBean;
import com.ok100.weather.bean.DefultGridViewBean;

public class MyCityAdapter4 extends BaseQuickAdapter<AllCityGreenBean, BaseViewHolder> {

    public MyCityAdapter4() {
        super(R.layout.my_city_list4);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllCityGreenBean item) {
        helper.setText(R.id.tv_title,item.getNATIONCN()+"-"+item.getPROVCN()+"-"+item.getDISTRICTCN()+"-"+item.getNAMECN());
    }
}
