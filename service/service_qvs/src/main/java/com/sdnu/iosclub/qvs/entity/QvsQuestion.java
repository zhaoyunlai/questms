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
@TableName("qvs_question")
@ApiModel(value="Question对象", description="")
public class QvsQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问题id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "关联问卷id")
    private String surveyId;

    @ApiModelProperty(value = "问题描述")
    private String title;

    @ApiModelProperty(value = "题目类型 0：单选 1：多选 2：下拉框 3：文本 4:日期")
    private Integer  type;

    @ApiModelProperty(value = "是否必填 0：非必填 1：必填")
    private Boolean required;

    @ApiModelProperty(value = "排序号")
    private Integer orderby;

    @ApiModelProperty(value = "记录创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "记录修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "是否被逻辑删除 0：否1：是")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean isDeleted;


}
