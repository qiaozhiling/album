package com.qiao.album.pojo.vo;

import io.swagger.annotations.ApiModelProperty;

public class ImageVo {
    private String name;
    private String url;
    @ApiModelProperty("记录id，差不多就是图片id")
    private Integer iid; // imal id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public ImageVo() {
    }
}
