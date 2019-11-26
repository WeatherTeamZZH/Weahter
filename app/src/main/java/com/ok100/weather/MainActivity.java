package com.ok100.weather;

import android.app.Person;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ok100.greendao.gen.AllCityGreenBeanDao;
import com.ok100.greendao.gen.CityGreenDaoBeanDao;
import com.ok100.weather.activity.MyCityActivity;
import com.ok100.weather.activity.UserInofActivity;
import com.ok100.weather.activity.ZhutiImgeActivity;
import com.ok100.weather.adapter.MianSpotAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.base.BaseApplication;
import com.ok100.weather.bean.AllCityGreenBean;
import com.ok100.weather.bean.CityGreenDaoBean;
import com.ok100.weather.bean.DepartmentListBean;
import com.ok100.weather.bean.EventTitleMessage;
import com.ok100.weather.bean.MainSpotClickBean;
import com.ok100.weather.bean.WeatherBean;
import com.ok100.weather.fragment.MainFragment;
import com.ok100.weather.fragment.NoticeMainFragment1;
import com.ok100.weather.gb.view.HomeActivity;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.myviewpager.TextPagerAdapter;
import com.ok100.weather.utils.DataUtils;
import com.ok100.weather.utils.ListDataSave;
import com.ok100.weather.view.MainViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ok100.weather.activity.MyCityActivity.MyCityResult;

public class MainActivity extends BaseActivity implements View.OnClickListener, ReturnDataView {

