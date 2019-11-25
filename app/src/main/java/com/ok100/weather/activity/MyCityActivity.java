package com.ok100.weather.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ok100.greendao.gen.AllCityGreenBeanDao;
import com.ok100.greendao.gen.CityGreenDaoBeanDao;
import com.ok100.weather.R;
import com.ok100.weather.adapter.MyCityAdapter1;
import com.ok100.weather.adapter.MyCityAdapter2;
import com.ok100.weather.adapter.MyCityAdapter4;
import com.ok100.weather.base.BaseActivity;
import com.ok100.weather.base.BaseApplication;
import com.ok100.weather.bean.AllCityGreenBean;
import com.ok100.weather.bean.CityGreenDaoBean;
import com.ok100.weather.bean.DataBean;
import com.ok100.weather.bean.DefultGridViewBean;
import com.ok100.weather.bean.MyCityAdapter1Bean;
import com.ok100.weather.bean.WeatherTotalBean;
import com.ok100.weather.http.ReturnDataView;
import com.ok100.weather.presenter.NewsListPresenterImpl;
import com.ok100.weather.presenter.NoticeMainListPresenterImpl;
import com.ok100.weather.utils.ChooseTypeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCityActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener , ReturnDataView<Object> {


    @BindView(R.id.tv_my_city)
    TextView mTvMyCity;
    @BindView(R.id.edittext_search)
    EditText editText;
    @BindView(R.id.textview_quxiao)
    TextView mTextviewQuxiao;
    @BindView(R.id.recycleview1)
    RecyclerView mRecycleview1;
    @BindView(R.id.recycleview2)
    RecyclerView mRecycleview2;
    @BindView(R.id.recycleview3)
    RecyclerView mRecycleview3;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.ll_recycle1)
    LinearLayout mLlRecycle1;
    @BindView(R.id.ll_recycle2)
    LinearLayout mLlRecycle2;

    MyCityAdapter1 myCityAdapter1;
    @BindView(R.id.iv_add_city)
    ImageView mIvAddCity;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_choose_city)
    ImageView mIvChooseCity;

    public List<CityGreenDaoBean> cityGreenDaoBeanList = new ArrayList<>();
    public List<MyCityAdapter1Bean> adapter1BeanList = new ArrayList<>();
    @BindView(R.id.recycleview4)
    RecyclerView mRecycleview4;

    MyCityAdapter4 myPindaoAdapter4;
    @BindView(R.id.nestedscrollview)
    NestedScrollView mNestedscrollview;

    @Override
    public int getLayoutID() {
        return R.layout.activity_my_city;
    }

    @Override
    public void InitView() {
        initWeatherData();
        //数据
        myCityAdapter1 = new MyCityAdapter1();
        myCityAdapter1.setOnItemChildClickListener(this);
        mRecycleview1.setAdapter(myCityAdapter1);
        myCityAdapter1.setNewData(adapter1BeanList);
        mRecycleview1.setLayoutManager(new LinearLayoutManager(MyCityActivity.this));
//        myCityAdapter1.setNestedScrollingEnabled(false);//禁止滑动
        myCityAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyCityAdapter1Bean myCityAdapter1Bean = (MyCityAdapter1Bean) adapter.getData().get(position);
                stataActivity(myCityAdapter1Bean.getArea());
            }
        });

        mRecycleview2.setLayoutManager(new GridLayoutManager(MyCityActivity.this, 3));
        MyCityAdapter2 myPindaoAdapter2 = new MyCityAdapter2();
        myPindaoAdapter2.setNewData(DataBean.getHotCicy());
        mRecycleview2.setAdapter(myPindaoAdapter2);
        mRecycleview2.setNestedScrollingEnabled(false);//禁止滑动
        myPindaoAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DefultGridViewBean cityName = (DefultGridViewBean) adapter.getData().get(position);
                stataActivity(cityName.getName());
            }
        });


        mRecycleview3.setLayoutManager(new GridLayoutManager(MyCityActivity.this, 3));
        MyCityAdapter2 myPindaoAdapter3 = new MyCityAdapter2();
        //热门景区的东西
