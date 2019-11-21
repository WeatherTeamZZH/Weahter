package com.ok100.weather.gh;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ok100.weather.R;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.WeatherTotal7Bean;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-08.
 * @from:
 */
public class AirItemFragment extends BaseFragment {

    @BindView(R.id.sesame_view)
    NewCreditSesameView sesameView;
    @BindView(R.id.tv_co2)
    TextView tvCo2;
    @BindView(R.id.tv_o3)
    TextView tvO3;
    @BindView(R.id.tv_so2)
    TextView tvSo2;
    @BindView(R.id.tv_co)
    TextView tvCo;
    @BindView(R.id.tv_pm25)
    TextView tvPm25;
    @BindView(R.id.tv_pm10)
    TextView tvPm10;
    Unbinder unbinder;

    private String type;
    private String cityId;

    private Random random = new Random();

    private WeatherTotal7Bean.DataBean.Day7Bean data;

    public static AirItemFragment getInstance(WeatherTotal7Bean.DataBean.Day7Bean data) {
        AirItemFragment fragment = new AirItemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.gh_fragment_air_item;
    }

    @Override
    protected void init(Bundle savedInstanceState, View contentView) {
        initBundle();
        initView();
        initData();
    }

    private void initBundle() {
        Bundle args = getArguments();
        if (args != null) {
            data = (WeatherTotal7Bean.DataBean.Day7Bean) args.getSerializable("data");
        }
    }

    private void initView() {

    }

    private void initData() {
//        sesameView.setSesameValues(200, Integer.valueOf(data.getAqi()), data.getQuality());
        tvCo2.setText("--");
        tvO3.setText("--");
        tvSo2.setText("--");
        tvCo.setText("--");
        tvPm25.setText("--");
        tvPm10.setText("--");
    }

    @Override
    protected void lazyLoad() {
        setRefresh(true);
//        int i = random.nextInt(950 - 351);
//        sesameView.setSesameValues(i + 351);
        sesameView.setSesameValues(200,Integer.valueOf(data.getAqi()),  data.getQuality());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
