package com.ok100.weather.adapter;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.HistoryTodayBean;
import com.ok100.weather.bean.NewsListBean;

public class HistoryTodayAdapter extends BaseQuickAdapter<HistoryTodayBean.ResultBean, BaseViewHolder> {

    private Context mContext;
    public HistoryTodayAdapter(Context context) {
        super(R.layout.history_today_list);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, HistoryTodayBean.ResultBean item) {

        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_data,item.getDes());
        helper.setText(R.id.tv_date,item.getYear()+"-"+item.getMonth()+"-"+item.getDay());
//        helper.setText(R.id.tv_date,item.getDate());
       ImageView imageview =  helper.getView(R.id.imageview);
        Glide.with(mContext)
                .load(item.getPic())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageview);


    }
}
