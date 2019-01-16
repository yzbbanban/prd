package com.pl.controller;

import com.pl.common.security.JwtConstant;
import com.pl.common.security.JwtHelper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.pl.common.security.JwtConstant.MANAGE_JWT_SECRET;

/**
 * Created by brander on 2019/1/15
 */
public class BaseApi {

    public static final String JWT_SECRET = JwtConstant.JWT_SECRET;
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取 app token
     *
     * @param userId id
     * @return id
     */
    protected String getToken(String userId) {
        String token = JwtHelper.createJWT("userid", JWT_SECRET, userId, 3600000L);
        return token;
    }


    /**
     * 从token中获取userid
     *
     * @return id
     */
    protected String getDeviceIdFrom() {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        try {
            String token = request.getHeader("Authorization");
            Claims claims = JwtHelper.parseJWT(token, JWT_SECRET);
            String userId = "userid";
            String id = String.valueOf(claims.get(userId));
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
