package com.sdnu.iosclub.device.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceInfoVo {

    @ApiModelProperty(value = "设备ID")
    private String id;

    @ApiModelProperty(value = "二维码字段")
    private String qrCode;

    @ApiModelProperty(value = "设备状态，0：空闲，1：借用中，2：维修中")
    private Integer state;

    @ApiModelProperty(value = "当前使用者id")
    private String currentUserId;

    @ApiModelProperty(value = "设备标签")
    private String label;

    @ApiModelProperty(value = "设备种类")
    private String type;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "实验室ID")
    private String labId;

    @ApiModelProperty(value = "实验室名称")
    private String labName;

    @ApiModelProperty(value = "最大借用时间")
    private Integer maxUseTime;

    @ApiModelProperty(value = "0:可借 1:不可借")
    private Integer disabled;

    @ApiModelProperty(value = "批次")
    private Integer batch;
}
