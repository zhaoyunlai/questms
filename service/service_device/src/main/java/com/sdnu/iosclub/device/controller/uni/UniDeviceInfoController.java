package com.sdnu.iosclub.device.controller.uni;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.vo.DeviceInfoVo;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zyl
 * @Date: 2021/10/10/21:10
 * @Description: 查询设备
 */
@RestController
@RequestMapping("/device/uni/deviceInfo")
public class UniDeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;


    @ApiOperation("通过实验室id，查询设备状态信息（预约情况）")
    @GetMapping("getDeviceStateListByLabId/{labId}")
    public R getDeviceStateListByLabId(@PathVariable String labId){

        QueryWrapper<DeviceInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("lab_id",labId)
                .select("id");

        List<DeviceInfo> deviceInfoList = deviceInfoService.list(wrapper);
        for(DeviceInfo deviceInfo:deviceInfoList){
            DeviceInfoVo deviceInfoVo = deviceInfoService.getDeviceInfoVo(deviceInfo.getId());

        }


        return R.ok();
    }

}
