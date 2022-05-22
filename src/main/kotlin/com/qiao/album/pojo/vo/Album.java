package com.qiao.album.pojo.vo;

import java.util.Date;

public class Album {
    private Integer id;
    private String name;
    private String cover; // 封面图片
    private Integer owner;
    private String describe;
    private Date createTime;
    private Date modifiedTime;
    private Boolean isPrivate;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Album(String name, Integer owner, String describe, boolean isPrivate) {
        this.name = name;
        this.owner = owner;
        this.describe = describe;
        this.isPrivate = isPrivate;
    }

    public Album() {
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", owner=" + owner +
                ", describe='" + describe + '\'' +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
