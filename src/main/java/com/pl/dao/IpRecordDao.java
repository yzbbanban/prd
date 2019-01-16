package com.pl.dao;

import com.pl.domain.dto.IpRecordDTO;
import com.pl.domain.dto.IpRecordUpdateDTO;
import com.pl.domain.vo.IpMessageVO;
import com.pl.domain.vo.IpRecordVO;

import java.util.List;

/**
 * Created by brander on 2019/1/15
 */
public interface IpRecordDao {

    /**
     * 获取ip记录 列表
     *
     * @param ip ip
     * @return ip 列表
     */
    List<IpRecordVO> listIpRecordMessage(String ip);

    /**
     * 获取数量
     *
     * @return 数量
     */
    int getCount();

    /**
     * 添加 ip
     *
     * @param ipRecordDTO ip信息
     * @return >0添加成功
     */
    int addIpRecord(IpRecordDTO ipRecordDTO);


    /**
     * 更新 ip 信息
     *
     * @param ipRecordUpdateDTO ip 信息
     * @return >0成功
     */
    int updateIpRecord(IpRecordUpdateDTO ipRecordUpdateDTO);


    /**
     * 获取
     *
     * @param deviceId 设备 id
     * @return ip 信息
     */
    IpMessageVO getIpRecord(String deviceId);
}
