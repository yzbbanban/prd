package com.pl.controller;

import com.pl.common.constant.MessageKey;
import com.pl.common.cache.LocalCache;
import com.pl.common.result.ResultJson;
import com.pl.common.result.ResultStatus;
import com.pl.common.security.JwtConstant;
import com.pl.common.security.JwtHelper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.pl.common.constant.MessageConstant.SMS_REPEAT;

/**
 * Created by brander on 2019/1/15
 */
public class BaseApi {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected LocalCache localCache;

    /**
     * 获取 app token
     *
     * @param userId id
     * @return id
     */
    protected String getToken(String userId) {
        String token = JwtHelper.createJWT("userid", JwtConstant.JWT_SECRET, userId, 3600000L);
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
            Claims claims = JwtHelper.parseJWT(token, JwtConstant.JWT_SECRET);
            String userId = "userid";
            String id = String.valueOf(claims.get(userId));
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从token中获取userid
     *
     * @return id
     */
    protected String getPath() {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        try {
            return (String) request.getAttribute("path");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取验证码是否正确
     *
     * @param resultJson              接口返回值
     * @param systemSmsLoginCodePhone 标示
     * @param code                    验证码
     * @param mobile                  手机号
     * @return 是否正确
     */
    protected boolean getCode(ResultJson resultJson, String systemSmsLoginCodePhone, String code, String countryCode, String mobile) {
        String smsCode = localCache.getCache(systemSmsLoginCodePhone + countryCode + mobile);
        if (code.equals(smsCode)) {
            resultJson.setStatus(ResultStatus.OK);
            return false;
        }
        resultJson.setStatus(ResultStatus.ERROR);
        resultJson.setMessage(MessageKey.CODE_ERROR);
        return true;
    }


    /**
     * 是否重复发送
     *
     * @param resultJson 接口返回值
     * @param mobile     手机号
     * @return 是否正确
     */
    protected boolean isCodeRepeat(ResultJson resultJson, String mobile) {
        String smsCode = localCache.getCache(SMS_REPEAT + mobile);
        if (StringUtils.isBlank(smsCode)) {
            resultJson.setStatus(ResultStatus.OK);
            return false;
        }
        resultJson.setStatus(ResultStatus.ERROR);
        resultJson.setMessage(MessageKey.CODE_REPEAT);
        return true;
    }

}
