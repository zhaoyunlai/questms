package com.sdnu.iosclub.device.mapper;

import com.sdnu.iosclub.device.entity.DeviceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdnu.iosclub.device.entity.vo.DeviceInfoVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
public interface DeviceInfoMapper extends BaseMapper<DeviceInfo> {

    DeviceInfoVo getDeviceInfoVo(String id);
}
