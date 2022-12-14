package com.sdnu.iosclub.oss.controller;

import com.sdnu.iosclub.oss.service.OssService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author Zhao YunLai
 * @Date 2021/6/8 15:58
 * @Version 1.0
 **/
@RestController
@RequestMapping("/file")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("image")
    public String uploadImage(MultipartFile file,
                              @ApiParam(required = false) String host) {

        return ossService.uploadImage(file,host);
    }
}
