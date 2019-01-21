package com.pl.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by brander on 2019/1/15
 */
@ApiModel("更新 ip")
public class IpMessageUpdateDTO {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("ip")
    private String ip;
    @ApiModelProperty("领取数量")
    private Integer count;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("0：可不用，1：可用")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IpMessageUpdateDTO{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", count=" + count +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}
