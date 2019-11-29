package com.ok100.weather.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.DefultGridViewBean;

public class ZhougongTitleAdapter extends BaseQuickAdapter<DefultGridViewBean, BaseViewHolder> {


    public ZhougongTitleAdapter() {
        super(R.layout.zhougong_title_list);
    }


    @Override
    protected void convert(BaseViewHolder helper, DefultGridViewBean item) {
        helper.setText(R.id.tv_name,item.getName());
//        helper.setVisible(R.id.tv_subhead_pop_project_item, isSubhead);
//        TextView textView = helper.getView(R.id.tv_name);
//        if(item.isClick()){
//            textView.setTextColor(Color.parseColor("#ffffff"));
//        }else {
//            textView.setTextColor(Color.parseColor("#000000"));
//        }



    }
}
