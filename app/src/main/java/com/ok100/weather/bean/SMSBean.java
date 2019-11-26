package com.ok100.weather.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/11.
 */

public class SMSBean implements Serializable {

    private String code;
    private String message;
    private String status;
    private String date;

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
