package com.ok100.weather.http;

/**
 * Created by qiandd on 2017-2-18.
 */

public class Urls {

    public static String NewAppkey = "3d257c1f272086cf35fdbf818cfe2c44";
    public static String NewUrl = "http://v.juhe.cn/toutiao/index?"+"key="+NewAppkey;
    public static final String httpWeather  = "http://iweather.market.alicloudapi.com/address";
    public static final String httpWeatherGps  = "http://iweather.market.alicloudapi.com/gps";
    public static final String httpWeathers  = "https://iweather.market.alicloudapi.com/address";

    public static final String http = "http://";
    public static final String rapIp = http + "192.168.23.1";
    //获取通知列表
    public static final String noticeMainList = rapIp + "/announcement/getAnnouncementList";

    //获取短信
    public static final String sendSms = "http://www.asiaky.top/api/sendSms";
    //注册
    public static final String smslogin = "http://www.asiaky.top/api/smslogin";

}
