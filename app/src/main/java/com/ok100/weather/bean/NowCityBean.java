package com.ok100.weather.bean;

import java.util.List;

/**
 * @Description: This is DefultBean
 * @Author: ZHANGZH
 * @Time: 2019/11/29 13:53
 * @Email: qq.com
 * @org: OK100
 */
public class NowCityBean {

    /**
     * data : {"cities":[{"letter":"G","name":"广州","code":"020"}]}
     * status : 0
     * message : OK
     */

    private DataBean data;
    private int status;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<CitiesBean> cities;

        public List<CitiesBean> getCities() {
            return cities;
        }

        public void setCities(List<CitiesBean> cities) {
            this.cities = cities;
        }

        public static class CitiesBean {
            /**
             * letter : G
             * name : 广州
             * code : 020
             */

            private String letter;
            private String name;
            private String code;

            public String getLetter() {
                return letter;
            }

            public void setLetter(String letter) {
                this.letter = letter;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
