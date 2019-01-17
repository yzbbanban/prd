package com.pl.controller.backend;

import com.pl.common.ResultJson;
import com.pl.common.util.PropertiesUtil;
import com.pl.service.IFileService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/manage/file")
@Api(tags = {"文件 manage资源api"})
public class FileController {

    @Autowired
    private IFileService iFileService;


    @PostMapping(value = "android/upload")
    public ResultJson uploadAndroid(@RequestParam(value = "uploadFile", required = false) MultipartFile file,
                                    HttpServletRequest request) {

        //上传保存图片
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        if (StringUtils.isBlank(targetFileName)) {
            return ResultJson.createByErrorMsg("上传失败");
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        Map<String, String> fileMap = new HashMap<>(2);
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ResultJson.createBySuccess(fileMap);

    }

    @PostMapping(value = "ios/upload")
    public ResultJson uploadIos(@RequestParam(value = "uploadFile", required = false) MultipartFile file,
                                HttpServletRequest request) {

        return ResultJson.createBySuccess();

    }

}
