package com.ok100.weather.event;

/**
 * @Description: This is EventTitleMessage
 * @Author: QianDongDong
 * @Time: 2019/11/26 16:36
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
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
