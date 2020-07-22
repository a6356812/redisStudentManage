package com.student.bean;

import java.util.Date;

public class Student {
    //主键
    private String id;
    //姓名
    private String name;
    //生日日期
    private Date birthday;
    //生日日期字符串
    private String birthdayString;
    //备注
    private String description;
    //平均成绩
    private Integer avgscore;
    //状态，0为正常，1为已删除
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Integer avgscore) {
        this.avgscore = avgscore;
    }

    public String getBirthdayString() {
        return birthdayString;
    }

    public void setBirthdayString(String birthdayString) {
        this.birthdayString = birthdayString;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
