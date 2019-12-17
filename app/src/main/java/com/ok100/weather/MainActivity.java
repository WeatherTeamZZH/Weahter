package com.ok100.weather;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Person;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ok100.greendao.gen.AllCityGreenBeanDao;
import com.ok100.greendao.gen.CityGreenDaoBeanDao;
import com.ok100.weather.activity.MyCityActivity;
import com.ok100.weather.activity.UserInofActivity;
import com.ok100.weather.activity.WelcomeActivity;
import com.ok100.weather.activity.ZhutiImgeActivity;
import com.ok100.weather.adapter.MianSpotAdapter;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.base.BaseApplication;
import com.ok100.weather.bean.AllCityGreenBean;
import com.ok100.weather.bean.CityGreenDaoBean;
import com.ok100.weather.bean.DepartmentListBean;
import com.ok100.weather.bean.EventTitleMessage;
import com.ok100.weather.bean.GetMyTokenBean;
import com.ok100.weather.bean.MainSpotClickBean;
import com.ok100.weather.bean.TokenRuturnBean;
import com.ok100.weather.bean.WeatherBean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.constant.ConstantCode;
import com.ok100.weather.constant.GGPositionId;
import com.ok100.weather.fragment.MainFragment;
import com.ok100.weather.fragment.NoticeMainFragment1;
import com.ok100.weather.gb.view.HomeActivity;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.http.Urls;
import com.ok100.weather.myviewpager.TextPagerAdapter;
import com.ok100.weather.presenter.NewsListPresenterImpl;
import com.ok100.weather.presenter.NoticeMainListPresenterImpl;
import com.ok100.weather.presenter.UcDataPresenterImpl;
import com.ok100.weather.utils.ChooseTypeUtils;
import com.ok100.weather.utils.DataUtils;
import com.ok100.weather.utils.ListDataSave;
import com.ok100.weather.view.MainViewPager;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialAD;
import com.qq.e.ads.interstitial2.UnifiedInterstitialADListener;
import com.qq.e.ads.interstitial2.UnifiedInterstitialMediaListener;
import com.qq.e.comm.constants.AdPatternType;
import com.qq.e.comm.util.AdError;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ok100.weather.activity.MyCityActivity.MyCityResult;

public class MainActivity extends BaseActivity implements View.OnClickListener, ReturnDataView, UnifiedInterstitialADListener, UnifiedInterstitialMediaListener {

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

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<String> tagList;
    private LongSparseArray<MainFragment> mTestFragments;
    private int key;
    private TextPagerAdapter mPagerAdapter;

    public String locationX = "";
    public String locationY = "";
    public String locationCiyt = "";
    AllCityGreenBeanDao allCityGreenBeanDao ;

    public static boolean isInitDb = false;
    public static String MYTOKEN = "";
    public static String APDIDP = "";
    UcDataPresenterImpl ucDataPresenter ;

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

