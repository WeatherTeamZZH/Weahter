package com.ok100.weather.bean;

import java.util.List;

public class WeatherTotal15Bean {

    /**
     * ret : 200
     * data : {"cityinfo":{"provinces":"浙江","city":"杭州","area":"杭州","id":"101210101","prov_py":"zhejiang","city_py":"hangzhou","qh":"0571","jb":"2","yb":"310000","area_py":"hangzhou","area_short_code":"hz","lng":"120.153576","lat":"30.287459"},"day15":[{"date":"20191126","week":"二","nongli":"十一月初一","day_air_weather":"多云","night_air_weather":"阴","day_air_temperature":"14","night_air_temperature":"9","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"东北风","night_wind_direction":"北风","sun_begin":"06:33","sun_end":"16:59","day_weather_pic":"/day/01.png","night_weather_pic":"/day/02.png"},{"date":"20191127","week":"三","nongli":"十一月初二","day_air_weather":"雨","night_air_weather":"雨","day_air_temperature":"17","night_air_temperature":"11","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"东北风","night_wind_direction":"北风","sun_begin":"06:34","sun_end":"16:58","day_weather_pic":"/day/301.png","night_weather_pic":"/day/301.png"},{"date":"20191128","week":"四","nongli":"十一月初三","day_air_weather":"阴","night_air_weather":"雨","day_air_temperature":"15","night_air_temperature":"9","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"北风","night_wind_direction":"北风","sun_begin":"06:35","sun_end":"16:58","day_weather_pic":"/day/02.png","night_weather_pic":"/day/301.png"},{"date":"20191129","week":"五","nongli":"十一月初四","day_air_weather":"雨","night_air_weather":"雨","day_air_temperature":"12","night_air_temperature":"8","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"东北风","night_wind_direction":"北风","sun_begin":"06:35","sun_end":"16:58","day_weather_pic":"/day/301.png","night_weather_pic":"/day/301.png"},{"date":"20191130","week":"六","nongli":"十一月初五","day_air_weather":"多云","night_air_weather":"雨","day_air_temperature":"11","night_air_temperature":"7","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"东北风","night_wind_direction":"北风","sun_begin":"06:36","sun_end":"16:58","day_weather_pic":"/day/01.png","night_weather_pic":"/day/301.png"},{"date":"20191201","week":"日","nongli":"十一月初六","day_air_weather":"多云","night_air_weather":"雨","day_air_temperature":"13","night_air_temperature":"7","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"西北风","night_wind_direction":"北风","sun_begin":"06:37","sun_end":"16:58","day_weather_pic":"/day/01.png","night_weather_pic":"/day/301.png"},{"date":"20191202","week":"一","nongli":"十一月初七","day_air_weather":"多云","night_air_weather":"雨","day_air_temperature":"11","night_air_temperature":"6","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"北风","night_wind_direction":"北风","sun_begin":"06:38","sun_end":"16:58","day_weather_pic":"/day/01.png","night_weather_pic":"/day/301.png"},{"date":"20191203","week":"二","nongli":"十一月初八","day_air_weather":"雨","night_air_weather":"雨","day_air_temperature":"10","night_air_temperature":"4","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"北风","night_wind_direction":"北风","sun_begin":"06:39","sun_end":"16:58","day_weather_pic":"/day/301.png","night_weather_pic":"/day/301.png"}]}
     * qt : 0.021
     */

    private int ret;
    private DataBean data;
    private double qt;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public double getQt() {
        return qt;
    }

    public void setQt(double qt) {
        this.qt = qt;
    }

