package com.example.admin.housekeeper.bean;

/**
 * Created by admin on 2016/7/14.
 */
public class Live {
    //实体bean  用来封装  电话分类信息（本地电话  或 银行信息）
    private String name;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Live{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }

    public Live(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