        String[] testDeviceInfo = getTestDeviceInfo(MainActivity.this);
        Log.e("testDeviceInfo",testDeviceInfo[0]+"---"+testDeviceInfo[1]);
    }

    public static String[] getTestDeviceInfo(Context context){
        String[] deviceInfo = new String[2];
        try {
            if(context != null){
                deviceInfo[0] = DeviceConfig.getDeviceIdForGeneral(context);
                deviceInfo[1] = DeviceConfig.getMac(context);
            }
        } catch (Exception e){
        }
        return deviceInfo;
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
         ucDataPresenter = new UcDataPresenterImpl(this);
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("api_key",Urls.UCAppid);
        objectObjectHashMap.put("api_secret",Urls.UCappsecret);
        ucDataPresenter.viptoken(MainActivity.this,objectObjectHashMap);
        showPopGuanggao();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iad != null) {
            iad.destroy();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_weather_user:
                intent = new Intent(MainActivity.this, UserInofActivity.class);
                startActivity(intent);
                MobclickAgent.onEvent(MainActivity.this, "clickMyinfo");
                break;
            case R.id.iv_add_weather:
                if(isInitDb){
                    intent = new Intent(MainActivity.this, MyCityActivity.class);
                    startActivityForResult(intent, 10001);
                    overridePendingTransition(R.anim.my_city_activity_in, R.anim.my_city_activity_out);
                    MobclickAgent.onEvent(MainActivity.this, "clickMyCity");
                    MobclickAgent.onEvent(MainActivity.this, "clickMyCity","clickMyCityLabel");
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
                intent.putExtra("weather",weather);
                intent.putExtra("temp",temp);
                if(!TextUtils.isEmpty(mTvCity.getText())){
                    intent.putExtra("city",mTvCity.getText());
                }
                intent.putExtra("mouth",mouth);
                startActivity(intent);

                MobclickAgent.onEvent(MainActivity.this, "clickShareBtn");
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
    WeatherTotalBean  weatherTotalBean ;

    @Override
    public void returnData(String responseCode, Object o) {
        switch (responseCode) {
            case "getTotalWeatherGPS":
                weatherTotalBean = (WeatherTotalBean) o;
                if(weatherTotalBean!=null){
                    Log.e("weatherTotalBean", weatherTotalBean.toString());
                    String area = weatherTotalBean.getData().getCityinfo().getArea();
                    if(!TextUtils.isEmpty(area)){
                        mTvCity.setText(area);
                        addCity(area);
                    }
                }
                break;
            case "viptoken":
                TokenRuturnBean tokenRuturnBean = (TokenRuturnBean) o;
                if(tokenRuturnBean.getCode().equals("200")){
                    MYTOKEN =tokenRuturnBean.getData().getToken();
                    APDIDP =tokenRuturnBean.getData().getApdid();
                }
                mTestFragments = new LongSparseArray<>();
                initViewpagerAdapter();
                initSpotAdapter();
                break;
        }
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

    public String mouth,week,nongli,weather,temp;
    public void setTitleDate(String string1,String string2,String string3) {
        mouth = string1;
        week = string2;
        nongli = string3;
        mTvDate.setText(string1+" 周"+string2+" 农历"+string3);
    }

    public void setTitleWeather(String string) {
        temp = string;
        mTvTitleCity.setText(string);
    }
    public void setTitleWeatherImage(String string) {
        weather = string;
        mIvTitleBigImage.setBackgroundResource(ChooseTypeUtils.getWeatherImgge(string));
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
                    try{
                        if(!(TextUtils.isEmpty(locationX)||TextUtils.isEmpty(locationY))){
                            NoticeMainListPresenterImpl noticeMainListPresenter = new NoticeMainListPresenterImpl(MainActivity.this);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("from", "autonavi");
                            hashMap.put("lat", locationX);
                            hashMap.put("lng", locationY);
                            hashMap.put("needday", "1");
                            noticeMainListPresenter.getTotalWeatherGPS(MainActivity.this, hashMap);

                        }
                    }catch (Exception e){

                    }

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
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                List<Activity> list = ((BaseApplication) getApplication()).getActivities();
                for (int i = 0; i < list.size(); i++) {
                    Activity activity = (Activity) list.get(i);
                    activity.finish();
                }
                this.finish();
                ActivityManager activityMgr = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
                activityMgr.restartPackage(this.getPackageName());
//                System.exit(0);//退出应用
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showPopGuanggao() {
        iad = getIAD();
        setVideoOption();
        iad.loadAD();
        new Thread(new Runnable() {
            @Override
            public void run() {

                SystemClock.sleep(5000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        showAsPopup();
                        showAD();
                    }
                });

            }
        }).start();

    }


    private UnifiedInterstitialAD iad;

    private void setVideoOption(){
        VideoOption.Builder builder = new VideoOption.Builder();
        VideoOption option = builder.build();
            //不配置VideoOption，使用默认值
            option = builder.setAutoPlayMuted(true)
                    //始终自动播放
                    .setAutoPlayPolicy(1)
                    //禁音自动播放
                    .setDetailPageMuted(true)
                    .build();
        iad.setVideoOption(option);
        iad.setMinVideoDuration(getMinVideoDuration());
        iad.setMaxVideoDuration(getMaxVideoDuration());

        /**
         * 如果广告位支持视频广告，强烈建议在调用loadData请求广告前调用setVideoPlayPolicy，有助于提高视频广告的eCPM值 <br/>
         * 如果广告位仅支持图文广告，则无需调用
         */

        /**
         * 设置本次拉取的视频广告，从用户角度看到的视频播放策略<p/>
         *
         * "用户角度"特指用户看到的情况，并非SDK是否自动播放，与自动播放策略AutoPlayPolicy的取值并非一一对应 <br/>
         *
         * 如自动播放策略为AutoPlayPolicy.WIFI，但此时用户网络为4G环境，在用户看来就是手工播放的
         */
        iad.setVideoPlayPolicy(getVideoPlayPolicy(option.getAutoPlayPolicy(), this));
    }


    //这个方法别的activity弄过来的
    public static int getVideoPlayPolicy(int autoPlayPolicy, Context context){
        if(autoPlayPolicy == VideoOption.AutoPlayPolicy.ALWAYS){
            return VideoOption.VideoPlayPolicy.AUTO;
        }else if(autoPlayPolicy == VideoOption.AutoPlayPolicy.WIFI){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            return wifiNetworkInfo != null && wifiNetworkInfo.isConnected() ? VideoOption.VideoPlayPolicy.AUTO
                    : VideoOption.VideoPlayPolicy.MANUAL;
        }else if(autoPlayPolicy == VideoOption.AutoPlayPolicy.NEVER){
            return VideoOption.VideoPlayPolicy.MANUAL;
        }
        return VideoOption.VideoPlayPolicy.UNKNOWN;
    }


    private UnifiedInterstitialAD getIAD() {
        String posId = getPosID();
        if (this.iad != null) {
            iad.close();
            iad.destroy();
            iad = null;
        }
        iad = new UnifiedInterstitialAD(this, ConstantCode.GGAPPID, posId, this);
        return iad;
    }

    private void showAD() {
        if (iad != null) {
            iad.show();
        } else {
//            Toast.makeText(this, "请加载广告后再进行展示 ！ ", Toast.LENGTH_LONG).show();
        }
    }

    private void showAsPopup() {
        if (iad != null) {
            iad.showAsPopupWindow();
        } else {
//            Toast.makeText(this, "请加载广告后再进行展示 ！ ", Toast.LENGTH_LONG).show();
        }
    }

    private void close() {
        if (iad != null) {
            iad.close();
        } else {
//            Toast.makeText(this, "广告尚未加载 ！ ", Toast.LENGTH_LONG).show();
        }
    }

    private String getPosID() {
        return GGPositionId.MAIN_POP_POS_ID;
    }

    @Override
    public void onADReceive() {
//        Toast.makeText(this, "广告加载成功 ！ ", Toast.LENGTH_LONG).show();
        // onADReceive之后才能调用getAdPatternType()
        if(iad.getAdPatternType() == AdPatternType.NATIVE_VIDEO){
            iad.setMediaListener(this);
        }
        // onADReceive之后才可调用getECPM()
        Log.d(TAG, "eCPM = " + iad.getECPM() + " , eCPMLevel = " + iad.getECPMLevel());
    }

    @Override
    public void onVideoCached() {
        // 视频素材加载完成，在此时调用iad.show()或iad.showAsPopupWindow()视频广告不会有进度条。
        Log.i(TAG, "onVideoCached");
    }

    @Override
    public void onNoAD(AdError error) {
        String msg = String.format(Locale.getDefault(), "onNoAD, error code: %d, error msg: %s",
                error.getErrorCode(), error.getErrorMsg());
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onADOpened() {
        Log.i(TAG, "onADOpened");
    }

    @Override
    public void onADExposure() {
        Log.i(TAG, "onADExposure");
    }

    @Override
    public void onADClicked() {
        Log.i(TAG, "onADClicked : " + (iad.getExt() != null? iad.getExt().get("clickUrl") : ""));
    }

    @Override
    public void onADLeftApplication() {
        Log.i(TAG, "onADLeftApplication");
    }

    @Override
    public void onADClosed() {
        Log.i(TAG, "onADClosed");
    }

    @Override
    public void onVideoInit() {
        Log.i(TAG, "onVideoInit");
    }

    @Override
    public void onVideoLoading() {
        Log.i(TAG, "onVideoLoading");
    }

    @Override
    public void onVideoReady(long videoDuration) {
        Log.i(TAG, "onVideoReady, duration = " + videoDuration);
    }

    @Override
    public void onVideoStart() {
        Log.i(TAG, "onVideoStart");
    }

    @Override
    public void onVideoPause() {
        Log.i(TAG, "onVideoPause");
    }

    @Override
    public void onVideoComplete() {
        Log.i(TAG, "onVideoComplete");
    }

    @Override
    public void onVideoError(AdError error) {
        Log.i(TAG, "onVideoError, code = " + error.getErrorCode() + ", msg = " + error.getErrorMsg());
    }

    @Override
    public void onVideoPageOpen() {
        Log.i(TAG, "onVideoPageOpen");
    }

    @Override
    public void onVideoPageClose() {
        Log.i(TAG, "onVideoPageClose");
    }



    private int getMinVideoDuration() {

        return 10;
    }

    private int getMaxVideoDuration() {

        return 60;
    }
}