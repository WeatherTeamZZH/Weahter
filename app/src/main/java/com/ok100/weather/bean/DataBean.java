package com.ok100.weather.bean;

import com.ok100.weather.R;

import java.util.ArrayList;
import java.util.List;

import me.zhouzhuo.zzweatherview.AirLevel;
import me.zhouzhuo.zzweatherview.WeatherModel;

/**
 * @Description: This is DataBean
 * @Author: ZHANGZH
 * @Time: 2019/11/10 15:07
 * @Email: qq.com
 * @org: OK100
 */
public class DataBean {
    public static List<WeatherModel> generateData() {
        ArrayList<WeatherModel> weatherModels = new ArrayList<>();

        //数据源
        WeatherModel model = new WeatherModel();
        model.setDate("12/07");//日期
        model.setWeek("昨天");  //星期
        model.setDayWeather("大雪"); //白天天气
        model.setDayTemp(11); //白天温度
        model.setNightTemp(5); //夜晚温度
        model.setNightWeather("晴"); //夜晚天气
        model.setWindOrientation("西南风"); //风向
        model.setWindLevel("3级"); //风级
        model.setAirLevel(AirLevel.EXCELLENT); //空气质量
        model.setDayPic(R.mipmap.ic_launcher); //白天天气图标(默认提供常用天气图标)
        model.setNightPic(R.mipmap.ic_launcher); //晚上天气图标(默认提供常用天气图标)
        weatherModels.add(model);

        WeatherModel mode2 = new WeatherModel();
        mode2.setDayTemp(11); //白天温度
        setDataWeather(mode2);
        WeatherModel mode3 = new WeatherModel();
        mode3.setDayTemp(27); //白天温度
        setDataWeather(mode3);
        WeatherModel mode4 = new WeatherModel();
        mode4.setDayTemp(34); //白天温度
        setDataWeather(mode4);
        WeatherModel mode5 = new WeatherModel();
        mode5.setDayTemp(21); //白天温度
        setDataWeather(mode5);
        WeatherModel mode6 = new WeatherModel();
        mode6.setDayTemp(-10); //白天温度
        setDataWeather(mode6);
        WeatherModel mode7 = new WeatherModel();
        mode7.setDayTemp(11); //白天温度
        setDataWeather(mode7);
        weatherModels.add(mode2);
        weatherModels.add(mode3);
        weatherModels.add(mode4);
        weatherModels.add(mode5);
        weatherModels.add(mode6);
        weatherModels.add(mode7);
        weatherModels.add(mode2);
        return weatherModels;
    }

    public static void setDataWeather(WeatherModel model) {
        model.setDate("12/07");//日期
        model.setWeek("昨天");  //星期
        model.setDayWeather("大雪"); //白天天气
        model.setNightTemp(5); //夜晚温度
        model.setNightWeather("晴"); //夜晚天气
        model.setWindOrientation("西南风"); //风向
        model.setWindLevel("3级"); //风级
        model.setAirLevel(AirLevel.EXCELLENT); //空气质量
        model.setDayPic(R.mipmap.ic_launcher); //白天天气图标(默认提供常用天气图标)
        model.setNightPic(R.mipmap.ic_launcher); //晚上天气图标(默认提供常用天气图标)
    }

    public static List<DefultGridViewBean> getHotCicy() {
        DefultGridViewBean defultGridViewBean;
        ArrayList<DefultGridViewBean> defultGridViewBeans = new ArrayList<>();
        defultGridViewBean = new DefultGridViewBean("北京");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("上海");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("广州");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("深圳");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("杭州");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("南京");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("成都");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("重庆");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("武汉");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("东莞");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("苏州");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("郑州");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("西安");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("青岛");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("大理");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("哈尔滨");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("丽江");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("天津");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("沈阳");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("三亚");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("台北");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("香港");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("澳门");
        defultGridViewBeans.add(defultGridViewBean);
        return defultGridViewBeans;

    }

    public static List<DefultGridViewBean> getUserAdapter() {
        DefultGridViewBean defultGridViewBean ;
        ArrayList<DefultGridViewBean> defultGridViewBeans = new ArrayList<>();
        defultGridViewBean = new DefultGridViewBean("消息中心",R.mipmap.userinfo_xiaoxi);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("热点新闻",R.mipmap.userinfo_redianxinewn);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("星座运势",R.mipmap.userinfo_xingzuo);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("主题换肤",R.mipmap.userinfo_zhuti);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("意见反馈",R.mipmap.userinfo_yijian);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("万年历",R.mipmap.userinfo_wannianli);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("历史上的今天",R.mipmap.userinfo_lishijintian);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("抖乐笑话",R.mipmap.userinfo_xiaohua);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("周公解梦",R.mipmap.userinfo_zhougong);
        defultGridViewBeans.add(defultGridViewBean);
        return defultGridViewBeans;
    }

