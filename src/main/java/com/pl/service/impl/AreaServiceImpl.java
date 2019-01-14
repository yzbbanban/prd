package com.pl.service.impl;

import com.pl.dao.AreaDao;
import com.pl.domain.orm.Area;
import com.pl.service.IAreaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by brander on 2018/3/1
 */
@Service
public class AreaServiceImpl implements IAreaService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AreaDao areaDao;


    @Override
    @CacheEvict(value = "area", key = "getAreaList()", allEntries = true)
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

    @Override
    @CacheEvict(value = "area", key = "getAreaById()", allEntries = true)
    public Area getAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addAreaById(Area area) {
        if (!StringUtils.isEmpty(area.getAreaName())) {
            try {
                int effectNum = areaDao.insertArea(area);
                if (effectNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("插入区域信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("插入区域信息失败!" + e.getMessage());
            }
        } else {
            throw new RuntimeException("区域信息不能为空");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modifyArea(Area area) {
        if (area.getAreaId() != null && area.getAreaId() > 0) {
            try {
                int effectNum = areaDao.updateArea(area);
                if (effectNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("更新区域信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新区域信息失败!" + e.getMessage());
            }

        } else {
            throw new RuntimeException("区域信息不能为空");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteAreaById(int areaId) {
        if (areaId > 0) {
            try {
                int effectNum = areaDao.deleteArea(areaId);
                if (effectNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除区域信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("删除区域信息失败!" + e.getMessage());
            }

        } else {
            throw new RuntimeException("区域id不能为空");
        }
    }


}
