package com.sdnu.iosclub.device.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/4 10:52
 * @Version 1.0
 **/
@Data
public class DeviceRecordVo {

    private String id;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    private String deviceLabel;

    @ApiModelProperty(value = "借用者ID")
    private String userId;

    private String userName;

    private String userNumber;

    private String userPhone;

    private String departmentName;

    @ApiModelProperty(value = "0: 预约中 1：借用中 2：已归还")
    private Integer state;

    @ApiModelProperty(value = "借用时间")
    private Date startTime;

    @ApiModelProperty(value = "理论归还时间")
    private Date endTime;

    @ApiModelProperty(value = "真实归还时间")
    private Date realEndTime;

    @ApiModelProperty(value = "借用时照片")
    private String startImg;

    @ApiModelProperty(value = "归还时照片")
    private String endImg;

    @ApiModelProperty(value = "用途")
    private String purpose;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否同意")
    private Integer disabled;
}
