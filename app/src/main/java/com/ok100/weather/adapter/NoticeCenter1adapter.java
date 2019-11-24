package com.ok100.weather.adapter;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.MyCityListBean;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.NoticeCenter1Bean;
import com.ok100.weather.bean.NoticeCenter2Bean;

public class NoticeCenter1adapter extends BaseQuickAdapter<NewsListBean.ResultBean.DataBean, BaseViewHolder> {

    private Context mContext;
    public NoticeCenter1adapter(Context context) {
        super(R.layout.notice_center_list1);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, NewsListBean.ResultBean.DataBean item) {

        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_data,item.getTitle());
        helper.setText(R.id.tv_date,item.getDate());
       ImageView imageview1 =  helper.getView(R.id.imageview1);
        Glide.with(mContext)
                .load(item.getThumbnail_pic_s())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageview1);


    }
}
