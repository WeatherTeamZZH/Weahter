package com.ok100.weather.bean;

/**
 * @Description: This is DefultBean
 * @Author: ZHANGZH
 * @Time: 2019/11/29 13:53
 * @Email: qq.com
 * @org: OK100
 */
public class TokenRuturnBean {

    /**
     * code : 200
     * message : sucess
     * status : 1
     * data : {"token":"###15de17f23f38b925a525f4e8db36ba2a","apdid":"e17f23f38b925a"}
     * date : 2019-12-17 14:18:42
     */

    private String code;
    private String message;
    private int status;
    private DataBean data;
    private String date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class DataBean {
        /**
         * token : ###15de17f23f38b925a525f4e8db36ba2a
         * apdid : e17f23f38b925a
         */

        private String token;
        private String apdid;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getApdid() {
            return apdid;
        }

        public void setApdid(String apdid) {
            this.apdid = apdid;
        }
    }
}
