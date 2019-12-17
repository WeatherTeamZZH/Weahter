package com.ok100.weather.bean;

/**
 * @Description: This is DefultBean
 * @Author: ZHANGZH
 * @Time: 2019/11/29 13:53
 * @Email: qq.com
 * @org: OK100
 */
public class GetMyTokenBean {

    /**
     * code : 200
     * message : success
     * status : 1
     * data : {"UcToken ":"1576120579510-b0daa2534f5a5c1bc08c899c5ddf4671-b0c986c095a84b0f51c04ba5a196c4b0"}
     * date : 2019-12-16 10:23:39
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
         * UcToken  : 1576120579510-b0daa2534f5a5c1bc08c899c5ddf4671-b0c986c095a84b0f51c04ba5a196c4b0
         */

        private String UcToken;

        public String getUcToken() {
            return UcToken;
        }

        public void setUcToken(String UcToken) {
            this.UcToken = UcToken;
        }
    }
}