//        myPindaoAdapter3.setNewData(getAdapterData2());
        mRecycleview3.setAdapter(myPindaoAdapter3);
        mRecycleview3.setNestedScrollingEnabled(false);//禁止滑动
        myPindaoAdapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DefultGridViewBean cityName = (DefultGridViewBean) adapter.getData().get(position);
                stataActivity(cityName.getName());
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String textEidt = editText.getText().toString();
                if (TextUtils.isEmpty(textEidt)) {
                    mLlRecycle1.setVisibility(View.VISIBLE);
                    mRecycleview4.setVisibility(View.GONE);
                } else {
                    mLlRecycle1.setVisibility(View.GONE);
                    mRecycleview4.setVisibility(View.VISIBLE);

                }
                BaseApplication application = (BaseApplication) getApplication();
                AllCityGreenBeanDao allCityGreenBeanDao = application.getDaoSession().getAllCityGreenBeanDao();
                ArrayList<AllCityGreenBean> allCityGreenBeans = new ArrayList<>();
                List<AllCityGreenBean> list1 = allCityGreenBeanDao.queryBuilder().where(AllCityGreenBeanDao.Properties.NAMECN.like("%" + textEidt + "%")).list();
                List<AllCityGreenBean> list2 = allCityGreenBeanDao.queryBuilder().where(AllCityGreenBeanDao.Properties.DISTRICTCN.like("%" + textEidt + "%")).list();
                for (int i = 0; i < list1.size(); i++) {
                    Log.e("list1", list1.get(i).toString());
                }
                for (int i = 0; i < list2.size(); i++) {
                    Log.e("list2", list2.get(i).toString());
                }
                allCityGreenBeans.addAll(list1);
                allCityGreenBeans.addAll(list2);
                myPindaoAdapter4.setNewData(allCityGreenBeans);
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mRlTitle.setVisibility(View.GONE);
                    mTvMyCity.setVisibility(View.GONE);
                    mRecycleview1.setVisibility(View.GONE);
                    mTextviewQuxiao.setVisibility(View.VISIBLE);
                    mLlRecycle1.setVisibility(View.VISIBLE);
                    //热门景区
//                    mLlRecycle2.setVisibility(View.VISIBLE);
                    editText.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                } else {
                    mRlTitle.setVisibility(View.VISIBLE);
                    mTvMyCity.setVisibility(View.VISIBLE);
                    mRecycleview1.setVisibility(View.VISIBLE);
                    mTextviewQuxiao.setVisibility(View.GONE);
                    mLlRecycle1.setVisibility(View.GONE);
                    //热门景区
                    mLlRecycle2.setVisibility(View.GONE);
                    editText.setGravity(Gravity.CENTER_VERTICAL);
                }
            }
        });

        mRecycleview1.setNestedScrollingEnabled(false);


        mRecycleview4.setLayoutManager(new LinearLayoutManager(MyCityActivity.this));
        myPindaoAdapter4 = new MyCityAdapter4();
        mRecycleview4.setAdapter(myPindaoAdapter4);
        myPindaoAdapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AllCityGreenBean bean = (AllCityGreenBean) adapter.getData().get(position);
                stataActivity(bean.getNAMECN());
            }
        });
