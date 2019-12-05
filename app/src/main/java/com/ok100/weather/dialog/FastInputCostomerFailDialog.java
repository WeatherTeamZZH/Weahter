package com.ok100.weather.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ok100.weather.R;
import com.ok100.weather.activity.AboutOursActivity;
import com.ok100.weather.activity.SettingActivity;
import com.ok100.weather.base.BaseDialog;

/**
 * Created by qiandd on 2016-12-20.
 */

@SuppressLint("ValidFragment")
public class FastInputCostomerFailDialog extends BaseDialog implements View.OnClickListener {



    private OffDialogListener offDialogListener;
    private ImageView iv_fast_input_costomer_commit_dialog;
    private TextView tv_fast_input_costomer_commit_dialog;
    private TextView tv_fast_input_costomer_showstring1;
    private TextView tv_fast_input_costomer_showstring2;
    private Context context ;
    private TextView tv_zhuce_book ;
    private TextView tv_yinsi_book ;
    private TextView tv_no_agree ;
    private TextView tv_agree ;



    public FastInputCostomerFailDialog(Context context) {
        this.context = context;

    }

    @Override
    protected boolean cancelable() {
        return true;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.dialog_fast_input_commit_fail;
    }

    @Override
    protected void init(Bundle savedInstanceState, View content) {
        tv_zhuce_book = (TextView) content.findViewById(R.id.tv_zhuce_book);
        tv_yinsi_book = (TextView) content.findViewById(R.id.tv_yinsi_book);
        tv_no_agree = (TextView) content.findViewById(R.id.tv_no_agree);
        tv_agree = (TextView) content.findViewById(R.id.tv_agree);
        tv_zhuce_book.setOnClickListener(this);
        tv_yinsi_book.setOnClickListener(this);
        tv_no_agree.setOnClickListener(this);
        tv_agree.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_zhuce_book:
                intent =  new Intent(context, AboutOursActivity.class);
                intent.putExtra("title","注册协议");
                intent.putExtra("url", "http://www.512kx.top/?act=zyPrivacy");
                startActivity(intent);
                break;
            case R.id.tv_yinsi_book:
                intent =  new Intent(context, AboutOursActivity.class);
                intent.putExtra("title","隐私协议");
                intent.putExtra("url","http://www.512kx.top/?act=dyPrivacy");
                startActivity(intent);
                break;
            case R.id.tv_no_agree://取消
                offDialogListener.offDialog(false);
                break;
            case R.id.tv_agree://取消
                offDialogListener.offDialog(true);
                break;

        }

    }


    public interface OffDialogListener {
        void offDialog(boolean isagree);
    }

    public void setOnOffDialogListener(OffDialogListener offDialogListener) {
        this.offDialogListener = offDialogListener;
    }
}
