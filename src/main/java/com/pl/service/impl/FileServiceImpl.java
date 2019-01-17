package com.pl.service.impl;

import com.google.common.collect.Lists;
import com.pl.common.util.FTPUtil;
import com.pl.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 上传到 ftp 服务器
     *
     * @param file 文件
     * @param path 创建好的文件名
     * @return 路径
     */
    public String upload(MultipartFile file, String path) {
        //原文件名
        String fileName = file.getOriginalFilename();
        //源文件扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
        //上传的新文件名 不可以重复
        String uploadFileName = UUID.randomUUID().toString() + fileExtensionName;
        logger.info("文件名：{}，文件路径:{}，新文件名：{}，", fileName, path, uploadFileName);
        //创建文件
        File fileDir = new File(path);
        //创建目录 先存到 tomcat 中
        if (!fileDir.exists()) {
            fileDir.setWritable(true);//tomcat 写权限
            fileDir.mkdirs();
        }
        //创建路径
        File targetFile = new File(path, uploadFileName);

        try {
            //写文件到文件夹 （tomcat 文件夹下）
            file.transferTo(targetFile);

            //上传文件targetFile到 ftp 服务器

            boolean suc = FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            if (suc) {
                logger.info("成功");
                //删除 tomcat 文件夹下的文件，防止过大
                targetFile.delete();
                return targetFile.getName();
            }
            logger.info("失败");
        } catch (IOException e) {
            logger.info("上传文件异常", e);
            return null;
        }

        return null;
    }

}