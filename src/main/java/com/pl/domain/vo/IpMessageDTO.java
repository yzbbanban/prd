package com.pl.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by brander on 2019/1/15
 */
@ApiModel("添加 ip")
public class IpMessageDTO {

    @ApiModelProperty("ip")
    private String ip;
    @ApiModelProperty("备注")
    private String remark;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "IpMessageDTO{" +
                "ip='" + ip + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
