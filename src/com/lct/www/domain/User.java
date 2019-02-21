package com.lct.www.domain;

import java.util.Date;

/**
 * Created by Administrator on 2019/2/19 0019.
 * 用户实体
 */
public class User {
    private Integer uId;//用户id
    private String uName;//用户姓名
    private Date bithday;//用户数生日
    private Float price;//用户身价

    public Date getBithday() {
        return bithday;
    }

    public void setBithday(Date bithday) {
        this.bithday = bithday;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Override
    public String toString() {
        return "User{" +
                "bithday=" + bithday +
                ", uId=" + uId +
                ", uName='" + uName + '\'' +
                ", price=" + price +
                '}';
    }
}
