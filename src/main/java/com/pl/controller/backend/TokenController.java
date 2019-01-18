package com.pl.controller.backend;

import com.pl.common.result.ResultJson;
import com.pl.controller.BaseApi;
import com.pl.domain.dto.SmsMessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by brander on 2019/1/15
 */
@RestController
@RequestMapping("v1/manage/token")
@Api(tags = {"验证相关"})
public class TokenController extends BaseApi {

    @ApiOperation(value = "获取 manage token")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResultJson<String> getAppToken(SmsMessageDTO messageDTO, String code, HttpSession session) {
        if (ObjectUtils.isEmpty(messageDTO)) {
            return ResultJson.createByErrorMsg("无领用人信息");
        }
        if (StringUtils.isEmpty(code)) {
            return ResultJson.createByErrorMsg("验证码错误");
        }
        String key = messageDTO.getCountryCode() + messageDTO.getPhoneNumber();
        String sessionCode = (String) session.getAttribute(key);
        if (code.equals(sessionCode)) {
            return ResultJson.createBySuccess(getToken(key));
        }
        return ResultJson.createByError();
    }

}
