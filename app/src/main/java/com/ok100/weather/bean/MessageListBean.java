package com.ok100.weather.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author: gh
 * @description:
 * @date: 2019-12-24.
 * @from:
 */
public class MessageListBean implements Serializable {

    private ArrayList<MessageBean> list;

    public MessageListBean() {
        list = new ArrayList<>();
    }

    public ArrayList<MessageBean> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(ArrayList<MessageBean> list) {
        this.list = list;
    }
}
