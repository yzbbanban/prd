package com.pl.service.impl;

import com.pl.dao.IpMessageDao;
import com.pl.domain.dto.PageParamDTO;
import com.pl.domain.vo.IpMessageDTO;
import com.pl.domain.vo.IpMessageUpdateDTO;
import com.pl.domain.vo.IpMessageVO;
import com.pl.service.IIpMessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by brander on 2019/1/16
 */
@Service
public class IpMessageServiceImpl implements IIpMessageService {

    @Autowired
    private IpMessageDao ipMessageDao;

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
}
