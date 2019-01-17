package com.pl.controller.backend;

import com.pl.common.PageParamUtil;
import com.pl.common.ResultJson;
import com.pl.common.ResultList;
import com.pl.controller.BaseApi;
import com.pl.domain.dto.IpRecordPageParamDTO;
import com.pl.domain.dto.IpRecordUpdateDTO;
import com.pl.domain.dto.PageParamDTO;
import com.pl.domain.vo.IpMessageDTO;
import com.pl.domain.vo.IpMessageUpdateDTO;
import com.pl.domain.vo.IpMessageVO;
import com.pl.domain.vo.IpRecordVO;
import com.pl.service.IIpMessageService;
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
 * Created by brander on 2019/1/14
 */
@RestController
@RequestMapping("v1/manage/ip")
@Api(tags = {"ip manage资源api"})
public class IpManageController extends BaseApi {

    @Autowired
    private IIpMessageService ipMessageService;

    @ApiOperation(value = "查询 ip 列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultJson<ResultList<IpMessageVO>> getIpList(PageParamDTO pageParamDTO) {
        ResultList<IpMessageVO> resultList = new ResultList<>();

        int count = ipMessageService.getCount();
        if (count <= 0) {
            return ResultJson.createByErrorMsg("无数据");
        }

        List<IpMessageVO> ipMessageVOList = ipMessageService.getIpMessageList(PageParamUtil.setPageParam(pageParamDTO));
        if (CollectionUtils.isEmpty(ipMessageVOList)) {
            return ResultJson.createByErrorMsg("无数据");
        }
        resultList.setCount(count);
        resultList.setDataList(ipMessageVOList);

        return ResultJson.createBySuccess(resultList);
    }

    @ApiOperation(value = "添加 ip")
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ResultJson<String> addIp(IpMessageDTO messageDTO) {
        if (ObjectUtils.isEmpty(messageDTO)) {
            return ResultJson.createByErrorMsg("参数错误");
        }
        if (ipMessageService.addIp(messageDTO)) {
            return ResultJson.createBySuccess();
        }

        return ResultJson.createByError();
    }

    @ApiOperation(value = "更新 ip 信息")
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public ResultJson<String> updateIp(IpMessageUpdateDTO updateDTO) {
        if (updateDTO == null || updateDTO.getId() == null || updateDTO.getId() <= 0) {
            return ResultJson.createByErrorMsg("参数错误");
        }
        if (ipMessageService.updateIp(updateDTO)) {
            return ResultJson.createBySuccess();
        }

        return ResultJson.createBySuccess();
    }


    @ApiOperation(value = "更新 ip记录 信息")
    @RequestMapping(value = "/record/update", method = RequestMethod.GET)
    public ResultJson<String> updateIpRecord(IpRecordUpdateDTO updateDTO) {
        if (updateDTO == null || updateDTO.getId() == null || updateDTO.getId() <= 0) {
            return ResultJson.createByErrorMsg("参数错误");
        }
        if (ipMessageService.updateIpRecord(updateDTO)) {
            return ResultJson.createBySuccess();
        }

        return ResultJson.createBySuccess();
    }

    @ApiOperation(value = "根据 ip 的 id获取 ip 的领用信息")
    @RequestMapping(value = "/record/list", method = RequestMethod.GET)
    public ResultJson<ResultList<IpRecordVO>> listIpRecord(IpRecordPageParamDTO pageParamDTO) {
        ResultList<IpRecordVO> resultList = new ResultList<>();
        if (pageParamDTO == null
                || pageParamDTO.getId() == null
                || pageParamDTO.getId() <= 0) {
            return ResultJson.createByErrorMsg("参数错误");
        }

        int count = ipMessageService.getIpRecordCount(pageParamDTO.getId());
        if (count <= 0) {
            return ResultJson.createByErrorMsg("没有数据");
        }

        List<IpRecordVO> list = ipMessageService.getIpRecordList(pageParamDTO);
        if (CollectionUtils.isEmpty(list)) {
            return ResultJson.createByErrorMsg("没有数据");
        }
        resultList.setCount(count);
        resultList.setDataList(list);
        return ResultJson.createBySuccess(resultList);

    }

}
