package com.ok100.weather.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.presenter.LoginPresenterImpl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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
        switch (v.getId()){
            case R.id.textview1:
                string1 = mEdittext1.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("phoneNum", string1);
               String string =  beas64util("phone="+string1+"&secret=weater_128_As");
                Log.e("Base64",string);

                try {
                    String s = getMD5(string);
                    Log.e("MD5",s);
                    hashMap.put("token",s );
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
                    hashMap2.put("token", getMD5(beas64util("phone="+string1+"&secret=weater_128_As")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
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
}
