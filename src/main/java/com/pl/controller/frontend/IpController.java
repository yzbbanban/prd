package com.pl.controller.frontend;

import com.pl.common.ResultJson;
import com.pl.controller.BaseApi;
import com.pl.service.IIpMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brander on 2019/1/14`
 */
@RestController
@RequestMapping("v1/app/ip")
@Api(tags = {"ip app资源api"})
public class IpController extends BaseApi {

    @Autowired
    private IIpMessageService ipMessageService;

    @ApiOperation(value = "根据设备 id 获取ip")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResultJson<String> getIpInfo(@RequestHeader("deviceId") String deviceId) {


        return null;
    }


}
