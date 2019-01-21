package com.pl.service.impl;

import com.pl.dao.VersionRecordDao;
import com.pl.domain.dto.PageParamDTO;
import com.pl.domain.dto.VersionRecordDTO;
import com.pl.domain.vo.VersionRecordVO;
import com.pl.service.IVersionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by brander on 2019/1/16
 */
@Service
public class VersionRecordServiceImpl implements IVersionRecordService {

    @Autowired
    private VersionRecordDao versionRecordDao;

    /**
     * 获取版本记录数量
     *
     * @return 数量
     */
    @Override
    public int getCount() {
        return versionRecordDao.getCount();
    }

    /**
     * 获取 版本 列表
     *
     * @param pageParamDTO 分页数据
     * @return 列表
     */
    @Override
    public List<VersionRecordVO> getVersionRecordList(PageParamDTO pageParamDTO) {
        return versionRecordDao.listVersionMessage(pageParamDTO);
    }

    /**
     * 添加 版本
     *
     * @param recordDTO 版本 信息
     * @return true 添加成功
     */
    @Override
    public boolean addVersion(VersionRecordDTO recordDTO) {
        return versionRecordDao.addVersion(recordDTO) > 0;
    }

    /**
     * 获取版本号
     *
     * @param type 类型
     * @return true 成功
     */
    @Override
    public VersionRecordVO getVersionByType(Integer type) {
        return versionRecordDao.getVersionByType(type);
    }
}
