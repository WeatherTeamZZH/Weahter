package com.ok100.weather.adapter;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.CityGreenDaoBean;
import com.ok100.weather.bean.MainSpotClickBean;
import com.ok100.weather.bean.MyCityListBean;
import com.ok100.weather.utils.ChooseTypeUtils;

public class MyCityAdapter1 extends BaseQuickAdapter<CityGreenDaoBean, BaseViewHolder> {

    private boolean isHide = true;

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public MyCityAdapter1() {
        super(R.layout.my_city_list1);
    }


    @Override
    protected void convert(BaseViewHolder helper, CityGreenDaoBean item) {
        if(isHide){
            helper.getView(R.id.iv_delete).setVisibility(View.GONE);
            helper.getView(R.id.iv_end).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.iv_delete).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_end).setVisibility(View.VISIBLE);
        }
//        helper.setBackgroundRes(R.id.iv_weather,ChooseTypeUtils.getWeatherImgge(item.getArea()));

        helper.setText(R.id.tv_home,item.getArea());
        helper.addOnClickListener(R.id.iv_delete);
//        TextView textView = helper.getView(R.id.tv_name);
//        if(item.isClick()){
//            textView.setTextColor(Color.parseColor("#ffffff"));
//        }else {
//            textView.setTextColor(Color.parseColor("#000000"));
//        }



    }
}
