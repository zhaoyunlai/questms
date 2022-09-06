package com.sdnu.iosclub.qvs.entity;

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
 * @author wcr
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("qvs_survey")
@ApiModel(value="Survey对象", description="")
public class QvsSurvey implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问卷id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "问卷标题")
    private String title;

    @ApiModelProperty(value = "问卷说明")
    private String remark;

    @ApiModelProperty(value = "问卷开始时间")
    private Date startTime;

    @ApiModelProperty(value = "问卷结束时间")
    private Date endTime;

    @ApiModelProperty(value = "问卷访问规则 0：公开 1：需要密码")
    private Integer rules;

    @ApiModelProperty(value = "问卷密码")
    private String password;

    @ApiModelProperty(value = "问卷url")
    private String url;

    @ApiModelProperty(value = "状态 0：已创建 1：执行中 2：结束")
    private Integer state;

    @ApiModelProperty(value = "问卷图片的url")
    private String background;

    @ApiModelProperty(value = "是否匿名 0：匿名 1：不匿名")
    private Integer anon;

    @ApiModelProperty(value = "问卷创建人")
    private String creator;

    @ApiModelProperty(value = "记录创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "记录修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "是否被逻辑删除 0：否 1：是")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;


}
