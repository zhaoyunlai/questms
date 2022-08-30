package com.sdnu.iosclub.ucenter.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author Wang Chen
 * @Date 2021/6/3 15:10
 * @Version 1.0
 **/
@Data
public class UserVo {
    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "微信openid")
    private String wxOpenid;

    @ApiModelProperty(value = "学号/工号")
    private String number;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "QQ")
    private String qq;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "部门")
    private String departmentName;

    private String departmentId;

    @ApiModelProperty(value = "学院")
    private String academyName;

    private String academyId;

    @ApiModelProperty(value = "专业")
    private String majorName;

    private String majorId;

    @ApiModelProperty(value = "班级")
    private String clazzName;

    private String clazzId;

    @ApiModelProperty(value = "是否禁用")
    private Integer disabled;

    private Integer isTeacher;
}
