package com.ok100.weather.gh;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.ok100.weather.R;
import com.ok100.weather.base.BaseFragment;
import com.ok100.weather.bean.WeatherTotal7Bean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: gh
 * @description:
 * @date: 2019-11-12.
 * @from:
 */
public class AirFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.ll_national)
    LinearLayout llNational;
    @BindView(R.id.tv_switch_1)
    TextView tvSwitch1;
    @BindView(R.id.tv_switch_2)
    TextView tvSwitch2;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] titlelist = new String[]{"周一\n11-8", "周二", "周三", "周四", "周五"};
    private ArrayList<Fragment> fragmentList;

    private WeatherTotal7Bean data;

    public static AirFragment getInstance( WeatherTotal7Bean weatherTotal7Bean) {
        AirFragment fragment = new AirFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", weatherTotal7Bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.gh_fragment_air;
    }

    @Override
    protected void init(Bundle savedInstanceState, View view) {
        initBundle();
        initView(view);
        initData();
    }

    private void initBundle() {
        Bundle args = getArguments();
        if (args != null) {
            data = (WeatherTotal7Bean) args.getSerializable("data");
        }
    }

    private void initView(View view) {
        tablayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewPager);
    }

    private void initData() {
        if (data == null || data.getData() == null || data.getData().getDay7() == null || data.getData().getDay7().size()<5)return;

        List<WeatherTotal7Bean.DataBean.Day7Bean> list = data.getData().getDay7();

        fragmentList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            fragmentList.add(AirItemFragment.getInstance(list.get(i)));
            mTabEntities.add(new TabEntity(titlelist[i]));
        }

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titlelist[position];
            }
        };
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setupWithViewPager(viewPager, false);
        for (int i = 0; i < 5; i++) {
            tablayout.getTabAt(i).setCustomView(R.layout.tablayou_view_housepk_left);
            ((TextView) (tablayout.getTabAt(i).getCustomView().findViewById(R.id.tv_week))).setText("周"+list.get(i).getWeek());
            ((TextView) (tablayout.getTabAt(i).getCustomView().findViewById(R.id.tv_date))).setText(list.get(i).getDate());
        }
        tablayout.getTabAt(0).select();

        rvList.setLayoutManager(new LinearLayoutManager(getActivity()) );
        AirAdapter airAdapter = new AirAdapter();
        rvList.setAdapter(airAdapter);

        ArrayList<String> lista = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            lista.add("");
        }

        airAdapter.setNewData(lista);
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


    @OnClick({R.id.tv_switch_1, R.id.tv_switch_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_switch_1:
                llNational.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_switch_2:
                llNational.setVisibility(View.GONE);
                break;
        }
    }
}
