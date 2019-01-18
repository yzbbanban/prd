package com.pl.controller.backend;

import com.pl.common.result.ResultJson;
import com.pl.common.sms.ISmsUtils;
import com.pl.common.sms.SmsYpUtils;
import com.pl.common.util.RandomUtils;
import com.pl.domain.dto.SmsMessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/manage/sms")
@Api(tags = {"短信 manage资源api"})
public class SmsController {

    @ApiOperation(value = "发送获取token短信")
    @PostMapping(value = "token")
    public ResultJson<String> getTokenSms() {
        SmsMessageDTO messageDTO=new SmsMessageDTO();
        ISmsUtils sms = new SmsYpUtils();
        int codeLength = 4;
        String code = RandomUtils.generateMixNum(codeLength);
        sms.sendSms(messageDTO.getPhoneNumber(), messageDTO.getCountryCode(), code, SmsYpUtils.SMS_YP);
        return ResultJson.createBySuccess();
    }

}
