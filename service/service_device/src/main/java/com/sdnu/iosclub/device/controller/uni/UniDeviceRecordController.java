package com.sdnu.iosclub.device.controller.uni;


import com.sdnu.iosclub.device.entity.DeviceRecord;
import com.sdnu.iosclub.device.entity.vo.DeviceInfoVo;
import com.sdnu.iosclub.device.entity.vo.DeviceRecordFormVo;
import com.sdnu.iosclub.device.entity.vo.uni.DeviceListVo;
import com.sdnu.iosclub.device.entity.vo.uni.RecordListVo;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.device.service.DeviceRecordService;
import com.sdnu.iosclub.servicebase.exceptionhandler.CustomException;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: zyl
 * @Date: 2021/10/01/21:43
 * @Description:
 */
@RestController
@RequestMapping("/device/uni/record")
public class UniDeviceRecordController {

    @Autowired
    private DeviceRecordService recordService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @ApiOperation("根据用户id获取用户的借用记录")
    @GetMapping("getRecordList/{userId}")
    public R getRecordList(@PathVariable String userId){
        List<DeviceListVo> list=recordService.getRecordList(userId);

        return R.ok().data("items",list);
    }

    @ApiOperation("根据用户id获取当前用户正在借用的设备列表")
    @GetMapping("getBorrowList/{userId}")
    public R getBorrowList(
            @PathVariable String userId
    ) {
        List<DeviceListVo> list =  recordService.getBorrowList(userId);
        return R.ok().data("items",list);
    }


    @ApiOperation("得到设备的信息")
    @PostMapping("getDeviceInfo")
    public R getDeviceInfo(
            @RequestBody Map<String, String> params
    ){

        DeviceInfoVo deviceInfoVo = null;
        try {
            deviceInfoVo = deviceInfoService.getDeviceInfo(params);
        } catch (CustomException customException) {

            return R.error().message(customException.getMsg());
        }
        //deviceInfoVo中包含了当前设备是否允许借用，和当前设备的状态，
        //前端代码进行判断
        return R.ok().data("deviceInfoVo",deviceInfoVo);
    }

    @ApiOperation("现场借用/使用设备")
    @PostMapping("borrowDevice")
    public R borrowDevice(@RequestBody DeviceRecordFormVo deviceRecordFormVo){

        boolean success = false;
        try {
            success = recordService.borrowDevice(deviceRecordFormVo);
        } catch (CustomException e) {
            return R.error().message(e.getMsg());
        }

        return success?R.ok():R.error();
    }

    @ApiOperation("查询用户借用当前设备的状态")
    @PostMapping("getDeviceRecordInfo")
    public R getDeviceRecordInfo(@RequestBody Map<String, String> params){

        DeviceRecord deviceRecord = null;
        try {
            deviceRecord = recordService.getDeviceRecordInfo(params);
        } catch (CustomException e) {
            return R.error().message(e.getMsg());
        }

        return R.ok().data("deviceRecord", deviceRecord);
    }

    @ApiOperation("归还设备")
    @PostMapping("backDevice")
    public R backDevice(@RequestBody DeviceRecordFormVo deviceRecordFormVo){

        boolean success = false;
        try {
            success = recordService.backDevice(deviceRecordFormVo);
        } catch (CustomException e) {
            return R.error().message(e.getMsg());
        }

        return success?R.ok():R.error();
    }

    @ApiOperation("查询当前设备的借用状态和预约状态，不通过扫码，通过列表选择设备")
    @GetMapping("getDeviceRecordList/{deviceId}")
    public R getDeviceRecordList(@PathVariable String deviceId){

        List<DeviceRecord> records = recordService.getDeviceRecordList(deviceId);
        return R.ok().data("records",records);
    }


    @ApiOperation("预约")
    @PostMapping("order")
    public R orderDevice(@RequestBody DeviceRecordFormVo deviceRecordFormVo){

        boolean save= false;
        try {
            save = recordService.orderDevice(deviceRecordFormVo);
        } catch (CustomException e) {
            return R.error().message(e.getMsg());
        }

        return save?R.ok():R.error();
    }

    @ApiOperation("查询未完成的记录，并按照生效时间升序排序，并保留（一天内）已经完成的记录")
    @GetMapping("getMyRecordList/{userId}")
    public R getMyRecordList(@PathVariable String userId){
        List<RecordListVo> list = recordService.getMyRecordList(userId);
        //未签到
        List<RecordListVo> unSignList = new ArrayList<>();
        //进行中
        List<RecordListVo> onGoingList = new ArrayList<>();
        //已经完成，完成时间为一天内的数据
        List<RecordListVo> finishList = new ArrayList<>();

        for(RecordListVo recordListVo : list){
            if(recordListVo.getSign()==0){
                unSignList.add(recordListVo);
            }else if(recordListVo.getSign()==1&&recordListVo.getState()==1){
                onGoingList.add(recordListVo);
            }else {
                finishList.add(recordListVo);
            }
        }
        //使预约中的记录按照开始时间大小升序排列，其他记录默认按照开始时间大小降序排列

        Collections.reverse(unSignList);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("unSignList",unSignList);
        map.put("onGoingList",onGoingList);
        map.put("finishList",finishList);
        return R.ok().data(map);
    }

    @ApiOperation("现场签到")
    @PostMapping("sign")
        public R sign(@RequestBody Map<String, String> params){

        boolean success;
        try {
             success =  recordService.sign(params);
        } catch (CustomException e) {
            return R.error().message(e.getMsg());
        }

        return success?R.ok():R.error();
    }

    @ApiOperation("取消预约")
    @GetMapping("cancelOrder/{recordId}")
    public R cancelOrder(@PathVariable String recordId){

        boolean remove = recordService.removeById(recordId);

        return remove?R.ok():R.error().message("未查询到此条记录");
    }


}
