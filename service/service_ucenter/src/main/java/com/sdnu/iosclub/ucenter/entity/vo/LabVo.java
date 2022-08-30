package com.sdnu.iosclub.ucenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zyl
 * @Date: 2021/07/17/21:01
 * @Description:
 */
@Data
public class LabVo {

    @ApiModelProperty(value = "实验室")
    private String id;

    @ApiModelProperty(value = "实验室父级部门id")
    private String parentId;

    @ApiModelProperty(value = "实验室父级部门id")
    private String departmentName;

    @ApiModelProperty(value = "实验室名字")
    private String name;

    @ApiModelProperty(value = "实验室头像")
    private String avatar;

    @ApiModelProperty(value = "负责实验室的老师")
    private String teacherName;

    @ApiModelProperty(value = "负责老师的电话")
    private String teacherPhone;

    @ApiModelProperty(value = "实验室位置")
    private String position;

    @ApiModelProperty(value = "实验室介绍")
    private String details;

    @ApiModelProperty(value = "实验室规章")
    private String rules;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
