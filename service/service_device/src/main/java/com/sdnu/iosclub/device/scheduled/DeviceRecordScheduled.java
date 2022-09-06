package com.sdnu.iosclub.device.scheduled;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdnu.iosclub.device.entity.DeviceRecord;
import com.sdnu.iosclub.device.service.DeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/8/2 8:50
 * @Version 1.0
 **/
@Component
public class DeviceRecordScheduled {

    @Autowired
    private DeviceRecordService recordService;

//    @Scheduled(fixedDelay = 1000 * 30)
//    public void updateRecordState() {
//        System.out.println("updateRecordState");
//        // 预约生效
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateFormat = sdf.format(new Date());
//        QueryWrapper<DeviceRecord> deviceRecordQueryWrapper = new QueryWrapper<>();
//        deviceRecordQueryWrapper.eq("state", 0);
//        deviceRecordQueryWrapper.lt("start_time", currentDateFormat);
//        List<DeviceRecord> updateRecordList = recordService.list(deviceRecordQueryWrapper);
//        if (!updateRecordList.isEmpty()) {
//            updateRecordList.forEach(record -> {
//                record.setState(1);
//            });
//            recordService.updateBatchById(updateRecordList);
//        }
//    }

}
