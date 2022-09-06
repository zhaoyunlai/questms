package com.sdnu.iosclub.device.client;

import com.sdnu.iosclub.device.client.impl.UcenterClientImpl;
import com.sdnu.iosclub.device.entity.vo.ucenter.UcenterLaboratory;
import com.sdnu.iosclub.device.entity.vo.ucenter.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/4 13:32
 * @Version 1.0
 **/
@Component
@FeignClient(value = "service-ucenter", fallback = UcenterClientImpl.class)  // 服务调用的名称
public interface UcenterClient {

    @GetMapping("/ucenter/user/userInfo/{id}")
    UserVo getUserInfo(@PathVariable("id") String id);

    @GetMapping("/ucenter/laboratory/getLabInfo/{id}")
    UcenterLaboratory getLabInfo(@PathVariable("id") String id);

}
