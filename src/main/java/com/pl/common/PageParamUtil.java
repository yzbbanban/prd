package com.pl.common;

import com.pl.domain.dto.PageParamDTO;

/**
 * Created by brander on 2019/1/15
 */
public class PageParamUtil {

    public static <T extends PageParamDTO> PageParamDTO setPageParam(T pageParam) {
        if (pageParam == null) {
            try {
                pageParam = (T) PageParamDTO.class.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        int pageNo = pageParam.getPageNo();
        int pageSize = pageParam.getPageSize();

        if (pageNo == 0) {
            pageNo = 1;
        }

        int start = (pageNo - 1) * pageSize;
        pageParam.setPageNo(start);

        return pageParam;
    }
}
