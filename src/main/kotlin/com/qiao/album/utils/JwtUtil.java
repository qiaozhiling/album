package com.qiao.album.utils;


import cn.hutool.extra.spring.SpringUtil;
import com.qiao.album.config.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private static final JwtProperties jwtProperties = SpringUtil.getBean("jwtProperties", JwtProperties.class);
    private static JwtBuilder jwtBuilder = null;
    private static JwtParser jwtParser = null;

    public static String generateToken(String username) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * jwtProperties.getExpire());
        if (jwtBuilder == null) {
            jwtBuilder = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret()); // 设置签名秘钥
        }
        return jwtBuilder.setId(UUID.randomUUID().toString()) // jwt唯一标识符
                .setSubject(username) // sub 面向的用户
                .setIssuedAt(nowDate) // iat 设置签发时间
                .setExpiration(expireDate)
                .compact(); // 过期
    }

    public static Claims getClaimsByToken(String jwt) {
        if (jwtParser == null) {
            jwtParser = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret());
        }
        return jwtParser.parseClaimsJws(jwt).getBody();
    }

    public static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
