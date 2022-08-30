package com.sdnu.iosclub.serviceoss.controller;

import com.sdnu.iosclub.serviceoss.service.OssService;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author Wang Chen
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
