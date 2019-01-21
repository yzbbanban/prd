package com.pl.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by brander on 2017/12/24
 */
public interface IFileService {
    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 路径
     * @return 路径
     */
    String upload(MultipartFile file, String path);
}
