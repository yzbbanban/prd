package com.pl.domain.orm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("管理员白名单")
public class ManageUser {

    @ApiModelProperty("国家代码")
    private String countryCode;

    @ApiModelProperty("手机号")
    private String phoneNum;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "ManageUser{" +
                "countryCode='" + countryCode + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
