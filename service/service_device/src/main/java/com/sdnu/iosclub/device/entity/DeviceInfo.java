package com.sdnu.iosclub.device.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DeviceInfo对象", description="")
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "二维码字段")
    @TableField(fill = FieldFill.INSERT)
    private String qrCode;

    @ApiModelProperty(value = "设备标签")
    private String label;

    @ApiModelProperty(value = "设备种类")
    private String type;

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

    @ApiModelProperty(value = "实验室id")
    private String labId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
