package com.pl.controller.backend;

import com.pl.common.result.ResultJson;
import com.pl.common.sms.ISmsUtils;
import com.pl.common.sms.SmsYpUtils;
import com.pl.common.util.RandomUtils;
import com.pl.controller.BaseApi;
import com.pl.domain.dto.SmsMessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.pl.common.constant.MessageConstant.SMS_REPEAT;
import static com.pl.common.constant.MessageConstant.SYSTEM_SMS_LOGIN_CODE_PHONE;

@RestController
@RequestMapping("v1/manage/sms")
@Api(tags = {"短信 manage资源api"})
public class SmsController extends BaseApi {

    @ApiOperation(value = "发送获取token短信")
    @PostMapping(value = "token")
    public ResultJson<String> getTokenSms(SmsMessageDTO messageDTO) {
        ISmsUtils sms = new SmsYpUtils();
        int codeLength = 4;
        ResultJson<String> resultJson = new ResultJson<>();
        String code = RandomUtils.generateMixNum(codeLength);

        if (isCodeRepeat(resultJson, messageDTO.getCountryCode() + messageDTO.getPhoneNumber())) {
            return resultJson;
        }

        boolean result = sms.sendSms(messageDTO.getPhoneNumber(), messageDTO.getCountryCode(), code, SmsYpUtils.SMS_YP);

        if (result) {
            localCache.setCache(SYSTEM_SMS_LOGIN_CODE_PHONE + messageDTO.getCountryCode() + messageDTO.getPhoneNumber(), code);
            localCache.setCache(SMS_REPEAT + messageDTO.getCountryCode() + messageDTO.getPhoneNumber(), code);
            return ResultJson.createBySuccess();
        }
        return ResultJson.createByError();
    }

}
