package com.sdnu.iosclub.device.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.RepairRecord;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.device.service.RepairRecordService;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-22
 */
@RestController
@RequestMapping("/device/repairRecord")
public class RepairRecordController {

    @Autowired
    private RepairRecordService repairRecordService;
    @Autowired
    private DeviceInfoService deviceInfoService;



    @ApiOperation("结束修理，修改状态")
    @GetMapping("overRepair/{id}")
    public R overRepair(@PathVariable String id){

        RepairRecord repairRecord = repairRecordService.getById(id);
        if(repairRecord!=null){
            if(repairRecord.getState()==0){

                //更改设备状态
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.setId(repairRecord.getDeviceId())
                        .setState(0);
                deviceInfoService.updateById(deviceInfo);

                //更改修理状态
                repairRecord.setState(1);
                boolean update = repairRecordService.updateById(repairRecord);

                return update?R.ok():R.error();
            }
        }

        return R.error().message("未查到对应的修理信息");
    }

    @ApiOperation("根据用户id查询其报修记录")
    @GetMapping("getRepairList/{userId}")
    public R getRepairList(@PathVariable String userId){

        QueryWrapper<RepairRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId)
                .orderByDesc("create_time");

        List<RepairRecord> list = repairRecordService.list(wrapper);
        return R.ok().data("records",list);
    }
}

