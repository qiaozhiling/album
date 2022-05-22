package com.qiao.album.pojo.vo;

import java.util.Date;


public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer used;
    private String email;
    private Date createTime;
    private String role;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(Integer id, String name, String password, Integer used, String email, Date createTime, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.used = used;
        this.email = email;
        this.createTime = createTime;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", used=" + used +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", role='" + role + '\'' +
                '}';
    }
}
