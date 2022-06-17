package com.qiao.album.pojo.dto;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComResult<T> {
    private String code;
    private String msg;
    private T data;

    private static final String CODE_OK = "OK";
    private static final String CODE_BD = "BD";

    public ComResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ComResult<T> ok(String msg) {
        return ComResult.ok(msg, null);
    }

    public static <T> ComResult<T> ok(String msg, T data) {
        return new ComResult<>(CODE_OK, msg, data);
    }

    public static <T> ComResult<T> success(T data) {
        return ComResult.ok("success", data);
    }

    public static <T> ComResult<T> success() {
        return ComResult.success(null);
    }

    public static <T> ComResult<T> er(String msg, T data) {
        return new ComResult<>(CODE_BD, msg, data);
    }

    public static <T> ComResult<T> er(String msg) {
        return ComResult.er(msg, null);
    }

    public static <T> ComResult<T> fail() {
        return ComResult.er("fail");
    }

    public byte[] toByteArray() {
        return JSONUtil.toJsonStr(this).getBytes();
    }

    public String toJsonStr() {
        return JSONUtil.toJsonStr(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
