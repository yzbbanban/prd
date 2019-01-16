package com.pl.domain.vo;

import com.pl.domain.orm.IpRecord;
import io.swagger.annotations.ApiModel;

/**
 * Created by brander on 2019/1/16
 */
@ApiModel("ip记录 vo")
public class IpRecordVO extends IpRecord {
    @Override
    public String toString() {
        return "IpRecordVO{" + super.toString() + "}";
    }
}
