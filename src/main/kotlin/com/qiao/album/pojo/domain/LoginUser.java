package com.qiao.album.pojo.domain;

import com.qiao.album.pojo.vo.User;

public class LoginUser {
    private Integer id;
    private String name;
    private Integer used;
    private String role;

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

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LoginUser() {
    }

    public LoginUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        this.used = user.getUsed();
    }


    public LoginUser(Integer id, String name, Integer used, String role) {
        this.id = id;
        this.name = name;
        this.used = used;
        this.role = role;
    }
}
