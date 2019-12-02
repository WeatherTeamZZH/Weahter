package com.ok100.weather.bean;

import com.ok100.weather.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import me.zhouzhuo.zzweatherview.AirLevel;
import me.zhouzhuo.zzweatherview.WeatherModel;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class AllCityGreenBean {
    @Id(autoincrement = true)
    private Long Id;
    private String AREAID;
    private String NAMECN;
    private String DISTRICTCN;
    private String PROVCN;
    private String NATIONCN;
    @Generated(hash = 2044809607)
    public AllCityGreenBean(Long Id, String AREAID, String NAMECN,
            String DISTRICTCN, String PROVCN, String NATIONCN) {
        this.Id = Id;
        this.AREAID = AREAID;
        this.NAMECN = NAMECN;
        this.DISTRICTCN = DISTRICTCN;
        this.PROVCN = PROVCN;
        this.NATIONCN = NATIONCN;
    }
    @Generated(hash = 571814462)
    public AllCityGreenBean() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getAREAID() {
        return this.AREAID;
    }
    public void setAREAID(String AREAID) {
        this.AREAID = AREAID;
    }
    public String getNAMECN() {
        return this.NAMECN;
    }
    public void setNAMECN(String NAMECN) {
        this.NAMECN = NAMECN;
    }
    public String getDISTRICTCN() {
        return this.DISTRICTCN;
    }
    public void setDISTRICTCN(String DISTRICTCN) {
        this.DISTRICTCN = DISTRICTCN;
    }
    public String getPROVCN() {
        return this.PROVCN;
    }
    public void setPROVCN(String PROVCN) {
        this.PROVCN = PROVCN;
    }
    public String getNATIONCN() {
        return this.NATIONCN;
    }
    public void setNATIONCN(String NATIONCN) {
        this.NATIONCN = NATIONCN;
    }
}
