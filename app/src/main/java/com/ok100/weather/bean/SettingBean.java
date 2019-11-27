package com.ok100.weather.bean;

/**
 * Created by Administrator on 2017/12/11.
 */

public class SettingBean {
    private int type;
    private String title;
    private String content;
    private String image;
    private String url;

    public String getUrl() {
        return url;
    }

    public SettingBean(int type, String title, String content, String url) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public SettingBean(int type, String title, String content) {
        this.type = type;
        this.title = title;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SettingBean(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SettingBean(int type) {
        this.type = type;
    }
}
