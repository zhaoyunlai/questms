package com.sdnu.iosclub.device.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/4 11:12
 * @Version 1.0
 **/
@Data
public class DeviceRecordFormVo {

    @ApiModelProperty(value = "借用记录id")
    private String id;

    @ApiModelProperty(value = "设备id")
    private String deviceId;

    @ApiModelProperty(value = "所属实验室ID")
    private String labId;

    @ApiModelProperty(value = "二维码")
    private String qrCode;

    @ApiModelProperty(value = "借用者ID")
    private String userId;

    @ApiModelProperty(value = "借用时间")
    private Date startTime;

    @ApiModelProperty(value = "理论归还时间")
    private Date endTime;

    @ApiModelProperty(value = "最大借用时间")
    private Integer maxUseTime;

    @ApiModelProperty(value = "借用时照片")
    private String startImg;

    @ApiModelProperty(value = "归还时照片")
    private String endImg;

    @ApiModelProperty(value = "用途")
    private String purpose;

    @ApiModelProperty(value = "描述")
    private String description;
}
