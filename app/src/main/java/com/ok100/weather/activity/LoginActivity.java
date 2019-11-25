package com.ok100.weather.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.presenter.LoginPresenterImpl;

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

    LoginPresenterImpl loginPresenter ;

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void InitView() {
        setTitle("登录", true, TITLE_TYPE_IMG, R.mipmap.back_left_hei, false, TITLE_TYPE_IMG, R.mipmap.ic_launcher);
        registerBack();
        loginPresenter = new LoginPresenterImpl(this);

    }

    public String beas64util(String string) {
        String s = Base64.encodeToString(string.getBytes(), Base64.DEFAULT);
        return s;
    }

    public String md5Decode32(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
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
        switch (v.getId()){
            case R.id.textview1:
                string1 = mEdittext1.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("phoneNum", string1);
                hashMap.put("token", md5Decode32(beas64util("phone="+string1+"&secret=weater_128_As")));
                loginPresenter.sendSms(LoginActivity.this, hashMap);
                break;
            case R.id.textview2:
                string1 = mEdittext1.getText().toString();
                String string2 = mEdittext2.getText().toString();
                HashMap<String, String> hashMap2 = new HashMap<>();
                hashMap2.put("phoneNum", string1);
                hashMap2.put("codelatedPoints", string2);
                hashMap2.put("token", md5Decode32(beas64util("phone="+string1+"&secret=weater_128_As")));
                loginPresenter.smslogin(LoginActivity.this, hashMap2);
                break;
        }
    }

    @Override
    public void returnData(String responseCode, Object o) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