    @BindView(R.id.iv_add_weather)
    ImageView mIvAddWeather;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.iv_weather_share)
    ImageView mIvWeatherShare;
    @BindView(R.id.iv_weather_user)
    ImageView mIvWeatherUser;
    @BindView(R.id.id_viewpager)
    MainViewPager mviewpager;
    @BindView(R.id.rceycleview_spot)
    RecyclerView mRceycleviewSpot;
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

    MianSpotAdapter mianSpotAdapter;
    List<MainSpotClickBean> mainSpotClickBeanList = new ArrayList<>();
    public List<CityGreenDaoBean> cityGreenDaoBeanList = new ArrayList<>();

    private List<String> tagList;
    private LongSparseArray<MainFragment> mTestFragments;
    private int key;
    private TextPagerAdapter mPagerAdapter;

    public String locationX = "";
    public String locationY = "";
    public String locationCiyt = "";
    AllCityGreenBeanDao allCityGreenBeanDao ;

    public static boolean isInitDb = false;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)

    public static boolean backTitle = false;

    @Override
    public void InitView() {
        locationX = getIntent().getStringExtra("locationX");
        locationY =  getIntent().getStringExtra("locationY");
        locationCiyt =  getIntent().getStringExtra("locationCiyt");
        BaseApplication application = (BaseApplication)getApplication();
        allCityGreenBeanDao = application.getDaoSession().getAllCityGreenBeanDao();
        initAllDbCity();
        initWeatherData();
        mTestFragments = new LongSparseArray<>();
        initViewpagerAdapter();
        initSpotAdapter();

        List<CityGreenDaoBean> cityGreenDaoBeans = cityGreenDaoBeanDao.loadAll();
        if(cityGreenDaoBeans!=null&&cityGreenDaoBeans.size()>0){
            mTvCity.setText(cityGreenDaoBeans.get(0).getArea());
            mTvTitlePosition.setText(cityGreenDaoBeans.get(0).getArea());

        }

        mviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.e("sort:", "onPageSelected: " + position);
//                mviewpager.setCurrentItem(position);
                setSelect(position);
//                MainFragment mainfragment = mTestFragments.get(position);
//                mainfragment.setTitleDateListener(new TitleDateListener() {
//                    @Override
//                    public void setTitleDate(String string) {
//                        setTitleDate(string);
//                    }
//                });
//                mainfragment.setTitleData();
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



    private void initWeatherData() {
        cityGreenDaoBeanList.clear();
        cityGreenDaoBeanList = searchGreenDao();
    }

    private void initViewpagerAdapter() {
        for (int i = 0; i < cityGreenDaoBeanList.size(); i++) {
            mTestFragments.put(cityGreenDaoBeanList.get(i).getId(), MainFragment.newInstance(cityGreenDaoBeanList.get(i).getProv(), cityGreenDaoBeanList.get(i).getCity(), cityGreenDaoBeanList.get(i).getArea()));
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
        mTvCity.setText(cityGreenDaoBeanList.get(location).getArea());
        mTvTitlePosition.setText(cityGreenDaoBeanList.get(location).getArea());

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_weather_user:
                intent = new Intent(MainActivity.this, UserInofActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_add_weather:
                if(isInitDb){
                    intent = new Intent(MainActivity.this, MyCityActivity.class);
                    startActivityForResult(intent, 10001);
                    overridePendingTransition(R.anim.my_city_activity_in, R.anim.my_city_activity_out);
                }else {
                    Toast.makeText(MainActivity.this,"正在初始化数据库，请您稍等片刻。。。",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.iv_title_back_weather:
                MainFragment mainFragment = (MainFragment) mTestFragments.get(getFragmentId());
                Log.e("mainFragment",mainFragment.city+mainFragment.area);
//                if(mainFragment!=null){
                    mainFragment.setAllGoneViewVisible(true);
                    mainFragment.setBottomVisible(false);
//                    mainFragment.setXiaoyulin();
                    hitiTitle(true);
//                }

                EventTitleMessage msg = new EventTitleMessage(true,"messsage");
                EventBus.getDefault().post(msg);
                backTitle = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(1000);
                        backTitle = false;
                    }
                }).start();
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
            }else {

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
        for(int i=0;i<cityGreenDaoBeanList.size();i++){
            if(cityGreenDaoBeanList.get(i).getArea().contains(cityName)){
                mviewpager.setCurrentItem(i);
                return;
            }
        }
//        weatherBeanList.add(new WeatherBean(cityName));
//        dataSave.setDataList("javaBean", weatherBeanList);
        //插入城市
        addGreenDAO(cityName);
        initWeatherData();
        MainSpotClickBean mainSpotClickBean = new MainSpotClickBean();
        mainSpotClickBean.setClick(false);
        mainSpotClickBeanList.add(mainSpotClickBean);
        mianSpotAdapter.notifyDataSetChanged();
//        int ceil = (int)Math.ceil(mTestFragments.size() / 2);
//        mviewpager.setOffscreenPageLimit(ceil);
        CityGreenDaoBean cityGreenDaoBean = cityGreenDaoBeanList.get(cityGreenDaoBeanList.size() - 1);

        mTestFragments.put(cityGreenDaoBean.getId(), MainFragment.newInstance(cityGreenDaoBean.getProv(), cityGreenDaoBean.getCity(), cityGreenDaoBean.getArea()));
        mPagerAdapter.notifyDataSetChanged();
//        mPagerAdapter = new TextPagerAdapter(getSupportFragmentManager(), mTestFragments);
//        mviewpager.setAdapter(mPagerAdapter);
        mviewpager.setCurrentItem(mTestFragments.size()-1);
    }

    private void addGreenDAO(String city) {
        CityGreenDaoBean cityGreenDaoBean = new CityGreenDaoBean();
        List<AllCityGreenBean> list = allCityGreenBeanDao.queryBuilder().where(AllCityGreenBeanDao.Properties.NAMECN.like("%" + city + "%")).list();

        if(list!=null&&list.size()>0){
            cityGreenDaoBean.setCity(list.get(0).getDISTRICTCN());
            cityGreenDaoBean.setProv(list.get(0).getPROVCN());
            cityGreenDaoBean.setArea(list.get(0).getNAMECN());

        }
        cityGreenDaoBeanDao.insert(cityGreenDaoBean);
    }

    private List<CityGreenDaoBean> searchGreenDao() {
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

    private void deleteGreenDao(String cityName) {
        CityGreenDaoBean cityDao = cityGreenDaoBeanDao.queryBuilder().where(CityGreenDaoBeanDao.Properties.Area.eq(cityName)).build().unique();
        if (cityDao != null) {
            //通过Key来删除，这里的Key就是user字段中的ID号
            cityGreenDaoBeanDao.deleteByKey(cityDao.getId());
        }

    }

    public void deleteCity(int position) {

        mainSpotClickBeanList.remove(position);
        mianSpotAdapter.notifyDataSetChanged();

        //删除城市
        deleteGreenDao(cityGreenDaoBeanList.get(position).getCity());
        initWeatherData();
        mTestFragments.removeAt(position);
        Log.e("mTestFragments",mTestFragments.size()+"")
;        mPagerAdapter.notifyDataSetChanged();
    }

    public interface SetZhutiBgListener {
        void zhutiBgListener(int res);
    }

    public void hitiTitle(boolean visitle) {
        mviewpager.setCanScroll(visitle);
        if (visitle) {
            mRlTitleAll.setVisibility(View.GONE);
            mRlTitleDefult.setVisibility(View.VISIBLE);
        } else {
            mRlTitleAll.setVisibility(View.VISIBLE);
            mRlTitleDefult.setVisibility(View.GONE);
        }

    }

    public void setTitleDate(String string) {
        mTvDate.setText(string);
    }

    public void setTitleWeather(String string) {
        mTvTitleCity.setText(string);
    }
    public void setTitleWeatherImage(int string) {
        mIvTitleBigImage.setBackgroundResource(string);
    }

    public interface TitleDateListener {
        void setTitleDate(String string);
    }

    private void initAllDbCity() {
        Log.e("initAllDbCity","initAllDbCity");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String readTxtFile = ReadTxtFile();
                Log.e("readTxtFile",readTxtFile);
                List<String> stringList = stringToList(readTxtFile,"\\|");
                Log.e("stringList",stringList.size()+"");
                addAllCityDb(stringList);
            }
        }).start();
    }


    public void addAllCityDb(List<String> stringList){
//        try{

        AllCityGreenBean allCityGreenBean ;
        for(int i = 0;i<stringList.size();i++){
            String string = stringList.get(i);
            List<String> strings = stringToList(string, ",");
         int size =  allCityGreenBeanDao.queryBuilder().where(AllCityGreenBeanDao.Properties.NAMECN.eq(strings.get(2))).list().size();
            if(size == 0){
                allCityGreenBean = new AllCityGreenBean();
                allCityGreenBean.setAREAID(strings.get(0));
                allCityGreenBean.setNAMECN(strings.get(2));
                allCityGreenBean.setDISTRICTCN(strings.get(4));
                allCityGreenBean.setPROVCN(strings.get(6));
                allCityGreenBean.setNATIONCN(strings.get(8));
                allCityGreenBeanDao.insert(allCityGreenBean);
            }
        }
        isInitDb = true;
        Log.e("init","数据库初始化完成");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(cityGreenDaoBeanList.size()==0){
                    addCity(locationCiyt.substring(0,locationCiyt.length()-1));
                }
            }
        });

