package com.sdnu.iosclub.serviceoss.service;

import com.sdnu.iosclub.serviceutil.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/8 15:59
 * @Version 1.0
 **/
public interface OssService {
    String uploadImage(MultipartFile file,String host);
}
