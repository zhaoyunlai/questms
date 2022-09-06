package com.sdnu.iosclub.ucenter.entity;

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
 * @since 2021-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UcenterLaboratory对象", description="")
public class UcenterLaboratory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "实验室")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "实验室父级部门id")
    private String parentId;

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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