//        }catch (Exception e){
//            Log.e("init","数据库初始化失败");
//        }

    }


    private List<String> stringToList(String strs,String split) {
        String str[] = strs.split(split);
        return Arrays.asList(str);
    }

        public ArrayList<String> newList;

    public String ReadTxtFile() {

        StringBuffer stringBuffer = new StringBuffer();
        try {
//                InputStream inputStream = new FileInputStream(file);
                InputStream inputStream = getResources().openRawResource(R.raw.city);
                if (inputStream != null) {
                    InputStreamReader inputreader = new InputStreamReader(inputStream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while ((line = buffreader.readLine()) != null) {
//                        newList.add(line + "\n");
                        stringBuffer.append(line);
                    }
                    inputStream.close();
                }
            } catch (java.io.FileNotFoundException e) {
                Log.d("TestFile", "The File doesn't not exist.");
            } catch (IOException e) {
                Log.d("TestFile", e.getMessage());
            }
        return stringBuffer.toString();

    }



    public long fragmentId = 0;
    public long getFragmentId(){
        int currentItem = mviewpager.getCurrentItem();
        Log.e("mainFragment",currentItem+"");
        fragmentId = cityGreenDaoBeanList.get(currentItem).getId();
        return fragmentId;
    }

    public void bottomView(boolean visible){
        MainFragment mainFragment = (MainFragment) mTestFragments.get(getFragmentId());
        Log.e("mainFragment",mainFragment.city+mainFragment.area);
        mainFragment.setBottomVisible(visible);


    }

    public void appBarView(boolean visible){
        MainFragment mainFragment = (MainFragment) mTestFragments.get(getFragmentId());
        mainFragment.setAllGoneViewVisible(visible);
        hitiTitle(visible);
    }

}