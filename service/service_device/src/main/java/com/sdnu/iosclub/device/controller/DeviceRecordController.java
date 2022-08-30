package com.sdnu.iosclub.device.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.DeviceRecord;
import com.sdnu.iosclub.device.entity.vo.DeviceRecordFormVo;
import com.sdnu.iosclub.device.entity.vo.DeviceRecordVo;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.device.service.DeviceRecordService;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("/device/record")
public class DeviceRecordController {

    @Autowired
    private DeviceRecordService deviceRecordService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @ApiOperation("根据用户id和设备id获取用户借用该设备的状态")
    @GetMapping("recordState/{userId}/{deviceId}")
    public R recordState(
            @PathVariable String userId,
            @PathVariable String deviceId
    ) {
        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
        deviceRecordQueryWrapper.eq("user_id", userId);
        deviceRecordQueryWrapper.eq("device_id", deviceId);
        deviceRecordQueryWrapper.orderByDesc("create_time");
        deviceRecordQueryWrapper.select("state");

        DeviceRecord record = deviceRecordService.getOne(deviceRecordQueryWrapper);
        if (record == null) {
            return R.ok().data("state", 2);
        }
        if (record.getState() == 1) {
            return R.ok().data("state", 1);
        } else if (record.getState() == 2) {
            return R.ok().data("state", 2);
        }
        return R.error();
    }

    // TODO 优化
    @ApiOperation("分页显示设备使用记录")
    @GetMapping("getPageRecord/{page}/{limit}")
    public R getPageRecord(
            @PathVariable("page") Integer page,
            @PathVariable("limit") Integer limit
    ) {
        List<DeviceRecordVo> deviceRecordVoList = deviceRecordService.getPageRecord(page, limit);
        return R.ok().data("records", deviceRecordVoList).data("total", deviceRecordVoList.size());
    }

    @ApiOperation("借用设备")
    @PostMapping("reserveDevice")
    public R reserveDevice(
            @RequestBody DeviceRecordFormVo deviceRecordFormVo
    ) {
        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
        deviceRecordQueryWrapper.eq("device_id", deviceRecordFormVo.getDeviceId());
        deviceRecordQueryWrapper.eq("user_id", deviceRecordFormVo.getUserId());
        //借用中的设备
        deviceRecordQueryWrapper.eq("state", 1);
        deviceRecordQueryWrapper.orderByDesc("create_time");
        DeviceRecord deviceRecord1 = deviceRecordService.getOne(deviceRecordQueryWrapper);
        if (deviceRecord1 != null) {
            return R.error().message("您已借用此设备，请勿重复提交");
        }
        String deviceId = deviceRecordFormVo.getDeviceId();
        DeviceRecord deviceRecord = new DeviceRecord();
//        String qrCode = deviceRecordFormVo.getQrCode();
//        QueryWrapper<DeviceInfo> deviceRecordQueryWrapper = new QueryWrapper<>();
//        deviceRecordQueryWrapper.eq("qr_code", qrCode);
//        String deviceId = deviceInfoService.getOne(deviceRecordQueryWrapper).getId();
//        deviceRecord.setDeviceId(deviceId);
        BeanUtils.copyProperties(deviceRecordFormVo, deviceRecord);
        if (StringUtils.isEmpty(deviceRecord.getDeviceId())) {
            return R.error().message("请选择设备");
        }
        if (StringUtils.isEmpty(deviceRecord.getUserId())) {
            return R.error().message("请选择借用者");
        }
        if (StringUtils.isEmpty(deviceRecord.getPurpose())) {
            return R.error().message("请填写您的借用目的");
        }
        if (deviceRecord.getStartTime() == null) {
            return R.error().message("请选择开始借用时间");
        }
        if (deviceRecord.getEndTime() == null) {
            return R.error().message("请选择归还时间");
        }
//        deviceRecord.setState(0);  // 设置预约中
//        deviceRecord.setDisabled(1);  // 设置未同意
        deviceRecord.setState(1);  // 设置借用中
        deviceRecord.setDisabled(0);  // 设置同意
        boolean flag = deviceRecordService.save(deviceRecord);
        return flag? R.ok(): R.error();
    }

