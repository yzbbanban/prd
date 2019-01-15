package com.pl.dao;

import com.pl.domain.dto.PageParamDTO;
import com.pl.domain.dto.VersionRecordDTO;
import com.pl.domain.vo.VersionRecordVO;

import java.util.List;

/**
 * Created by brander on 2019/1/16
 */
public interface VersionRecordDao {

    /**
     * 获取版本列表
     *
     * @param pageParamDTO 分页
     * @return 列表
     */
    List<VersionRecordVO> listVersionMessage(PageParamDTO pageParamDTO);

    /**
     * 数量
     *
     * @return 数量
     */
    int getCount();

    /**
     * add版本
     *
     * @param recordDTO 记录
     * @return >0添加成功
     */
    int addVersion(VersionRecordDTO recordDTO);

    /**
     * 根据类型获取版本信息
     *
     * @param type 类型，1 android  2 ios
     * @return 版本信息
     */
    VersionRecordVO getVersionByType(Integer type);

}
