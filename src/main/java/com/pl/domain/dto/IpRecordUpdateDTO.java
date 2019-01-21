package com.pl.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by brander on 2019/1/16
 */
@ApiModel("ip记录更新")
public class IpRecordUpdateDTO {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("设备 id")
    private String deviceId;
    @ApiModelProperty("ip id")
    private Integer ipId;
    @ApiModelProperty("地址信息")
    private String address;
    @ApiModelProperty("备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getIpId() {
        return ipId;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "IpRecordUpdateDTO{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", ipId='" + ipId + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
