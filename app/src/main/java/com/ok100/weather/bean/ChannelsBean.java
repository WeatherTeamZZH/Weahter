package com.ok100.weather.bean;

import java.util.List;

/**
 * @Description: This is DefultBean
 * @Author: ZHANGZH
 * @Time: 2019/11/29 13:53
 * @Email: qq.com
 * @org: OK100
 */
public class ChannelsBean {

    /**
     * code : 200
     * message : sucess
     * status : 1
     * data : [{"catid":"57","catname":"旅游","parentid":"41"},{"catid":"56","catname":"国际","parentid":"40"}]
     * date : 2019-12-17 14:18:42
     */

    private String code;
    private String message;
    private int status;
    private String date;
    private List<DataBean> data;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * catid : 57
         * catname : 旅游
         * parentid : 41
         */

        private String catid;
        private String catname;
        private String parentid;

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }
    }
}
