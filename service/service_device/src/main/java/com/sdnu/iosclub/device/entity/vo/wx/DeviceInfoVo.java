package com.sdnu.iosclub.device.entity.vo.wx;

import com.sdnu.iosclub.device.entity.DeviceRecord;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/7/28 16:48
 * @Version 1.0
 **/
@Data
public class DeviceInfoVo {

    String label;

    String type;

    String typeName;

    Integer disabled;

    Integer state;

    Integer maxUseTime;

    String labId;

    String labName;

    List<DeviceRecord> unFinish;
}
