package com.example.admin.housekeeper.bean;

/**
 * Created by admin on 2016/7/14.
 */
public class Live {
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
