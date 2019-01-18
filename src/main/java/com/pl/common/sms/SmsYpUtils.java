package com.pl.common.sms;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import com.yunpian.sdk.model.Template;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;


/**
 * 云片短信
 *
 * @author wangban
 * @date 19:52 2018/8/3
 */
public class SmsYpUtils implements SmsConfig, ISmsUtils {

    private final Logger logger = Logger.getLogger(SmsYpUtils.class);

    /**
     * 用户修改支付密码
     */
    public static final String SMS_YP_USER_MODIFY_PAY_PWD = "2424582";
    /**
     * 用户修改登录密码
     */
    public static final String SMS_YP_USER_MODIFY_LOGIN_PWD = "2424586";
    /**
     * 用户设置支付密码
     */
    public static final String SMS_YP_USER_SET_PAY_PWD = "2424510";
    /**
     * 用户设置登录密码
     */
    public static final String SMS_YP_USER_SET_LOGIN_PWD = "2424516";
    /**
     * 用户正在登录
     */
    public static final String SMS_YP_USER_LOGIN = "2424568";
    /**
     * 用户正在注册
     */
    public static final String SMS_YP_USER_REGISTER = "2424570";
    /**
     * 用户找回支付密码
     */
    public static final String SMS_YP_USER_GET_PAY_PASSWORD = "2424518";
    /**
     * 用户找回登录密码
     */
    public static final String SMS_YP_USER_GET_LOGIN_PASSWORD = "2424522";

    /**
     * 用户异常登录 此为通知消息
     */
    public static final String SMS_YP_USER_ERROR_ADDRESS = "2424500";

    /**
     * 管理员正在登录
     */
    public static final String SMS_YP_SYS_LOGIN = "2424566";
    /**
     * 管理员修改登录密码
     */
    public static final String SMS_YP_SYS_MODIFY_LOGIN_PWD = "2424564";

    /**
     * 用户密码错误过多，异常提醒
     */
    public static final String SMS_YP_SYS_LOGIN_FAILED_WARN = "2523574";

    /**
     * 通用验证码
     */
    public static final String SMS_YP = "2423482";

    /**
     * 国际验证码
     */
    public static final String SMS_INT = "2556214";

    /**
     * 删除用户
     */
    public static final String SMS_YP_DELETE_USER = "2625490";

    /**
     * 异地登录提醒
     */
    public static final String SMS_YP_REMOTE_LOGIN = "2626028";

    /**
     * 编码格式。发送编码格式统一用UTF-8
     */
    private static String ENCODING = "UTF-8";

    /**
     * 国内国家代码
     */
    public static final String COUNTRY_SIDE = "86";

    /**
     * 发送短信
     *
     * @param phoneNumber 电话号码
     * @param countryCode 国家代码
     * @param message     信息
     * @param template    模板
     * @return 发送短信结果
     */
    @Override
    public boolean sendSms(String phoneNumber, String countryCode, String message, String template) {
        boolean result = false;
        try {
            if (COUNTRY_SIDE.equals(countryCode)) {
                //国内短信
                phoneNumber = "+" + countryCode + phoneNumber;
                result = sendCountryside(phoneNumber, message, template);
            } else {
                //国际短息
                phoneNumber = "+" + countryCode + phoneNumber;
                result = sendCountryside(phoneNumber, message, SMS_INT);

            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 国内短信
     *
     * @param phoneNumber 电话号码
     * @param message     信息
     * @param template    模板
     * @return 返回结果
     */
    private boolean sendCountryside(String phoneNumber, String message, String template) {
        YunpianClient clnt = new YunpianClient(API_KEY).init();
        //获取模板
        Map<String, String> params2 = clnt.newParam(1);
        //发送的模版id
        params2.put(YunpianClient.TPL_ID, template);
        Result<List<Template>> res = clnt.tpl().get(params2);
        //获取模板内容
        if (res == null) {
            return false;
        }
        if (res.getData() == null || res.getData().size() <= 0) {
            return false;
        }
        String temp = res.getData().get(0).getTpl_content();
        if (StringUtils.isBlank(temp)) {
            return false;
        }

        Map<String, String> params = clnt.newParam(3);
        params.put(YunpianClient.MOBILE, phoneNumber);
        //替换# #之间的数据
        String regex = "(\\#.*?\\#)";
        temp = temp.replaceAll(regex, message);
        temp = temp.replaceAll("#", "");

        params.put(YunpianClient.TEXT, temp);
        Result<SmsSingleSend> r = clnt.sms().single_send(params);
        if (r == null) {
            logger.error("sendSms 发送失败---->" + message + "--->" + phoneNumber + ":");
            return false;
        }
        if (r.getData() == null) {
            logger.error("sendSms 发送失败---->" + message + "--->" + phoneNumber + ":" + r.toString());
            return false;
        }
        if (r.getData().getCode() == 0) {
            return true;
        }
        logger.error("sendSms 发送失败---->" + message + "--->" + phoneNumber + ":" + r.toString());
        return false;
    }


}