    public static class DataBean {
        /**
         * cityinfo : {"provinces":"浙江","city":"杭州","area":"杭州","id":"101210101","prov_py":"zhejiang","city_py":"hangzhou","qh":"0571","jb":"2","yb":"310000","area_py":"hangzhou","area_short_code":"hz","lng":"120.153576","lat":"30.287459"}
         * day15 : [{"date":"20191126","week":"二","nongli":"十一月初一","day_air_weather":"多云","night_air_weather":"阴","day_air_temperature":"14","night_air_temperature":"9","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"东北风","night_wind_direction":"北风","sun_begin":"06:33","sun_end":"16:59","day_weather_pic":"/day/01.png","night_weather_pic":"/day/02.png"},{"date":"20191127","week":"三","nongli":"十一月初二","day_air_weather":"雨","night_air_weather":"雨","day_air_temperature":"17","night_air_temperature":"11","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"东北风","night_wind_direction":"北风","sun_begin":"06:34","sun_end":"16:58","day_weather_pic":"/day/301.png","night_weather_pic":"/day/301.png"},{"date":"20191128","week":"四","nongli":"十一月初三","day_air_weather":"阴","night_air_weather":"雨","day_air_temperature":"15","night_air_temperature":"9","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"北风","night_wind_direction":"北风","sun_begin":"06:35","sun_end":"16:58","day_weather_pic":"/day/02.png","night_weather_pic":"/day/301.png"},{"date":"20191129","week":"五","nongli":"十一月初四","day_air_weather":"雨","night_air_weather":"雨","day_air_temperature":"12","night_air_temperature":"8","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"东北风","night_wind_direction":"北风","sun_begin":"06:35","sun_end":"16:58","day_weather_pic":"/day/301.png","night_weather_pic":"/day/301.png"},{"date":"20191130","week":"六","nongli":"十一月初五","day_air_weather":"多云","night_air_weather":"雨","day_air_temperature":"11","night_air_temperature":"7","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"东北风","night_wind_direction":"北风","sun_begin":"06:36","sun_end":"16:58","day_weather_pic":"/day/01.png","night_weather_pic":"/day/301.png"},{"date":"20191201","week":"日","nongli":"十一月初六","day_air_weather":"多云","night_air_weather":"雨","day_air_temperature":"13","night_air_temperature":"7","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"西北风","night_wind_direction":"北风","sun_begin":"06:37","sun_end":"16:58","day_weather_pic":"/day/01.png","night_weather_pic":"/day/301.png"},{"date":"20191202","week":"一","nongli":"十一月初七","day_air_weather":"多云","night_air_weather":"雨","day_air_temperature":"11","night_air_temperature":"6","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"北风","night_wind_direction":"北风","sun_begin":"06:38","sun_end":"16:58","day_weather_pic":"/day/01.png","night_weather_pic":"/day/301.png"},{"date":"20191203","week":"二","nongli":"十一月初八","day_air_weather":"雨","night_air_weather":"雨","day_air_temperature":"10","night_air_temperature":"4","day_wind_power":"<3级","night_wind_power":"<3级","day_wind_direction":"北风","night_wind_direction":"北风","sun_begin":"06:39","sun_end":"16:58","day_weather_pic":"/day/301.png","night_weather_pic":"/day/301.png"}]
         */

        private CityinfoBean cityinfo;
        private List<Day15Bean> day15;

        public CityinfoBean getCityinfo() {
            return cityinfo;
        }

        public void setCityinfo(CityinfoBean cityinfo) {
            this.cityinfo = cityinfo;
        }

        public List<Day15Bean> getDay15() {
            return day15;
        }

        public void setDay15(List<Day15Bean> day15) {
            this.day15 = day15;
        }

        public static class CityinfoBean {
            /**
             * provinces : 浙江
             * city : 杭州
             * area : 杭州
             * id : 101210101
             * prov_py : zhejiang
             * city_py : hangzhou
             * qh : 0571
             * jb : 2
             * yb : 310000
             * area_py : hangzhou
             * area_short_code : hz
             * lng : 120.153576
             * lat : 30.287459
             */

            private String provinces;
            private String city;
            private String area;
            private String id;
            private String prov_py;
            private String city_py;
            private String qh;
            private String jb;
            private String yb;
            private String area_py;
            private String area_short_code;
            private String lng;
            private String lat;

            public String getProvinces() {
                return provinces;
            }

