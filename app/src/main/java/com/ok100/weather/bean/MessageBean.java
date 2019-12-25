package com.ok100.weather.bean;

import java.io.Serializable;

/**
 * @author: gh
 * @description:
 * @date: 2019-12-24.
 * @from:
 */
public class MessageBean implements Serializable {

    private String title;
    private String text;

    public MessageBean(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
