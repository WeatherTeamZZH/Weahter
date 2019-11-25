package com.ok100.weather.bean;

/**
 * Created by Administrator on 2017/12/11.
 */

public class SuggestGridViewBean {
 private String name1 ;
 private String name2 ;
 private String name3 ;

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    private String imageUlr ;
 private int imageUlrRes ;
 private boolean isClick ;

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getImageUlr() {
        return imageUlr;
    }

    public void setImageUlr(String imageUlr) {
        this.imageUlr = imageUlr;
    }

    public int getImageUlrRes() {
        return imageUlrRes;
    }

    public void setImageUlrRes(int imageUlrRes) {
        this.imageUlrRes = imageUlrRes;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