            public void setProvinces(String provinces) {
                this.provinces = provinces;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProv_py() {
                return prov_py;
            }

            public void setProv_py(String prov_py) {
                this.prov_py = prov_py;
            }

            public String getCity_py() {
                return city_py;
            }

            public void setCity_py(String city_py) {
                this.city_py = city_py;
            }

            public String getQh() {
                return qh;
            }

            public void setQh(String qh) {
                this.qh = qh;
            }

            public String getJb() {
                return jb;
            }

            public void setJb(String jb) {
                this.jb = jb;
            }

            public String getYb() {
                return yb;
            }

            public void setYb(String yb) {
                this.yb = yb;
            }

            public String getArea_py() {
                return area_py;
            }

            public void setArea_py(String area_py) {
                this.area_py = area_py;
            }

            public String getArea_short_code() {
                return area_short_code;
            }

            public void setArea_short_code(String area_short_code) {
                this.area_short_code = area_short_code;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }
        }

        public static class Day15Bean {
            /**
             * date : 20191126
             * week : 二
             * nongli : 十一月初一
             * day_air_weather : 多云
             * night_air_weather : 阴
             * day_air_temperature : 14
             * night_air_temperature : 9
             * day_wind_power : <3级
             * night_wind_power : <3级
             * day_wind_direction : 东北风
             * night_wind_direction : 北风
             * sun_begin : 06:33
             * sun_end : 16:59
             * day_weather_pic : /day/01.png
             * night_weather_pic : /day/02.png
             */

            private String date;
            private String week;
            private String nongli;
            private String day_air_weather;
            private String night_air_weather;
            private String day_air_temperature;
            private String night_air_temperature;
            private String day_wind_power;
            private String night_wind_power;
            private String day_wind_direction;
            private String night_wind_direction;
            private String sun_begin;
            private String sun_end;
            private String day_weather_pic;
            private String night_weather_pic;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getNongli() {
                return nongli;
            }

            public void setNongli(String nongli) {
                this.nongli = nongli;
            }

            public String getDay_air_weather() {
                return day_air_weather;
            }

            public void setDay_air_weather(String day_air_weather) {
                this.day_air_weather = day_air_weather;
            }

            public String getNight_air_weather() {
                return night_air_weather;
            }

            public void setNight_air_weather(String night_air_weather) {
                this.night_air_weather = night_air_weather;
            }

            public String getDay_air_temperature() {
                return day_air_temperature;
            }

            public void setDay_air_temperature(String day_air_temperature) {
                this.day_air_temperature = day_air_temperature;
            }

            public String getNight_air_temperature() {
                return night_air_temperature;
            }

            public void setNight_air_temperature(String night_air_temperature) {
                this.night_air_temperature = night_air_temperature;
            }

            public String getDay_wind_power() {
                return day_wind_power;
            }

            public void setDay_wind_power(String day_wind_power) {
                this.day_wind_power = day_wind_power;
            }

            public String getNight_wind_power() {
                return night_wind_power;
            }

            public void setNight_wind_power(String night_wind_power) {
                this.night_wind_power = night_wind_power;
            }

            public String getDay_wind_direction() {
                return day_wind_direction;
            }

            public void setDay_wind_direction(String day_wind_direction) {
                this.day_wind_direction = day_wind_direction;
            }

            public String getNight_wind_direction() {
                return night_wind_direction;
            }

            public void setNight_wind_direction(String night_wind_direction) {
                this.night_wind_direction = night_wind_direction;
            }

            public String getSun_begin() {
                return sun_begin;
            }

            public void setSun_begin(String sun_begin) {
                this.sun_begin = sun_begin;
            }

            public String getSun_end() {
                return sun_end;
            }

            public void setSun_end(String sun_end) {
                this.sun_end = sun_end;
            }

            public String getDay_weather_pic() {
                return day_weather_pic;
            }

            public void setDay_weather_pic(String day_weather_pic) {
                this.day_weather_pic = day_weather_pic;
            }

            public String getNight_weather_pic() {
                return night_weather_pic;
            }

            public void setNight_weather_pic(String night_weather_pic) {
                this.night_weather_pic = night_weather_pic;
            }
        }
    }
}
