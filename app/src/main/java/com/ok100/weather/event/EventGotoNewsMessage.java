package com.ok100.weather.event;

/**
 * @Description: This is EventTitleMessage
 * @Author: ZHANGZH
 * @Time: 2019/11/26 16:36
 * @Email: qq.com
 * @org: OK100
 */
public class EventGotoNewsMessage {
    public EventGotoNewsMessage(String title) {
        this.title = title;
    }

    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "EventGotoNewsMessage{" +
                "title='" + title + '\'' +
                '}';
    }
}
