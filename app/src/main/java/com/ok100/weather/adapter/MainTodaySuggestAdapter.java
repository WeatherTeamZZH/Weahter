package com.ok100.weather.adapter;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.SuggestGridViewBean;
import com.ok100.weather.bean.WeatherTotal7Bean;
import com.ok100.weather.bean.WeatherTotalBean;

import me.zhouzhuo.zzweatherview.WeatherModel;

public class MainTodaySuggestAdapter extends BaseQuickAdapter<SuggestGridViewBean, BaseViewHolder> {

    public MainTodaySuggestAdapter() {
        super(R.layout.main_today_suggest_list);
    }


    @Override
    protected void convert(BaseViewHolder helper, SuggestGridViewBean item) {
                helper.setText(R.id.tv_name1,item.getName1());
                helper.setText(R.id.tv_name2,item.getName2());
                if(!TextUtils.isEmpty(item.getImageUlrRes()+"")){
                    helper.setBackgroundRes(R.id.iv_image,item.getImageUlrRes());
                }


//        helper.setVisible(R.id.tv_subhead_pop_project_item, isSubhead);
//        helper.setBackgroundColor(R.id.tv_subhead_pop_project_item, item.isCheck() ? 0xFFFAFAFA : 0xFFFFFFFF);
//
//        helper.setText(R.id.tv_notice_main_choose_name ,item.getName());
//        if(item.isClick()){
//            helper.setBackgroundRes(R.id.iv_notice_main_choose_state ,R.mipmap.notice_main_choose_true);
//        }else {
//            helper.setBackgroundRes(R.id.iv_notice_main_choose_state ,R.mipmap.notice_main_choose_false);
//        }

    }
}
