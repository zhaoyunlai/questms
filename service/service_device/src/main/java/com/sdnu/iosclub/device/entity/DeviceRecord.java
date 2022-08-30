package com.sdnu.iosclub.device.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 
 * </p>
 *
 * @author CodeGenerator
 * @since 2021-06-04
 */
@Data
@TableName(value = "device_record")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DeviceRecord对象", description="")
public class DeviceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "设备所属实验室ID")
    private String labId;

    @ApiModelProperty(value = "借用者ID")
    private String userId;

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

    @ApiModelProperty(value = "是否通过预约，0：未通过，1：通过")
    private Integer disabled;

    @ApiModelProperty(value = "是否签到，0：未签到，1：签到，2：超时未签到")
    private Integer sign;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
