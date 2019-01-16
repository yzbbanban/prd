package com.pl.service.impl;

import com.pl.dao.IpMessageDao;
import com.pl.dao.IpRecordDao;
import com.pl.domain.dto.IpRecordDTO;
import com.pl.domain.dto.IpRecordPageParamDTO;
import com.pl.domain.dto.IpRecordUpdateDTO;
import com.pl.domain.dto.PageParamDTO;
import com.pl.domain.vo.IpMessageDTO;
import com.pl.domain.vo.IpMessageUpdateDTO;
import com.pl.domain.vo.IpMessageVO;
import com.pl.domain.vo.IpRecordVO;
import com.pl.service.IIpMessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by brander on 2019/1/16
 */
@Service
public class IpMessageServiceImpl implements IIpMessageService {

    @Autowired
    private IpMessageDao ipMessageDao;

    @Autowired
    private IpRecordDao ipRecordDao;

    /**
     * 获取数量
     *
     * @return 数量
     */
    @Override
    public int getCount() {
        return ipMessageDao.getCount();
    }

    /**
     * 获取 ip 列表
     *
     * @param pageParamDTO 分页数据
     * @return 列表
     */
    @Override
    public List<IpMessageVO> getIpMessageList(PageParamDTO pageParamDTO) {
        return ipMessageDao.listIpMessage(pageParamDTO);
    }

    /**
     * 添加 ip
     *
     * @param messageDTO ip 信息
     * @return true 添加成功
     */
    @Override
    public boolean addIp(IpMessageDTO messageDTO) {
        return ipMessageDao.addIp(messageDTO) > 0;
    }

    /**
     * 更新 ip
     *
     * @param updateDTO 更新数据
     * @return true 成功
     */
    @Override
    public boolean updateIp(IpMessageUpdateDTO updateDTO) {
        return ipMessageDao.updateIp(updateDTO) > 0;
    }

    /**
     * 获取 ip
     *
     * @param deviceId 设备 id
     * @return ip
     */
    @Override
    public String getIp(String deviceId) {

        //查询 record 有没有此记录
        IpMessageVO ipMessageVO = ipRecordDao.getIpRecord(deviceId);
        if (ipMessageVO != null) {
            return ipMessageVO.getIp();
        }

        //获取合适的 ip count 为最小的 ip
        IpMessageVO ipVO = ipMessageDao.getMinCountIpInfo();
        if (ipVO == null) {
            return null;
        }

        IpRecordDTO ipRecordDTO = new IpRecordDTO();
        ipRecordDTO.setAddress(StringUtils.EMPTY);
        ipRecordDTO.setDeviceId(deviceId);
        ipRecordDTO.setIpId(ipVO.getId());
        ipRecordDTO.setRemark("");
        int row = ipRecordDao.addIpRecord(ipRecordDTO);
        if (row > 0) {
            return ipVO.getIp();
        }
        return null;
    }

    /**
     * 更新 ip 记录信息
     *
     * @param updateDTO 更新信息
     * @return true 成功
     */
    @Override
    public boolean updateIpRecord(IpRecordUpdateDTO updateDTO) {
        return ipRecordDao.updateIpRecord(updateDTO) > 0;
    }

    @Override
    public int getIpRecordCount(Integer id) {
        return ipRecordDao.getCount();
    }

    @Override
    public List<IpRecordVO> getIpRecordList(IpRecordPageParamDTO pageParamDTO) {
        return ipRecordDao.listIpRecordMessage(pageParamDTO);
    }
}
