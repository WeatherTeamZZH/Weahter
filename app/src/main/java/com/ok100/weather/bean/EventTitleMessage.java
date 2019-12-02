package com.ok100.weather.bean;

/**
 * @Description: This is EventTitleMessage
 * @Author: ZHANGZH
 * @Time: 2019/11/26 16:36
 * @Email: qq.com
 * @org: OK100
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
