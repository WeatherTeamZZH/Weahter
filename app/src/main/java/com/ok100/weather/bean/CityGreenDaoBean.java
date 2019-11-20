package com.ok100.weather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Description: This is CityGreenDaoBean
 * @Author: QianDongDong
 * @Time: 2019/11/19 18:08
 * @Email: qdd2977@sina.com
 * @org: www.vanlian.cn 万科链家（北京）装饰有限公司
 */
@Entity
public class CityGreenDaoBean {
    @Id(autoincrement = true)
    private Long Id;
    private String area;
    private String city;
    private String prov;
    private String jq;
    private String ip;
    private String from;
    private String lat;
    private String lng;
    @Generated(hash = 375294992)
    public CityGreenDaoBean(Long Id, String area, String city, String prov,
            String jq, String ip, String from, String lat, String lng) {
        this.Id = Id;
        this.area = area;
        this.city = city;
        this.prov = prov;
        this.jq = jq;
        this.ip = ip;
        this.from = from;
        this.lat = lat;
        this.lng = lng;
    }
    @Generated(hash = 476296921)
    public CityGreenDaoBean() {
    }

    public String getArea() {
        return this.area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProv() {
        return this.prov;
    }
    public void setProv(String prov) {
        this.prov = prov;
    }
    public String getJq() {
        return this.jq;
    }
    public void setJq(String jq) {
        this.jq = jq;
    }
    public String getIp() {
        return this.ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getFrom() {
        return this.from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getLat() {
        return this.lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLng() {
        return this.lng;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }


}
