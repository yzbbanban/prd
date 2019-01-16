package com.pl.controller.backend;

import com.pl.common.ResultJson;
import com.pl.controller.BaseApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brander on 2019/1/15
 */
@RestController
@RequestMapping("v1/manage/token")
@Api(tags = {"验证相关"})
public class TokenController extends BaseApi {

    @ApiOperation(value = "获取 manage token")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResultJson<String> getAppToken(@RequestHeader("ip") String ip) {
        if (StringUtils.isBlank(ip)) {
            return ResultJson.createByErrorMsg("无ip信息");
        }
        return ResultJson.createBySuccess(getToken(ip));

    }

}
