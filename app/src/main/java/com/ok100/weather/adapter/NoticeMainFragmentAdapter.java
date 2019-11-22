package com.ok100.weather.adapter;


import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.ok100.weather.R;
import com.ok100.weather.bean.NewsListBean;
import com.ok100.weather.bean.NoticeMainListBean;


public class NoticeMainFragmentAdapter extends BaseQuickAdapter<NewsListBean.ResultBean.DataBean, BaseViewHolder> {

    private Context context;

    public NoticeMainFragmentAdapter(Context context) {
        super(null);
        this.context = context;
        setMultiTypeDelegate(new MultiTypeDelegate<NewsListBean.ResultBean.DataBean>() {
            @Override
            protected int getItemType(NewsListBean.ResultBean.DataBean itemBean) {
                int plateType = 1;
                if ( !TextUtils.isEmpty(itemBean.getThumbnail_pic_s03())) {
                    plateType = 1;
                } else {
                    plateType = 2;
                }
                return plateType;
            }
        });

        getMultiTypeDelegate()
                .registerItemType(1, R.layout.item_notice_main1_fragment1)
                .registerItemType(2, R.layout.item_notice_main1_fragment2)
                .registerItemType(3, R.layout.item_notice_main1_fragment3)
                .registerItemType(4, R.layout.item_notice_main1_fragment1_top)
                .registerItemType(5, R.layout.item_notice_main1_fragment3_top);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final NewsListBean.ResultBean.DataBean item) {

        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.tv_title,item.getTitle());
                ImageView imageview1 = helper.getView(R.id.imageview1);
                ImageView imageview2 = helper.getView(R.id.imageview2);
                ImageView imageview3 = helper.getView(R.id.imageview3);
                Glide.with(context)
                        .load(item.getThumbnail_pic_s())
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageview1);
                Glide.with(context)
                        .load(item.getThumbnail_pic_s02())
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageview2);
                Glide.with(context)
                        .load(item.getThumbnail_pic_s03())
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageview3);

                break;
            case 2:
                if(!TextUtils.isEmpty(item.getTitle())){
                    helper.setText(R.id.tv_title,item.getTitle());
                    helper.setText(R.id.tv_updata,item.getDate());
                }

                ImageView imageview = helper.getView(R.id.imageview1);
                Glide.with(context)
                        .load(item.getThumbnail_pic_s())
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageview);
                break;
            case 3:
//                setData(helper, item);
//                helper.setText(R.id.tv_notice_main_list_content, item.getSubtitle());
                break;
            case 4:

                break;
            case 5:

                break;
        }
    }
}
