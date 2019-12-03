package com.ok100.weather.adapter;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.XiaohuaBean;

public class XiaohuaAdapter extends BaseQuickAdapter<XiaohuaBean.ResultBean.DataBean, BaseViewHolder> {

    private Context mContext;
    public XiaohuaAdapter(Context context) {
        super(R.layout.xiaohua_list);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, XiaohuaBean.ResultBean.DataBean item) {

        helper.setText(R.id.tv_title,item.getContent());
        helper.setText(R.id.tv_content,item.getContent());
        helper.setText(R.id.tv_date,item.getUpdatetime());


    }
}