    @ApiOperation("设置同意")
    @PostMapping("agreeBorrowRecord/{recordId}")
    public R handleBorrowRecord(
            @PathVariable("recordId") String recordId
    ) {
        DeviceRecord deviceRecord = deviceRecordService.getById(recordId);
        deviceRecord.setDisabled(0);  // 设置同意
        boolean flag = deviceRecordService.updateById(deviceRecord);
        return flag? R.ok(): R.error();
    }

    @ApiOperation("借用设备")
    @PostMapping("borrowDevice")
    public R borrowDevice(
            @RequestBody DeviceRecordFormVo deviceRecordFormVo
    ) {
        String deviceId = deviceRecordFormVo.getDeviceId();
        DeviceRecord deviceRecord = new DeviceRecord();
        deviceRecord.setDeviceId(deviceId);
        if (StringUtils.isEmpty(deviceRecordFormVo.getDeviceId())) {
            return R.error().message("请选择设备");
        }
        if (StringUtils.isEmpty(deviceRecordFormVo.getUserId())) {
            return R.error().message("请选择借用者");
        }
        if (StringUtils.isEmpty(deviceRecordFormVo.getPurpose())) {
            return R.error().message("请填写您的借用目的");
        }
        if (deviceRecordFormVo.getStartTime() == null) {
            return R.error().message("请选择开始借用时间");
        }
        if (deviceRecordFormVo.getEndTime() == null) {
            return R.error().message("请选择归还时间");
        }
        BeanUtils.copyProperties(deviceRecordFormVo, deviceRecord);
        deviceRecord.setDisabled(0);  // 设置同意
        String qrCode = deviceRecordFormVo.getQrCode();
//        String deviceId = deviceRecordFormVo.getDeviceId();
        String realQrCode = deviceInfoService.getById(deviceId).getQrCode();
        if (!realQrCode.equals(qrCode)) {
            return R.error().message("二维码过期，请联系管理员更换新的二维码标签");
        }
//        String userId = deviceRecordFormVo.getUserId();
//        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
//        deviceRecordQueryWrapper.eq("user_id", userId);
//        deviceRecordQueryWrapper.eq("device_id", deviceId);
//        deviceRecordQueryWrapper.orderByDesc("create_time");
//        DeviceRecord deviceRecord = deviceRecordService.getOne(deviceRecordQueryWrapper);
//        if (deviceRecord.getEndTime().compareTo(new Date()) >= 0) {
//            // deviceRecord.getEndTime() > new Date()
//            return R.error().message("当前时间已超过借用结束时间");
//        }
//        if (deviceRecord.getDisabled() != 0) {
//            return R.error().message("您未被允许借用此设备");
//        }
        if (StringUtils.isEmpty(deviceRecordFormVo.getStartImg())) {
            return R.error().message("请拍下您借用的设备");
        }
//        deviceRecord.setState(1);
//        boolean flag = deviceRecordService.updateById(deviceRecord);
        boolean flag = deviceRecordService.save(deviceRecord);
        return flag? R.ok(): R.error();
    }

    @ApiOperation("归还设备")
    @PostMapping("endBorrowDevice")
    public R endBorrowDevice(
            @RequestBody DeviceRecordFormVo deviceRecordFormVo
    ) {
        String deviceId = deviceRecordFormVo.getDeviceId();
        String userId = deviceRecordFormVo.getUserId();
        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
        deviceRecordQueryWrapper.eq("device_id", deviceId);
        deviceRecordQueryWrapper.eq("user_id", userId);
        deviceRecordQueryWrapper.eq("state", 1);
        deviceRecordQueryWrapper.orderByDesc("create_time");
        DeviceRecord deviceRecord = deviceRecordService.getOne(deviceRecordQueryWrapper);
        deviceRecord.setState(2);
        deviceRecord.setRealEndTime(new Date());
        deviceRecord.setEndImg(deviceRecordFormVo.getEndImg());
        deviceRecord.setDescription(deviceRecordFormVo.getDescription());
        boolean flag = deviceRecordService.updateById(deviceRecord);
        return flag? R.ok(): R.error();
    }

}

