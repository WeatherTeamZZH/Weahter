package com.ok100.weather.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.utils.ActivityBarSettingUtils;
import com.teprinciple.mailsender.Mail;
import com.teprinciple.mailsender.MailSender;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SuggestionActivity extends BaseActivity {

    public String url = "280115676@qq.com";
    @BindView(R.id.suggest_et_input)
    EditText mSuggestEtInput;
    String title = "意见反馈";
    String content = "";


    @Override
    public int getLayoutID() {
        return R.layout.activity_suggestion;


    }

    @Override
    public void InitView() {
        setTitle("意见反馈", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, true, TITLE_TYPE_TEXT, "提交");
        registerBack();
        rightDo();
        ActivityBarSettingUtils.setAndroidNativeLightStatusBar(SuggestionActivity.this, true);

//        sendMessage();
        MobclickAgent.onEvent(SuggestionActivity.this, "SuggestionActivity");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void onClick(View v) {

    }

    public void sendMessage() {
// 创建邮箱
        Mail mail = new Mail();
        mail.setMailServerHost("smtp.qq.com");
        mail.setMailServerPort("587");
        mail.setFromAddress("280115676@qq.com");
        mail.setPassword("zhonghua");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("305802595@qq.com");
        mail.setToAddress(strings);
        mail.setSubject("MailSender");
        mail.setContent("MailSender Android快速实现发送邮件");
        // 发送邮箱
        MailSender.getInstance().sendMail(mail, new MailSender.OnMailSendListener() {
            @Override
            public void onSuccess() {
                Log.e("MailSender", "onSuccess");
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                Log.e("MailSender", "onError");
            }
        });
    }

    public void gotoMessage() {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:" + url));
        data.putExtra(Intent.EXTRA_SUBJECT, title);
        data.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(data);
    }

    @Override
    protected void rightDoWhat() {
        super.rightDoWhat();
        content = mSuggestEtInput.getText().toString();
        gotoMessage();

    }


}
