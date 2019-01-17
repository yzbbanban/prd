package com.pl.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by brander on 2019/1/16
 */
@ApiModel("ip记录")
public class IpRecordPageParamDTO extends PageParamDTO {
    @ApiModelProperty("ip id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IpRecordPageParamDTO{" +
                "id=" + id +
                '}';
    }
}
