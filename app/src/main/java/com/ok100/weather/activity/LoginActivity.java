package com.ok100.weather.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.LoginBean;
import com.ok100.weather.bean.SMSBean;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.presenter.LoginPresenterImpl;
import com.ok100.weather.utils.SPObj;
import com.ok100.weather.utils.ToastUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements ReturnDataView {

    @BindView(R.id.edittext1)
    EditText mEdittext1;
    @BindView(R.id.textview1)
    TextView mTextview1;
    @BindView(R.id.edittext2)
    EditText mEdittext2;
    @BindView(R.id.textview2)
    TextView mTextview2;

    LoginPresenterImpl loginPresenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_title_left)
    TextView tvTitleLeft;
    @BindView(R.id.ll_title_left)
    LinearLayout llTitleLeft;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout llTitleRight;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.base_rl_title)
    RelativeLayout baseRlTitle;

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void InitView() {
        baseRlTitle.setBackgroundColor(getResources().getColor(R.color.transparent));
        setTitle("登录", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        loginPresenter = new LoginPresenterImpl(this);

    }

    public String beas64util(String string) {
        String s = Base64.encodeToString(string.getBytes(), Base64.NO_WRAP);
        return s;
    }


    @Override
    public void initListener() {
        mTextview1.setOnClickListener(this);
        mTextview2.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void onClick(View v) {
        String string1 = "";
        switch (v.getId()) {
            case R.id.textview1:
                string1 = mEdittext1.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("phoneNum", string1);
                String string = beas64util("phone=" + string1 + "&secret=weater_128_As");
                Log.e("Base64", string);

                try {
                    String s = getMD5(string);
                    Log.e("MD5", s);
                    hashMap.put("token", s);
                    loginPresenter.sendSms(LoginActivity.this, hashMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.textview2:
                string1 = mEdittext1.getText().toString();
                String string2 = mEdittext2.getText().toString();
                HashMap<String, String> hashMap2 = new HashMap<>();
                hashMap2.put("phoneNum", string1);
                hashMap2.put("codelatedPoints", string2);
                try {
                    hashMap2.put("token", getMD5(beas64util("phone=" + string1 + "&secret=weater_128_As")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                loginPresenter.smslogin(LoginActivity.this, hashMap2);
                break;
        }
    }

    /**
     * 发送验证码计时器
     */
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            mTextview1.setClickable(false);
            mTextview1.setEnabled(false);
            mTextview1.setText((millisUntilFinished / 1000) + "秒重发");
            mTextview1.setTextColor(Color.parseColor("#BABABA"));
        }

        @Override
        public void onFinish() {
            updateGetCodeEnable();
        }
    };

    /**
     * 更新验证码可以发送的状态
     */
    private void updateGetCodeEnable() {
        mTextview1.setClickable(true);
        mTextview1.setEnabled(true);
        mTextview1.setText("发送验证码");
    }

    @Override
    public void returnData(String responseCode, Object o) {
        switch (responseCode) {
            case "sendSms":
                SMSBean smsBean = (SMSBean) o;
                if ("200".equals(smsBean.getCode())) {
                    timer.start();
                } else {
                    ToastUtil.makeLongText(getContext(),smsBean.getMessage());
                }
                break;
            case "smslogin":
                LoginBean loginBean = (LoginBean) o;
                if ("200".equals(loginBean.getCode())) {
                    new SPObj(getContext(), "gh").setObject("phone", loginBean.getData().getPhonenum());
                    new SPObj(getContext(), "gh").setObject("isLogin", true);
                    finish();
                } else {
                    ToastUtil.makeLongText(getContext(),loginBean.getMessage());
                }
                break;
        }
    }

    @Override
    public void showError(String msg) {

    }


    /*
     * 方法名称：getMD5
     * 功    能：字符串MD5加密
     * 参    数：待转换字符串
     * 返 回 值：加密之后字符串
     */
    public static String getMD5(String sourceStr) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(sourceStr)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(sourceStr.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
