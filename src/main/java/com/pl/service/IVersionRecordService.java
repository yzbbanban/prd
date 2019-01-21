package com.pl.service;

import com.pl.domain.dto.PageParamDTO;
import com.pl.domain.dto.VersionRecordDTO;
import com.pl.domain.vo.VersionRecordVO;

import java.util.List;

/**
 * Created by brander on 2019/1/16
 */
public interface IVersionRecordService {

    /**
     * 获取版本记录数量
     *
     * @return 数量
     */
    int getCount();

    /**
     * 获取 版本 列表
     *
     * @param pageParamDTO 分页数据
     * @return 列表
     */
    List<VersionRecordVO> getVersionRecordList(PageParamDTO pageParamDTO);

    /**
     * 添加 版本
     *
     * @param recordDTO 版本 信息
     * @return true 添加成功
     */
    boolean addVersion(VersionRecordDTO recordDTO);

    /**
     * 获取版本号
     *
     * @param type 类型
     * @return true 成功
     */
    VersionRecordVO getVersionByType(Integer type);

}
