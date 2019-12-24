package com.ok100.weather.bean;

/**
 * @Description: This is DefultBean
 * @Author: ZHANGZH
 * @Time: 2019/11/29 13:53
 * @Email: qq.com
 * @org: OK100
 */
public class TianqiyubaoBean {

    /**
     * code : 1
     * msg : success
     * data : {"appname":"天气预报新闻","appalias":"tianqiyubao","status":0,"add_time":1563343742,"update_time":1576820385,"url":"","jiami":0,"pendding":1}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appname : 天气预报新闻
         * appalias : tianqiyubao
         * status : 0
         * add_time : 1563343742
         * update_time : 1576820385
         * url :
         * jiami : 0
         * pendding : 1
         */

        private String appname;
        private String appalias;
        private int status;
        private int add_time;
        private int update_time;
        private String url;
        private int jiami;
        private int pendding;

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getAppalias() {
            return appalias;
        }

        public void setAppalias(String appalias) {
            this.appalias = appalias;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getJiami() {
            return jiami;
        }

        public void setJiami(int jiami) {
            this.jiami = jiami;
        }

        public int getPendding() {
            return pendding;
        }

        public void setPendding(int pendding) {
            this.pendding = pendding;
        }
    }
}
