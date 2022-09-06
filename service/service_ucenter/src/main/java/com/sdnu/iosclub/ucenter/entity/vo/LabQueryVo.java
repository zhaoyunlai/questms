package com.sdnu.iosclub.ucenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zyl
 * @Date: 2021/07/17/20:56
 * @Description:
 */
@Data
public class LabQueryVo {

    @ApiModelProperty(value = "实验室父级部门id")
    private String parentId;

    @ApiModelProperty(value = "实验室名字")
    private String name;

    @ApiModelProperty(value = "负责实验室的老师")
    private String teacherName;

    @ApiModelProperty(value = "起始时间")
    private String begin;

    @ApiModelProperty(value = "结束时间")
    private String end;
}
