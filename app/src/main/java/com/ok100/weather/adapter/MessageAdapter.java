package com.ok100.weather.adapter;
import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ok100.weather.R;
import com.ok100.weather.bean.MessageBean;

public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {

    private Context mContext;
    public MessageAdapter(Context context) {
        super(R.layout.item_message);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_text,item.getText());
    }
}