//        mRecycleview4.setNestedScrollingEnabled(false);//禁止滑动

    }


    private List<DefultGridViewBean> getAdapterData2() {
        ArrayList<DefultGridViewBean> defultGridViewBeans = new ArrayList<>();
        DefultGridViewBean defultGridViewBean = new DefultGridViewBean("西双版纳");
        defultGridViewBeans.add(defultGridViewBean);
        return defultGridViewBeans;
    }


    @Override
    public void initListener() {
        mIvAddCity.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mIvChooseCity.setOnClickListener(this);
        mTextviewQuxiao.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState, View contentView) {
        adapter1BeanList.clear();
        for (int i=0;i<cityGreenDaoBeanList.size();i++){
            MyCityAdapter1Bean myCityAdapter1Bean = new MyCityAdapter1Bean();
            myCityAdapter1Bean.setArea(cityGreenDaoBeanList.get(i).getArea());
            myCityAdapter1Bean.setCity(cityGreenDaoBeanList.get(i).getCity());
            myCityAdapter1Bean.setProv(cityGreenDaoBeanList.get(i).getProv());
            adapter1BeanList.add(myCityAdapter1Bean);
        }
        myCityAdapter1.notifyDataSetChanged();
        http();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_city:
                myCityAdapter1.setHide(!myCityAdapter1.isHide());
                myCityAdapter1.notifyDataSetChanged();
                break;
            case R.id.iv_back:
                finish();
                overridePendingTransition(R.anim.my_city_activity_in, R.anim.my_city_activity_out);
                break;
            case R.id.textview_quxiao:
                fangjiaodian(v);
                mRlTitle.setVisibility(View.VISIBLE);
                mTvMyCity.setVisibility(View.VISIBLE);
                mRecycleview1.setVisibility(View.VISIBLE);
                mTextviewQuxiao.setVisibility(View.GONE);
                mLlRecycle1.setVisibility(View.GONE);
                mLlRecycle2.setVisibility(View.GONE);
                mRecycleview4.setVisibility(View.GONE);
                break;
            case R.id.iv_choose_city:
                mRlTitle.setVisibility(View.GONE);
                mTvMyCity.setVisibility(View.GONE);
                mRecycleview1.setVisibility(View.GONE);
                mTextviewQuxiao.setVisibility(View.VISIBLE);
                mLlRecycle1.setVisibility(View.VISIBLE);
                mLlRecycle2.setVisibility(View.GONE);
                break;
        }
    }

    private void fangjiaodian(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            assert v != null;
            hideSoftKeyboard(MyCityActivity.this);
            if (editText != null) {
                editText.clearFocus();
            }
        }
    }

    //    使editText点击外部时候失去焦点
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if (isShouldHideInput(v, ev)) {//点击editText控件外部
//                fangjiaodian(v);
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
//    }


    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            editText = (EditText) v;
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_delete:
                //删除
                if(cityGreenDaoBeanList.size()<=1){
                    Toast.makeText(MyCityActivity.this,"请您至少保留一个城市",Toast.LENGTH_SHORT).show();
                }
                deleteGreenDao(cityGreenDaoBeanList.get(position).getArea());
                adapter.getData().remove(position);
                adapter.notifyDataSetChanged();
                delectCityListener.setdelectCity(position);
                break;
        }
    }

    public static int MyCityResult = 10001;

    public void stataActivity(String cityName) {
        Intent intent = new Intent();
        intent.putExtra("cityName", cityName); //将计算的值回传回去
        //通过intent对象返回结果，必须要调用一个setResult方法，
        //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
        setResult(MyCityResult, intent);
        finish();
        overridePendingTransition(R.anim.my_city_activity_in, R.anim.my_city_activity_out);
    }

    private void initWeatherData() {
        cityGreenDaoBeanList.clear();
        cityGreenDaoBeanList = searchGreenDao();
    }

    NewsListPresenterImpl newsListPresenterImpl ;
    WeatherTotalBean weatherTotalBean ;

    @Override
    public void returnData(String responseCode, Object o) {
        switch (responseCode) {
            case "getTotalWeather":
                weatherTotalBean = (WeatherTotalBean) o;
                Log.e("weatherTotalBean", weatherTotalBean.toString());
                for(int i=0;i<adapter1BeanList.size();i++){
                    if(adapter1BeanList.get(i).getArea().equals(weatherTotalBean.getData().getCityinfo().getArea())){
                        adapter1BeanList.get(i).setDay_air_temperature(weatherTotalBean.getData().getNow().getCity().getDay_air_temperature()+"°");
                        adapter1BeanList.get(i).setNight_air_temperature(weatherTotalBean.getData().getNow().getCity().getNight_air_temperature()+"°");
                        adapter1BeanList.get(i).setWeatherImage(ChooseTypeUtils.getWeatherImgge(weatherTotalBean.getData().getNow().getCity().getWeather()));
                    }
                }
               myCityAdapter1.notifyDataSetChanged();
                break;
        }
    }

    private void http() {
        NoticeMainListPresenterImpl noticeMainListPresenter = new NoticeMainListPresenterImpl(this);
        HashMap<String, String> hashMap ;
//        hashMap.put("area", "西湖");
        for(int i= 0;i<cityGreenDaoBeanList.size();i++){
            hashMap = new HashMap<>();
            String prov = cityGreenDaoBeanList.get(i).getProv();
            String city = cityGreenDaoBeanList.get(i).getCity();
            String area = cityGreenDaoBeanList.get(i).getArea();
            if (!TextUtils.isEmpty(prov)) {
                hashMap.put("prov", prov);
            }
            if (!TextUtils.isEmpty(city)) {
                hashMap.put("city", city);
            }
            if (!TextUtils.isEmpty(area)) {
                hashMap.put("area", area);
            }
            hashMap.put("needday", "1");
            noticeMainListPresenter.getTotalWeather(MyCityActivity.this, hashMap);
        }
    }

    @Override
    public void showError(String msg) {

    }


    public interface delectCityListener {
        void setdelectCity(int cityPosition);
    }

    public static MyCityActivity.delectCityListener delectCityListener;

    public static void setDelectCityListener(MyCityActivity.delectCityListener delectCityListener) {
        MyCityActivity.delectCityListener = delectCityListener;
    }

    private void addGreenDAO(String city) {
        CityGreenDaoBean cityGreenDaoBean = new CityGreenDaoBean();
        cityGreenDaoBean.setCity(city);
        cityGreenDaoBeanDao.insert(cityGreenDaoBean);
    }


    private List<CityGreenDaoBean> searchGreenDao() {

        List<CityGreenDaoBean> cityGreenDaoBeans = cityGreenDaoBeanDao.loadAll();
        Log.e("cityGreenDaoBeans",cityGreenDaoBeans.size()+"");
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
}
