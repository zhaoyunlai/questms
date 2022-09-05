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
@TableName("qvs_result")
@ApiModel(value="Result对象", description="")
public class QvsResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问卷单多选结果id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "绑定问卷的id")
    private String surveyId;

    @ApiModelProperty(value = "绑定问题的id")
    private String questionId;

    @ApiModelProperty(value = "选项id")
    private String optionId;

    @ApiModelProperty(value = "问卷提交人id")
    private String voter;

    @ApiModelProperty(value = "类型 0：单选 1：多选 2:下拉框")
    private Integer type;

    @ApiModelProperty(value = "记录创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "记录修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "是否被逻辑删除 0：否 1：是")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean isDeleted;


}
