package com.ok100.weather;

import android.app.Person;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ok100.greendao.gen.CityGreenDaoBeanDao;
import com.ok100.weather.activity.MyCityActivity;
import com.ok100.weather.activity.UserInofActivity;
import com.ok100.weather.activity.ZhutiImgeActivity;
import com.ok100.weather.adapter.MianSpotAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.base.BaseApplication;
import com.ok100.weather.bean.CityGreenDaoBean;
import com.ok100.weather.bean.DepartmentListBean;
import com.ok100.weather.bean.MainSpotClickBean;
import com.ok100.weather.bean.WeatherBean;
import com.ok100.weather.fragment.MainFragment;
import com.ok100.weather.gb.view.HomeActivity;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.myviewpager.TextPagerAdapter;
import com.ok100.weather.utils.DataUtils;
import com.ok100.weather.utils.ListDataSave;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ok100.weather.activity.MyCityActivity.MyCityResult;

public class MainActivity extends BaseActivity implements View.OnClickListener, ReturnDataView {

    @BindView(R.id.iv_add_weather)
    ImageView mIvAddWeather;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.tv_data)
    TextView mTvData;
    @BindView(R.id.iv_weather_share)
    ImageView mIvWeatherShare;
    @BindView(R.id.iv_weather_user)
    ImageView mIvWeatherUser;
    @BindView(R.id.id_viewpager)
    ViewPager mviewpager;
    @BindView(R.id.rceycleview_spot)
    RecyclerView mRceycleviewSpot;

    MianSpotAdapter mianSpotAdapter;
    List<MainSpotClickBean> mainSpotClickBeanList = new ArrayList<>();

    public List<CityGreenDaoBean> cityGreenDaoBeanList = new ArrayList<>();
    @BindView(R.id.ll_all_bg)
    LinearLayout mLlAllBg;
    @BindView(R.id.iv_title_big_image)
    ImageView mIvTitleBigImage;
    @BindView(R.id.tv_title_position)
    TextView mTvTitlePosition;
    @BindView(R.id.tv_title_city)
    TextView mTvTitleCity;
    @BindView(R.id.rl_title_city)
    RelativeLayout mRlTitleCity;
    @BindView(R.id.iv_title_shouzhang)
    ImageView mIvTitleShouzhang;
    @BindView(R.id.iv_title_back_weather)
    TextView mIvTitleBackWeather;
    @BindView(R.id.rl_title_all)
    RelativeLayout mRlTitleAll;
    @BindView(R.id.rl_title_defult)
    RelativeLayout mRlTitleDefult;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    private List<String> tagList;
    private SparseArray<MainFragment> mTestFragments;
    private int key;
    private TextPagerAdapter mPagerAdapter;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void InitView() {
//        setTitle("首页", true, TITLE_TYPE_IMG, R.mipmap.back_left, false, 0, "地址管理");
        initWeatherData();
        mTestFragments = new SparseArray<>();
        initViewpagerAdapter();
        initSpotAdapter();

        mviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.d("sort:", "onPageSelected: " + position);
                mviewpager.setCurrentItem(position);
                setSelect(position);
//                switch (position) {
//                    case 0:
//                        setSelect(0);
//                        break;
//                    case 1:
//                        setSelect(1);
//                        break;
//                    case 2:
//                        setSelect(2);
//                        break;
//                    case 3:
//                        setSelect(3);
//                        break;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        MyCityActivity.setDelectCityListener(new MyCityActivity.delectCityListener() {
            @Override
            public void setdelectCity(int cityPosition) {
                Log.e("cityPosition", cityPosition + "");
                deleteCity(cityPosition);
            }
        });

//        mBtnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTestFragments.removeAt(mCurPos);
//                mPagerAdapter.notifyDataSetChanged();
//            }
//        });
//
//        mBtnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTestFragments.put(key++,TestFragment.newInstance("第"+key));
//                mPagerAdapter.notifyDataSetChanged();
//            }
//        });

        ZhutiImgeActivity.mSetZhutiBgListener = new SetZhutiBgListener() {
            @Override
            public void zhutiBgListener(int res) {
                mLlAllBg.setBackgroundResource(res);
            }
        };
    }


