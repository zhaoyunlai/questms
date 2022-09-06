package com.sdnu.iosclub.device.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/4 9:39
 * @Version 1.0
 **/
@Data
public class AddDeviceVo {

    @ApiModelProperty(value = "设备种类")
    private String type;

    @ApiModelProperty(value = "最大借用时间")
    private Integer maxUseTime;

    private String labId;

    private Integer batch;

    private Integer total;
}
