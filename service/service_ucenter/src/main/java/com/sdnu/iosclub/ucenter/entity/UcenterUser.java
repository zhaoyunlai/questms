package com.sdnu.iosclub.ucenter.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/09/04/16:08
 * @Description:
 * 视图对象
 */
@Data
public class UcenterUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "学号、工号")
    private String number;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "性别，0女，1男，2保密")
    private Integer gender;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否禁用，0：不禁用，1：禁用")
    private Integer disabled;

    @ApiModelProperty(value = "是否删除，0：未删除；1：删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
