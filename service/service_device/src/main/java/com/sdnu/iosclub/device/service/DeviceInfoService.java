package com.sdnu.iosclub.device.service;

import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdnu.iosclub.device.entity.vo.DeviceInfoVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
public interface DeviceInfoService extends IService<DeviceInfo> {

    DeviceInfoVo getDeviceInfoVo(String id);

    DeviceInfoVo getDeviceInfo(Map<String, String> params);
}
