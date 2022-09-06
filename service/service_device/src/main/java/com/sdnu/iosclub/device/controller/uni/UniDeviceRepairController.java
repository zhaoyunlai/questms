package com.sdnu.iosclub.device.controller.uni;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.sdnu.iosclub.device.entity.RepairRecord;
import com.sdnu.iosclub.device.service.DeviceInfoService;
import com.sdnu.iosclub.device.service.RepairRecordService;
import com.sdnu.iosclub.serviceutil.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zyl
 * @Date: 2021/10/04/12:04
 * @Description:
 */

@RestController
@RequestMapping("/device/uni/repairRecord")
public class UniDeviceRepairController {

    @Autowired
    private RepairRecordService repairRecordService;
    @Autowired
    private DeviceInfoService deviceInfoService;

    @ApiOperation("添加报修的设备")
    @PostMapping("addRepair")
    public R addRepair(@RequestBody RepairRecord repairRecord){

        //更改设备的状态
        String deviceId = repairRecord.getDeviceId();
        if(StringUtils.isEmpty(deviceId)){
            return R.error().message("设备不存在！");
        }
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setId(deviceId)
                .setState(2);
        deviceInfoService.updateById(deviceInfo);


        //报修表中添加记录
        boolean save = repairRecordService.save(repairRecord);

        return save?R.ok():R.error();
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

    @ApiOperation("结束修理，修改状态")
    @GetMapping("overRepair/{id}")
    public R overRepair(@PathVariable String id){
        RepairRecord repairRecord = repairRecordService.getById(id);
        if(repairRecord!=null){
            if(repairRecord.getState()==0){
                repairRecord.setState(1);
                boolean update = repairRecordService.updateById(repairRecord);

                return update?R.ok():R.error();
            }
        }

        return R.error().message("未查到对应的修理信息");
    }
}