    public static List<DefultGridViewBean> getUserAdapter1() {
        DefultGridViewBean defultGridViewBean ;
        ArrayList<DefultGridViewBean> defultGridViewBeans = new ArrayList<>();
        defultGridViewBean = new DefultGridViewBean("新车试驾");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("生活服务");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("特价机票");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("酒店折扣");
        defultGridViewBeans.add(defultGridViewBean);
        return defultGridViewBeans;
    }


    public static List<SettingBean> getSettingData() {
        SettingBean settingBean2 ;
        ArrayList<SettingBean> settingBeans = new ArrayList<>();
        SettingBean settingBean1 = new SettingBean(1);
        settingBeans.add(settingBean1);
         settingBean2 = new SettingBean(2,"账号管理");
        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"隐私政策");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"去除广告");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"关闭头条新闻");
//        settingBeans.add(settingBean2);
//        settingBeans.add(settingBean1);
//        settingBean2 = new SettingBean(2,"推送城市");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"天气早晚提醒");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"预警提醒");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"变天提醒");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"预警提醒");
//        settingBeans.add(settingBean2);
        settingBeans.add(settingBean1);
        settingBean2 = new SettingBean(2,"注册协议","","http://www.512kx.top/?act=zyPrivacy");
        settingBeans.add(settingBean2);
        settingBeans.add(new SettingBean(2, "隐私协议", "","http://www.512kx.top/?act=dyPrivacy"));
        settingBean2 = new SettingBean(2,"关于我们","","http://www.512kx.top/?act=about");
        settingBeans.add(settingBean2);
//        settingBeans.add(settingBean1);
//        settingBean2 = new SettingBean(2,"主题广场");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"随机主题");
//        settingBeans.add(settingBean2);
//        settingBeans.add(settingBean1);
//        settingBean2 = new SettingBean(2,"清理缓存");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"版本检测");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"意见反馈");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"加入QQ群");
//        settingBeans.add(settingBean2);
//        settingBean2 = new SettingBean(2,"关注微信公众号");
//        settingBeans.add(settingBean2);
        settingBean2 = new SettingBean(2,"官网" ,"http://www.512kx.top/","http://www.512kx.top/");
        settingBeans.add(settingBean2);
//        settingBeans.add(settingBean1);
        return settingBeans;
    }

    public static List<DefultGridViewBean> getZhutiImageAdapter() {
        DefultGridViewBean defultGridViewBean ;
        ArrayList<DefultGridViewBean> defultGridViewBeans = new ArrayList<>();
        defultGridViewBean = new DefultGridViewBean("默认主题",R.mipmap.main_bg);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("黑白",R.mipmap.main_bg1);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("品质",R.mipmap.main_bg2);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("色彩",R.mipmap.main_bg3);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("秋",R.mipmap.main_bg4);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("日落",R.mipmap.main_bg5);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("简约",R.mipmap.main_bg6);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("星空",R.mipmap.main_bg7);
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("夏季",R.mipmap.main_bg8);
        defultGridViewBeans.add(defultGridViewBean);


        return defultGridViewBeans;
    }

    public static List<String> getNewTitleList(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("头条");
        strings.add("社会");
        strings.add("国内");
        strings.add("国际");
        strings.add("娱乐");
        strings.add("体育");
        strings.add("军事");
        strings.add("科技");
        strings.add("财经");
        strings.add("时尚");
        return strings;
    }
    public static List<DefultGridViewBean> getZhougongDeat() {
        DefultGridViewBean defultGridViewBean ;
        ArrayList<DefultGridViewBean> defultGridViewBeans = new ArrayList<>();
        defultGridViewBean = new DefultGridViewBean("人");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("动物");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("山水");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("宗教");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("建筑");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("爱情");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("植物");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("疾病");
        defultGridViewBeans.add(defultGridViewBean);
        defultGridViewBean = new DefultGridViewBean("鬼神");
        defultGridViewBeans.add(defultGridViewBean);
        return defultGridViewBeans;
    }

}
