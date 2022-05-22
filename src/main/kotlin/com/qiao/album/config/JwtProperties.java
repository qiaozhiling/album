package com.qiao.album.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component("jwtProperties")
@ConfigurationProperties(prefix = "myjwt")
public class JwtProperties {
    private long expire = 10 * 60; // 时效 单位 s
    private String secret = "6sd84h684hgsdt43t4h";
    private String startWith = "";
    private String header = "Auth";
    private String redisKey = "";

    public JwtProperties(long expire, String secret, String startWith, String header, String redisKey) {
        this.expire = expire;
        this.secret = secret;
        this.startWith = startWith;
        this.header = header;
        this.redisKey = redisKey;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getStartWith() {
        return startWith;
    }

    public void setStartWith(String startWith) {
        this.startWith = startWith;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public JwtProperties() {
    }
}