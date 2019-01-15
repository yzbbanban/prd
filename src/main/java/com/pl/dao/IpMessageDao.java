package com.pl.dao;

import com.pl.domain.dto.PageParamDTO;
import com.pl.domain.vo.IpMessageDTO;
import com.pl.domain.vo.IpMessageUpdateDTO;
import com.pl.domain.vo.IpMessageVO;

import java.util.List;

/**
 * Created by brander on 2019/1/15
 */
public interface IpMessageDao {

    /**
     * 获取ip 列表
     *
     * @param pageParamDTO 分页数据
     * @return ip 列表
     */
    List<IpMessageVO> listIpMessage(PageParamDTO pageParamDTO);

    /**
     * 获取数量
     *
     * @return 数量
     */
    int getCount();

    /**
     * 添加 ip
     *
     * @param ipMessageDTO ip信息
     * @return >0添加成功
     */
    int addIp(IpMessageDTO ipMessageDTO);


    /**
     * 更新 ip 信息
     *
     * @param ipMessageUpdateDTO ip 信息
     * @return >0成功
     */
    int updateIp(IpMessageUpdateDTO ipMessageUpdateDTO);

}
