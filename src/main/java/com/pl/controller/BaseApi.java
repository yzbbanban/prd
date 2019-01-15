package com.pl.controller;

import com.pl.common.security.JwtConstant;
import com.pl.common.security.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
