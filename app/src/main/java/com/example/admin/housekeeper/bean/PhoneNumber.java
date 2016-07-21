package com.example.admin.housekeeper.bean;

/**
 * Created by admin on 2016/7/14.
 */
public class PhoneNumber {
    private String name;
    private String number;
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public PhoneNumber(String name, String number, String id) {

        this.name = name;
        this.number = number;
        this.id = id;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
