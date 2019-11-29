package com.ok100.weather.gh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.ok100.weather.R;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.bean.XingzuoBean;
import com.ok100.weather.http.DialogCallback;
import com.ok100.weather.http.Urls;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-29.
 * @from:
 */
public class XingzuoActivity extends BaseActivity {

    @BindView(R.id.tv_xingzuo)
    TextView tvXingzuo;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_content_001)
    TextView tvContent001;
    @BindView(R.id.tv_content_002)
    TextView tvContent002;
    @BindView(R.id.tv_content_003)
    TextView tvContent003;
    @BindView(R.id.tv_content_004)
    TextView tvContent004;
    @BindView(R.id.tv_content_005)
    TextView tvContent005;
    @BindView(R.id.rb_start_001)
    ScaleRatingBar rbStart001;
    @BindView(R.id.tv_start_001)
    TextView tvStart001;
    @BindView(R.id.rb_start_002)
    ScaleRatingBar rbStart002;
    @BindView(R.id.tv_start_002)
    TextView tvStart002;
    @BindView(R.id.rb_start_003)
    ScaleRatingBar rbStart003;
    @BindView(R.id.tv_start_003)
    TextView tvStart003;
    @BindView(R.id.rb_start_004)
    ScaleRatingBar rbStart004;
    @BindView(R.id.tv_start_004)
    TextView tvStart004;
    @BindView(R.id.rb_start_005)
    ScaleRatingBar rbStart005;
    @BindView(R.id.tv_start_005)
    TextView tvStart005;
    @BindView(R.id.rl_start_001)
    RelativeLayout rlStart001;
    @BindView(R.id.rl_start_002)
    RelativeLayout rlStart002;
    @BindView(R.id.rl_start_003)
    RelativeLayout rlStart003;
    @BindView(R.id.rl_start_004)
    RelativeLayout rlStart004;
    @BindView(R.id.rl_start_005)
    RelativeLayout rlStart005;
    @BindView(R.id.v_line_001)
    View vLine001;
    @BindView(R.id.v_line_002)
    View vLine002;
    @BindView(R.id.v_line_003)
    View vLine003;
    @BindView(R.id.v_line_004)
    View vLine004;
    @BindView(R.id.v_line_005)
    View vLine005;

    public static void access(Context context) {
        Intent intent = new Intent(context, XingzuoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_xingzuo;
    }

    @Override
    public void InitView() {
        initNoLinkOptionsPicker();

        tvXingzuo.addTextChangedListener(textWatcher);
        tvDate.addTextChangedListener(textWatcher);

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            getNetData();
        }
    };

    private void getNetData() {
        String date = "today";
        switch (tvDate.getText().toString()) {
            case "今日":
                date = "today";
                break;
            case "明日":
                date = "tomorrow";
                break;
            case "本周":
                date = "week";
                break;
            case "本月":
                date = "month";
                break;
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", "ead4d05fe366c4eefc98450cec2612d7");
        hashMap.put("consName", tvXingzuo.getText().toString());
        hashMap.put("type", date);

        switch (tvDate.getText().toString()) {
            case "今日":
                getNetDataDay(hashMap);
            case "明日":
                getNetDataDay(hashMap);
                break;
            case "本周":
                getNetDataWeek(hashMap);
                break;
            case "本月":
                getNetDataMonth(hashMap);
                break;
        }

    }

    private void getNetDataDay(HashMap<String, String> hashMap) {
        OkHttpUtils.get(Urls.xingzuo).params(hashMap).execute(new DialogCallback<XingzuoBean.Day>(XingzuoActivity.this, new TypeToken<XingzuoBean.Day>() {
        }.getType()) {
            @Override
            public void onSuccess(XingzuoBean.Day day, Call call, Response response) {
                tvContent001.setText(day.getHealth());
                tvContent002.setText(day.getWork());
                tvContent003.setText(day.getNumber());
                tvContent004.setText(day.getColor());
                tvContent005.setText(day.getQFriend());
                tvStart001.setText(day.getSummary());

                rlStart001.setVisibility(View.VISIBLE);
                rlStart002.setVisibility(View.GONE);
                rlStart003.setVisibility(View.GONE);
                rlStart004.setVisibility(View.GONE);
                rlStart005.setVisibility(View.GONE);

                vLine001.setVisibility(View.VISIBLE);
                vLine002.setVisibility(View.GONE);
                vLine003.setVisibility(View.GONE);
                vLine004.setVisibility(View.GONE);
                vLine005.setVisibility(View.GONE);

            }
        }.showErrorMsg());
    }

    private void getNetDataWeek(HashMap<String, String> hashMap) {
        OkHttpUtils.get(Urls.xingzuo).params(hashMap).execute(new DialogCallback<XingzuoBean.Week>(XingzuoActivity.this, new TypeToken<XingzuoBean.Week>() {
        }.getType()) {
            @Override
            public void onSuccess(XingzuoBean.Week day, Call call, Response response) {
                tvContent001.setText("--");
                tvContent002.setText("--");
                tvContent003.setText("--");
                tvContent004.setText("--");
                tvContent005.setText("--");
                tvStart001.setText("--");
                tvStart002.setText(day.getLove());
                tvStart003.setText(day.getWork());
                tvStart004.setText(day.getMoney());
                tvStart005.setText(day.getHealth());

                rlStart001.setVisibility(View.GONE);
                rlStart002.setVisibility(View.VISIBLE);
                rlStart003.setVisibility(View.VISIBLE);
                rlStart004.setVisibility(View.VISIBLE);
                rlStart005.setVisibility(View.VISIBLE);

                vLine001.setVisibility(View.GONE);
                vLine002.setVisibility(View.VISIBLE);
                vLine003.setVisibility(View.VISIBLE);
                vLine004.setVisibility(View.VISIBLE);
                vLine005.setVisibility(View.VISIBLE);

            }
        }.showErrorMsg());
    }

    private void getNetDataMonth(HashMap<String, String> hashMap) {
        OkHttpUtils.get(Urls.xingzuo).params(hashMap).execute(new DialogCallback<XingzuoBean.Month>(XingzuoActivity.this, new TypeToken<XingzuoBean.Month>() {
        }.getType()) {
            @Override
            public void onSuccess(XingzuoBean.Month day, Call call, Response response) {
                tvContent001.setText("--");
                tvContent002.setText("--");
                tvContent003.setText("--");
                tvContent004.setText("--");
                tvContent005.setText("--");
                tvStart001.setText(day.getAll());
                tvStart002.setText(day.getLove());
                tvStart003.setText(day.getWork());
                tvStart004.setText(day.getMoney());
                tvStart005.setText(day.getHealth());

                rlStart001.setVisibility(View.VISIBLE);
                rlStart002.setVisibility(View.VISIBLE);
                rlStart003.setVisibility(View.VISIBLE);
                rlStart004.setVisibility(View.VISIBLE);
                rlStart005.setVisibility(View.VISIBLE);

                vLine001.setVisibility(View.VISIBLE);
                vLine002.setVisibility(View.VISIBLE);
                vLine003.setVisibility(View.VISIBLE);
                vLine004.setVisibility(View.VISIBLE);
                vLine005.setVisibility(View.VISIBLE);

            }
        }.showErrorMsg());
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {
        getNetData();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_xingzuo, R.id.tv_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_xingzuo:
                xingzuoPicker.show();
                break;
            case R.id.tv_date:
                datePicker.show();
                break;
        }
    }

    private OptionsPickerView xingzuoPicker;
    private OptionsPickerView datePicker;

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        String[] xzArray = {"白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "室女座", "天秤座", "天蝎座", "人马座", "摩羯座", "宝瓶座", "双鱼座"};

        List<String> xzList = Arrays.asList(xzArray);

        xingzuoPicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvXingzuo.setText(xzList.get(options1));
            }
        })
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                    }
                })
                .setItemVisibleCount(5)
                // .setSelectOptions(0, 1, 1)
                .build();
        xingzuoPicker.setNPicker(xzList, null, null);
        xingzuoPicker.setSelectOptions(0, 1, 1);


        String[] dateArray = {"今日", "明日", "本周", "本月"};

        List<String> dateList = Arrays.asList(dateArray);

        datePicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvDate.setText(dateList.get(options1));
            }
        })
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                    }
                })
                .setItemVisibleCount(5)
                // .setSelectOptions(0, 1, 1)
                .build();
        datePicker.setNPicker(dateList, null, null);
        datePicker.setSelectOptions(0, 1, 1);

    }
}
