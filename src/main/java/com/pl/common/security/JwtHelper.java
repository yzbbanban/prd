package com.pl.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 创建、验证token
 *
 * @author b
 */
public class JwtHelper {

    private static SecretKey generalKey(String secretKey) {
        try {
            byte[] encodedKey = Base64.encode(secretKey.getBytes("UTF-8")).getBytes("UTF-8");
            SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            return key;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Claims parseJWT(String jsonWebToken, String secretKey) {
        try {
            SecretKey ex = generalKey(secretKey);
            Claims claims = (Claims) Jwts.parser().setSigningKey(ex).parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    /**
     * 创建默认有效时间的token 1小时
     *
     * @param name   用户名
     * @param userId 用户id
     * @return 创建的token
     */
    public static String createJWT(String name, String secretKey, String userId) {
        return createJWT(name, secretKey, userId, null);
    }

    /**
     * 创建token
     *
     * @param name       用户名
     * @param userId     用户id
     * @param expireTime 有效时间 单位秒
     * @return 创建的token
     */
    public static String createJWT(String name, String secretKey, String userId, Long expireTime) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey signingKey = generalKey(secretKey);
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .claim("unique_name", name)
                .claim("userid", userId)
                .setIssuer(JwtConstant.JWT_NAME)
                .setAudience(JwtConstant.JWT_ID)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);

        if (expireTime != null) {
            Long eT = nowMillis + expireTime * 1000L;
            Date date = new Date(eT);
            //存活时间
            builder.setExpiration(date);
        } else {
            long expMillis = nowMillis + JwtConstant.JWT_REFRESH_TTL;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    /**
     * 创建token,查找好友使用
     *
     * @param name   用户名
     * @param userId 用户id
     * @return 创建的token
     */
    public static String createChartJWT(String name, String secretKey, String userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey signingKey = generalKey(secretKey);
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .claim("unique_name", name)
                .claim("userid", userId)
                .setIssuer(JwtConstant.JWT_NAME)
                .setAudience(JwtConstant.JWT_ID)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlxdWVfbmFtZSI6InVzZXJpZCIsInVzZXJpZCI6ImFlZnciLCJpc3MiOiJhcHAuYXBpLnBsLmNvbSIsImF1ZCI6IjA5OGY2YmNkNDYyMWQzNzNjYWRlNGU4MzI2MjdiNGY2IiwiaWF0IjoxNTQ3NTY5MTgxLCJleHAiOjE1NTExNjkxODF9.uXhoh0XqDR18sV-jLS6YSvBCiEieo_rYTfSCGEDzJp4";
        System.out.println(token);
        System.out.println(parseJWT(token, JwtConstant.JWT_SECRET));
    }

}

