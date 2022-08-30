package com.sdnu.iosclub.device.mapper;

import com.sdnu.iosclub.device.entity.DeviceRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdnu.iosclub.device.entity.vo.uni.DeviceListVo;
import com.sdnu.iosclub.device.entity.vo.uni.RecordListVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
public interface DeviceRecordMapper extends BaseMapper<DeviceRecord> {

    List<DeviceListVo> getBorrowList(String userId);

    List<DeviceListVo> getRecordList(String userId);

    List<RecordListVo> getMyRecordList(String userId);
}
