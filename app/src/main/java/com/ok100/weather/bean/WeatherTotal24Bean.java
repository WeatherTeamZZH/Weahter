package com.ok100.weather.bean;

import java.util.List;

public class WeatherTotal24Bean {

    /**
     * ret : 200
     * data : {"cityinfo":{"provinces":"浙江","city":"杭州","area":"杭州","id":"101210101","prov_py":"zhejiang","city_py":"hangzhou","qh":"0571","jb":"2","yb":"310000","area_py":"hangzhou","area_short_code":"hz","lng":"120.153576","lat":"30.287459"},"hour":[{"time":"201911191700","temperature":"11","weather":"多云","weather_code":"01","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911191800","temperature":"10","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911191900","temperature":"9","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911192000","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911192100","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911192200","temperature":"8","weather":"多云","weather_code":"01","wind_power":"<3级","wind_direction":"北风"},{"time":"201911192300","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"微风"},{"time":"201911200000","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911200100","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200200","temperature":"7","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"微风"},{"time":"201911200300","temperature":"7","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200400","temperature":"7","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200500","temperature":"7","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"微风"},{"time":"201911200600","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200700","temperature":"9","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200800","temperature":"9","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"微风"},{"time":"201911200900","temperature":"11","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东北风"},{"time":"201911201000","temperature":"12","weather":"多云","weather_code":"01","wind_power":"3-4级","wind_direction":"东北风"},{"time":"201911201100","temperature":"14","weather":"多云","weather_code":"01","wind_power":"3-4级","wind_direction":"东风"},{"time":"201911201200","temperature":"14","weather":"多云","weather_code":"01","wind_power":"3-4级","wind_direction":"东风"},{"time":"201911201300","temperature":"15","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东风"},{"time":"201911201400","temperature":"16","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东风"},{"time":"201911201500","temperature":"15","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东风"},{"time":"201911201600","temperature":"15","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东风"},{"time":"201911201700","temperature":"15","weather":"多云","weather_code":"01","wind_power":"3-4级","wind_direction":"东风"}]}
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
         * hour : [{"time":"201911191700","temperature":"11","weather":"多云","weather_code":"01","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911191800","temperature":"10","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911191900","temperature":"9","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911192000","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911192100","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911192200","temperature":"8","weather":"多云","weather_code":"01","wind_power":"<3级","wind_direction":"北风"},{"time":"201911192300","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"微风"},{"time":"201911200000","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"东北风"},{"time":"201911200100","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200200","temperature":"7","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"微风"},{"time":"201911200300","temperature":"7","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200400","temperature":"7","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200500","temperature":"7","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"微风"},{"time":"201911200600","temperature":"8","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200700","temperature":"9","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"北风"},{"time":"201911200800","temperature":"9","weather":"晴","weather_code":"00","wind_power":"<3级","wind_direction":"微风"},{"time":"201911200900","temperature":"11","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东北风"},{"time":"201911201000","temperature":"12","weather":"多云","weather_code":"01","wind_power":"3-4级","wind_direction":"东北风"},{"time":"201911201100","temperature":"14","weather":"多云","weather_code":"01","wind_power":"3-4级","wind_direction":"东风"},{"time":"201911201200","temperature":"14","weather":"多云","weather_code":"01","wind_power":"3-4级","wind_direction":"东风"},{"time":"201911201300","temperature":"15","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东风"},{"time":"201911201400","temperature":"16","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东风"},{"time":"201911201500","temperature":"15","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东风"},{"time":"201911201600","temperature":"15","weather":"多云","weather_code":"01","wind_power":"4-5级","wind_direction":"东风"},{"time":"201911201700","temperature":"15","weather":"多云","weather_code":"01","wind_power":"3-4级","wind_direction":"东风"}]
         */

        private CityinfoBean cityinfo;
        private List<HourBean> hour;

        public CityinfoBean getCityinfo() {
            return cityinfo;
        }

        public void setCityinfo(CityinfoBean cityinfo) {
            this.cityinfo = cityinfo;
        }

        public List<HourBean> getHour() {
            return hour;
        }

        public void setHour(List<HourBean> hour) {
            this.hour = hour;
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

        public static class HourBean {
            /**
             * time : 201911191700
             * temperature : 11
             * weather : 多云
             * weather_code : 01
             * wind_power : <3级
             * wind_direction : 东北风
             */

            private String time;
            private String temperature;
            private String weather;
            private String weather_code;
            private String wind_power;
            private String wind_direction;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getWeather_code() {
                return weather_code;
            }

            public void setWeather_code(String weather_code) {
                this.weather_code = weather_code;
            }

            public String getWind_power() {
                return wind_power;
            }

            public void setWind_power(String wind_power) {
                this.wind_power = wind_power;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }
        }
    }
}
