package com.pl.common.shiro;

import com.pl.common.security.JwtHelper;
import com.pl.controller.BaseApi;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt过滤器
 *
 * @author wangban
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {


    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     *
     * @update 调整所有接口全要鉴权、列外接口在ShiroConfig里面指定
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {

        return true;
    }

    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isBlank(authorization)) {
            throw new AuthenticationException();
        }
        JwtToken token = new JwtToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);

        //没有异常则将用户id写入request
        Claims claims = JwtHelper.parseJWT(authorization, BaseApi.JWT_SECRET);
        String userId = "userid";
        //如果id为空则抛出去
        if (claims.get(userId) == null) {
            throw new AuthenticationException();
        }
        String id = String.valueOf(claims.get(userId));
        request.setAttribute("userid", id);

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                response401(request, response);
            }
        }
        return true;
    }

    /**
     * 将非法请求跳转到 /401
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        try {
            httpServletResponse.sendRedirect("/401");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
