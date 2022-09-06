package com.sdnu.iosclub.device.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.vo.AddDeviceVo;
import com.sdnu.iosclub.device.entity.vo.DeviceInfoVo;
import com.sdnu.iosclub.device.entity.vo.DeviceQuery;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.device.service.DeviceTypeService;
import com.sdnu.iosclub.device.util.QrCodeUtil;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/device/info")
public class DeviceInfoController {

    @Autowired
    private DeviceInfoService deviceInfoService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private QrCodeUtil qrCodeUtil;

    @PostMapping("getPageDeviceInfo/{page}/{limit}")
    public R getPageDeviceInfo(
            @PathVariable Integer page,
            @PathVariable Integer limit,
            @RequestBody DeviceQuery deviceQuery
    ) {
        QueryWrapper<DeviceInfo> deviceInfoQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(deviceQuery.getType())) {
            deviceInfoQueryWrapper.eq("type", deviceQuery.getType());
        }
        deviceInfoQueryWrapper.select("id")
                .orderByDesc("update_time");

        Page<DeviceInfo> pageDeviceInfo = new Page<>(page, limit);
        deviceInfoService.page(pageDeviceInfo, deviceInfoQueryWrapper);
        List<DeviceInfo> deviceInfoList = pageDeviceInfo.getRecords();
        long total = pageDeviceInfo.getTotal();

        List<DeviceInfoVo> deviceInfoVoList = new ArrayList<>();
        for (DeviceInfo deviceInfo : deviceInfoList) {
            DeviceInfoVo deviceInfoVo = deviceInfoService.getDeviceInfoVo(deviceInfo.getId());
            deviceInfoVoList.add(deviceInfoVo);
        }
        return R.ok().data("records", deviceInfoVoList).data("total", total);
    }

    @GetMapping("getDeviceInfo/{id}")
    public R getDeviceInfo(
            @PathVariable String id
    ) {
        DeviceInfo deviceInfo = deviceInfoService.getById(id);
        if (deviceInfo == null) {
            return R.error().message("没有此设备");
        }
        return R.ok().data("item", deviceInfo);
    }

    @ApiOperation("添加一批同类型的设备")
    @PostMapping("addDeviceInfo")
    public R addDeviceInfo(
            @RequestBody AddDeviceVo addDeviceVo
    ) {
        List<DeviceInfo> deviceInfoList = new ArrayList<>();
        Integer total = addDeviceVo.getTotal();
        String deviceType = addDeviceVo.getType();
        Integer maxUseTime = addDeviceVo.getMaxUseTime();
        String labId = addDeviceVo.getLabId();
        QueryWrapper<DeviceInfo> deviceInfoQueryWrapper1 = new QueryWrapper<>();
        deviceInfoQueryWrapper1.eq("type", addDeviceVo.getType());
        int count = deviceInfoService.count(deviceInfoQueryWrapper1);
        String typeName = deviceTypeService.getById(addDeviceVo.getType()).getName();
        for (int i = 1; i <= total; i++) {
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setLabel(typeName+(count+i));
            deviceInfo.setMaxUseTime(maxUseTime);
            deviceInfo.setType(deviceType);
            deviceInfo.setLabId(labId);
            deviceInfo.setBatch(addDeviceVo.getBatch());
            deviceInfoList.add(deviceInfo);
        }
        boolean flag = deviceInfoService.saveBatch(deviceInfoList);
        return flag? R.ok(): R.error();
    }

    @PostMapping("updateDeviceInfo")
    public R updateDeviceInfo(
            @RequestBody DeviceInfo deviceInfo
    ) {
        DeviceInfo oldDeviceInfo = deviceInfoService.getById(deviceInfo.getId());
        if (oldDeviceInfo == null) {
            return R.error().message("设备不存在");
        }
        boolean flag = deviceInfoService.updateById(deviceInfo);
        return flag? R.ok(): R.error();
    }

    @DeleteMapping("deleteDeviceInfo/{deviceId}")
    public R deleteDeviceInfo(
            @PathVariable("deviceId") String deviceId
    ) {
        boolean flag = deviceInfoService.removeById(deviceId);
        return flag? R.ok(): R.error();
    }

    @ApiOperation("根据ID生成二维码")
    @GetMapping("genQrCode/{id}")
    public R genQrCode(
            @PathVariable("id") String id
    ) {
        String qrCodeTemp = deviceInfoService.getById(id).getQrCode();
        String qrCode = id + ":" + qrCodeTemp;
        String qrCodeByBase64 = qrCodeUtil.genQrCode(qrCode);
        return R.ok().data("qrCode", qrCodeByBase64);
    }
}

