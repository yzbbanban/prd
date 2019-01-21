package com.pl.domain.vo;

import com.pl.domain.orm.IpMessage;
import io.swagger.annotations.ApiModel;

/**
 * Created by brander on 2019/1/15
 */
@ApiModel("ip信息视图")
public class IpMessageVO extends IpMessage {
    @Override
    public String toString() {
        return "IpMessageVO{" + super.toString() + "}";
    }
}
