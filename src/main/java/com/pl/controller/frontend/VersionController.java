package com.pl.controller.frontend;

import com.pl.common.ResultJson;
import com.pl.domain.vo.VersionRecordVO;
import com.pl.service.IVersionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brander on 2019/1/16
 */
@RestController
@RequestMapping("v1/app/version")
@Api(tags = {"app 版本资源api"})
public class VersionController {
    @Autowired
    private IVersionRecordService iVersionRecordService;

    @ApiOperation(value = "获取最近版本信息")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResultJson<VersionRecordVO> versionInfo(Integer type) {
        if (type == null || type <= 0) {
            return ResultJson.createByErrorMsg("参数错误");
        }

        VersionRecordVO vo = iVersionRecordService.getVersionByType(type);
        if (vo != null) {
            return ResultJson.createBySuccess(vo);
        }

        return ResultJson.createByError();
    }


}
