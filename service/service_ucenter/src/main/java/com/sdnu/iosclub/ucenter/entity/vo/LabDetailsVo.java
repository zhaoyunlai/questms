package com.sdnu.iosclub.ucenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/7/18 22:51
 * @Version 1.0
 **/
@Data
public class LabDetailsVo {

    @ApiModelProperty(value = "实验室")
    private String id;

    @ApiModelProperty(value = "实验室父级部门id")
    private String parentId;

    @ApiModelProperty(value = "实验室父级部门id")
    private String departmentName;

    @ApiModelProperty(value = "实验室名字")
    private String name;

    private String position;

    @ApiModelProperty(value = "实验室头像")
    private String avatar;

    @ApiModelProperty(value = "负责实验室的老师")
    private String teacherName;

    @ApiModelProperty(value = "负责老师的电话")
    private String teacherPhone;

    @ApiModelProperty(value = "实验室介绍")
    private String details;

    @ApiModelProperty(value = "实验室规章")
    private String rules;

    private List<LabTeacherVo> labTeacherList;
}