//    ListDataSave dataSave;

    private void initWeatherData() {
        cityGreenDaoBeanList.clear();
        cityGreenDaoBeanList = searchGreenDao();
    }

    private void initViewpagerAdapter() {
        for (int i = 0; i < cityGreenDaoBeanList.size(); i++) {
            mTestFragments.put(key++, MainFragment.newInstance(cityGreenDaoBeanList.get(i).getProv(),cityGreenDaoBeanList.get(i).getCity(),cityGreenDaoBeanList.get(i).getArea()));
        }

        mPagerAdapter = new TextPagerAdapter(getSupportFragmentManager(), mTestFragments);
        mviewpager.setAdapter(mPagerAdapter);
//        int ceil = (int)Math.ceil(mTestFragments.size() / 2);
        mviewpager.setOffscreenPageLimit(10);
    }

    private void initSpotAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRceycleviewSpot.setLayoutManager(linearLayoutManager);
        mianSpotAdapter = new MianSpotAdapter();

        for (int i = 0; i < mTestFragments.size(); i++) {
            MainSpotClickBean mainSpotClickBean = new MainSpotClickBean();
            if (i == 0) {
                mainSpotClickBean.setClick(true);
            } else {
                mainSpotClickBean.setClick(false);
            }
            mainSpotClickBeanList.add(mainSpotClickBean);
        }
        mianSpotAdapter.setNewData(mainSpotClickBeanList);
        mRceycleviewSpot.setAdapter(mianSpotAdapter);
    }

    private void setSelect(int location) {
        for (int i = 0; i < mainSpotClickBeanList.size(); i++) {
            if (i == location) {
                mainSpotClickBeanList.get(i).setClick(true);
            } else {
                mainSpotClickBeanList.get(i).setClick(false);
            }
        }
        mianSpotAdapter.notifyDataSetChanged();
        mTvCity.setText(cityGreenDaoBeanList.get(location).getCity());
    }


    @Override
    public void initListener() {
        mIvWeatherUser.setOnClickListener(this);
        mIvAddWeather.setOnClickListener(this);
        mIvWeatherShare.setOnClickListener(this);
        mIvTitleBackWeather.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_weather_user:
                intent = new Intent(MainActivity.this, UserInofActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_add_weather:
                intent = new Intent(MainActivity.this, MyCityActivity.class);
                startActivityForResult(intent, 10001);
                break;
            case R.id.iv_title_back_weather:
                MainFragment mainFragment = (MainFragment) mTestFragments.get(mviewpager.getCurrentItem());
//                mainFragment.setMainHeightAll();
                mainFragment.setBottomVisible(false);
                mainFragment.setAllGoneViewVisible(true);
                hitiTitle(false);
                break;
            case R.id.iv_weather_share:
//                intent = new Intent(MainActivity.this, ShareMainActivity.class);
//                startActivity(intent);
                intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    // 为了获取结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1

//        if (resultCode == 2) {
        if (requestCode == MyCityResult) {
            if (data != null) {
                String cityName = data.getStringExtra("cityName");
                Log.e("cityName", cityName);
                //设置结果显示框的显示数值
                addCity(cityName);
            }
        }
//        }
    }

    @Override
    public void returnData(String responseCode, Object o) {
        List<DepartmentListBean> departmentListBean = (List<DepartmentListBean>) o;
//        initPop();
//        setNewArraylist(departmentListBean);
    }


    @Override
    public void showError(String msg) {

    }


    public void addCity(String cityName) {

//        weatherBeanList.add(new WeatherBean(cityName));
//        dataSave.setDataList("javaBean", weatherBeanList);
        //插入城市
        addGreenDAO(cityName);
        MainSpotClickBean mainSpotClickBean = new MainSpotClickBean();
        mainSpotClickBean.setClick(false);
        mainSpotClickBeanList.add(mainSpotClickBean);
        mianSpotAdapter.notifyDataSetChanged();


//        int ceil = (int)Math.ceil(mTestFragments.size() / 2);
//        mviewpager.setOffscreenPageLimit(ceil);

        mTestFragments.put(key++, MainFragment.newInstance("上海",cityName,"上海"));
        mPagerAdapter.notifyDataSetChanged();


    }

    private void addGreenDAO(String city) {
        CityGreenDaoBean cityGreenDaoBean = new CityGreenDaoBean();
        cityGreenDaoBean.setCity(city);
        cityGreenDaoBeanDao.insert(cityGreenDaoBean);

    }

    private List<CityGreenDaoBean> searchGreenDao(){
        List<CityGreenDaoBean> cityGreenDaoBeans = cityGreenDaoBeanDao.loadAll();
        return cityGreenDaoBeans;
//        if(cityBean != null) {
//            cityBean.setCity("CD");
//            // update为更新
//            cityGreenDaoBeanDao.update(cityBean);
//            Toast.makeText(MainActivity.this,"修改成功", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(MainActivity.this, "城市不存在", Toast.LENGTH_SHORT).show();
//        }
    }

    private void deleteGreenDao(String cityName){
        CityGreenDaoBean cityDao = cityGreenDaoBeanDao.queryBuilder().where(CityGreenDaoBeanDao.Properties.City.eq(cityName)).build().unique();
        if(cityDao != null){
            //通过Key来删除，这里的Key就是user字段中的ID号
            cityGreenDaoBeanDao.deleteByKey(cityDao.getId());
}
    }

    public void deleteCity(int position) {
//        int ceil = (int)Math.ceil(mTestFragments.size() / 2);
//        mviewpager.setOffscreenPageLimit(ceil);

        mainSpotClickBeanList.remove(position);
        mianSpotAdapter.notifyDataSetChanged();

//        dataSave.setDataList("javaBean", weatherBeanList);
        //删除城市
        deleteGreenDao(cityGreenDaoBeanList.get(position).getCity());
        mTestFragments.removeAt(position);
        mPagerAdapter.notifyDataSetChanged();
    }

    public interface SetZhutiBgListener {
        void zhutiBgListener(int res);
    }

    public void hitiTitle(boolean visitle) {
        if (!visitle) {
            mRlTitleAll.setVisibility(View.GONE);
            mRlTitleDefult.setVisibility(View.VISIBLE);
            Log.e("visible","mRlTitleAll隐藏");
            Log.e("visible","RlTitleDefult显示");
        } else {
            mRlTitleAll.setVisibility(View.VISIBLE);
            mRlTitleDefult.setVisibility(View.GONE);
            Log.e("visible","mRlTitleAll显示");
            Log.e("visible","RlTitleDefult隐藏");
        }

    }

}
