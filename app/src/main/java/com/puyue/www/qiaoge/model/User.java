package com.puyue.www.qiaoge.model;

import java.io.Serializable;

/**
 * @Description
 * @Author SUN
 * @CreateDate 2018/11/16 11:40
 * @Mail Kaseeton@163.com
 */
public class User implements Serializable {
    private String name ;//用户昵称
    private String headPortrait;//用户头像
    private String token;//token
    private String id;//用户id
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User() {
        this.name = "";
        this.headPortrait = "";
        this.token = "";
        this.id = "";
        this.phone = "";
    }

    public User(String name, String headPortrait, String token, String id,String phone) {
        this.name = name;
        this.headPortrait = headPortrait;
        this.token = token;
        this.id = id;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
