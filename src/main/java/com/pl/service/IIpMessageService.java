package com.pl.service;

import com.pl.domain.dto.PageParamDTO;
import com.pl.domain.vo.IpMessageDTO;
import com.pl.domain.vo.IpMessageUpdateDTO;
import com.pl.domain.vo.IpMessageVO;

import java.util.List;

/**
 * Created by brander on 2019/1/16
 */
public interface IIpMessageService {

    /**
     * 获取数量
     *
     * @return 数量
     */
    int getCount();

    /**
     * 获取 ip 列表
     *
     * @param pageParamDTO 分页数据
     * @return 列表
     */
    List<IpMessageVO> getIpMessageList(PageParamDTO pageParamDTO);

    /**
     * 添加 ip
     *
     * @param messageDTO ip 信息
     * @return true 添加成功
     */
    boolean addIp(IpMessageDTO messageDTO);

    /**
     * 更新 ip
     *
     * @param updateDTO 更新数据
     * @return true 成功
     */
    boolean updateIp(IpMessageUpdateDTO updateDTO);
}
