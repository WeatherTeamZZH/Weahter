package com.ok100.weather.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.ok100.weather.R;
import com.ok100.weather.bean.ChannelBean;
import com.ok100.weather.utils.TimeUtils;
import com.qq.e.ads.nativ.NativeExpressADView;

import java.util.ArrayList;
import java.util.List;


public class NoticeMainFragmentAdapter extends BaseQuickAdapter<ChannelBean.DataBean, BaseViewHolder> {

    private Context context;

    List<NativeExpressADView> array  = new ArrayList<>();

    public List<NativeExpressADView> getArray() {
        return array;
    }

    public void setArray(List<NativeExpressADView> array) {
        this.array = array;
    }

    public NoticeMainFragmentAdapter(Context context) {
        super(null);
        this.context = context;
        setMultiTypeDelegate(new MultiTypeDelegate<ChannelBean.DataBean>() {
            @Override
            protected int getItemType(ChannelBean.DataBean itemBean) {
                int plateType = 4000;
                if(TextUtils.isEmpty(itemBean.getType())){
                    if (!TextUtils.isEmpty(itemBean.getStyle_type())) {
                        switch (itemBean.getStyle_type()){
                            case "3361":
                                plateType = 3361;
                                break;
                            case "3362":
                                plateType = 3362;
                                break;
                            case "3363":
                                plateType = 3363;
                                break;
                            case "3364":
                                plateType = 3364;
                                break;
                            case "3365":
                                plateType = 3365;
                                break;
                            case "3366":
                                plateType = 3366;
                                break;
                            case "3367":
                                plateType = 3367;
                                break;
                            case "3368":
                                plateType = 3368;
                                break;
                            case "3369":
                                plateType = 3369;
                                break;
                        }
                    }
                }else {
                    plateType = 4000;
                }

                return plateType;
            }
        });

        getMultiTypeDelegate()
                .registerItemType(3361, R.layout.item_news_fragment3361)
                .registerItemType(3362, R.layout.item_news_fragment3362)
                .registerItemType(3363, R.layout.item_news_fragment3363)
                .registerItemType(3364, R.layout.item_news_fragment3364)
                .registerItemType(3365, R.layout.item_news_fragment3365)
                .registerItemType(3366, R.layout.item_news_fragment3366)
                .registerItemType(3367, R.layout.item_news_fragment3367)
                .registerItemType(3368, R.layout.item_news_fragment3368)
                .registerItemType(3369, R.layout.item_news_fragment3369)
                .registerItemType(4000, R.layout.item_news_fragment4000);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ChannelBean.DataBean item) {
        if(TextUtils.isEmpty(item.getType())){
            ImageView imageview1 ,imageview2,imageview3;
            switch (helper.getItemViewType()) {
                case 3361:
                case 3362:
                case 3363:
                case 3364:
                    helper.setText(R.id.tv_title,item.getTitle());
                    helper.setText(R.id.tv_copyfrom,item.getCopyfrom());
                    helper.setText(R.id.tv_time,TimeUtils.stampToDateMiao(item.getInputtime()));
                    imageview1 = helper.getView(R.id.imageview1);
                    if(item.getImage_type()!=null){
                        Glide.with(context)
                                .load(item.getImage_type().get(0).getUrl())
                                .centerCrop()
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageview1);
                    }
                    break;

                case 3365:
                    helper.setText(R.id.tv_title,item.getTitle());
                    helper.setText(R.id.tv_copyfrom,item.getCopyfrom());
                    helper.setText(R.id.tv_time,TimeUtils.stampToDateMiao(item.getInputtime()));
                    imageview1 = helper.getView(R.id.imageview1);
                    imageview2 = helper.getView(R.id.imageview2);
                    if(item.getImage_type()!=null&&item.getImage_type().size()==2){
                        Glide.with(context)
                                .load(item.getImage_type().get(0).getUrl())
                                .centerCrop()
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageview1);
                        Glide.with(context)
                                .load(item.getImage_type().get(1).getUrl())
                                .centerCrop()
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageview2);
                    }
                    break;
                case 3366:
                    helper.setText(R.id.tv_copyfrom,item.getCopyfrom());
                    helper.setText(R.id.tv_time,TimeUtils.stampToDateMiao(item.getInputtime()));
                    imageview1 = helper.getView(R.id.imageview1);
                    if(item.getImage_type()!=null){
                        Glide.with(context)
                                .load(item.getImage_type().get(0).getUrl())
                                .centerCrop()
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageview1);
                    }
                    break;
                case 3367:
                    helper.setText(R.id.tv_title,item.getTitle());
                    helper.setText(R.id.tv_copyfrom,item.getCopyfrom());
                    helper.setText(R.id.tv_time,TimeUtils.stampToDateMiao(item.getInputtime()));
                    imageview1 = helper.getView(R.id.imageview1);
                    imageview2 = helper.getView(R.id.imageview2);
                    imageview3 = helper.getView(R.id.imageview3);
                    if(item.getImage_type()!=null&&item.getImage_type().size()==3){
                        Glide.with(context)
                                .load(item.getImage_type().get(0).getUrl())
                                .centerCrop()
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageview1);
                        Glide.with(context)
                                .load(item.getImage_type().get(1).getUrl())
                                .centerCrop()
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageview2);
                        Glide.with(context)
                                .load(item.getImage_type().get(2).getUrl())
                                .centerCrop()
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageview3);
                    }

                    break;
                case 3368:

                    break;
                case 3369:
                    helper.setText(R.id.tv_title,item.getTitle());
                    helper.setText(R.id.tv_copyfrom,item.getCopyfrom());
                    helper.setText(R.id.tv_time,TimeUtils.stampToDateMiao(item.getInputtime()));
                    helper.setText(R.id.tv_content,item.getDescription());
                    break;
            }
        }else {
            if(array.size()>0){
                int random=(int)(Math.random()*array.size());
                FrameLayout  container=  helper.getView(R.id.frameLayoutframeLayout);
                NativeExpressADView adView = array.get(random);
                if (container.getChildCount() > 0
                        && container.getChildAt(0) == adView) {
                    return;
                }

                if (container.getChildCount() > 0) {
                    container.removeAllViews();
                }

                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                container.addView(adView);
                adView.render(); // 调用render方法后sdk才会开始展示广告
            }

        }


    }
}
