package com.sdnu.iosclub.device.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.device.DeviceApplication;
import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.DeviceType;
import com.sdnu.iosclub.device.entity.vo.DeviceTypeVo;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.device.service.DeviceTypeService;
import com.sdnu.iosclub.serviceutil.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/device/type")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private DeviceInfoService deviceInfoService;

    @GetMapping("getAllDeviceType")
    public R getAllDeviceType() {
        List<DeviceType> deviceTypeList = deviceTypeService.list(null);
        List<DeviceTypeVo> deviceTypeVoList = new ArrayList<>();
        for (DeviceType deviceType : deviceTypeList) {
            DeviceTypeVo deviceTypeVo = new DeviceTypeVo();
            BeanUtils.copyProperties(deviceType, deviceTypeVo);
            QueryWrapper<DeviceInfo> deviceInfoQueryWrapper = new QueryWrapper<>();
            deviceInfoQueryWrapper.eq("type", deviceType.getId());
            int total = deviceInfoService.count(deviceInfoQueryWrapper);
            deviceTypeVo.setTotal(total);
            deviceTypeVoList.add(deviceTypeVo);
        }
        return R.ok().data("items", deviceTypeVoList);
    }

    @PostMapping("addDeviceType")
    public R addDeviceType(
            @RequestBody DeviceType deviceType
    ) {
        boolean flag = deviceTypeService.save(deviceType);
        return flag? R.ok(): R.error();
    }

    @PostMapping("updateDeviceType")
    public R updateDeviceType(
            @RequestBody DeviceType deviceType
    ) {
        DeviceType oldDevice = deviceTypeService.getById(deviceType.getId());
        if (oldDevice == null) {
            return R.error().message("设备不存在");
        }
        boolean flag = deviceTypeService.updateById(deviceType);
        return flag? R.ok(): R.error();
    }

    @DeleteMapping("deleteDeviceType/{deviceId}")
    public R deleteDeviceType(
            @PathVariable("deviceId") String deviceId
    ) {
        boolean flag = deviceTypeService.removeById(deviceId);
        return flag? R.ok(): R.error();
    }

}

