package com.sdnu.iosclub.device.entity.vo.uni;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zyl
 * @Date: 2021/10/10/21:17
 * @Description: 用于预约页面，选中实验室后，显示设备列表，并显示设备的状态
 */
@Data
public class DeviceStateList {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "设备标签")
    private String label;

    @ApiModelProperty(value = "设备种类id")
    private String type;

    @ApiModelProperty(value = "设备种类")
    private String typeName;

    @ApiModelProperty(value = "最大借用时间")
    private Integer maxUseTime;

    @ApiModelProperty(value = "0:可借 1:不可借")
    private Integer disabled;

    @ApiModelProperty(value = "设备状态，0：空闲，1：借用中，2：维修中")
    private Integer state;

    @ApiModelProperty(value = "当前使用者id")
    private String currentUserId;

    @ApiModelProperty(value = "批次")
    private Integer batch;


}
