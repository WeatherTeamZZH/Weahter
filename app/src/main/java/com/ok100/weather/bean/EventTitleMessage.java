package com.ok100.weather.bean;

/**
 * @Description: This is EventTitleMessage
 * @Author: QianDongDong
 * @Time: 2019/11/26 16:36
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
public class EventTitleMessage {
    public EventTitleMessage(boolean returnTitle) {
        this.returnTitle = returnTitle;
    }

    public EventTitleMessage(boolean returnTitle, String message) {
        this.returnTitle = returnTitle;
        this.message = message;
    }

    private boolean returnTitle;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public boolean isReturnTitle() {
        return returnTitle;
    }

    public void setReturnTitle(boolean returnTitle) {
        this.returnTitle = returnTitle;
    }
}
