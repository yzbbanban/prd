package com.pl.controller.backend;

import com.pl.common.PageParamUtil;
import com.pl.common.ResultJson;
import com.pl.common.ResultList;
import com.pl.domain.dto.PageParamDTO;
import com.pl.domain.dto.VersionRecordDTO;
import com.pl.domain.vo.VersionRecordVO;
import com.pl.service.IVersionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by brander on 2019/1/16
 */
@RestController
@RequestMapping("v1/manage/version")
@Api(tags = {" manage 版本资源api"})
public class VersionManageController {
    @Autowired
    private IVersionRecordService iVersionRecordService;


    @ApiOperation(value = "查询版本信息列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultJson<ResultList<VersionRecordVO>> getVersionList(PageParamDTO pageParamDTO) {
        ResultList<VersionRecordVO> resultList = new ResultList<>();

        int count = iVersionRecordService.getCount();
        if (count <= 0) {
            return ResultJson.createByErrorMsg("无数据");
        }

        List<VersionRecordVO> ipMessageVOList = iVersionRecordService.getVersionRecordList(PageParamUtil.setPageParam(pageParamDTO));
        if (CollectionUtils.isEmpty(ipMessageVOList)) {
            return ResultJson.createByErrorMsg("无数据");
        }
        resultList.setCount(count);
        resultList.setDataList(ipMessageVOList);

        return ResultJson.createBySuccess(resultList);
    }


    @ApiOperation(value = "添加 版本信息")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ResultJson<String> addVersion(VersionRecordDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            return ResultJson.createByErrorMsg("参数错误");
        }
        if (iVersionRecordService.addVersion(dto)) {
            return ResultJson.createBySuccess();
        }

        return ResultJson.createByError();
    }


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
