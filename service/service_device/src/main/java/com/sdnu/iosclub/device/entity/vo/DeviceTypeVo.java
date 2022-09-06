package com.sdnu.iosclub.device.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/4 9:53
 * @Version 1.0
 **/
@Data
public class DeviceTypeVo {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "类型名字")
    private String name;

    private Integer total;
}
