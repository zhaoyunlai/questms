package com.sdnu.iosclub.device.controller.wx;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.device.client.UcenterClient;
import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.DeviceRecord;
import com.sdnu.iosclub.device.entity.vo.DeviceRecordFormVo;
import com.sdnu.iosclub.device.entity.vo.wx.DeviceInfoVo;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.device.service.DeviceRecordService;
import com.sdnu.iosclub.device.service.DeviceTypeService;
import com.sdnu.iosclub.serviceutil.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/7/26 17:20
 * @Version 1.0
 **/
@RestController
@RequestMapping("/device/wx/record")
public class WxDeviceRecordController {

    @Autowired
    private DeviceRecordService recordService;
    @Autowired
    private DeviceInfoService deviceInfoService;
    @Autowired
    private UcenterClient ucenterClient;
    @Autowired
    private DeviceTypeService typeService;

    @GetMapping("getBorrowList/{userId}")
    public R getBorrowList(
            @PathVariable String userId
    ) {
//        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
//        deviceRecordQueryWrapper.eq("user_id", userId);
//        deviceRecordQueryWrapper.eq("state", 1);
//        List<DeviceRecord> borrowList = recordService.list(deviceRecordQueryWrapper);
//        return R.ok().data("record", borrowList);
      // List<DeviceListVo> list =  recordService.getBorrowList(userId);
//        return R.ok().data("items",list);
        return R.ok();
    }

    @PostMapping("getDeviceInfo")
    public R getDeviceInfo(
            @RequestBody Map<String, String> params
    ) {
        String qrCode = params.get("qrCode");
        String deviceId = params.get("deviceId");
        if (StringUtils.isEmpty(qrCode) || StringUtils.isEmpty(deviceId)) {
            return R.error();
        }
        // ???????????????????????????
        DeviceInfoVo deviceInfoVo = new DeviceInfoVo();
        DeviceInfo deviceInfo = deviceInfoService.getById(deviceId);
        if (!deviceInfo.getQrCode().equals(qrCode)) {
            return R.error().message("?????????????????????????????????????????????");
        }
        // ??????????????????
        BeanUtils.copyProperties(deviceInfo, deviceInfoVo);
        deviceInfoVo.setLabName(ucenterClient.getLabInfo(deviceInfo.getLabId()).getName());
        deviceInfoVo.setTypeName(typeService.getById(deviceInfo.getType()).getName());
        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
        deviceRecordQueryWrapper.eq("device_id", deviceId);
        deviceRecordQueryWrapper.orderByAsc("start_time");
        // ?????????????????????????????????--???????????????????????????
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format(new Date());
        deviceRecordQueryWrapper.ne("state", 2);
        deviceRecordQueryWrapper.gt("end_time", currentDate);
        List<DeviceRecord> unFinish = recordService.list(deviceRecordQueryWrapper);
        deviceInfoVo.setUnFinish(unFinish);
        return R.ok().data("item", deviceInfoVo);
    }

    /**
     *
     * @param deviceRecordFormVo
     * deviceId ??????id
     * userId ??????id
     * purpose ??????
     * startTime ??????????????????
     * endTime ??????????????????
     * startImg ????????????
     * @return
     */
    @PostMapping("borrowDevice")
    public R borrowDevice(
            @RequestBody DeviceRecordFormVo deviceRecordFormVo
    ) {
        String deviceId = deviceRecordFormVo.getDeviceId();
        DeviceInfo deviceInfo = deviceInfoService.getById(deviceId);
        if (deviceInfo == null) {
            return R.error().message("?????????????????????");
        }
        if (deviceInfo.getDisabled() == 1) {
            return R.error().message("???????????????????????????");
        }
        DeviceRecord deviceRecord = new DeviceRecord();
        deviceRecord.setDeviceId(deviceId);
        if (StringUtils.isEmpty(deviceRecordFormVo.getDeviceId())) {
            return R.error().message("???????????????");
        }
        if (StringUtils.isEmpty(deviceRecordFormVo.getUserId())) {
            return R.error().message("??????????????????");
        }
        if (StringUtils.isEmpty(deviceRecordFormVo.getPurpose())) {
            return R.error().message("???????????????????????????");
        }
        if (deviceRecordFormVo.getStartTime() == null) {
            return R.error().message("???????????????????????????");
        }
        if (deviceRecordFormVo.getEndTime() == null) {
            return R.error().message("?????????????????????");
        }
//        if (StringUtils.isEmpty(deviceRecordFormVo.getStartImg())) {
//            return R.error().message("???????????????????????????");
//        }
        if (ucenterClient.getUserInfo(deviceRecordFormVo.getUserId()).getDisabled() == 1) {
            return R.error().message("??????????????????????????????");
        }
        BeanUtils.copyProperties(deviceRecordFormVo, deviceRecord);
        deviceRecord.setDisabled(0);  // ????????????
        // ?????????????????????????????????--???????????????????????????
        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
        deviceRecordQueryWrapper.eq("device_id", deviceId);
        deviceRecordQueryWrapper.orderByDesc("start_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        System.out.println("currentDate = " + currentDate);
        System.out.println("deviceRecordFormVo.getStartTime() = " + deviceRecordFormVo.getStartTime());
        String currentDateFormat = sdf.format(new Date());
        deviceRecordQueryWrapper.gt("end_time", currentDateFormat);
        deviceRecordQueryWrapper.ne("state", 2);
        deviceRecordQueryWrapper.eq("real_end_time", null);
        List<DeviceRecord> unFinishList = recordService.list(deviceRecordQueryWrapper);
        for (DeviceRecord record : unFinishList) {
            Date endTime = record.getEndTime();
            if (currentDate.getTime() > endTime.getTime()) {
                return R.error().message("?????????????????????");
            }
        }
        if (deviceRecordFormVo.getStartTime().getTime() >= currentDate.getTime()) {
            deviceRecord.setState(0);
        } else {
            deviceRecord.setState(1);
        }

        boolean flag = recordService.save(deviceRecord);
        return flag? R.ok(): R.error();
    }

    /**
     * ???????????????????????????????????????
     * @param params
     * userId
     * deviceId
     * @return
     */
    @PostMapping("getDeviceRecordInfo")
    public R getDeviceRecordInfo(
            @RequestBody Map<String, String> params
    ) {
        String userId = params.get("userId");
        String deviceId = params.get("deviceId");
        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
        deviceRecordQueryWrapper.eq("user_id", userId);
        deviceRecordQueryWrapper.eq("device_id", deviceId);
        deviceRecordQueryWrapper.eq("state", 1);
        DeviceRecord item = recordService.getOne(deviceRecordQueryWrapper);
        return R.ok().data("item", item);
    }

    @PostMapping("backDevice")
    public R backDevice(
            @RequestBody DeviceRecordFormVo deviceRecordFormVo
    ) {
        DeviceRecord deviceRecord = recordService.getById(deviceRecordFormVo.getId());
        if (StringUtils.isEmpty(deviceRecordFormVo.getDeviceId())) {
            return R.error().message("???????????????");
        }
        if (StringUtils.isEmpty(deviceRecordFormVo.getUserId())) {
            return R.error().message("??????????????????");
        }
        deviceRecord.setRealEndTime(new Date());
        deviceRecord.setDescription(deviceRecordFormVo.getDescription());
        deviceRecord.setState(2);
        deviceRecord.setEndImg(deviceRecordFormVo.getEndImg());
        recordService.updateById(deviceRecord);
        return R.ok();
    }

}
