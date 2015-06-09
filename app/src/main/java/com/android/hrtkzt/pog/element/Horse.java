package com.android.hrtkzt.pog.element;

import android.database.Cursor;

import com.android.hrtkzt.pog.config.POGconfig;

/**
 * Created by kazhirot on 6/9/15.
 */
public class Horse {

    private String isStable;
    private String name;
    private String sex;
    private String mother;
    private String father;
    private String motherFather;
    private String brother;
    private String owner;
    private String belong;
    private String trainer;
    private String producer;
    private String birthday;
    private String sale;
    private String price;
    private String mode;
    private String oneMode;
    private String club;
    private String update;
    private int id;

    public Horse(Cursor c) {
        this.isStable = c.getString(0);
        this.name = c.getString(1);
        this.sex = c.getString(2);
        this.mother = c.getString(3);
        this.father = c.getString(4);
        this.motherFather = c.getString(5);
        this.brother = c.getString(6);
        this.owner = c.getString(7);
        this.belong = c.getString(8);
        this.trainer = c.getString(9);
        this.producer = c.getString(10);
        this.birthday = c.getString(11);
        this.sale = c.getString(12);
        this.price = c.getString(13);
        this.mode = c.getString(14);
        this.oneMode = c.getString(15);
        this.club = c.getString(16);
        this.update = c.getString(17);
        this.id = c.getInt(18);
    }

    public String getIsStable() {
        return isStable;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getMother() {
        return mother;
    }

    public String getFather() {
        return father;
    }

    public String getMotherFather() {
        return motherFather;
    }

    public String getBrother() {
        return brother;
    }

    public String getOwner() {
        return owner;
    }

    public String getBelong() {
        return belong;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getProducer() {
        return producer;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSale() {
        return sale;
    }

    public String getPrice() {
        return price;
    }

    public String getMode() {
        return mode;
    }

    public String getOneMode() {
        return oneMode;
    }

    public String getClub() {
        return club;
    }

    public String getUpdate() {
        return update;
    }

    public int getId() {
        return id;
    }
}
