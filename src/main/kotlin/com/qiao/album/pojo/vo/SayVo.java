package com.qiao.album.pojo.vo;

import io.swagger.annotations.ApiModelProperty;

public class SayVo {

    private String message;

    @ApiModelProperty("评论人的id")
    private Integer userId;
    @ApiModelProperty("评论id")
    private Integer id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SayVo() {
    }
}
