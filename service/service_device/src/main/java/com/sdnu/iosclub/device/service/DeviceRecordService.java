package com.sdnu.iosclub.device.service;

import com.sdnu.iosclub.device.entity.DeviceRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.device.entity.vo.DeviceRecordFormVo;
import com.sdnu.iosclub.device.entity.vo.DeviceRecordVo;
import com.sdnu.iosclub.device.entity.vo.uni.DeviceListVo;
import com.sdnu.iosclub.device.entity.vo.uni.RecordListVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
public interface DeviceRecordService extends IService<DeviceRecord> {

    List<DeviceRecordVo> getPageRecord(Integer page, Integer limit);

    List<DeviceListVo> getBorrowList(String userId);

    //根据用户id和设备id查询记录信息
    DeviceRecord getDeviceRecordInfo(Map<String, String> params);

    boolean borrowDevice(DeviceRecordFormVo deviceRecordFormVo);

    boolean backDevice(DeviceRecordFormVo deviceRecordFormVo);

    List<DeviceRecord> getDeviceRecordList(String deviceId);

    boolean orderDevice(DeviceRecordFormVo deviceRecordFormVo);

    boolean sign(Map<String, String> params);

    List<DeviceListVo> getRecordList(String userId);

    List<RecordListVo> getMyRecordList(String userId);
}
