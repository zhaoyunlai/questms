package com.sdnu.iosclub.device.entity.vo.uni;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: zyl
 * @Date: 2021/10/08/20:14
 * @Description: 显示在主页上的记录信息
 */
@Data
public class RecordListVo {

    @ApiModelProperty(value = "借用记录ID")
    private String id;

    @ApiModelProperty(value = "0: 预约中 1：借用中 2：已归还")
    private Integer state;

    @ApiModelProperty(value = "是否签到，0：未签到，1：签到，2：超时未签到")
    private Integer sign;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "设备名称")
    private String label;

    @ApiModelProperty(value = "设备种类")
    private String type;

    @ApiModelProperty(value = "类型名称")
    private String typeName;

    @ApiModelProperty(value = "设备所属实验室ID")
    private String labId;

    @ApiModelProperty(value = "设备所属实验室名称")
    private String labName;

    @ApiModelProperty(value = "借用时间")
    private Date startTime;

    @ApiModelProperty(value = "理论归还时间")
    private Date endTime;

    @ApiModelProperty(value = "真实归还时间")
    private Date realEndTime;

    @ApiModelProperty(value = "提交时间")
    private Date createTime;
}
