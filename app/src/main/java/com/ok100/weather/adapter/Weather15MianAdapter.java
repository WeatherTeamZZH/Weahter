package com.ok100.weather.adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;

import me.zhouzhuo.zzweatherview.WeatherModel;

public class Weather15MianAdapter extends BaseQuickAdapter<WeatherModel, BaseViewHolder> {

    public Weather15MianAdapter() {
        super(R.layout.weather15_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherModel item) {
//        helper.setVisible(R.id.tv_subhead_pop_project_item, isSubhead);
//        helper.setBackgroundColor(R.id.tv_subhead_pop_project_item, item.isCheck() ? 0xFFFAFAFA : 0xFFFFFFFF);
//
        helper.setText(R.id.tv_xingqi ,item.getWeek());
        helper.setText(R.id.tv_data ,item.getDate());
//        helper.setText(R.id.iv_weather ,item.getName());
        helper.setText(R.id.tv_weather ,item.getDayWeather());
        helper.setText(R.id.tv_day_temperature ,item.getDayTemp()+"");
        helper.setText(R.id.tv_night_temperature ,item.getNightTemp()+"");
//        if(item.isClick()){
//            helper.setBackgroundRes(R.id.iv_notice_main_choose_state ,R.mipmap.notice_main_choose_true);
//        }else {
//            helper.setBackgroundRes(R.id.iv_notice_main_choose_state ,R.mipmap.notice_main_choose_false);
//        }

    }
}
