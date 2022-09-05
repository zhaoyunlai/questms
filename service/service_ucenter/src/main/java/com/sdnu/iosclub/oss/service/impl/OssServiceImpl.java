package com.sdnu.iosclub.oss.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.sdnu.iosclub.oss.service.OssService;
import com.sdnu.iosclub.oss.util.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {


    @Override
    public String uploadImage(MultipartFile file,String host) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        // 创建OSSClient实例。
        try {
            // 上传文件流。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            // 获取文件名称
            String fileName = file.getOriginalFilename();
//            System.out.println(fileName);

            // 在文件名称中添加随机唯一的一个值，让文件名称不同
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;

            //根据传递的文件路径命名文件
            if("avatar".equals(host)){
                fileName=host+"/"+fileName;
            }else if(!StringUtils.isEmpty(host)){
                String datePath = new DateTime().toString("yyyy/MM/dd");
                fileName=host+"/"+datePath+"/"+fileName;
            }else{
                fileName="temp/"+fileName;
            }

            // 第一个参数：bucket的名称
            // 第二个参数：上传到oss文件路径和文件名称
            // 第三个参数：上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            // 上传之后文件路径返回
            // https://sdnu-wcz-edu.oss-cn-beijing.aliyuncs.com/1.jpeg
            return "https://" + bucketName + "." + endpoint + "/" + fileName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
