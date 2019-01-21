package com.pl.controller.frontend;

import com.pl.common.result.ResultJson;
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
@RequestMapping("v1/app/token")
@Api(tags = {"验证相关"})
public class AppTokenController extends BaseApi {

    @ApiOperation(value = "获取 app token")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResultJson<String> getAppToken(@RequestHeader("deviceId") String deviceId) {
        if (StringUtils.isBlank(deviceId)) {
            return ResultJson.createByErrorMsg("无设备信息");
        }
        return ResultJson.createBySuccess(getToken(deviceId));

    }

}